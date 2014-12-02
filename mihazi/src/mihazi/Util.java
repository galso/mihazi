package mihazi;

//Utility class for static utility methods
public class Util {

	public static Direction getPrevDir(Direction dir){
		if(dir == Direction.UP){
			return Direction.LEFT;
		}
		if(dir == Direction.RIGHT){
			return Direction.UP;
		}
		if(dir == Direction.DOWN){
			return Direction.RIGHT;
		}
		if(dir == Direction.LEFT){
			return Direction.DOWN;
		}
		return dir;
	}
	
	public static Direction getNextDir(Direction dir){
		if(dir == Direction.UP){
			return Direction.RIGHT;
		}
		if(dir == Direction.RIGHT){
			return Direction.DOWN;
		}
		if(dir == Direction.DOWN){
			return Direction.LEFT;
		}
		if(dir == Direction.LEFT){
			return Direction.UP;
		}
		return dir;
	}
}
