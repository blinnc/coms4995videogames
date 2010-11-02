package pongRevolution;

import java.util.TimerTask;

import network.TGameState;
import network.TPlayer;
import network.TSettings;

import org.apache.thrift.TException;

public class PongServer implements network.TNetworkServer.Iface{
	public static final int CLOCK_INTERVAL = 15;
	
	private Game game;
	
	public PongServer() {
		game = new Game();
	}
	
	class ClockThread extends TimerTask {
		
		public void run() {
			
			game.movePaddles();
			game.moveBalls();
		}
	}

	@Override
	public void jump(TPlayer requester) throws TException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void moveLeft(TPlayer requester) throws TException {
		game.movePaddle(requester, true);
	}


	@Override
	public void moveRight(TPlayer requester) throws TException {
		game.movePaddle(requester, false);		
	}


	@Override
	public TGameState poll(TPlayer requester) throws TException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void usePowerUp(TPlayer requester) throws TException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public TSettings getSettings() throws TException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public TSettings getSettings(TPlayer preferred) throws TException {
		// TODO Auto-generated method stub
		return null;
	}
}
