package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;

import org.apache.thrift.TException;

import network.TDirection;
import network.TPaddle;
import network.TPlayer;
import network.TPowerUp;
import client.GameInfo;

import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

@SuppressWarnings("serial")
public class pongRev extends JFrame implements KeyListener {
	
	private static final int CIRCLE_X = 50;
	private static final int CIRCLE_DIAMETER = 600;
	private static final int CIRCLE_CENTER = CIRCLE_X + (CIRCLE_DIAMETER / 2) + 6;
	public double paddleRotation = 0;
	private Image dbImage;
	private Graphics dbg; 
	private int otherColor = 240;
	private int change = 0;
	int rotate = 0;
	int rotateChange = 0;
	int rotateZ = 10;
	int rotateChangeZ = 0;
	int explode = 0;
	int shardN=15;
	int shardX[] = new int[shardN];
	int shardY[] = new int[shardN];
	int shardXZ[] = new int[shardN];
	int shardYZ[] = new int[shardN];
	int shardXS[] = new int[shardN];
	int shardYS[] = new int[shardN];
	int shardS[] = new int[shardN];
	Color shardC[] = new Color[shardN];
	Image backG = Toolkit.getDefaultToolkit().getImage("assets/back.png");
	Image front = Toolkit.getDefaultToolkit().getImage("assets/front.png");
	Image red1 = Toolkit.getDefaultToolkit().getImage("assets/red1.png");
	Image red2 = Toolkit.getDefaultToolkit().getImage("assets/red2.png");
	Image blue1 = Toolkit.getDefaultToolkit().getImage("assets/blue1.png");
	Image blue2 = Toolkit.getDefaultToolkit().getImage("assets/blue2.png");
	Image[] paddles = {null, red1, red2, blue1, blue2};
	Image redBall = Toolkit.getDefaultToolkit().getImage("assets/redball.png");
	Image blueBall = Toolkit.getDefaultToolkit().getImage("assets/blueball.png");
	Image blueMax = Toolkit.getDefaultToolkit().getImage("assets/blueMax.gif");
	Image redMax = Toolkit.getDefaultToolkit().getImage("assets/redMax.gif");
	Image neutralBall = Toolkit.getDefaultToolkit().getImage("assets/neutralball.png");
	Image score = Toolkit.getDefaultToolkit().getImage("assets/score.png");
	Image ad = Toolkit.getDefaultToolkit().getImage("assets/AD.png");
	Image number = Toolkit.getDefaultToolkit().getImage("assets/number.png");
	private boolean a;
	private boolean d;
	private boolean w;
	private boolean s;
	static GameInfo gameinfo;
	private static boolean waitForInput = true;
	AffineTransform tx[] = new AffineTransform[5];
	AffineTransform txAD;
    int offset = 33;
    double ballRadius = 7.5;

	public pongRev()
	{
		super( "Pong Revolution" );
        setBackground(Color.black );
        setForeground(Color.white);
        setSize( 1000, 700 );
        this.addKeyListener(this);
        setVisible(true);

	}
	
