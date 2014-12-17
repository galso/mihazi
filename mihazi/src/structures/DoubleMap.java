package structures;

import java.util.HashMap;
import java.util.Map;

import utils.Constants;

public class DoubleMap {
	
	private Map<String,Map<Action,Double>> doubleMap;
	
	public DoubleMap(){
		doubleMap = new HashMap<String,Map<Action,Double>>();
	}
	
	public double get(State s, Action a){
		return doubleMap.get(""+s.getX()+s.getY()).get(a);
	}
	
	public void set(State s, Action a, double d){
		Map<Action,Double> innerMap = doubleMap.get(""+s.getX()+s.getY());
		innerMap.put(a, d);
		doubleMap.put(""+s.getX()+s.getY(), innerMap);
	}
	
	public void init(){
		for(int i = 0; i < Constants.rows; i++){
			for(int j = 0; j < Constants.cols; j++){
				State s = new State(i,j);
				Map<Action, Double> inner = new HashMap<Action, Double>();
				inner.put(Action.UP, 0.0);
				inner.put(Action.RIGHT, 0.0);
				inner.put(Action.DOWN, 0.0);
				inner.put(Action.LEFT, 0.0);
				doubleMap.put(""+s.getX()+s.getY(), inner);
			}
		}
	}
	
}
