package pongRevolution;

import java.awt.geom.Point2D;

import network.TDirection;
import network.TPaddle;
import network.TPlayer;
import network.TPowerUp;

public class ServerPaddle {
	double t;
	double r;
	double vt;
	double vr;
	
	double[] xpoints;
	double[] ypoints;
	
	private double length;
	private int jumpCounter;
	private int powerCounter;
	
	private boolean isJumping;
	
	private TPlayer player;
	private TPaddle tPaddle;
	private TPowerUp power;
	
	private TDirection direction;
	
	public ServerPaddle(TPlayer player) {
		this.player = player;
		getBounds();
		t = GameSettings.STARTING_POSITIONS[player.getValue()];
		r = GameSettings.ARENA_RADIUS;
		vt = GameSettings.PADDLE_VELOCITY;
		direction = TDirection.NONE;
		isJumping = false;
		jumpCounter = 0;
		power = TPowerUp.NONE;
		powerCounter = -1;
		
		tPaddle = new TPaddle(r, t, length, player, false, false, false);
	}
	
	public double getX() {
		return r * Math.cos(Math.toRadians(t));
	}
	
	public double getY() {
		return r * Math.sin(Math.toRadians(t));
	}
	
	public double getT() {
		return t;
	}
	
	public double getR() {
		return r;
	}

	public double getVT() {
		return vt;
	}
	
	public double getLength() {
		return length;
	}
	
	public TPlayer getPlayer() {
		return player;
	}
	
	public TPaddle getTPaddle() {
		return tPaddle;
	}

	public void setDirection(TDirection direction) {
		this.direction = direction;
	}

	public void move() {
		if(isJumping) {
			jumpCounter++;
			r = GameSettings.ARENA_RADIUS - getJumpHeight();
			tPaddle.setRadius(r);
		}
		
		if(powerCounter > 0) {
			powerCounter--;
		}
		else if(powerCounter == 0){
			restore();
		}
		
		if(direction == TDirection.NONE) {
			return;
		}
		
		boolean clockwise = direction == TDirection.LEFT;
		
		t = clockwise ? t - vt : t + vt;
		if(t > 360) {
			t = t % 360;
		}
		else if(t < 0) {
			t = 360 + t; 
		}
		tPaddle.setAngle(t);
	}
	
	public void jump() {
		if(!isJumping) {
			isJumping = true;
			jumpCounter = 0;
		}
	}
	
	public void setPowerup(TPowerUp p) {
		power = p;
		System.out.println(power);
	}
	
	public void usePowerup() {
		if(TPowerUp.SPEED == power) {
			setSpeedUp(true);
			tPaddle.isSpeedup = true;
			powerCounter = GameSettings.POWERUP_TIME[TPowerUp.SPEED.getValue()];
		}
		else if(TPowerUp.INVIS == power) {
			tPaddle.isInvisible = true;
			powerCounter = GameSettings.POWERUP_TIME[TPowerUp.INVIS.getValue()];
		}
	}
	
	private void restore() {
		tPaddle.isSpeedup = false;
		setSpeedUp(false);
		
		tPaddle.isInvisible = false;
		
		power = TPowerUp.NONE;
		powerCounter = -1;
	}
	
	private double getJumpHeight() {
		double height = GameSettings.PADDLE_JUMP_INIT_VELOCITY * jumpCounter - GameSettings.PADDLE_JUMP_ACCEL * Math.pow(jumpCounter, 2);
		if(height < 0) {
			height = 0;
			isJumping = false;
		}
		return height;
	}
	
	public void setSpeedUp(boolean enable) {
		vt = enable ? GameSettings.PADDLE_SPEEDUP : GameSettings.PADDLE_VELOCITY;
	}

