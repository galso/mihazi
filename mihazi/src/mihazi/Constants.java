package mihazi;

//Constants for the simulation
public class Constants {

	//number of episodes
	public static int episodes = 2;
	
	//max steps per episode
	public static int maxSteps = 10;
	
	//agent start position
	public static int startX = 1;
	public static int startY = 1;
	
	//rewards
	public static int stepReward = -1;
	public static int goalReward = 200;
	public static int bombReward = -100;
	
	//probabilities for the steps
	public static int mainProbability = 80; //%
	public static int sideProbability = 10; //%
	
	//field of the simulation
	public static int rows = 12;
	public static int cols = 12;
	public static int[] field = 
	{
		1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
		1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
		1, 0, 0, 0, 0, 2, 0, 0, 1, 0, 0, 1,
		1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
		1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1,
		1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1,
		1, 0, 0, 0, 0, 2, 1, 0, 0, 0, 0, 1,
		1, 0, 0, 0, 0, 0, 1, 0, 0, 2, 0, 1,
		1, 0, 0, 0, 0, 0, 2, 0, 1, 1, 0, 1,
		1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1,
		1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1,
		1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
	};
	
	public static int gridSize = 30;
	public static int sceneXOffset = 50;
	public static int sceneYOffset = 75;
}
