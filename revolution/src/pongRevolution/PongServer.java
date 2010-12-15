package pongRevolution;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import network.TDirection;
import network.TGameState;
import network.TNetworkServer;
import network.TPlayer;
import network.TSettings;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

public class PongServer implements network.TNetworkServer.Iface{	
	private Game game;
	private Timer timer;
	Logger log = Logger.getLogger(PongServer.class);
	
	private boolean pause;
	
	public PongServer() {
		game = new Game();
		timer = new Timer();
		pause = false;
		BasicConfigurator.configure();
		timer.scheduleAtFixedRate(new ClockThread(), 0, GameSettings.CLOCK_INTERVAL);
		
		final JButton startButton = new JButton("Start Game");
		final JButton exitButton = new JButton("Exit");
		final JButton pauseButton = new JButton("Pause");
		final JButton resetButton = new JButton("Reset");
		final JPanel buttonPanel = new JPanel(new GridLayout(2, 2));
		buttonPanel.add(startButton);
		buttonPanel.add(exitButton);
		buttonPanel.add(pauseButton);
		buttonPanel.add(resetButton);
		
		final JCheckBox powerBox = new JCheckBox("Enable power-ups");
		final JCheckBox spawnBox = new JCheckBox("Enable ball spawn towards loser");
		final JTextField pointsBox = new JTextField();
		powerBox.setEnabled(true);
		spawnBox.setEnabled(true);
		pointsBox.setText("1000");
		final JPanel settingsPanel = new JPanel(new GridLayout(3, 1));
		settingsPanel.add(powerBox);
		settingsPanel.add(spawnBox);
		settingsPanel.add(pointsBox);
		
		final JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.add(buttonPanel, BorderLayout.CENTER);
		frame.add(settingsPanel, BorderLayout.SOUTH);
		try {
			final JLabel ipLabel = new JLabel(InetAddress.getLocalHost().getHostAddress());
			final JPanel ipPanel = new JPanel();
			ipPanel.add(ipLabel);
			frame.add(ipPanel, BorderLayout.NORTH);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
		pauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pause = !pause;
				if(pause) {
					pauseButton.setText("Unpause");
					frame.pack();
				}
				else {
					pauseButton.setText("  Pause  ");
					frame.pack();
				}
			}
		});
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.resetGame();
				pause = false;
			}
		});
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.startGame();
			}
		});
		powerBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				
			}
		});
		spawnBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				
			}
		});
	}
	
	private void start(){
	      try {
	         TServerSocket serverTransport = new TServerSocket(GameSettings.SERVER_PORT);
	         TNetworkServer.Processor processor = new TNetworkServer.Processor(this);
	         Factory protFactory = new TBinaryProtocol.Factory(true, true);
	         TServer server = new TThreadPoolServer(processor, serverTransport, protFactory);
	         System.out.println("Starting server on port " + GameSettings.SERVER_PORT + "...");
	         server.serve();
	      } catch (TTransportException e) {
	         e.printStackTrace();
//	      } catch (IOException e) {
//	         e.printStackTrace();
	      }
	   }
	public static void main(String args[]){
	      PongServer srv = new PongServer();
	      srv.start();
	}
	
	private class ClockThread extends TimerTask {
		public void run() {
			if(!pause) {
				game.updateGame();
			}
		}
	}

	@Override
	public void jump(TPlayer requester) throws TException {
		game.jumpPaddle(requester);		
	}


	@Override
	public void move(TPlayer requester, TDirection dir) throws TException {
		game.movePaddle(requester, dir);
	}


	@Override
	public TGameState poll(TPlayer requester) throws TException {
		return game.getState(requester);
	}


	@Override
	public void usePowerUp(TPlayer requester) throws TException {
		game.usePowerup(requester);
	}


	@Override
	public TSettings getSettings(TPlayer preferred) throws TException {
		TPlayer player = game.registerPlayer(preferred);
		List<Integer> comboList = new ArrayList<Integer>();
		for(int i = 1; i < GameSettings.COMBO_SCORE.length; i++) {
			comboList.add(GameSettings.COMBO_SCORE[i] - GameSettings.COMBO_SCORE[i - 1]);
		}
		TSettings settings = new TSettings(GameSettings.BALL_RADIUS, GameSettings.ARENA_RADIUS, GameSettings.CLOCK_INTERVAL, player, comboList);
		return settings;
	}
}
