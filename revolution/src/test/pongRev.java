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
import network.TPower;
import network.TPowerUp;
import client.GameInfo;

import java.io.File;
import java.util.Hashtable;

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
	private Image spawnAnimation;
	private Graphics dbg;
	Hashtable<Integer, Integer> powerID = new Hashtable<Integer, Integer>();
	Hashtable<Integer, Integer> collideID = new Hashtable<Integer, Integer>();
	Hashtable<Integer, Integer> scoreID = new Hashtable<Integer, Integer>();
	Hashtable<Integer, Integer> spawnID = new Hashtable<Integer, Integer>();
	
	Clip invisClip, stunClip, speedClip, scoreClip;
	Clip[] teamCollide = new Clip[6];
	Clip[] enemyCollide = new Clip[6];
	
	Image backG = Toolkit.getDefaultToolkit().getImage("assets/back.png");
	Image front = Toolkit.getDefaultToolkit().getImage("assets/front.png");
	
	Image invisPaddle = Toolkit.getDefaultToolkit().getImage("assets/invisPaddle.png");
	Image yellowPaddle = Toolkit.getDefaultToolkit().getImage("assets/yellowPaddle.png");
	
	Image redSpeed = Toolkit.getDefaultToolkit().getImage("assets/redSpeed.png");
	Image red1 = Toolkit.getDefaultToolkit().getImage("assets/red1.png");
	Image red1Stun = Toolkit.getDefaultToolkit().getImage("assets/red1Stun.png");
	
	Image red2 = Toolkit.getDefaultToolkit().getImage("assets/red2.png");
	Image red2Stun = Toolkit.getDefaultToolkit().getImage("assets/red2Stun.png");
	
	Image blueSpeed = Toolkit.getDefaultToolkit().getImage("assets/blueSpeed.png");
	Image blue1 = Toolkit.getDefaultToolkit().getImage("assets/blue1.png");
	Image blue1Stun = Toolkit.getDefaultToolkit().getImage("assets/blue1Stun.png");
	
	Image blue2 = Toolkit.getDefaultToolkit().getImage("assets/blue2.png");
	Image blue2Stun = Toolkit.getDefaultToolkit().getImage("assets/blue2Stun.png");
	
	Image[] paddles = {null, red1, red2, blue1, blue2};
	Image[] stunPaddles = {null, red1Stun, red2Stun, blue1Stun, blue2Stun};
	
	Image redBall = Toolkit.getDefaultToolkit().getImage("assets/redball.png");
	Image blueBall = Toolkit.getDefaultToolkit().getImage("assets/blueball.png");
	Image blueMax = Toolkit.getDefaultToolkit().getImage("assets/blueMax.gif");
	Image redMax = Toolkit.getDefaultToolkit().getImage("assets/redMax.gif"); 
	Image neutralBall = Toolkit.getDefaultToolkit().getImage("assets/neutralball.png");
	Image yellowBall = Toolkit.getDefaultToolkit().getImage("assets/yellowball.png");
	Image score = Toolkit.getDefaultToolkit().getImage("assets/score.png");
	Image ad = Toolkit.getDefaultToolkit().getImage("assets/AD.png");
	Image number = Toolkit.getDefaultToolkit().getImage("assets/number.png");
	Image speedIcon = Toolkit.getDefaultToolkit().getImage("assets/speedIcon.png");
	Image invisIcon = Toolkit.getDefaultToolkit().getImage("assets/invisIcon.png");
	Image stunIcon = Toolkit.getDefaultToolkit().getImage("assets/stunIcon.png");
	Image invisActivated = Toolkit.getDefaultToolkit().getImage("assets/invisActivated.png");
	Image speedActivated = Toolkit.getDefaultToolkit().getImage("assets/speedActivated.png");
	Image stunActivated = Toolkit.getDefaultToolkit().getImage("assets/stunActivated.png");
	Image stunnedActivated = Toolkit.getDefaultToolkit().getImage("assets/stunnedActivated.png");
	
	private boolean a;
	private boolean d;
	private boolean w;
	private boolean s;
	static GameInfo gameinfo;
	private static boolean waitForInput = true;
	AffineTransform tx[] = new AffineTransform[5];
	AffineTransform txOld[][] = new AffineTransform[5][5];
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

		try {
	        AudioInputStream stream1 = AudioSystem.getAudioInputStream(new File("assets/sounds/stun.wav"));
	        DataLine.Info info1 = new DataLine.Info(Clip.class, stream1.getFormat());
	        stunClip = (Clip) AudioSystem.getLine(info1);
	        stunClip.open(stream1);
	        
	        AudioInputStream stream2 = AudioSystem.getAudioInputStream(new File("assets/sounds/invisibility.wav"));
	        DataLine.Info info2 = new DataLine.Info(Clip.class, stream2.getFormat());
	        invisClip = (Clip) AudioSystem.getLine(info2);
	        invisClip.open(stream2);
	        
	        AudioInputStream stream3 = AudioSystem.getAudioInputStream(new File("assets/sounds/speed.wav"));
	        DataLine.Info info3 = new DataLine.Info(Clip.class, stream3.getFormat());
	        speedClip = (Clip) AudioSystem.getLine(info3);
	        speedClip.open(stream3);
	        
	        AudioInputStream stream4 = AudioSystem.getAudioInputStream(new File("assets/sounds/score.wav"));
	        DataLine.Info info4 = new DataLine.Info(Clip.class, stream4.getFormat());
	        scoreClip = (Clip) AudioSystem.getLine(info4);
	        scoreClip.open(stream4);
	        
	        AudioInputStream[] stream = new AudioInputStream[6];
	        DataLine.Info[] info = new DataLine.Info[6];
	        AudioInputStream[] streamO = new AudioInputStream[6];
	        DataLine.Info[] infoO = new DataLine.Info[6];
	        for (int i = 0; i < 6; i++) {
	        	stream[i] = AudioSystem.getAudioInputStream(new File("assets/sounds/team" + i + ".wav"));
		        info[i] = new DataLine.Info(Clip.class, stream[i].getFormat());
		        teamCollide[i] = (Clip) AudioSystem.getLine(info[i]);
		        teamCollide[i].open(stream[i]);
		        
		        streamO[i] = AudioSystem.getAudioInputStream(new File("assets/sounds/opponent" + i + ".wav"));
		        infoO[i] = new DataLine.Info(Clip.class, streamO[i].getFormat());
		        enemyCollide[i] = (Clip) AudioSystem.getLine(infoO[i]);
		        enemyCollide[i].open(streamO[i]);
	        }
	        
	      } catch (Exception e) {
	           e.printStackTrace();
	           System.exit(0);
	      }
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
                		pr.play();
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
	                    new File("assets/game_song.wav"));
		 
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
	
		dbg.drawImage(backG,6,6,this);
		dbg.drawImage(score,700,0,this);
		
		if (gameinfo.state != null) {
	        for (int i = 0; i < gameinfo.state.balls.size(); i++) {
	            Image img = null;
	        	if (gameinfo.state.balls.get(i).player == TPlayer.NONE){
	        	    if (gameinfo.state.balls.get(i).store.type == TPowerUp.NONE) {
    	        		dbg.setColor(Color.GREEN);
    	        		img = neutralBall;
	        	    } else {
	        	        dbg.setColor(Color.YELLOW);
	        	        img = yellowBall; // TODO: CHANGE TO YELLOW BALL
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
        
//        dbg.setColor(Color.orange);
//        for (int i = 0; i < gameinfo.state.connections.size(); i++) {
//        	dbg.fillOval((int) gameinfo.state.connections.get(i).xPos + CIRCLE_CENTER,
//        			-(int) gameinfo.state.connections.get(i).yPos + CIRCLE_CENTER, 4, 4);
//        }
		
	        
	        // BALLS COLLIDING
	        for (int i = 0; i < gameinfo.state.collisions.size(); i++) {
	        	if (!collideID.containsKey(gameinfo.state.collisions.get(i).id)) {
	        		collideID.put(gameinfo.state.collisions.get(i).id, gameinfo.state.collisions.get(i).id);
	        		if (isEnemy(gameinfo.state.collisions.get(i).player)) {
	        			enemyCollide[gameinfo.state.collisions.get(i).ballCombo].stop();
	        			enemyCollide[gameinfo.state.collisions.get(i).ballCombo].setFramePosition(0);
	        			enemyCollide[gameinfo.state.collisions.get(i).ballCombo].start();
	        		} else {
	        			teamCollide[gameinfo.state.collisions.get(i).ballCombo].stop();
	        			teamCollide[gameinfo.state.collisions.get(i).ballCombo].setFramePosition(0);
	        			teamCollide[gameinfo.state.collisions.get(i).ballCombo].start();
	        		}
	        	}
	        }
	        
	        // BALLS SCORING
	        for (int i = 0; i < gameinfo.state.out.size(); i++) {
	        	if (!scoreID.containsKey(gameinfo.state.out.get(i).id)) {
	        		scoreID.put(gameinfo.state.out.get(i).id, gameinfo.state.out.get(i).id);
	        		scoreClip.stop();
	        		scoreClip.setFramePosition(0);
	        		scoreClip.start();
	        	}
	        }
	        
	        // BALL SPAWNING
	        System.out.println(gameinfo.state.spawning);
	        if (gameinfo.state.spawning != 0 && !spawnID.containsKey(gameinfo.state.spawning)) {
	        	spawnID.put(gameinfo.state.spawning, gameinfo.state.spawning);
	        	if (gameinfo.state.spawning < 0) {
	        		spawnAnimation = Toolkit.getDefaultToolkit().getImage("assets/yellowSpawn.gif");
	        	} else {
	        		spawnAnimation = Toolkit.getDefaultToolkit().getImage("assets/spawnBall.gif");
	        	}
	        }
	        dbg.drawImage(spawnAnimation, CIRCLE_CENTER, CIRCLE_CENTER, this);
	        
	        
	        // DRAW PADDLES
			dbg.drawImage(front,6,6,this);
			
			drawPaddle(gameinfo.enemy1);
			drawPaddle(gameinfo.enemy2);
			drawPaddle(gameinfo.ally);
			drawPaddle(gameinfo.player);
			((Graphics2D) dbg).drawImage(ad, txAD, this);
	
			if (gameinfo.state.paddles.get(gameinfo.player.getValue()).store.type == TPowerUp.INVIS) {
				dbg.drawImage(invisIcon,830,605,this);
			} else if (gameinfo.state.paddles.get(gameinfo.player.getValue()).store.type == TPowerUp.SPEED) {
				// draw speed up in bottom right
				dbg.drawImage(speedIcon,830,605,this);
			} else if (gameinfo.state.paddles.get(gameinfo.player.getValue()).store.type == TPowerUp.STUN) {
				// draw stun in bottom right
				dbg.drawImage(stunIcon,830,605,this);
			} 
			
			if (gameinfo.state.paddles.get(gameinfo.player.getValue()).used.type == TPowerUp.INVIS) {
				// draw a glow around the box
				dbg.drawImage(invisIcon,830,605,this);
				dbg.drawImage(invisActivated,813,588,this);
			} else if (gameinfo.state.paddles.get(gameinfo.player.getValue()).used.type == TPowerUp.SPEED) {
				// draw a glow around the box
				dbg.drawImage(speedIcon,830,605,this);
				dbg.drawImage(speedActivated,813,588,this);
			} else if (gameinfo.state.paddles.get(gameinfo.player.getValue()).used.type == TPowerUp.STUN) {
				// draw a glow around the box
				dbg.drawImage(stunIcon,830,605,this);
				dbg.drawImage(stunActivated,813,588,this);
			}
		}

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
	
	private void play() throws InterruptedException {
	    while (true) {
	        Thread.sleep(15);
	        try {
				gameinfo.state = gameinfo.client.poll(gameinfo.player);
			} catch (TException e) {
				e.printStackTrace();
			}
			for (int i = txOld.length-1; i > 0; i--) {
				txOld[i] = txOld[i-1];
			}
			txOld[0] = tx;
			tx = new AffineTransform[5];
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
						txAD.translate(CIRCLE_CENTER - ad.getWidth(null)/2, CIRCLE_DIAMETER + CIRCLE_X + 10);
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
	
	public void drawPaddle(TPlayer p) {
		int q = p.getValue();
		if (gameinfo.state.paddles.get(q).used != null) {
			if(gameinfo.state.paddles.get(q).used.type == TPowerUp.NONE) {
				((Graphics2D) dbg).drawImage(paddles[q], tx[q], this);
			} else if (gameinfo.state.paddles.get(q).used.type == TPowerUp.SPEED) {
				if (!powerID.containsKey(gameinfo.state.paddles.get(q).used.id)) {
					powerID.put(gameinfo.state.paddles.get(q).used.id, gameinfo.state.paddles.get(q).used.id);
					speedClip.stop();
					speedClip.setFramePosition(0);
					speedClip.start();
				}
				((Graphics2D) dbg).drawImage(paddles[q], tx[q], this);
				if (isBlue(p)) {
					((Graphics2D) dbg).drawImage(blueSpeed, txOld[1][q], this);
				} else {
					((Graphics2D) dbg).drawImage(redSpeed, txOld[1][q], this);
				}
			} else if (gameinfo.state.paddles.get(q).used.type == TPowerUp.INVIS) {
				if (!powerID.containsKey(gameinfo.state.paddles.get(q).used.id)) {
					powerID.put(gameinfo.state.paddles.get(q).used.id, gameinfo.state.paddles.get(q).used.id);
					invisClip.stop();
					invisClip.setFramePosition(0);
					invisClip.start();
				}
				if (!isEnemy(p)) {
					((Graphics2D) dbg).drawImage(invisPaddle, tx[q], this);
				}
			} else if (gameinfo.state.paddles.get(q).used.type == TPowerUp.STUN) {
				if (!powerID.containsKey(gameinfo.state.paddles.get(q).used.id)) {
					powerID.put(gameinfo.state.paddles.get(q).used.id, gameinfo.state.paddles.get(q).used.id);
					stunClip.stop();
					stunClip.setFramePosition(0);
					stunClip.start();
				}
				((Graphics2D) dbg).drawImage(yellowPaddle, tx[q], this);
			} else if (gameinfo.state.paddles.get(q).used.type == TPowerUp.STUNNED) {
				((Graphics2D) dbg).drawImage(stunPaddles[q], tx[q], this);
			}
		}
	}
	
	public boolean isBlue(TPlayer p) {
		if (p == TPlayer.RED_ONE || p == TPlayer.RED_TWO) {
			return false;
		}
		return true;
	}
	public boolean isEnemy(TPlayer p) {
		if (p == gameinfo.enemy1 || p == gameinfo.enemy2) {
			return true;
		}
		return false;
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
