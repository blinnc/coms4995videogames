package test;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class gameLobby extends javax.swing.JFrame {
	private JButton blueOneButton;
	private JButton blueTwoButton;
	private JLabel usernameLabel;
	private JLabel pongRevLabel;
	private JTextPane chatWindow;
	private JTextPane blueTwoUsername;
	private JTextPane blueOneUsername;
	private JTextPane redTwoUsername;
	private JTextPane redOneUsername;
	private JButton redTwoButton;
	private JButton redOneButton;
	private JButton sendButton;
	private JTextField chatInputWindow;
	private String username;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				gameLobby inst = new gameLobby("testWindow");
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
		
	}
	
	public gameLobby(String username) {
		super();
		this.username = username;
		initGUI();
		usernameLabel.setText(usernameLabel.getText() + username);
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setFont(new java.awt.Font("Arial",0,10));
			getContentPane().setForeground(new java.awt.Color(0,255,0));
			{
				chatInputWindow = new JTextField();
				getContentPane().add(chatInputWindow);
				chatInputWindow.setBounds(12, 340, 215, 23);
			}
			{
				sendButton = new JButton();
				getContentPane().add(sendButton);
				sendButton.setText("Send");
				sendButton.setBounds(12, 369, 64, 23);
				sendButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						//send the message text to the server
						String message = chatInputWindow.getText();
					}
				});
			}
			{
				redOneButton = new JButton();
				getContentPane().add(redOneButton);
				redOneButton.setText("Red One");
				redOneButton.setBounds(239, 53, 100, 23);
				redOneButton.setBackground(new java.awt.Color(255,0,0));
				redOneButton.setForeground(new java.awt.Color(0,0,0));
				redOneButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						//send the player information to the system.
						//What are we sending? Username? 
					}
				});
			}
			{
				redTwoButton = new JButton();
				getContentPane().add(redTwoButton);
				redTwoButton.setText("Red Two");
				redTwoButton.setBounds(239, 87, 100, 23);
				redTwoButton.setBackground(new java.awt.Color(255,0,0));
				redTwoButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						
					}
				});
			}
			{
				blueOneButton = new JButton();
				getContentPane().add(blueOneButton);
				blueOneButton.setText("Blue One");
				blueOneButton.setBounds(239, 121, 100, 23);
				blueOneButton.setBackground(new java.awt.Color(0,128,255));
				blueOneButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						
					}
				});
			}
			{
				blueTwoButton = new JButton();
				getContentPane().add(blueTwoButton);
				blueTwoButton.setText("Blue Two");
				blueTwoButton.setBounds(239, 155, 100, 23);
				blueTwoButton.setBackground(new java.awt.Color(0,128,255));
				blueTwoButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						
					}
				});
			}
			{
				redOneUsername = new JTextPane();
				getContentPane().add(redOneUsername);
				redOneUsername.setBounds(344, 54, 161, 22);
				redOneUsername.setEditable(false);
			}
			{
				redTwoUsername = new JTextPane();
				getContentPane().add(redTwoUsername);
				redTwoUsername.setBounds(344, 88, 161, 22);
				redTwoUsername.setEditable(false);
			}
			{
				blueOneUsername = new JTextPane();
				getContentPane().add(blueOneUsername);
				blueOneUsername.setBounds(344, 122, 161, 22);
				blueOneUsername.setEditable(false);
			}
			{
				blueTwoUsername = new JTextPane();
				getContentPane().add(blueTwoUsername);
				blueTwoUsername.setBounds(344, 156, 161, 22);
				blueTwoUsername.setEditable(false);
			}
			{
				chatWindow = new JTextPane();
				getContentPane().add(chatWindow);
				chatWindow.setBounds(12, 53, 215, 275);
				chatWindow.setEditable(false);
			}
			{
				pongRevLabel = new JLabel();
				getContentPane().add(pongRevLabel);
				pongRevLabel.setText("Pong Revolution Game Lobby");
				pongRevLabel.setBounds(12, 12, 263, 29);
				pongRevLabel.setFont(new java.awt.Font("Segoe UI",1,18));
			}
			{
				usernameLabel = new JLabel();
				getContentPane().add(usernameLabel);
				usernameLabel.setText("Welcome: ");
				usernameLabel.setBounds(281, 18, 217, 16);
				usernameLabel.setFont(new java.awt.Font("Segoe UI",1,18));
			}
			pack();
			this.setSize(532, 469);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

}
