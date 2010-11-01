package pongRevolution;

import java.awt.Polygon;
import java.awt.geom.Point2D;

public class ServerPaddle {
	double t;
	double r;
	double vr;
	boolean isInvisible;
	
	public Paddle paddle;
	
	public ServerPaddle() {
		t = 0;
		r = 0;
		vr = 0;
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
	 * Gets the radial velocity.
	 * @return the current radial velocity
	 */
	public double getVR() {
		return vr;
	}

	/**
	 * Checks if the paddle contains a point
	 * @param p the point
	 */
	public boolean contains(Point2D p) {
		Polygon polygon = getBounds();
		return polygon.contains(p);
	}

	/**
	 * Gets the bounds of the paddle
	 * 
	 * @return the polygon containing the bounds
	 */
	public Polygon getBounds() {
		int[] xpoints = new int[4];
		xpoints[0] = (int) (r * Math.cos(t) + GameSettings.PADDLE_LENGTH / 2 * Math.sin(t) + GameSettings.PADDLE_HEIGHT / 2 * Math.cos(t));
		xpoints[1] = (int) (r * Math.cos(t) - GameSettings.PADDLE_LENGTH / 2 * Math.sin(t) + GameSettings.PADDLE_HEIGHT / 2 * Math.cos(t));
		xpoints[2] = (int) (r * Math.cos(t) - GameSettings.PADDLE_LENGTH / 2 * Math.sin(t) - GameSettings.PADDLE_HEIGHT / 2 * Math.cos(t));
		xpoints[3] = (int) (r * Math.cos(t) + GameSettings.PADDLE_LENGTH / 2 * Math.sin(t) - GameSettings.PADDLE_HEIGHT / 2 * Math.cos(t));
		int[] ypoints = new int[4];
		ypoints[0] = (int) (r * Math.sin(t) + GameSettings.PADDLE_LENGTH / 2 * Math.cos(t) - GameSettings.PADDLE_HEIGHT / 2 * Math.sin(t));
		ypoints[1] = (int) (r * Math.sin(t) + GameSettings.PADDLE_LENGTH / 2 * Math.cos(t) + GameSettings.PADDLE_HEIGHT / 2 * Math.sin(t));
		ypoints[2] = (int) (r * Math.sin(t) - GameSettings.PADDLE_LENGTH / 2 * Math.cos(t) + GameSettings.PADDLE_HEIGHT / 2 * Math.sin(t));
		ypoints[3] = (int) (r * Math.sin(t) - GameSettings.PADDLE_LENGTH / 2 * Math.cos(t) - GameSettings.PADDLE_HEIGHT / 2 * Math.sin(t));
		Polygon polygon = new Polygon(xpoints, ypoints, 4);
		return polygon;
	}
	
	/**
	 * Gets the optimal connection point on the paddle.
	 * @param other the other point that connects to the paddle
	 */
	public Point2D getConnectionPoint(Point2D other) {
		double dx = other.getX() - r * Math.cos(t);
		double dy = other.getY() - r * Math.sin(t);
		double distance = Math.sqrt(dx * dx + dy * dy);
		if (distance == 0) {
			return other;
		} else {
			
			return new Point2D.Double(r * Math.cos(t) + dx * r / distance, r * Math.sin(t) + dy * r / distance);
		}
	}
	
}