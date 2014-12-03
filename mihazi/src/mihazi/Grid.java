package mihazi;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

//represent a grid on the field
public class Grid extends JPanel{

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
	
	public void drawObject(Graphics g, int x, int y) {
		if(available && reward == Constants.stepReward){
			g.setColor(Color.BLACK);
			g.drawRect(x, y, Constants.gridSize, Constants.gridSize);
		}
		if(!available && reward == Constants.stepReward){
			g.setColor(Color.BLACK);
			g.fillRect(x, y, Constants.gridSize, Constants.gridSize);
		}
		if(available && reward == Constants.bombReward){
			g.setColor(Color.RED);
			g.fillRect(x, y, Constants.gridSize, Constants.gridSize);
		}
		if(available && reward == Constants.goalReward){
			g.setColor(Color.GREEN);
			g.fillRect(x, y, Constants.gridSize, Constants.gridSize);
		}
		
//		System.out.println("grid rajz");
	}
	
}
