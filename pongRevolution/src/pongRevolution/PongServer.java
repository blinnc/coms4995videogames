package pongRevolution;

import java.util.TimerTask;

public class PongServer {
	
	private Game game;
	private int playerNum;
	
	public PongServer() {
		game = new Game();
		playerNum = 0;
	}
	
	public int registerUser() {
		if(playerNum >= 4) {
			return -1;
		}
		int num = playerNum;
		playerNum++;
		return num;
	}
	
	public void moveLeft(int player) {
		
	}
	
	public void moveRight(int player) {
		
	}
	
	public void jump(int player) {
		
	}
	
	public void usePowerup(int player) {
		
	}
	
	
	class ClockThread extends TimerTask {
		
		public void run() {
			
			
			game.moveBalls();
		}
	}
}
