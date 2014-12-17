package utils;

import structures.Action;

//Utility class for static utility methods
public class Util {

	public static Action getPrevDir(Action dir){
		if(dir == Action.UP){
			return Action.LEFT;
		}
		if(dir == Action.RIGHT){
			return Action.UP;
		}
		if(dir == Action.DOWN){
			return Action.RIGHT;
		}
		if(dir == Action.LEFT){
			return Action.DOWN;
		}
		return dir;
	}
	
	public static Action getNextDir(Action dir){
		if(dir == Action.UP){
			return Action.RIGHT;
		}
		if(dir == Action.RIGHT){
			return Action.DOWN;
		}
		if(dir == Action.DOWN){
			return Action.LEFT;
		}
		if(dir == Action.LEFT){
			return Action.UP;
		}
		return dir;
	}
}
