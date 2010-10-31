package pongRevolution;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class Game {
	
	private List<ServerBall> ballList;
	private ServerPaddle[] paddleArray;
	
	
	public Game() {
		ballList = new ArrayList<ServerBall>();
		paddleArray = new ServerPaddle[4];
		
		
	}
	
	public void moveBalls() {
		
	}
	
	public void movePaddle(int paddle, Command.Type direction) {
		
	}
	
	public GameState getGameState() {
		
		return null;
	}
}
