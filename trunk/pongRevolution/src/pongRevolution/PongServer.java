package pongRevolution;

import java.util.TimerTask;

import network.TGameState;
import network.TNetworkServer;
import network.TPlayer;
import network.TSettings;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

public class PongServer implements network.TNetworkServer.Iface{
	public static final int CLOCK_INTERVAL = 15;
	
	private Game game;
	
	public PongServer() {
		game = new Game();
	}
	
	private void start(){
	      try {
	         TServerSocket serverTransport = new TServerSocket(12020);
	         TNetworkServer.Processor processor = new TNetworkServer.Processor(new PongServer());
	         Factory protFactory = new TBinaryProtocol.Factory(true, true);
	         TServer server = new TThreadPoolServer(processor, serverTransport, protFactory);
	         System.out.println("Starting server on port 12020 ...");
	         server.serve();
	      } catch (TTransportException e) {
	         e.printStackTrace();
//	      } catch (IOException e) {
//	         e.printStackTrace();
	      }
	   }
	public static void main(String args[]){
	      PongServer srv = new PongServer();
	      srv.start();
	   }
	
	class ClockThread extends TimerTask {
		
		public void run() {
			// TODO: stuff
//			game.movePaddles();
//			game.moveBalls();
			
		}
	}

	@Override
	public void jump(TPlayer requester) throws TException {
		game.jumpPaddle(requester);		
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
		return game.getState();
	}


	@Override
	public void usePowerUp(TPlayer requester) throws TException {
		game.usePowerup(requester);
	}


	@Override
	public TSettings getSettings(TPlayer preferred) throws TException {
		TPlayer player = game.registerPlayer(preferred);
		TSettings settings = new TSettings(GameSettings.BALL_RADIUS, GameSettings.ARENA_RADIUS, GameSettings.CLOCK_INTERVAL, player);
		
		return settings;
	}


	@Override
	public TSettings getSettings() throws TException {
		// TODO Auto-generated method stub
		return null;
	}
}
