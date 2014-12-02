package mihazi;

//encapsulate return values (position and reward)
public class PosAndRew {

	private Position pos;
	private int reward;
	
	public PosAndRew(Position _pos, int _reward){
		pos = _pos;
		reward = _reward;
	}
	
	public Position getPos(){
		return pos;
	}
	
	public int getReward(){
		return reward;
	}
}
