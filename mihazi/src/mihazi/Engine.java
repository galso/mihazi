package mihazi;

import java.util.Random;

//The engine of the simulation
//This runs the episodes
public class Engine {

	private Field field;
	private Agent agent;
	
	public Engine(Field _field){
		field = _field;
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
	public void startEpisodes(){
		int episodeCount = 0;
		while(episodeCount < Constants.episodes){
			
			//...
			//in each episodes:
			agent = new Agent();
			int steps = 0;
			
			//TODO remove, just for test
			agent.setDirection(Direction.DOWN);
			//
			
			
			while(!agent.finished() && steps < Constants.maxSteps){
				
				step(agent.getPos(), agent.getDirection());
				//..
				steps++;
			}
			
		
			episodeCount++;
		}
	}
}
