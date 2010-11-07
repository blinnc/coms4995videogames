package pongRevolution;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import network.TPlayer;
import network.TPowerUp;
import network.TBall;
import network.TPosition;

public class ServerBall {
	private double vx, vy;
	private int x, y;
	private double t;
	
	private int combo;
	private TPlayer lastHit;
	private TPowerUp powerup;
	private TBall tball;
	
	public ServerBall() {
		x = 0;
		y = 0;
		combo = 0;
		
		// Random direction
		t = Math.random() * 2 * Math.PI;
		updateVelocity();
		
		// Random powerup
		int pu = (int)(Math.random() * 8) + 1;
		powerup = TPowerUp.findByValue(pu);
		
		TBall tball = new TBall();
		
		updatePosition();
	}
	
	public void updatePosition() {
		TPosition pos = new TPosition();
		pos.setXPos(x);
		pos.setYPos(y);
		tball.addToPositions(pos);
	}
	
	private void updateVelocity() {
		double speed = GameSettings.COMBO_SPEED[combo];
		vx = speed * Math.cos(t);
		vy = speed * Math.sin(t);
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
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public double getT() {
		return t;
	}
	
	public void setT(double t) {
		this.t = t;
		updateVelocity();
	}
	
	public void move() {
		x += vx;
		y += vy;
		updatePosition();
			// TODO: change color
			// TODO: change last hit
			// TODO: change combo
			// TODO: transfer powerup to player
	}
	
	public void increaseCombo() {
		combo++;
		updateVelocity();
	}
	
	public void resetCombo() {
		combo = 1;
		updateVelocity();
	}

	public boolean isOutsideArena() {
		if (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) >= GameSettings.ARENA_RADIUS) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Checks if the ball contains a point
	 * @param p the point
	 */
	public boolean contains(Point2D p) {
		Ellipse2D circle = new Ellipse2D.Double(x, y, 2 * GameSettings.BALL_RADIUS, 2 * GameSettings.BALL_RADIUS);
		return circle.contains(p);
	}

	/**
	 * Gets the bounds of the ball
	 * 
	 * @return the rectangle containing the bounds
	 */
	public Rectangle2D getBounds() {
		return new Rectangle2D.Double(x, y, 2 * GameSettings.BALL_RADIUS, 2 * GameSettings.BALL_RADIUS);
	}
	
	/**
	 * Gets the optimal connection point on the ball.
	 * @param other the other point that connects to the ball
	 */
	public Point2D getConnectionPoint(Point2D other) {
		double dx = other.getX() - x;
		double dy = other.getY() - y;
		double distance = Math.sqrt(dx * dx + dy * dy);
		if (distance == 0) {
			return other;
		} else {
			return new Point2D.Double(x + dx * GameSettings.BALL_RADIUS / distance, y + dy * GameSettings.BALL_RADIUS / distance);
		}
	}

	public TBall getTball() {
		return tball;
	}
}