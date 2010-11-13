package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.apache.thrift.TException;

import network.TBall;
import network.TPlayer;
import network.TPosition;
import network.TPowerUp;
import client.GameInfo;

@SuppressWarnings("serial")
public class pongRev extends JFrame implements KeyListener {
	
	private static final int CIRCLE_X = 50;
	private static final int CIRCLE_Y = 50;
	private static final int CIRCLE_DIAMETER = 510;
	private static final int CIRCLE_CENTER = CIRCLE_X + (CIRCLE_DIAMETER / 2);
	public double paddleRotation = 0;
	Rectangle paddle1 = new Rectangle(540, 280, 20, 40);
	Shape shape1 = new Rectangle(540, 280, 20, 40);
	
	Rectangle paddle2 = new Rectangle(540, 280, 20, 40);
	Shape shape2 = new Rectangle(540, 280, 20, 40);
	
	Rectangle paddle3 = new Rectangle(540, 280, 20, 40);
	Shape shape3 = new Rectangle(540, 280, 20, 40);
	
	Rectangle paddle4 = new Rectangle(540, 280, 20, 40);
	Shape shape4 = new Rectangle(540, 280, 20, 40);
	boolean blah = false;
	private Image dbImage;
	private Graphics dbg; 
	private int otherColor = 240;
	private int change = 0;
	int rotate = 0;
	int rotateChange = 0;
	int rotateZ = 10;
	int rotateChangeZ = 0;
	private int otherColorZ = 0;
	private int changeZ = 0;
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
	Image backG = Toolkit.getDefaultToolkit().getImage("assets/backZ.gif");
	Image score = Toolkit.getDefaultToolkit().getImage("assets/score.png");
	private boolean a;
	private boolean d;
	static GameInfo gameinfo;
	private static boolean waitForInput = true;
    
	@SuppressWarnings("deprecation")
	public pongRev()
	{
		super( "Pong Revolution" );
        setBackground(Color.black );
        setForeground(Color.white);
        setSize( 900, 600 );
        //paddle = new Rectangle(540, 280, 20, 40);
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
		
		dbg.drawImage(backG,6,6,this);
		dbg.drawImage(score,600,50,this);
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
		
		
		if(shape1 != null) {
			//dbg.draw(shape);
			((Graphics2D) dbg).fill(shape1);
		}
		if(shape2 != null) {
			((Graphics2D) dbg).fill(shape2);
		}
		if(shape3 != null) {
			((Graphics2D) dbg).fill(shape3);
		}
		if(shape4 != null) {
			((Graphics2D) dbg).fill(shape4);
		}
		
        dbg.setColor(Color.green);
        for (int i = 0; i < gameinfo.state.balls.size(); i++) {
        	for (int j = 0; j < gameinfo.state.balls.get(i).positions.size(); j++) {
        	dbg.fillOval((int)gameinfo.state.balls.get(i).positions.get(j).xPos + CIRCLE_CENTER,
        			-(int)gameinfo.state.balls.get(i).positions.get(j).yPos + CIRCLE_CENTER, 
        			gameinfo.settings.ballRadius, gameinfo.settings.ballRadius);
        	
        	}}
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
			//explode = 205;
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
			if (gameinfo.state.paddles.get(1) != null) {
		        AffineTransform tx1 = new AffineTransform();
		        paddleRotation = gameinfo.state.paddles.get(1).angle;
		        tx1.rotate(Math.toRadians(-paddleRotation), CIRCLE_CENTER, CIRCLE_CENTER);
		        shape1 = (Path2D) tx1.createTransformedShape(paddle1);
			}
	        
			if (gameinfo.state.paddles.get(2) != null) {
		        AffineTransform tx2 = new AffineTransform();
		        paddleRotation = gameinfo.state.paddles.get(2).angle;
		        tx2.rotate(Math.toRadians(-paddleRotation), CIRCLE_CENTER, CIRCLE_CENTER);
		        shape2 = (Path2D) tx2.createTransformedShape(paddle2);
			}
	        
			if (gameinfo.state.paddles.get(3) != null) {
		        AffineTransform tx3 = new AffineTransform();
		        paddleRotation = gameinfo.state.paddles.get(3).angle;
		        tx3.rotate(Math.toRadians(-paddleRotation), CIRCLE_CENTER, CIRCLE_CENTER);
		        shape3 = (Path2D) tx3.createTransformedShape(paddle3);
			}
	        
			if (gameinfo.state.paddles.get(4) != null) {
		        AffineTransform tx4 = new AffineTransform();
		        paddleRotation = gameinfo.state.paddles.get(4).angle;
		        tx4.rotate(Math.toRadians(-paddleRotation), CIRCLE_CENTER, CIRCLE_CENTER);
		        shape4 = (Path2D) tx4.createTransformedShape(paddle4);
			}
	        
	        

	        

	        this.repaint();
	        if (a && d) {
	        } else if (a) {
	            moveA();
	        } else if (d) {
	            moveD();
	        }
	    }
	}
	
	private void moveA() {
		try {
			gameinfo.client.moveLeft(gameinfo.player);
		} catch (TException e) {
			e.printStackTrace();
		}
	}

	private void moveD() {
		try {
			gameinfo.client.moveRight(gameinfo.player);
		} catch (TException e) {
			e.printStackTrace();
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
