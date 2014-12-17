package core;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
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
	private int overallReward = 0;
	private Map<Integer, Integer> chartData;
	private Map<Integer, Integer> chartDataOverall;
	
	private DoubleMap Q;
	private DoubleMap N;
	
	public Engine(Field _field){
		field = _field;
		agent = new Agent();
		
		Q = new DoubleMap();
		N = new DoubleMap();
		Q.init();
		N.init();
		
		//TODO remove, just for test
		agent.setDirection(Action.DOWN);
		//
	}

	
	//make a single step for the agent
	private StateAndRew step(State pos, Action dir){
		
		//TODO remove, just for test
		System.out.println("startPos: " + pos.getX() + " , " + pos.getY());
		//
		
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
		
		//TODO remove, just for test
		System.out.println("endPos: " + newPos.getX() + " , " + newPos.getY());
		//
		
		agent.setPos(newPos);
		
		return new StateAndRew(newPos, field.getReward(newPos));
		
	}
	
	//start measureing
	public void startEpisodes(double stepTime){
		
		//reset/init
		agent.resetAgent();
		episodeCount = 0;
		steps = 0;
		overallReward = 0;
		chartData = new HashMap<Integer, Integer>(); 
		chartDataOverall = new HashMap<Integer, Integer>(); 
		
		
		//visualize simulation
		if(stepTime != 0){	
			timer = new Timer();
			timer.scheduleAtFixedRate(new TimerTask(){
				
				@Override
				public void run() {
					StateAndRew saw = step(agent.getState(), agent.getAction());
					scene.repaint();
					steps++;
					frame.setStepCount(steps);
					agent.addReward(saw.getReward());
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
						drawChart(chartData);
						drawChart(chartDataOverall);
					}
					
				}
				
			}, 0, (long)(stepTime*1000));
		}
		//fast simulation without visualization
		else{
			while(episodeCount < Constants.episodes){
				
				agent.resetAgent();
				//...
				//in each episodes:

				while(!agent.finished() && steps < Constants.maxSteps){
					
					StateAndRew saw = step(agent.getState(), agent.getAction());	
					agent.addReward(saw.getReward());
					
					//..
					steps++;
				}
				
				overallReward += agent.getReward();
				chartDataOverall.put(episodeCount, overallReward);
				chartData.put(episodeCount, agent.getReward());
				
				steps = 0;
				agent.resetAgent();
				episodeCount++;
			}
			
			drawChart(chartData);
			drawChart(chartDataOverall);
		}

	}
	
	private void drawChart(Map<Integer,Integer> map){
		final LineChart demo = new LineChart("Learning curve", "Learning curve", map);
		demo.pack();
        demo.setVisible(true);
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
