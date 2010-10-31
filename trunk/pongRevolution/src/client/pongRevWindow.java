package client;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

import javax.swing.JFrame;

public class pongRevWindow extends JFrame implements KeyListener {
	
	private static final int CIRCLE_X = 50;
	private static final int CIRCLE_Y = 50;
	private static final int CIRCLE_DIAMETER = 500;
	private static final int CIRCLE_CENTER = CIRCLE_X + (CIRCLE_DIAMETER / 2);
	public int paddleRotation = 0;
	Rectangle paddle = new Rectangle(540, 280, 20, 40);
	Shape shape = new Rectangle(540, 280, 20, 40);
	boolean blah = false;
	
	@SuppressWarnings("deprecation")
	public pongRevWindow()
	{
		super( "Pong Revolution" );
        setBackground( Color.yellow );
        setSize( 800, 600 );
        //paddle = new Rectangle(540, 280, 20, 40);
        //this.addKeyListener(this);
        show();
	}
	
	public void paint(Graphics g)
	{
		Graphics2D g2d = ( Graphics2D ) g;
		g.clearRect(0, 0, 600, 600);
		g2d.drawOval(CIRCLE_X, CIRCLE_Y, CIRCLE_DIAMETER, CIRCLE_DIAMETER);
		if(shape != null) {
			g2d.draw(shape);
			g2d.fill(shape);
		}
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		char c = arg0.getKeyChar();
		if(c == 'a') 
		{
			AffineTransform tx = new AffineTransform();
			paddleRotation += 3;
			tx.rotate(Math.toRadians(paddleRotation), CIRCLE_CENTER, CIRCLE_CENTER);
			//double x = 240 * Math.cos(Math.toRadians(paddleRotation)) + CIRCLE_CENTER;
			//double y = 240 * Math.sin(Math.toRadians(paddleRotation)) + CIRCLE_CENTER;
			shape = (Path2D) tx.createTransformedShape(paddle);
			this.repaint();
		} 
		else if (c == 'd') 
		{
			AffineTransform tx = new AffineTransform();
			paddleRotation -= 3;
			tx.rotate(Math.toRadians(paddleRotation), CIRCLE_CENTER, CIRCLE_CENTER);
			//double x = 240 * Math.cos(Math.toRadians(paddleRotation)) + CIRCLE_CENTER;
			//double y = 240 * Math.sin(Math.toRadians(paddleRotation)) + CIRCLE_CENTER;
			shape = (Path2D) tx.createTransformedShape(paddle);
			this.repaint();
		}
	}
	
	/*public static void main(String[] args)
	{
		pongRevWindow pr = new pongRevWindow();
		pr.addWindowListener(new WindowAdapter()
		{
			 public void windowClosing( WindowEvent e )
             {
             System.exit( 0 );
             }
		});
	}*/

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

