package mihazi;

public class Agent {

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
}
