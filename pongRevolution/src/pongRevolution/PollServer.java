package pongRevolution;

import network.TGameState;
import network.TLobbyState;
import network.TPlayer;
import network.TSettings;

import org.apache.thrift.TException;

public class PollServer implements network.TPollServer.Iface {
	private Game game;
	
	public PollServer(Game g) {
		game = g;
	}
	
	@Override
	public TGameState poll(TPlayer requester) throws TException {
		TGameState state = game.getState();
		return game.getState();
	}

	@Override
	public TLobbyState pollLobby(String username) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TSettings getSettings(String username) throws TException {
		//TODO username?
        TSettings settings = new TSettings(GameSettings.BALL_RADIUS, GameSettings.ARENA_RADIUS, GameSettings.CLOCK_INTERVAL);
        return settings;
	}
}
