package pongRevolution;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class ServerBall {
	double radius;
	double vx, vy;
	double x, y;
	int multiplier;
	boolean isShadow;
	
	Powerup powerup;
	
	public ServerBall() {
		x = 0;
		y = 0;
		vx = 0;
		vy = 0;
	}
	
	/**
	 * Draws the ball
	 */
	public void draw(Graphics2D g2) {
		Ellipse2D circle = new Ellipse2D.Double(x, y, radius, radius);
		Color oldColor = g2.getColor();
		g2.setColor(Color.DARK_GRAY);
		g2.fill(circle);
		g2.setColor(oldColor);
		g2.draw(circle);
	}
	
	/**
	 * Translate the ball.
	 */
	public void translate(double dx, double dy) {
		x += dx;
		y += dy;
	}
	
	/**
	 * Sets the horizontal velocity.
	 * @param vx the new horizontal velocity
	 */
	public void setVX(double vx) {
		this.vx = vx;
	}
	
	/**
	 * Sets the vertical velocity.
	 * @param vy the new vertical velocity
	 */
	public void setVY(double vy) {
		this.vy = vy;
	}
	
	/**
	 * Gets the horizontal velocity.
	 * @return the current horizontal velocity
	 */
	public double getVX() {
		return vx;
	}
	
	/**
	 * Gets the vertical velocity.
	 * @return the current vertical velocity
	 */
	public double getVY() {
		return vy;
	}

	/**
	 * Checks if the ball contains a point
	 * @param p the point
	 */
	public boolean contains(Point2D p) {
		Ellipse2D circle = new Ellipse2D.Double(x, y, radius, radius);
		return circle.contains(p);
	}

	/**
	 * Gets the bounds of the ball
	 * 
	 * @return the rectangle containing the bounds
	 */
	public Rectangle2D getBounds() {
		return new Rectangle2D.Double(x, y, radius, radius);
	}
	
}
