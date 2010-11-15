package pongRevolution;

import network.TPollServer;
import network.TGameState;
import network.TLobbyState;
import network.TPlayer;
import network.TSettings;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

public class PollServer implements network.TPollServer.Iface {
	private Game game;
	Logger log = Logger.getLogger(CommandServer.class);
	
	public PollServer(Game g) {
		game = g;
		BasicConfigurator.configure();
	}
	
	public void start(){
	      try {
	         TServerSocket serverTransport = new TServerSocket(GameSettings.POLL_SERVER_PORT);
	         TPollServer.Processor processor = new TPollServer.Processor(new PollServer(game));
	         Factory protFactory = new TBinaryProtocol.Factory(true, true);
	         TServer server = new TThreadPoolServer(processor, serverTransport, protFactory);
	         System.out.println("Starting poll server on port " + GameSettings.POLL_SERVER_PORT + "...");
	         server.serve();
	      } catch (TTransportException e) {
	         e.printStackTrace();
	      }
	   }
	
	@Override
	public TGameState poll(TPlayer requester) throws TException {
		System.out.println("POLL CALLED");
		return game.getState();
	}

	@Override
	public TLobbyState pollLobby(String username) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TSettings getSettings(String username) throws TException {
		System.out.println("GET SETTINGS CALLED");
		//TODO username?
        TSettings settings = new TSettings(GameSettings.BALL_RADIUS, GameSettings.ARENA_RADIUS, GameSettings.CLOCK_INTERVAL);
        return settings;
	}
}
