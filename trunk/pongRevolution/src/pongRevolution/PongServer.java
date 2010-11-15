package pongRevolution;

import java.util.Timer;
import java.util.TimerTask;

//Class that starts the server

public class PongServer {
	private Game game;
	private Timer timer;
	
	public static void main(String[] args) {
		new PongServer();
	}
	
	private PongServer() {
		game = new Game();
		timer = new Timer();
		timer.scheduleAtFixedRate(new ClockThread(), 0, GameSettings.CLOCK_INTERVAL);
		CommandServer csrv = new CommandServer(game);
		PollServer psrv = new PollServer(game);
	}
	
	class ClockThread extends TimerTask {
		
		public void run() {
			// TODO: stuff
			game.updateGame();
		}
	}
}