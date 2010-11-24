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
import client.GameInfo;

@SuppressWarnings("serial")
public class pongRev extends JFrame implements KeyListener {
	
	private static final int CIRCLE_X = 50;
	private static final int CIRCLE_DIAMETER = 600;
	private static final int CIRCLE_CENTER = CIRCLE_X + (CIRCLE_DIAMETER / 2);
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
	Image red1 = Toolkit.getDefaultToolkit().getImage("assets/red1.png");
	Image red2 = Toolkit.getDefaultToolkit().getImage("assets/red2.png");
	Image blue1 = Toolkit.getDefaultToolkit().getImage("assets/blue1.png");
	Image blue2 = Toolkit.getDefaultToolkit().getImage("assets/blue2.png");
	Image[] paddles = {null, red1, red2, blue1, blue2};
	Image redBall = Toolkit.getDefaultToolkit().getImage("assets/redball.png");
	Image blueBall = Toolkit.getDefaultToolkit().getImage("assets/blueball.png");
	Image neutralBall = Toolkit.getDefaultToolkit().getImage("assets/neutralball.png");
	Image score = Toolkit.getDefaultToolkit().getImage("assets/score.png");
	Image ad = Toolkit.getDefaultToolkit().getImage("assets/AD.png");
	private boolean a;
	private boolean d;
	private boolean w;
	static GameInfo gameinfo;
	private static boolean waitForInput = true;
	AffineTransform tx[] = new AffineTransform[5];
	AffineTransform txAD;
    int offset = 18;

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
		
		
		dbg.drawImage(backG,0,0,this);
		dbg.drawImage(score,700,0,this);
		if(explode ==205)
		{		
			for(int z =0; z<shardN; z++)
			{
				shardX[z]=300+(int)(Math.random()*10);
				shardY[z]=300+(int)(Math.random()*10);
				shardXZ[z]=(int)(Math.random()*3);
				shardYZ[z]=(int)(Math.random()*3);
				shardXS[z]=(int)(Math.random()*5);
				shardYS[z]=(int)(Math.random()*5);
				shardS[z]=4;
				shardC[z]=new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
			}
		}
		if(explode>=100)
		{
			explode--;
			for(int z =0; z<shardN; z++)
			{
				if(shardXZ[z]==0)
					shardX[z]+=shardXS[z];
				else
					shardX[z]-=shardXS[z];
				if(shardYZ[z]==0)
					shardY[z]+=shardYS[z];
				else
					shardY[z]-=shardYS[z];
			
				dbg.setColor(shardC[z]);
				if(explode%50==0)
					shardS[z]--;
				dbg.fillOval(shardX[z], shardY[z], shardS[z], shardS[z]);
			}
		}
		
		/*else
		{
			dbg.setColor(Color.green);
			dbg.fillOval(300, 300, 10, 10);
			
			dbg.setColor(new Color(otherColorZ,255,otherColorZ));
			dbg.drawOval(305-rotate/2, 295, rotate, 20);
			
			dbg.setColor(new Color(otherColor,255,otherColor));
			dbg.drawOval(295, 305-rotateZ/2, 20, rotateZ);
			if(rotateChange == 0)
				rotate ++;
			if(rotateChange == 1)
				rotate --;
			
			if(rotate >= 20)
				rotateChange = 1;
			if(rotate <= 0)
				rotateChange = 0;
				
			if(rotateChangeZ == 0)
				rotateZ ++;
			if(rotateChangeZ == 1)
				rotateZ --;
			
			if(rotateZ >= 20)
				rotateChangeZ = 1;
			if(rotateZ <= 0)
				rotateChangeZ = 0;
			
			if(changeZ == 0)
				otherColorZ +=1;
			if(changeZ == 1)
				otherColorZ -= 1; 
			
			if(otherColorZ>=255)
				changeZ = 1;
			if(otherColorZ<=0)
				changeZ = 0;
		}*/
		
