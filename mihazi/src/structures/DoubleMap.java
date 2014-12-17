package structures;

import java.util.HashMap;
import java.util.Map;

import utils.Constants;

public class DoubleMap {
	
	private Map<State,Map<Action,Integer>> doubleMap;
	
	public DoubleMap(){
		doubleMap = new HashMap<State,Map<Action,Integer>>();
	}
	
	public Integer get(State s, Action a){
		return doubleMap.get(s).get(a);
	}
	
	public void set(State s, Action a, int i){
		Map<Action,Integer> innerMap = doubleMap.get(s);
		innerMap.put(a, i);
		doubleMap.put(s, innerMap);
	}
	
	public void init(){
		for(int i = 0; i < Constants.rows; i++){
			for(int j = 0; j < Constants.cols; j++){
				State s = new State(i,j);
				Map<Action, Integer> inner = new HashMap<Action, Integer>();
				inner.put(Action.UP, 0);
				inner.put(Action.RIGHT, 0);
				inner.put(Action.DOWN, 0);
				inner.put(Action.LEFT, 0);
				doubleMap.put(s, inner);
			}
		}
	}
}
