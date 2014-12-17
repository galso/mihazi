package structures;


//encapsulate return values (position and reward)
public class StateAndRew {

	private State pos;
	private double reward;
	
	public StateAndRew(State _pos, double _reward){
		pos = _pos;
		reward = _reward;
	}
	
	public State getState(){
		return pos;
	}
	
	public double getReward(){
		return reward;
	}
}
