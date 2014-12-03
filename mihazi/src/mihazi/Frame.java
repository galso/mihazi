package mihazi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Frame extends JFrame{
	
	private Engine engine;

	Scene mainPanel;
	JPanel upperPanel = new JPanel();
	JPanel upperUpperPanel = new JPanel();
	JPanel upperLowerPanel = new JPanel();
	JPanel lowerPanel = new JPanel();
	JLabel stepTimeLabel = new JLabel("Time between steps (in sec): ");
	JLabel episodeCountLabel = new JLabel("Episode: 0");
	JLabel stepCountLabel = new JLabel("Steps: 0");
	JLabel episodeRewardLabel = new JLabel("Episode reward: 0");
	JTextField stepTime = new JTextField("0");
	JButton simulate = new JButton("Start simulation");	
	
	public Frame(Engine _engine){
		engine = _engine;
		init();
		initListeners();
		
		engine.setScene(mainPanel);
	}
	
	private void init(){
		mainPanel = new Scene(engine.getField(), engine.getAgent());
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(500, 500));
		this.setTitle("Simulation");
		this.setLayout(new BorderLayout());
		this.add(mainPanel);
		
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(upperPanel, BorderLayout.NORTH);
		mainPanel.add(lowerPanel, BorderLayout.CENTER);
		
		upperPanel.setLayout(new BorderLayout());
		upperPanel.add(upperUpperPanel, BorderLayout.NORTH);
		upperPanel.add(upperLowerPanel, BorderLayout.CENTER);
		
		upperUpperPanel.add(stepTimeLabel);
		upperUpperPanel.add(stepTime);
		stepTime.setPreferredSize( new Dimension( 30, 24 ) );
		upperUpperPanel.add(simulate);
		
		upperLowerPanel.setLayout(new FlowLayout());
		upperLowerPanel.add(episodeCountLabel);
		upperLowerPanel.add(stepCountLabel);
		upperLowerPanel.add(episodeRewardLabel);
		
		lowerPanel.setBackground(Color.WHITE);
		
		this.pack();
		
		setVisible(true);
	}
	
	private void initListeners(){
		
		simulate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				stepTime.setBackground(Color.WHITE);
				try{
					double time = Double.parseDouble(stepTime.getText());
					engine.startEpisodes(time);
				}
				catch(Exception e){
					stepTime.setBackground(Color.RED);
				}
			}
		});
	}
	
	public void setEpisodeCount(int count){
		episodeCountLabel.setText("Episode: "+count);
	}
	
	public void setStepCount(int count){
		stepCountLabel.setText("Steps: "+count);
	}
	
	public void setEpisodeReward(int count){
		episodeRewardLabel.setText("Episode reward: "+count);
	}

}
