package pongRevolution;

import java.awt.Polygon;
import java.awt.geom.Point2D;

import network.TPlayer;
import network.TPowerUp;

public class ServerPaddle {
	double t;
	double r;
	double vr;
	
	private TPowerUp powerup;
	private TPlayer player;
	
	public ServerPaddle(TPlayer player) {
		this.player = player;
		t = GameSettings.STARTING_POSITIONS[player.getValue()];
		r = GameSettings.ARENA_RADIUS;
		vr = GameSettings.PADDLE_VELOCITY;
	}
	
	/**
	 * Translate the paddle.
	 */
	public void translate(double dt, double dr) {
		t += dt;
		r += dr;
	}
	
	/**
	 * Gets the radial velocity.
	 * @return the current radial velocity
	 */
	public double getVR() {
		return vr;
	}
	
	public void move(boolean clockwise) {
		t = clockwise ? t + vr : t - vr;
		if(t > 2 * Math.PI) {
			t = t % (2 * Math.PI);
		}
	}
	
	public void setSpeedUp(boolean enable) {
		vr = enable ? GameSettings.PADDLE_SPEEDUP : GameSettings.PADDLE_VELOCITY;
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
		double x1 = (int) (r * Math.cos(t) - GameSettings.PADDLE_LENGTH / 2 * Math.sin(t) - GameSettings.PADDLE_HEIGHT / 2 * Math.cos(t));
		double x2 = (int) (r * Math.cos(t) + GameSettings.PADDLE_LENGTH / 2 * Math.sin(t) - GameSettings.PADDLE_HEIGHT / 2 * Math.cos(t));
		double y1 = (int) (r * Math.sin(t) - GameSettings.PADDLE_LENGTH / 2 * Math.cos(t) + GameSettings.PADDLE_HEIGHT / 2 * Math.sin(t));
		double y2 = (int) (r * Math.sin(t) - GameSettings.PADDLE_LENGTH / 2 * Math.cos(t) - GameSettings.PADDLE_HEIGHT / 2 * Math.sin(t));
		double m1 = (y2 - y1) / (x2 - x1);
		double m2 = -1 / m1;
		double b1 = y1 - m1 * x1;
		double b2 = other.getY() - m2 * other.getX();
		double x = (b2 - b1) / (m1 - m2);
		double y = m1 * x + b1;
		return new Point2D.Double(x, y);
	}
	
}