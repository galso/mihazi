package mihazi;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Agent{

	private int reward;
	private Direction direction;
	private Position pos;
	private boolean goalReached;
	
	public Agent(){
		reward = 0;
		pos = new Position(Constants.startX, Constants.startY);
		goalReached = false;
	}
	
	public Position getPos(){
		return pos;
	}
	
	public int getReward(){
		return reward;
	}
	
	public Direction getDirection(){
		return direction;
	}
	
	public boolean finished(){
		return goalReached;
	}
	
	public void setPos(Position _pos){
		pos = _pos;
	}
	
	public void addReward(int _reward){
		if(_reward == Constants.goalReward){
			goalReached = true;
		}
		reward += _reward;
	}
	
	public void setDirection(Direction _direction){
		direction = _direction;
	}
	
	public void resetAgent(){
		reward = 0;
		pos = new Position(Constants.startX, Constants.startY);
		goalReached = false;
	}
	
	public void draw(Graphics g){
		System.out.println("agent paint");
		g.setColor(Color.BLUE);
		g.fillOval(pos.getX()*Constants.gridSize+Constants.sceneXOffset, pos.getY()*Constants.gridSize+Constants.sceneYOffset, Constants.gridSize, Constants.gridSize);
	}
}
