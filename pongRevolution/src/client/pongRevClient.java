package client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import pongRevolution.*;
import pongRevolution.Command.Type;

public class pongRevClient {

	//booleans that determine whether a key is pressed or not
	private static boolean a;
	private static boolean d;
	private static boolean s;
	private static boolean w;
	private static final int COMMAND_BUFFER_MAX = 5;
	
	//command class that buffers commands that come in
	static Command cmd = new Command();
	
	public static void main(String[] args)
	{
		//logic to connect to the server
		//end logic to connect to the server
		
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
	    int i = 0;
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
	        i++;
	        if(i == max) {
	        	i = 0;
	        	//logic to send the buffer to the server
	        	cmd.commandBuffer.clear();
	        }
	    }
	}
}

