package pongRevolution;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class ServerPaddle {
	double t;
	double r;
	double vr;
	double ar;
	public static final double LENGTH = 30;
	public static final double HEIGHT = 10;
	boolean isInvisible;
	
	Powerup powerup;
	
	public ServerPaddle() {
		t = 0;
		r = 0;
		vr = 0;
		ar = 0;
	}
	
	/**
	 * Draws the paddle
	 */
	public void draw(Graphics2D g2) {
		Rectangle2D rectangle = new Rectangle2D.Double(r * Math.cos(t), r * Math.sin(t), LENGTH, HEIGHT);
		Color oldColor = g2.getColor();
		g2.setColor(Color.LIGHT_GRAY);
		g2.fill(rectangle);
		g2.setColor(oldColor);
		g2.draw(rectangle);
	}
	
	/**
	 * Translate the paddle.
	 */
	public void translate(double dt, double dr) {
		t += dt;
		r += dr;
	}
	
	/**
	 * Sets the radial velocity.
	 * @param vr the new radial velocity
	 */
	public void setVR(double vr) {
		this.vr = vr;
	}
	
	/**
	 * Sets the radial acceleration.
	 * @param ar the new radial acceleration
	 */
	public void setAR(double ar) {
		this.ar = ar;
	}
	
	/**
	 * Gets the radial velocity.
	 * @return the current radial velocity
	 */
	public double getVR() {
		return vr;
	}
	
	/**
	 * Gets the radial acceleration.
	 * @return the current radial acceleration
	 */
	public double getAR() {
		return ar;
	}

	/**
	 * Checks if the paddle contains a point
	 * @param p the point
	 */
	public boolean contains(Point2D p) {
		Rectangle2D rectangle = new Rectangle2D.Double((r + HEIGHT / 2) * Math.cos(t), (r + HEIGHT / 2) * Math.sin(t), LENGTH, HEIGHT);
		return rectangle.contains(p);
	}

	/**
	 * Gets the bounds of the paddle
	 * 
	 * @return the rectangle containing the bounds
	 */
	public Rectangle2D getBounds() {
		return new Rectangle2D.Double(r * Math.cos(t), r * Math.sin(t), LENGTH, HEIGHT);
	}
		
}