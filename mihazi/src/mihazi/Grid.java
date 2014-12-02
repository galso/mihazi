package mihazi;

public class Grid {

	private boolean available;
	private int reward;
	
	
	public Grid(boolean _available, int _reward){
		available = _available;
		reward = _reward;
	}
	
	public boolean isAvailable(){
		return available;
	}
	
	public int getReward(){
		return reward;
	}
	
}
