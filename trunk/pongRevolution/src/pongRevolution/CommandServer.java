package pongRevolution;

import java.util.Timer;
import java.util.TimerTask;

import network.TCommandServer;
import network.TPlayer;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

public class CommandServer implements network.TCommandServer.Iface{
	private Game game;
	private Timer timer;
	Logger log = Logger.getLogger(CommandServer.class);
	
	public CommandServer(Game g) {
		game = g;
		timer = new Timer();
		BasicConfigurator.configure();
		timer.scheduleAtFixedRate(new ClockThread(), 0, GameSettings.CLOCK_INTERVAL);
	}
	
	private void start(){
	      try {
	         TServerSocket serverTransport = new TServerSocket(12020);
	         TCommandServer.Processor processor = new TCommandServer.Processor(new CommandServer(game));
	         Factory protFactory = new TBinaryProtocol.Factory(true, true);
	         TServer server = new TThreadPoolServer(processor, serverTransport, protFactory);
	         System.out.println("Starting server on port 12020 ...");
	         server.serve();
	      } catch (TTransportException e) {
	         e.printStackTrace();
	      }
	   }
	
	//TODO move main method to another class
//	public static void main(String args[]){
//	      CommandServer srv = new CommandServer();
//	      srv.start();
//	   }
	
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
	public void moveLeft(TPlayer requester) throws TException {
		game.movePaddle(requester, true);
	}


	@Override
	public void moveRight(TPlayer requester) throws TException {
		game.movePaddle(requester, false);		
	}


	@Override
	public void usePowerUp(TPlayer requester) throws TException {
		game.usePowerup(requester);
	}


	@Override
	public void pickTeam(TPlayer preferred, String username, boolean join)
			throws TException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendChat(String message, String username) throws TException {
		// TODO Auto-generated method stub
		
	}

}
