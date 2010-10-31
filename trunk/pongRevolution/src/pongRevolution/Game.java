package pongRevolution;

import java.util.ArrayList;
import java.util.List;

public class Game {
	
	private List<ServerBall> ballList;
	private ServerPaddle[] paddleArray;
	
	
	public Game() {
		ballList = new ArrayList<ServerBall>();
		paddleArray = new ServerPaddle[4];
		
		
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
