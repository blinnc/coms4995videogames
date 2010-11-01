package client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import pongRevolution.Command;
import pongRevolution.Command.Type;

public class pongRevClient extends TimerTask {
	
	//booleans that determine whether a key is pressed or not
	private static boolean a;
	private static boolean d;
	private static boolean s;
	private static boolean w;
	private static final int COMMAND_BUFFER_MAX = 5;
	private static final String HOST_IP = "209.2.231.243";
	
	//command class that buffers commands that come in
	static Command cmd = new Command();
	
	public static void main(String[] args)
	{
		//logic to connect to the server
		/*TTransport transport;
	      try {
	         transport = new TSocket(HOST_IP, 7911);
	         TProtocol protocol = new TBinaryProtocol(transport);
	         Client client = new Client(protocol);
	         transport.open();
	         boolean hi = true;
	         while (hi) {
	        	 long time = System.currentTimeMillis();
	        	 client.time();
	        	 long curr = System.currentTimeMillis();
	             System.out.println("Time from server:" + time + " | " + curr + " | " + (curr - time));
	         }
	         
	         transport.close();
//	      } catch (SocketException e) {
//	         e.printStackTrace();
	      } catch (TTransportException e) {
	         e.printStackTrace();
	      } catch (TException e) {
	         e.printStackTrace();
	      }*/
		//end logic to connect to the server
		
		Timer timer = new Timer();
		TimerTask commandTask = new pongRevClient();
		timer.schedule(commandTask, 0, 100);
		
		//set up and draw the pong game
		final pongRevWindow gameWindow = new pongRevWindow();
		(new Thread() {
            public void run() {
                try {
                    pongRevClient.doCommand(COMMAND_BUFFER_MAX);
                }
                catch(InterruptedException e) {}
            }
        }).start();
		//end set up and draw the pong game
		
		//add listeners to send commands
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
		//end add listeners to send commands
		
	}
	/**
	 * Helper method that adds a command to the given command class.
	 * @param cmd - the command class the the command is being added to
	 * @param code - the enum code that represents the command
	 */
	private static void addCommand(Command cmd, Command.Type code)
	{
		cmd.commandBuffer.add(code);
	}
	
	/**
	 * Helper method that checks to see which commands need to be added to the buffer. 
	 * To change the max buffer count, change COMMAND_BUFFER_MAX 
	 * @throws InterruptedException
	 */
	private static void doCommand(int max) throws InterruptedException {
		while (true) {
	        Thread.sleep(15);
	        if (a) {
	        	addCommand(cmd, Type.LEFT);
	        } else if (d) {
	        	addCommand(cmd, Type.RIGHT);
	        } else if (s) {
	        	addCommand(cmd, Type.POWERUP);
	        	s = false;
	        } else if (w) {
	        	addCommand(cmd, Type.JUMP);
	        	w = false;
	        }
	    }
	}
	@Override
	public void run() {
		//send the packets
		cmd.commandBuffer.clear();
	}
}


