package mihazi;

import java.awt.Graphics;

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
					//??negative reward if cant move??
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
	public Position getNewPos(Position pos, Direction dir){
		
		Position retPos = retPos = new Position(pos.getX(), pos.getY());;
		if(dir == Direction.UP){
			if(field[pos.getX()][pos.getY()-1].isAvailable()){
				retPos = new Position(pos.getX(), pos.getY()-1);
			}
		}
		if(dir == Direction.RIGHT){
			if(field[pos.getX()+1][pos.getY()].isAvailable()){
				retPos = new Position(pos.getX()+1, pos.getY());
			}
		}
		if(dir == Direction.DOWN){
			if(field[pos.getX()][pos.getY()+1].isAvailable()){
				retPos = new Position(pos.getX(), pos.getY()+1);
			}
		}
		if(dir == Direction.LEFT){
			if(field[pos.getX()-1][pos.getY()].isAvailable()){
				retPos = new Position(pos.getX()-1, pos.getY());
			}
		}
		
		return retPos;
	}
	
	//gives back the reward on the given position
	public int getReward(Position pos){
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
