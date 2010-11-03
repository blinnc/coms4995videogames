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
	public TPlayer me;
	public TSettings settings;
	public TGameState state;
	
	public GameInfo(String ip, String team) {
		this.ip = ip;
		this.start();
		try {
			if (team.equals("red")) {
				settings = client.getSettings(TPlayer.RED_ONE);
			} else if (team.equals("blue")) {
				settings = client.getSettings(TPlayer.BLUE_ONE);
			} else {
				settings = client.getSettings(TPlayer.NONE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		me = settings.getColor();
		
		(new Thread() {
            public void run() {
                try {
                	Thread.sleep(15);
                	state = client.poll(me);
                	System.out.println(state.paddles.get(0).angle);
                } catch (InterruptedException e) {
                	e.printStackTrace();
                } catch (TException e) {
					e.printStackTrace();
				}
            }
		}).start();
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
