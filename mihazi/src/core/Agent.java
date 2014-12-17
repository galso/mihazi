package core;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import structures.Action;
import structures.State;
import utils.Constants;

public class Agent{

	private int reward;
	private Action direction;
	private State pos;
	private boolean goalReached;
	
	public Agent(){
		reward = 0;
		pos = new State(Constants.startX, Constants.startY);
		goalReached = false;
	}
	
	public State getState(){
		return pos;
	}
	
	public int getReward(){
		return reward;
	}
	
	public Action getAction(){
		return direction;
	}
	
	public boolean finished(){
		return goalReached;
	}
	
	public void setState(State _pos){
		pos = _pos;
	}
	
	public void addReward(int _reward){
		if(_reward == Constants.goalReward){
			goalReached = true;
		}
		reward += _reward;
	}
	
	public void setDirection(Action _direction){
		direction = _direction;
	}
	
	public void resetAgent(){
		reward = 0;
		pos = new State(Constants.startX, Constants.startY);
		goalReached = false;
	}
	
	public void draw(Graphics g){
//		System.out.println("agent paint");
		g.setColor(Color.BLUE);
		g.fillOval(pos.getX()*Constants.gridSize+Constants.sceneXOffset, pos.getY()*Constants.gridSize+Constants.sceneYOffset, Constants.gridSize, Constants.gridSize);
	}
}
