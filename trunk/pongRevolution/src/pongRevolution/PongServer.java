package pongRevolution;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.thrift.TException;

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
		final CommandServer csrv = new CommandServer(game);
		final PollServer psrv = new PollServer(game);
		(new Thread() {
            public void run() {
                csrv.start();
            }
        }).start();
		(new Thread() {
            public void run() {
            	psrv.start();
            }
        }).start();
	}
	
	class ClockThread extends TimerTask {
		
		public void run() {
			// TODO: stuff
			game.updateGame();
		}
	}
}