package pongRevolution;

import java.awt.Polygon;
import java.awt.geom.Point2D;

import network.TPlayer;
import network.TPowerUp;

public class ServerPaddle {
	double t;
	double r;
	double vr;
	
	int[] xpoints;
	int[] ypoints;
	
	private TPowerUp powerup;
	private TPlayer player;
	
	public ServerPaddle(TPlayer player) {
		this.player = player;
		getBounds();
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
	
	public double getX() {
		return r * Math.cos(t);
	}
	
	public double getY() {
		return r * Math.sin(t);
	}
	
	public double getT() {
		return t;
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
		xpoints = new int[4];
		xpoints[0] = (int) (r * Math.cos(t) + GameSettings.PADDLE_LENGTH / 2 * Math.sin(t) + GameSettings.PADDLE_HEIGHT / 2 * Math.cos(t));
		xpoints[1] = (int) (r * Math.cos(t) - GameSettings.PADDLE_LENGTH / 2 * Math.sin(t) + GameSettings.PADDLE_HEIGHT / 2 * Math.cos(t));
		xpoints[2] = (int) (r * Math.cos(t) - GameSettings.PADDLE_LENGTH / 2 * Math.sin(t) - GameSettings.PADDLE_HEIGHT / 2 * Math.cos(t));
		xpoints[3] = (int) (r * Math.cos(t) + GameSettings.PADDLE_LENGTH / 2 * Math.sin(t) - GameSettings.PADDLE_HEIGHT / 2 * Math.cos(t));
		ypoints = new int[4];
		ypoints[0] = (int) (r * Math.sin(t) + GameSettings.PADDLE_LENGTH / 2 * Math.cos(t) - GameSettings.PADDLE_HEIGHT / 2 * Math.sin(t));
		ypoints[1] = (int) (r * Math.sin(t) + GameSettings.PADDLE_LENGTH / 2 * Math.cos(t) + GameSettings.PADDLE_HEIGHT / 2 * Math.sin(t));
		ypoints[2] = (int) (r * Math.sin(t) - GameSettings.PADDLE_LENGTH / 2 * Math.cos(t) + GameSettings.PADDLE_HEIGHT / 2 * Math.sin(t));
		ypoints[3] = (int) (r * Math.sin(t) - GameSettings.PADDLE_LENGTH / 2 * Math.cos(t) - GameSettings.PADDLE_HEIGHT / 2 * Math.sin(t));
		Polygon polygon = new Polygon(xpoints, ypoints, 4);
		return polygon;
	}
	
	/**
	 * Gets the optimal horizontal connection point on the paddle.
	 * @param other the other point that connects to the paddle
	 */
	public Point2D[] getConnectionPoints(Point2D other) {
		Point2D[] points = new Point2D[4];
		double x0 = xpoints[0];
		double x1 = xpoints[1];
		double x2 = xpoints[2];
		double x3 = xpoints[3];
		double y0 = ypoints[0];
		double y1 = ypoints[1];
		double y2 = ypoints[2];
		double y3 = ypoints[3];
		
		double m1 = (y2 - y1) / (x2 - x1);
		double m2 = -1 / m1;
		double b1 = y0 - m1 * x0;
		double b2 = y2 - m1 * x2; 
		double b3 = other.getY() - m2 * other.getX();
		
		double x_0 = (b3 - b1) / (m1 - m2);
		double x_1 = (b3 - b2) / (m1 - m2);
		double y_0 = m1 * x_0 + b1;
		double y_1 = m1 * x_1 + b2;
		
		points[0] = new Point2D.Double(x_0, y_0);
		points[1] = new Point2D.Double(x_1, y_1);
		
		double b4 = y0 - m2 * x0;
		double b5 = y2 - m2 * x2;
		double b6 = other.getY() - m1 * other.getX();
		
		double x_2 = (b6 - b4) / (m2 - m1);
		double x_3 = (b6 - b5) / (m2 - m1);
		double y_2 = (b6 - b4) / (m2 - m1);
		double y_3 = (b6 - b5) / (m2 - m1);

		points[2] = new Point2D.Double(x_2, y_2);
		points[3] = new Point2D.Double(x_3, y_3);
		
		return points;
	}
	
}