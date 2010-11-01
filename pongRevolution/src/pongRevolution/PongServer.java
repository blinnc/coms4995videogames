package pongRevolution;

import java.util.TimerTask;

import network.GameState;
import network.Player;
import network.Settings;

import org.apache.thrift.TException;

public class PongServer implements network.PongServer.Iface{
	public static final int CLOCK_INTERVAL = 15;
	
	private Game game;
	
	public PongServer() {
		game = new Game();
	}


	@Override
	public Settings getSettings() throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void jump(Player requester) throws TException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveLeft(Player requester) throws TException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveRight(Player requester) throws TException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GameState poll(Player requester) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void usePowerUp(Player requester) throws TException {
		// TODO Auto-generated method stub
		
	}
	
	class ClockThread extends TimerTask {
		
		public void run() {
			
			game.movePaddles();
			game.moveBalls();
		}
	}
}
