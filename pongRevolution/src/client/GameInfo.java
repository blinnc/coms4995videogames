package client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import network.TCommandServer;

import network.*;

public class GameInfo {

	final int COMMAND_PORT = 12020;
	final int POLL_PORT = 12022;
	
	public TCommandServer.Client commandClient;
	public TTransport commandTransport;
	
	public TPollServer.Client pollClient;
	public TTransport pollTransport;
	
	public String ip;
	public TPlayer player;
	public TSettings settings;
	public TGameState state;
	
	public GameInfo(String ip, String userName) {
		this.ip = ip;
		this.start();
		try {
			//settings = pollClient.getSettings(userName);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
	}
	
	private void start(){
	      try {
	         pollTransport = new TSocket(ip, POLL_PORT);
	         TProtocol pollProtocol = new TBinaryProtocol(pollTransport);
	         pollClient = new TPollServer.Client(pollProtocol);
	         pollTransport.open();
	         
	         commandTransport = new TSocket(ip, COMMAND_PORT);
	         TProtocol commandProtocol = new TBinaryProtocol(pollTransport);
	         commandClient = new TCommandServer.Client(commandProtocol);
	         commandTransport.open();

	      } catch (TTransportException e) {
	         e.printStackTrace();
	      }
	   }
	
	public void close() {
		pollTransport.close();
		commandTransport.close();
	}
}
