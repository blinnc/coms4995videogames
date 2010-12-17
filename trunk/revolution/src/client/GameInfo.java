package client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import network.TNetworkServer.Client;
import network.TPlayer;

import network.*;

public class GameInfo {

	final int PORT = 12020;
	
	public Client client;
	public TTransport transport;
	public String ip;
	public TPlayer player;
	public TPlayer ally;
	public TPlayer enemy1;
	public TPlayer enemy2;
	public TSettings settings;
	public TGameState state;
	
	public GameInfo(String ip, String team) {
		this.ip = ip;
		System.out.println(team);
		this.start();
		try {
			if (team.equals("RED TEAM")) {
				settings = client.getSettings(TPlayer.RED_ONE);
			} else if (team.equals("BLUE TEAM")) {
				settings = client.getSettings(TPlayer.BLUE_ONE);
			} else {
				settings = client.getSettings(TPlayer.NONE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		player = settings.getColor();
		if (player == TPlayer.BLUE_ONE) {
			ally = TPlayer.BLUE_TWO;
			enemy1 = TPlayer.RED_ONE;
			enemy2 = TPlayer.RED_TWO;
		} else if (player == TPlayer.BLUE_TWO) {
			ally = TPlayer.BLUE_ONE;
			enemy1 = TPlayer.RED_ONE;
			enemy2 = TPlayer.RED_TWO;
		} else if (player == TPlayer.RED_ONE) {
			ally = TPlayer.RED_TWO;
			enemy1 = TPlayer.BLUE_ONE;
			enemy2 = TPlayer.BLUE_TWO;
		} else if (player == TPlayer.RED_TWO) {
			ally = TPlayer.RED_ONE;
			enemy1 = TPlayer.BLUE_ONE;
			enemy2 = TPlayer.BLUE_TWO;
		}
		System.out.println(player);
		
	}
	
	private void start(){
	      try {
	         transport = new TSocket(ip, PORT);
	         TProtocol protocol = new TBinaryProtocol(transport);
	         client = new Client(protocol);
	         transport.open();
	         
	         //transport.close();
	      } catch (TTransportException e) {
	      }
	   }
	
	public void close() {
		transport.close();
	}
}
