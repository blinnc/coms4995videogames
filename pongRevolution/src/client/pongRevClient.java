package client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import network.Player;
import network.PongServer.Client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import pongRevolution.Command;

public class pongRevClient {
	
	//booleans that determine whether a key is pressed or not
	private static boolean a;
	private static boolean d;
	private static boolean s;
	private static boolean w;
	
	//ip address that the system connects to
	private static final String HOST_IP = "209.2.231.243";
	
	//client interface that we receive the server-side methods from
	static Client client;
	
	//command class that buffers commands that come in
	static Command cmd = new Command();
	
	//this will be set by the server at some point
	private static Player requester;
	
	public static void main(String[] args)
	{
		//-----LOGIC TO CONNECT TO THE SERVER
		TTransport transport;
	      try {
	         transport = new TSocket(HOST_IP, 7911);
	         TProtocol protocol = new TBinaryProtocol(transport);
	         client = new Client(protocol);
	         transport.open();
	         //transport.close();
	      } catch (TTransportException e) {
	         System.out.println("Problem when trying to connect to the server.");
	    	  e.printStackTrace();
	      }
		//-----END LOGIC TO CONNECT TO THE SERVER-----
		
	    /*Timer timer = new Timer();
		TimerTask commandTask = new pongRevClient();
		timer.schedule(commandTask, 0, 100);*/
		
		//-----BEGIN SET UP AND DRAW THE GUI-----
		final pongRevWindow gameWindow = new pongRevWindow();
		(new Thread() {
            public void run() {
                try {
                    pongRevClient.doCommand();
                }
                catch(InterruptedException e) {
                	System.out.println("Problem in the command thread.");
                }
                catch(TException e) {
                	System.out.println("Problem in the command thread.");
                }
            }
        }).start();
		//-----END SET UP AND DRAW THE GUI
		
		//-----BEGIN ADD LISTENERS TO SEND COMMANDS-----
		gameWindow.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(c == 's') {
					s = true;
					System.out.println("POWERUP!");
				} else if(c == 'w') {
					w = true;
					System.out.println("Jump");
				}
				
			}
			@Override
			public void keyReleased(KeyEvent e) {
				char c = e.getKeyChar();
				if(c == 'a') {
					a = false;
					System.out.println("Move Left: OFF");
				} else if(c == 'd') {
					d = false;
					System.out.println("Move Right: OFF");
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if(c == 'a') {
					a = true;
					System.out.println("Move Left: ON");
				} else if(c == 'd') {
					d = true;
					System.out.println("Move Right: ON");
				}
			}
		});
		//-----END ADD LISTENERS TO SEND COMMANDS-----
		
	}

	/**
	 * Helper method that sends the commands to the connected server. Sends commands
	 * every 15 milliseconds.
	 * @throws InterruptedException
	 * @throws TException 
	 */
	private static void doCommand() throws InterruptedException, TException {
		while (true) {
	        Thread.sleep(15);
	        if (a) {
	        	client.moveLeft(requester);
	        } else if (d) {
	        	client.moveRight(requester);
	        } else if (s) {
	        	client.usePowerUp(requester);
	        	s = false;
	        } else if (w) {
	        	client.jump(requester);
	        	w = false;
	        }
	    }
	}
}


