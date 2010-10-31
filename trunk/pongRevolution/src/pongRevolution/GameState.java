package pongRevolution;

import java.util.List;

public class GameState {

	public List<Paddle> paddleList;
	public List<Ball> ballList;
	public double arenaRadius;
	boolean isWalled;
	boolean isRedLaserEnabled;
	boolean isBlueLaserEnabled;
	public int redScore;
	public int blueScore;
	public Powerup.Type powerup;
	
}
