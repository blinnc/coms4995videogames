package client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import pongRevolution.*;
import pongRevolution.Command.Type;

public class pongRevClient {

	public static void main(String[] args)
	{
		final Command cmd = new Command();
		//logic to connect to the server
		//end logic to connect to the server
		//set up and draw the pong game
		pongRevWindow gameWindow = new pongRevWindow();
		//end set up and draw the pong game
		//add listeners to send commands
		gameWindow.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(c == 'a') {
					System.out.println("Move Left");
					addCommand(cmd, Type.LEFT);
				} else if(c == 'd') {
					addCommand(cmd, Type.RIGHT);
				} else if(c == 's') {
					addCommand(cmd, Type.POWERUP);
				} else if(c == 'w') {
					addCommand(cmd, Type.JUMP);
				}
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
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
}

