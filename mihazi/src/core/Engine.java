package core;

import java.awt.Graphics;
import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import structures.Action;
import structures.DoubleMap;
import structures.StateAndRew;
import structures.State;
import utils.Constants;
import utils.Util;

//The engine of the simulation
//This runs the episodes
public class Engine {

	private Field field;
	private Agent agent;
	private Scene scene;
	private Frame frame;
	private Timer timer;
	
	private int episodeCount = 0;
	private int steps = 0;
	private int episodeReward = 0;
	private double overallReward = 0;
	private Map<Integer, Double> chartData;
	private Map<Integer, Double> chartDataOverall;
	
	private DoubleMap Q;
	
	public Engine(Field _field){
		field = _field;
		agent = new Agent();
		
		Q = new DoubleMap();
	}

	
	//make a single step for the agent
	private StateAndRew step(State pos, Action dir){
		
		State newPos = null;
		
		Random dirGen = new Random();
		int intVal = dirGen.nextInt(100);
		
		//value between 0 and 79
		if(intVal < Constants.mainProbability){	
			newPos = field.getNewPos(pos, dir);
		}
		//value between 80 and 89
		else if(intVal < Constants.mainProbability+Constants.sideProbability){
			newPos = field.getNewPos(pos, Util.getPrevDir(dir));
		}
		//value between 90 and 99
		else if(intVal < Constants.mainProbability+2*Constants.sideProbability){
			newPos = field.getNewPos(pos, Util.getNextDir(dir));
		}
		
		return new StateAndRew(newPos, field.getReward(newPos));
		
	}
	
	//start measureing
	public void startEpisodes(double stepTime){
		
		//reset/init
		agent.resetAgent();
		episodeCount = 0;
		steps = 0;
		overallReward = 0;
		Q.init();
		chartData = new HashMap<Integer, Double>(); 
		chartDataOverall = new HashMap<Integer, Double>(); 
		
		
		//visualize simulation
		if(stepTime != 0){	
			timer = new Timer();
			timer.scheduleAtFixedRate(new TimerTask(){
				
				@Override
				public void run() {
					/*
					 * Simply select the highest value
					 * from Q
					 */
					//old values
					Action a = chooseAction(agent.getState());
					//take a step
					StateAndRew stateAndRew = step(agent.getState(), a);
					//update Q
					double newVal = Q.get(agent.getState(), a) + Constants.alpha *
							(stateAndRew.getReward() + Constants.gamma * Q.get(stateAndRew.getState(), chooseAction(stateAndRew.getState()))
									- Q.get(agent.getState(), a));
					Q.set(agent.getState(), a, newVal);
					agent.setState(stateAndRew.getState());
					
					
					scene.repaint();
					steps++;
					frame.setStepCount(steps);
					agent.addReward(stateAndRew.getReward());
					frame.setEpisodeReward(agent.getReward());
					
					//if episode is over
					if(agent.finished() || steps >= Constants.maxSteps){
						overallReward += agent.getReward();
						chartDataOverall.put(episodeCount, overallReward);
						chartData.put(episodeCount, agent.getReward());
						episodeCount++;
						frame.setEpisodeCount(episodeCount);
						steps = 0;
						frame.setStepCount(steps);
						frame.setEpisodeReward(agent.getReward());
						agent.resetAgent();
						
					}
					//if simulation is over
					if(episodeCount >= Constants.episodes){
						timer.cancel();
						timer = null;
						drawChart("Learning curve", "Reward/episodes", chartData);
						drawChart("Overall learning curve", "All reward/episodes", chartDataOverall);
					}
					
				}
				
			}, 0, (long)(stepTime*1000));
		}
		//fast simulation without visualization
		else{
			while(episodeCount < Constants.episodes){
				
				agent.resetAgent();
				//in each episodes:

				while(!agent.finished() && steps < Constants.maxSteps){
					/*
					 * Simply select the highest value
					 * from Q
					 */
					//old values
					Action a = chooseAction(agent.getState());
					//take a step
					StateAndRew stateAndRew = step(agent.getState(), a);
					//update Q
					double newVal = Q.get(agent.getState(), a) + Constants.alpha *
							(stateAndRew.getReward() + Constants.gamma * Q.get(stateAndRew.getState(), chooseAction(stateAndRew.getState()))
									- Q.get(agent.getState(), a));
					Q.set(agent.getState(), a, newVal);
					agent.setState(stateAndRew.getState());
					agent.addReward(stateAndRew.getReward());
					
					steps++;
				}
				
				overallReward += agent.getReward();
				chartDataOverall.put(episodeCount, overallReward);
				chartData.put(episodeCount, agent.getReward());
				
				steps = 0;
				agent.resetAgent();
				episodeCount++;
			}
			
			drawChart("Learning curve", "Reward/episodes", chartData);
			drawChart("Overall learning curve", "All reward/episodes", chartDataOverall);
		}

	}
	
	private void drawChart(String charTitle, String lineDesc, Map<Integer,Double> map){
		final LineChart demo = new LineChart("Learning curve", charTitle, lineDesc, map);
		demo.pack();
        demo.setVisible(true);
	}
	
	private Action chooseAction(State s){
		//simple choose the biggest value
		//or random if equal
		double highest = -Double.MAX_VALUE;
		Map<Action,Double> equals = new HashMap<Action,Double>();
		
		//get the highest value
		//and store actions/values in a structure
		for(Action a : Action.values()){
			if(Q.get(s, a) > highest){
				highest = Q.get(s, a);
			}
			equals.put(a, Q.get(s, a));
		}
		//remove smaller than the highest value elements 
		//from the structure
		Set<Action> set = new HashSet();
		for(Action a : equals.keySet()){
			if(equals.get(a) < highest){
				set.add(a);
			}
		}
		equals.keySet().removeAll(set);
		//if multiple elements are left
		//choose a random one
		if(equals.size() > 1){
			Random dirGen = new Random();
			int randIdx = dirGen.nextInt(100)%equals.size();
			int i = 0;
			for(Action a : equals.keySet()){
				if(i == randIdx){
					return a;
				}
				i++;
			}
		}
		//else the only one is the highest
		else{
			for(Action a : equals.keySet()){
				return a;
			}
		}
		return null;
		
	}
	

	public Agent getAgent(){
		return agent;
	}
	
	public int getEpisodeCOunt(){
		return episodeCount;
	}
	
	public int getSteps(){
		return steps;
	}
	
	public Field getField(){
		return field;
	}
	
	public void setScene(Scene _scene){
		scene = _scene;
	}
	
	public void setFrame(Frame _frame){
		frame = _frame;
	}
}
