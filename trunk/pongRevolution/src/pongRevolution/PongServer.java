package pongRevolution;

import java.util.TimerTask;

public class PongServer {
	
	private Game game;
	
	public PongServer() {
		
	}
	
	public void movePaddle(int player, Command.Type direction) {
		
	}
	
	
	
	class ClockThread extends TimerTask {
		
		public void run() {
			
			
			game.moveBalls();
		}
	}
}
