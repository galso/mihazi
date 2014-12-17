package core;

import java.awt.Graphics;

import structures.Action;
import structures.State;
import utils.Constants;

//represent the simulation field
public class Field {

	private Grid[][] field;
	
	public Field(){
		field = new Grid[Constants.rows][Constants.cols];
		init();
	}
	
	//reads the visualized field from the Constants.field attribute
	private void init(){
		
		for(int i = 0; i < Constants.rows; i++){
			for(int j = 0; j < Constants.cols; j++){
				switch (Constants.field[i*Constants.rows + j]) {
				case 0:
					//available grid
					field[j][i] = new Grid(true, Constants.stepReward);
					break;
				case 1:
					//wall
					//negative reward if cant move
					field[j][i] = new Grid(false, Constants.stepReward);
					break;
				case 2:
					//bomb
					field[j][i] = new Grid(true, Constants.bombReward);
					break;
				case 3:
					//goal
					field[j][i] = new Grid(true, Constants.goalReward);
					break;
				}
				
			}
			
		}
	}
	
	//gives back the next position from the given position and direction
	public State getNewPos(State pos, Action dir){
		
		State retPos = retPos = new State(pos.getX(), pos.getY());;
		if(dir == Action.UP){
			if(field[pos.getX()][pos.getY()-1].isAvailable()){
				retPos = new State(pos.getX(), pos.getY()-1);
			}
		}
		if(dir == Action.RIGHT){
			if(field[pos.getX()+1][pos.getY()].isAvailable()){
				retPos = new State(pos.getX()+1, pos.getY());
			}
		}
		if(dir == Action.DOWN){
			if(field[pos.getX()][pos.getY()+1].isAvailable()){
				retPos = new State(pos.getX(), pos.getY()+1);
			}
		}
		if(dir == Action.LEFT){
			if(field[pos.getX()-1][pos.getY()].isAvailable()){
				retPos = new State(pos.getX()-1, pos.getY());
			}
		}
		
		return retPos;
	}
	
	//gives back the reward on the given position
	public double getReward(State pos){
		return field[pos.getX()][pos.getY()].getReward();
	}
	
	public void draw(Graphics g){
		for(int i = 0; i < Constants.rows; i++){
			for(int j = 0; j < Constants.cols; j++){
				field[i][j].drawObject(g,i*Constants.gridSize+Constants.sceneXOffset,j*Constants.gridSize+Constants.sceneYOffset);
			}
		}
	}
}