	/**
	 * Gets the bounds of the paddle
	 */
	public void getBounds() {
		xpoints = new double[4];
		xpoints[0] = (r * Math.cos(Math.toRadians(t)) + GameSettings.PADDLE_LENGTH / 2 * Math.sin(Math.toRadians(t)) + GameSettings.PADDLE_HEIGHT / 2 * Math.cos(Math.toRadians(t)));
		xpoints[1] = (r * Math.cos(Math.toRadians(t)) + GameSettings.PADDLE_TOP / 2 * Math.sin(Math.toRadians(t)) - GameSettings.PADDLE_HEIGHT / 2 * Math.cos(Math.toRadians(t)));
		xpoints[2] = (r * Math.cos(Math.toRadians(t)) - GameSettings.PADDLE_TOP / 2 * Math.sin(Math.toRadians(t)) - GameSettings.PADDLE_HEIGHT / 2 * Math.cos(Math.toRadians(t)));
		xpoints[3] = (r * Math.cos(Math.toRadians(t)) - GameSettings.PADDLE_LENGTH / 2 * Math.sin(Math.toRadians(t)) + GameSettings.PADDLE_HEIGHT / 2 * Math.cos(Math.toRadians(t)));
		ypoints = new double[4];
		ypoints[0] = (r * Math.sin(Math.toRadians(t)) - GameSettings.PADDLE_LENGTH / 2 * Math.cos(Math.toRadians(t)) + GameSettings.PADDLE_HEIGHT / 2 * Math.sin(Math.toRadians(t)));
		ypoints[1] = (r * Math.sin(Math.toRadians(t)) - GameSettings.PADDLE_TOP / 2 * Math.cos(Math.toRadians(t)) - GameSettings.PADDLE_HEIGHT / 2 * Math.sin(Math.toRadians(t)));
		ypoints[2] = (r * Math.sin(Math.toRadians(t)) + GameSettings.PADDLE_TOP / 2 * Math.cos(Math.toRadians(t)) - GameSettings.PADDLE_HEIGHT / 2 * Math.sin(Math.toRadians(t)));
		ypoints[3] = (r * Math.sin(Math.toRadians(t)) + GameSettings.PADDLE_LENGTH / 2 * Math.cos(Math.toRadians(t)) + GameSettings.PADDLE_HEIGHT / 2 * Math.sin(Math.toRadians(t)));
	}
	
	public Point2D[] getBoundPoints() {
		Point2D[] points = new Point2D[4];
		for(int i = 0; i < 4; i++) {
			points[i] = new Point2D.Double(xpoints[i], ypoints[i]);
		}
		return points;
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
		
		// System.out.println("(" + xpoints[0] + ", " + ypoints[0] + "), (" + xpoints[1] + ", " + ypoints[1] + "), ("+ xpoints[2] + ", " + ypoints[2] + "), ("+ xpoints[3] + ", " + ypoints[3] + ")");
		
		double m1 = (y2 - y1) / (x2 - x1);
		double m2 = -1 / m1;
		if (x2 - x1 == 0) {
			m1 = 999999999;
			m2 = 0.00000001;
		} else if (m1 == 0) {
			m1 = 0.00000001;
			m2 = 999999999;
		}
		double b1 = y2 - m1 * x2;
		double b2 = y0 - m1 * x0; 
		double b3 = other.getY() - m2 * other.getX();
		
		double x_0 = (b3 - b1) / (m1 - m2);
		double x_1 = (b3 - b2) / (m1 - m2);
		double y_0 = m1 * x_0 + b1;
		double y_1 = m1 * x_1 + b2;
		
		points[0] = new Point2D.Double(x_0, y_0);
		points[1] = new Point2D.Double(x_1, y_1);
		
		double m3 = (y1 - y0) / (x1 - x0);
		double m4 = -1 / m3;
		if (x1 - x0 == 0) {
			m3 = 999999999;
			m4 = 0.00000001;
		} else if (m3 == 0) {
			m3 = 0.00000001;
			m4 = 999999999;
		}
		double m5 = (y3 - y2) / (x3 - x2);
		double m6 = -1 / m5;
		if (x3 - x2 == 0) {
			m5 = 999999999;
			m6 = 0.00000001;
		} else if (m5 == 0) {
			m5 = 0.00000001;
			m6 = 999999999;
		}
		double b4 = y0 - m3 * x0;
		double b5 = y2 - m5 * x2;
		double b6 = other.getY() - m4 * other.getX();
		double b7 = other.getY() - m6 * other.getX();

		double x_2 = (b6 - b4) / (m3 - m4);
		double x_3 = (b7 - b5) / (m5 - m6);
		double y_2 = m3 * x_2 + b4;
		double y_3 = m5 * x_3 + b5;

		points[2] = new Point2D.Double(x_2, y_2);
		points[3] = new Point2D.Double(x_3, y_3);

		//System.out.println("(" + x_0 + ", " + y_0 + "), (" + x_1 + ", " + y_1 + "), (" + x_2 + ", " + y_2 + "), ("+ x_3 + ", " + y_3 + ")");
		
		return points;
	}
	
}