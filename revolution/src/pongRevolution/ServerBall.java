package pongRevolution;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import network.TPaddle;
import network.TPlayer;
import network.TPowerUp;
import network.TBall;
import network.TPosition;

public class ServerBall {
	private static final int NUM_POSITIONS = 20;
	
	private double vx, vy;
	private double x, y;
	private double t;
	
	private int combo, rehit;
	private TPlayer lastHit;
	private TBall tball;
	
	private TPowerUp power;
	
	private ArrayList<TPosition> prevPositions;
	
	public ServerBall() {
		x = 0;
		y = 0;
		combo = 0;
		rehit = 0;
		prevPositions = new ArrayList<TPosition>();
		
		if(GameSettings.WITH_POWERUPS && Math.random() < GameSettings.POWERUP_SPAWN_RATE) {
			power = getRandomPowerup();
		}
		else {
			power = TPowerUp.NONE;
		}
		
		tball = new TBall(new ArrayList<TPosition>(), power, TPlayer.NONE, false);
		addPosition(x, y);
		updatePosition();
		
		// Random direction
		t = GameSettings.BALL_SPAWN_DIRECTION == GameSettings.RANDOM_DIRECTION ? Math.random() * 360 : GameSettings.BALL_SPAWN_DIRECTION;
		
		updateVelocity();
	}
	
	public ServerBall(double angle) {
		this();
		double dif = Math.random() * 2 * GameSettings.BALL_SPAWN_RANGE - GameSettings.BALL_SPAWN_RANGE;
		t = angle + dif;
		updateVelocity();
	}
	
	private TPowerUp getRandomPowerup() {
		int num = (int)(Math.random() * 2) + 1;
		return TPowerUp.findByValue(num);
	}
	
	public void updatePosition() {
		List<TPosition> positions = new ArrayList<TPosition>();
		int[] comboSlot = GameSettings.COMBO_SLOTS[combo];
		if(combo == 0) {
			positions.add(prevPositions.get(0));
		}
		else {
			for(int i = 0; i < combo; i++) {
				positions.add(prevPositions.get(comboSlot[i]));
			}
		}
		
		tball.positions = positions;
	}
	
	private void updateVelocity() {
		double speed = GameSettings.COMBO_SPEED[combo];
		vx = speed * Math.cos(Math.toRadians(t));
		vy = speed * Math.sin(Math.toRadians(t));
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getAngle() {
		return t;
	}
	
	public int getCombo() {
		return combo;
	}

	public TPlayer getLastHit() {
		return lastHit;
	}
	
	public boolean canHit(TPlayer player) {
//		return player != lastHit || rehit == 0;
		return rehit == 0;
	}

	public void setLastHit(ServerPaddle paddle) {
		TPlayer player = paddle.getPlayer();
		if(GameSettings.isRed(lastHit) == GameSettings.isRed(player)) {
			increaseCombo();
		}
		else {
			resetCombo();
		}
		lastHit = player;
		tball.player = player;
		rehit = GameSettings.BALL_REHIT_TIME;
		if(power != TPowerUp.NONE) {
			paddle.setPowerup(power);
		}
	}

	public void setAngle(double t) {
		this.t = t;
		updateVelocity();
	}
	
	public void move() {
		x += vx;
		y += vy;
		addPosition(x, y);		
		if(rehit > 0) {
			rehit--;
		}
		updatePosition();
	}
	
	private void addPosition(double x, double y) {
		TPosition pos = new TPosition(x, y);
		prevPositions.add(0, pos);
		if(prevPositions.size() > NUM_POSITIONS) {
			prevPositions.remove(prevPositions.size() - 1);
		}
	}
	
	public void increaseCombo() {
		if(combo < 5) {
			combo++;
			updateVelocity();
		}
	}
	
	public void resetCombo() {
		combo = 1;
		updateVelocity();
	}

	public boolean isOutsideArena() {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) > GameSettings.ARENA_RADIUS + GameSettings.BALL_OUT_BUFFER;
	}
	
	/**
	 * Checks if the ball contains a point
	 * @param p the point
	 */
	public boolean contains(Point2D p) {
		return (Math.sqrt(Math.pow(x - p.getX(), 2) + Math.pow(y - p.getY(), 2)) <= GameSettings.BALL_RADIUS);
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