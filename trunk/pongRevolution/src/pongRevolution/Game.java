package pongRevolution;

import java.util.ArrayList;
import java.util.List;

import network.TPlayer;

public class Game {	
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
		ballSpawnTime = GameSettings.GAME_START_DELAY;
	}
	
	public void movePaddle(TPlayer requester, boolean clockwise) {
		paddleArray[requester.getValue()].move(clockwise);
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
