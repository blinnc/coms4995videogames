package pongRevolution;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import network.TPlayer;
import network.TPowerUp;
import network.TBall;
import network.TPosition;

public class ServerBall {
	private double vx, vy;
	private double x, y;
	private double t;
	
	private int combo;
	private TPlayer lastHit;
	private TPowerUp powerup;
	private TBall tball;
	
	public ServerBall() {
		x = 0;
		y = 0;
		combo = 0;
		
		tball = new TBall(new ArrayList<TPosition>(), TPowerUp.NONE, TPlayer.NONE, false);
		
		// Random direction
		// t = Math.random() * 360;
		t = 135;
		updateVelocity();
		
		// Random powerup
		int pu = (int)(Math.random() * 8) + 1;
		powerup = TPowerUp.findByValue(pu);
		
		updatePosition();
	}
	
	public void updatePosition() {
		// TODO: fix this
		TPosition pos = new TPosition(x, y);
		List<TPosition> positions = new ArrayList<TPosition>();
		positions.add(pos);
		tball.positions = positions;
	}
	
	private void updateVelocity() {
		double speed = GameSettings.COMBO_SPEED[combo];
		vx = speed * Math.cos(Math.toRadians(t));
		vy = speed * Math.sin(Math.toRadians(t));
		System.out.println(t + ", " + vx + ", " + vy);
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
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getT() {
		return t;
	}
	
	public double getR() {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
	
	public void setT(double t) {
		this.t = t;
		updateVelocity();
	}
	
	public void move() {
		x += vx;
		y += vy;
		updatePosition();
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
		return getR() > GameSettings.ARENA_RADIUS + 10;
	}
	
	/**
	 * Checks if the ball contains a point
	 * @param p the point
	 */
	public boolean contains(Point2D p) {
		return (Math.sqrt(Math.pow(x - p.getX(), 2) + Math.pow(y - p.getY(), 2)) <= GameSettings.BALL_RADIUS);
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