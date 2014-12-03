package mihazi;

import java.awt.Graphics;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;

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
	
	public Engine(Field _field){
		field = _field;
		agent = new Agent();
		
		//TODO remove, just for test
		agent.setDirection(Direction.DOWN);
	}

	
	//make a single step for the agent
	private PosAndRew step(Position pos, Direction dir){
		
		//TODO remove, just for test
		System.out.println("startPos: " + pos.getX() + " , " + pos.getY());
		//
		
		Position newPos = null;
		
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
		
		return new PosAndRew(newPos, field.getReward(newPos));
		
	}
	
	//start measureing
	public void startEpisodes(double stepTime){
		agent.resetAgent();
		episodeCount = 0;
		steps = 0;
		episodeReward = 0;
		
		//visualize simulation
		if(stepTime != 0){	
			timer = new Timer();
			timer.scheduleAtFixedRate(new TimerTask(){
				
				@Override
				public void run() {
					PosAndRew paw = step(agent.getPos(), agent.getDirection());
					scene.repaint();
					steps++;
					frame.setStepCount(steps);
					agent.addReward(paw.getReward());
					frame.setEpisodeReward(agent.getReward());
					
					if(agent.finished() || steps >= Constants.maxSteps){
						episodeCount++;
						frame.setEpisodeCount(episodeCount);
						steps = 0;
						frame.setStepCount(steps);
						episodeReward = 0;
						frame.setEpisodeReward(agent.getReward());
						agent.resetAgent();
					}
					if(episodeCount >= Constants.episodes){
						timer.cancel();
						timer = null;
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
					
					PosAndRew paw = step(agent.getPos(), agent.getDirection());	
					agent.addReward(paw.getReward());
					
					//..
					steps++;
				}
			
				episodeCount++;
			}
		}
		
		
		
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