	public static void main(String[] args) {
		//-----LOGIC TO CONNECT TO THE SERVER
		/*TTransport transport;
	      try {
	         transport = new TSocket(HOST_IP, 7911);
	         TProtocol protocol = new TBinaryProtocol(transport);
	         client = new Client(protocol);
	         transport.open();
	         //transport.close();
	      } catch (TTransportException e) {
	         f("Problem when trying to connect to the server.");
	    	  e.printStackTrace();
	      }*/
		//-----END LOGIC TO CONNECT TO THE SERVER-----
		
		//BEGIN SET UP AND DRAW THE SERVER GUI
		final StartWindow start = new StartWindow();
		start.joinButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String hostAddress = start.serverInput.getText();
				String team = null;
				try {
					team = start.teamList.getSelectedValue().toString();
				} catch (NullPointerException e) {
					System.out.println("Please select a team and try again.");
				}
				if(hostAddress.equals(""))
				{
					System.out.println("Please enter a server and try again.");
				}
				gameinfo = new GameInfo(hostAddress, team);
				start.setVisible(false);
				waitForInput = false;
			}
		});
		
		start.setVisible(true);
		
		while(waitForInput) {
			
		}
		//END SET UP AND DRAW THE SERVER GUI
		
		//BEGIN SET UP AND DRAW THE GAME GUI
		final pongRev pr = new pongRev();
		(new Thread() {
            public void run() {
                try {
                	while(true)
                	{
                		pr.movePaddle();
                	}
                }
                catch(InterruptedException e) {}
            }
        }).start();

		pr.addWindowListener(new WindowAdapter()
		{
			 public void windowClosing( WindowEvent e )
             {
             System.exit( 0 );
             }
		});
		//END SET UP AND DRAW THE GAME GUI
		
		  try {
		         AudioInputStream stream =
		                  AudioSystem.getAudioInputStream(
		                    new File("assets/song.wav"));
		 
		 	AudioFormat format = stream.getFormat();
		        DataLine.Info info =
		                  new DataLine.Info(Clip.class,
		                          stream.getFormat());
		        Clip clip = (Clip) AudioSystem.getLine(info);
		 
		        clip.open(stream);
		        clip.start();
		        clip.loop(Clip.LOOP_CONTINUOUSLY);
		 
		      } catch (Exception e) {
		           e.printStackTrace();
		      }
	}
	
	public void paint(Graphics g)
	{	
		if (dbImage == null) 
		{
			dbImage = createImage (this.getSize().width, this.getSize().height); 
			dbg = dbImage.getGraphics (); 
		} 
		
		dbg.drawLine(600, 0, 600, 600);
		
		//Graphics2D g2d = ( Graphics2D ) g;
		//dbg.setColor(Color.black);
		//dbg.fillOval(CIRCLE_X, CIRCLE_Y, CIRCLE_DIAMETER, CIRCLE_DIAMETER);
		//dbg.fillRect(601,0,300,600);
		//dbg.setColor (getBackground ()); 
		//dbg.fillRect (0, 0, this.getSize().width, this.getSize().height); 
		
		
		dbg.drawImage(backG,6,6,this);
		dbg.drawImage(score,700,0,this);
		
		if (gameinfo.state != null) {
	        for (int i = 0; i < gameinfo.state.balls.size(); i++) {
	            Image img = null;
	        	if (gameinfo.state.balls.get(i).player == TPlayer.NONE){
	        	    if (gameinfo.state.balls.get(i).type == TPowerUp.NONE) {
	        		dbg.setColor(Color.GREEN);
	        		img = neutralBall;
	        	    } else {
	        	        dbg.setColor(Color.YELLOW);
	        	        img = blueMax; //CHANGE TO YELLOW BALL
	        	    }
	        	} else if (gameinfo.state.balls.get(i).player == TPlayer.BLUE_ONE || 
	        			gameinfo.state.balls.get(i).player == TPlayer.BLUE_TWO) {
	        		dbg.setColor(Color.BLUE);
	        		if(gameinfo.state.balls.get(i).positions.size()==5)
	        			img = blueMax;
	        		else
	        			img = blueBall;
	        	} else if (gameinfo.state.balls.get(i).player == TPlayer.RED_ONE || 
	        			gameinfo.state.balls.get(i).player == TPlayer.RED_TWO) {
	        		dbg.setColor(Color.RED);
	        		if(gameinfo.state.balls.get(i).positions.size()==5)
	        			img = redMax;
	        		else
	        			img = redBall;
	        	}
	        	for (int j = 0; j < gameinfo.state.balls.get(i).positions.size(); j++) {
	        	    if (j == 0) {
	        	        dbg.drawImage(img, (int)gameinfo.state.balls.get(i).positions.get(j).xPos + CIRCLE_CENTER - img.getWidth(null) / 2,
	                            -(int)gameinfo.state.balls.get(i).positions.get(j).yPos + CIRCLE_CENTER - img.getHeight(null) / 2, this);
	        	    } else {
	        	        int br = (int) (ballRadius - j) *2;
        	        	dbg.drawOval((int)gameinfo.state.balls.get(i).positions.get(j).xPos + CIRCLE_CENTER - br / 2,
        	        			-(int)gameinfo.state.balls.get(i).positions.get(j).yPos + CIRCLE_CENTER - br / 2, 
        	        			br, br);
	        	    }
	        	}
	        }
	        
	        String tempScore = Integer.toString(gameinfo.state.redScore);
	        int xScore = 720;
	        int yScore = 130;
	        for(int temp = 0; temp < tempScore.length(); temp++)
	        {
	        	 dbg.drawImage(number, xScore + 0 + temp*30, yScore + 0, xScore + 30 + temp*30, yScore + 40, 0 + Character.getNumericValue(tempScore.charAt(temp))*30, 0, 30 + Character.getNumericValue(tempScore.charAt(temp))*30, 40, this);
	        }
	        xScore = 880;
	        tempScore = Integer.toString(gameinfo.state.blueScore);
	        for(int temp = 0; temp < tempScore.length(); temp++)
	        {
	        	dbg.drawImage(number, xScore + 0 + temp*30, yScore + 0, xScore + 30 + temp*30, yScore + 40, 0 + Character.getNumericValue(tempScore.charAt(temp))*30, 40, 30 + Character.getNumericValue(tempScore.charAt(temp))*30, 80, this);
	        }
		}
        
//        dbg.setColor(Color.orange);
//        for (int i = 0; i < gameinfo.state.connections.size(); i++) {
//        	dbg.fillOval((int) gameinfo.state.connections.get(i).xPos + CIRCLE_CENTER,
//        			-(int) gameinfo.state.connections.get(i).yPos + CIRCLE_CENTER, 4, 4);
//        }
		
		for (int i = 1; i < paddles.length; i++) {
			if (i != gameinfo.player.getValue() && tx[i] != null) {
				((Graphics2D) dbg).drawImage(paddles[i], tx[i], this);
			}
		}
		dbg.drawImage(front,6,6,this);
		
		((Graphics2D) dbg).drawImage(paddles[gameinfo.player.getValue()], tx[gameinfo.player.getValue()], this);
		((Graphics2D) dbg).drawImage(ad, txAD, this); //FIX THIS OFFSET FOR THE CONTROL AD
		
		g.drawImage(dbImage, 0, 0, this);
	}
	
	public void update(Graphics g)
	{
		repaint();
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		char c = arg0.getKeyChar();
		if(c == 'a' || c == 'A' || c == 'j' || c == 'J') 
		{
			a = true;
		} 
		else if (c == 'd' || c == 'D' || c == 'l' || c == 'L') 
		{
			d = true;
		}
		else if (c == 'w' || c == 'W' || c == 'i' || c == 'I' || c == ' ')
		{
			w = true;
		}
		else if (c == 's' || c == 'S' || c == 'k' || c == 'K')
		{
			s = true;
		}
	}
	
	private void movePaddle() throws InterruptedException {
	    while (true) {
	        Thread.sleep(15);
	        try {
				gameinfo.state = gameinfo.client.poll(gameinfo.player);
			} catch (TException e) {
				e.printStackTrace();
			}
			for (int i = 1; i < gameinfo.state.paddles.size(); i++) {
				if (!gameinfo.state.paddles.get(i).equals(new TPaddle())) {
			        tx[i] = new AffineTransform();
			        paddleRotation = gameinfo.state.paddles.get(i).angle;
			        tx[i].rotate(Math.toRadians(-paddleRotation), CIRCLE_CENTER, CIRCLE_CENTER);
			        tx[i].rotate(-Math.PI/2, CIRCLE_CENTER, CIRCLE_CENTER);
					tx[i].translate(CIRCLE_CENTER - red1.getWidth(null)/2, CIRCLE_DIAMETER / 2 + gameinfo.state.paddles.get(i).radius + offset);
					if (i == gameinfo.player.getValue()) {
						txAD = new AffineTransform();
						txAD.rotate(Math.toRadians(-paddleRotation), CIRCLE_CENTER, CIRCLE_CENTER);
				        txAD.rotate(-Math.PI/2, CIRCLE_CENTER, CIRCLE_CENTER);
						txAD.translate(CIRCLE_CENTER - ad.getWidth(null)/2, CIRCLE_DIAMETER + CIRCLE_X);
					}
				}
			}
			      
	        this.repaint();
	        if ((a && d) || (!a && !d)) {
	        	try {
	    			gameinfo.client.move(gameinfo.player, TDirection.NONE);
	    		} catch (TException e) {
	    			e.printStackTrace();
	    		}
	        } else if (a) {
	        	try {
	    			gameinfo.client.move(gameinfo.player, TDirection.LEFT);
	    		} catch (TException e) {
	    			e.printStackTrace();
	    		}
	        } else if (d) {
	        	try {
	    			gameinfo.client.move(gameinfo.player, TDirection.RIGHT);
	    		} catch (TException e) {
	    			e.printStackTrace();
	    		}
	        }
	        if (w) {
	        	w = false;
	        	try {
	        		gameinfo.client.jump(gameinfo.player);
	        	} catch (TException e) {
	        		e.printStackTrace();
	        	}
	        }
	        if (s) {
                s = false;
                try {
                    gameinfo.client.usePowerUp(gameinfo.player);
                } catch (TException e) {
                    e.printStackTrace();
                }
            }
	    }
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		char c = arg0.getKeyChar();
		if(c == 'a' || c == 'A' || c == 'j' || c == 'J') {
			a = false;
		} else if (c == 'd' || c == 'D' || c == 'l' || c == 'L') {
			d = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