		if(change == 0)
			otherColor +=1;
		if(change == 1)
			otherColor -= 1; 
		
		if(otherColor>=255)
			change = 1;
		if(otherColor<=0)
			change = 0;
		
		dbg.setColor(new Color(otherColor,otherColor,255));
		
		for (int i = 1; i < paddles.length; i++) {
			if (i != gameinfo.player.getValue()) {
				((Graphics2D) dbg).drawImage(paddles[i], tx[i], this);
			}
		}
		
		((Graphics2D) dbg).drawImage(paddles[gameinfo.player.getValue()], tx[gameinfo.player.getValue()], this);
		((Graphics2D) dbg).drawImage(ad, txAD, this); //FIX THIS OFFSET FOR THE CONTROL AD
		
		if (gameinfo.state != null) {
	        for (int i = 0; i < gameinfo.state.balls.size(); i++) {
	            Image img = null;
	        	if (gameinfo.state.balls.get(i).player == TPlayer.NONE){
	        		dbg.setColor(Color.GREEN);
	        		img = neutralBall;
	        	} else if (gameinfo.state.balls.get(i).player == TPlayer.BLUE_ONE || 
	        			gameinfo.state.balls.get(i).player == TPlayer.BLUE_TWO) {
	        		dbg.setColor(Color.BLUE);
	        		img = blueBall;
	        	} else if (gameinfo.state.balls.get(i).player == TPlayer.RED_ONE || 
	        			gameinfo.state.balls.get(i).player == TPlayer.RED_TWO) {
	        		dbg.setColor(Color.RED);
	        		img = redBall;
	        	}
	        	for (int j = 0; j < gameinfo.state.balls.get(i).positions.size(); j++) {
	        	    if (j == 0) {
	        	        dbg.drawImage(img, (int)gameinfo.state.balls.get(i).positions.get(j).xPos + CIRCLE_CENTER - img.getWidth(null) / 2,
	                            -(int)gameinfo.state.balls.get(i).positions.get(j).yPos + CIRCLE_CENTER - img.getHeight(null) / 2, this);
	        	    } else {
        	        	dbg.fillOval((int)gameinfo.state.balls.get(i).positions.get(j).xPos + CIRCLE_CENTER - gameinfo.settings.ballRadius,
        	        			-(int)gameinfo.state.balls.get(i).positions.get(j).yPos + CIRCLE_CENTER - gameinfo.settings.ballRadius, 
        	        			gameinfo.settings.ballRadius*2, gameinfo.settings.ballRadius*2);
	        	    }
	        	}
	        }
	        dbg.setColor(Color.red);
	        dbg.drawString("Red Score: " + gameinfo.state.redScore, 550, 50);
	        dbg.setColor(Color.blue);
	        dbg.drawString("Blue Score: " + gameinfo.state.blueScore, 550, 65);
		}
        
        dbg.setColor(Color.orange);
        for (int i = 0; i < gameinfo.state.connections.size(); i++) {
        	dbg.fillOval((int) gameinfo.state.connections.get(i).xPos + CIRCLE_CENTER,
        			-(int) gameinfo.state.connections.get(i).yPos + CIRCLE_CENTER, 3, 3);
        }
        
		//paint(dbg); 		
		//dbg.setColor(Color.white);
		//dbg.drawOval(CIRCLE_X, CIRCLE_Y, CIRCLE_DIAMETER, CIRCLE_DIAMETER);
		g.drawImage(dbImage, 0, 0, this);
	}
	
	public void update(Graphics g)
	{
		repaint();
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		char c = arg0.getKeyChar();
		if(c == 'a') 
		{
			a = true;
		} 
		else if (c == 'd') 
		{
			d = true;
		}
		else if (c == 'w')
		{
			w = true;
		}
		else if (c == 's')
		{
			explode = 205;
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
	    }
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		char c = arg0.getKeyChar();
		if(c == 'a') {
			a = false;
		} else if (c == 'd') {
			d = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
