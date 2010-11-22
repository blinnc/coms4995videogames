package pongRevolution;

import java.util.Timer;
import java.util.TimerTask;

import network.TDirection;
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

import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

public class PongServer implements network.TNetworkServer.Iface{
	private Game game;
	private Timer timer;
	Logger log = Logger.getLogger(PongServer.class);
	
	public PongServer() {
		game = new Game();
		timer = new Timer();
		BasicConfigurator.configure();
		timer.scheduleAtFixedRate(new ClockThread(), 0, GameSettings.CLOCK_INTERVAL);
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
			game.updateGame();
		}
	}

	@Override
	public void jump(TPlayer requester) throws TException {
		game.jumpPaddle(requester);		
	}


	@Override
	public void move(TPlayer requester, TDirection dir) throws TException {
		game.movePaddle(requester, dir);
	}


	@Override
	public TGameState poll(TPlayer requester) throws TException {
		TGameState state = game.getState();
//		System.out.println(state.getBalls().size());
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
//		System.out.println("getSettings()");
		return settings;
	}

}
