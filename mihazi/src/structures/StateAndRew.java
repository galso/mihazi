package structures;


//encapsulate return values (position and reward)
public class StateAndRew {

	private State pos;
	private int reward;
	
	public StateAndRew(State _pos, int _reward){
		pos = _pos;
		reward = _reward;
	}
	
	public State getState(){
		return pos;
	}
	
	public int getReward(){
		return reward;
	}
}
