package pongRevolution;

import java.util.ArrayList;
import java.util.List;

public class Game {
	private static final int GAME_START_DELAY = 3000;
	
	private List<ServerBall> ballList;
	private ServerPaddle[] paddleArray;
	private int redScore, blueScore;
	private int ballSpawnTime;
	
	public Game() {
		ballList = new ArrayList<ServerBall>();
		paddleArray = new ServerPaddle[5];
		
		
	}
	
	public void resetGame() {
		ballList.clear();
		redScore = 0;
		blueScore = 0;
		ballSpawnTime = GAME_START_DELAY;
	}
	
	public void movePaddle(Player requester, boolean moveLeft) {
		
	}
	
	/**
	 * Updates the balls to their next position
	 */
	public void moveBalls() {
		
	}
	
	/**
	 * Updates the paddles to their next position (up/down-wise)
	 */
	public void movePaddles() {
		
	}
	
	
	
	/**
	 * Gets the relevant information from the game that the clients need to know
	 * @return a GameState object that represents the game from the clients' point of view
	 */
	public GameState getGameState() {
		
		return null;
	}
}
