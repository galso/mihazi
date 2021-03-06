package core;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Scene extends JPanel{

	private Field field;
	private Agent agent;
	
	public Scene(Field _field, Agent _agent){
		super();
		field = _field;
		agent = _agent;
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		field.draw(g);
		agent.draw(g);
//		System.out.println("rajz");
	}
	
	public void setAgent(Agent _agent){
		agent = _agent;
	}
	
}
