package pongRevolution;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
	private static final int PORT = 12020;
	
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
		
		final JButton pauseButton = new JButton("  Pause  ");
		final JButton resetButton = new JButton("Reset");
		final JButton exitButton = new JButton("Exit");
		final JPanel buttonPanel = new JPanel();
		buttonPanel.add(pauseButton);
		buttonPanel.add(resetButton);
		buttonPanel.add(exitButton);
		
		final JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.add(buttonPanel, BorderLayout.CENTER);
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
	}
	
	private void start(){
	      try {
	         TServerSocket serverTransport = new TServerSocket(PORT);
	         TNetworkServer.Processor processor = new TNetworkServer.Processor(this);
	         Factory protFactory = new TBinaryProtocol.Factory(true, true);
	         TServer server = new TThreadPoolServer(processor, serverTransport, protFactory);
	         System.out.println("Starting server on port " + PORT + "...");
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
		return game.getState();
	}


	@Override
	public void usePowerUp(TPlayer requester) throws TException {
		game.usePowerup(requester);
	}


	@Override
	public TSettings getSettings(TPlayer preferred) throws TException {
		TPlayer player = game.registerPlayer(preferred);
		TSettings settings = new TSettings(GameSettings.BALL_RADIUS, GameSettings.ARENA_RADIUS, GameSettings.CLOCK_INTERVAL, player);
		return settings;
	}
}
