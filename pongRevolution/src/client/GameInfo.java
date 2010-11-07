package client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import network.TNetworkServer.Client;

import network.*;

public class GameInfo {

	final int PORT = 12020;
	
	public Client client;
	public TTransport transport;
	public String ip;
	public TPlayer player;
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
	         e.printStackTrace();
	      } catch (TException e) {
	         e.printStackTrace();
	      }
	   }
	
	public void close() {
		transport.close();
	}
}
