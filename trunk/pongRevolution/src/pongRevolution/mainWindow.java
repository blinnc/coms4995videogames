package pongRevolution;
import java.awt.*;

import javax.swing.*;

public class mainWindow {

	/**
	 * @param args
	 */
    
	//Hi!
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Pong Revolution");
		frame.setSize(800, 600);
		Container contents = frame.getContentPane();
		contents.setBackground(Color.white);
		JLabel score = new JLabel("SCORE");
		JLabel redScore = new JLabel("0");
		redScore.setForeground(Color.red);
		JLabel blueScore = new JLabel("0");
		blueScore.setForeground(Color.blue);
		JLabel name = new JLabel("PONG\nREVOLUTION!");
		JLabel pwrUp = new JLabel("POWER UP");
		ImageIcon icon = new ImageIcon("assets/lightning_icon01.gif", "POWERUPZ");
		JLabel powerUp = new JLabel(icon);
		
		JPanel scorePanel = new JPanel();
		scorePanel.setLayout(new BorderLayout());
		scorePanel.add(score, BorderLayout.NORTH);
		scorePanel.add(redScore, BorderLayout.CENTER);
		scorePanel.add(blueScore, BorderLayout.SOUTH);
	    
		contents.add(scorePanel, BorderLayout.NORTH);
	    contents.add(name, BorderLayout.CENTER);
	    
	    JPanel pwrUpPanel = new JPanel();
	    pwrUpPanel.setLayout(new BorderLayout());
	    pwrUpPanel.add(pwrUp, BorderLayout.NORTH);
	    pwrUpPanel.add(powerUp, BorderLayout.SOUTH);
	    
	    contents.add(pwrUpPanel, BorderLayout.SOUTH);
	    
	    frame.setVisible(true);
	}

}
