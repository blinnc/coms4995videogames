package pongRevolution;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import network.TPlayer;
import network.TPower;
import network.TPowerUp;
import network.TBall;
import network.TPosition;

public class ServerBall {
	private static final int NUM_POSITIONS = 20;
	
	private int id;
	private double vx, vy;
	private double x, y;
	private double t;
	
	private int combo, rehit;
	private TPlayer lastHit;
	private TBall tball;
	
	private TPower power;
	
	private ArrayList<TPosition> prevPositions;
	
	public ServerBall(int id) {
		x = 0;
		y = 0;
		combo = 0;
		rehit = 0;
		prevPositions = new ArrayList<TPosition>();
		
		t = GameSettings.BALL_SPAWN_DIRECTION == GameSettings.RANDOM_DIRECTION ? Math.random() * 360 : GameSettings.BALL_SPAWN_DIRECTION;
		
		TPowerUp powerup = TPowerUp.findByValue(id % 10);
		this.id = id;
		power = new TPower(this.id, powerup, -1);
		
		tball = new TBall(new ArrayList<TPosition>(), power, TPlayer.NONE, this.id, t, -1);
		addPosition(x, y);
		updatePosition();
		
		updateVelocity();
	}
	
	public ServerBall(int id, double angle) {
		this(id);
		if(Math.random() < 0.8) {
			double dif = Math.random() * 2 * GameSettings.BALL_SPAWN_RANGE - GameSettings.BALL_SPAWN_RANGE;
			t = (angle + dif) % 360;
			tball.angle = t;
			updateVelocity();
		}
	}
	
	public void updatePosition() {
		List<TPosition> positions = new ArrayList<TPosition>();
		positions.clear();
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
		if(power.type != TPowerUp.NONE) {
			paddle.setPowerup(power);
			power = GameSettings.getNullPower();
			tball.store = power;
		}
		
	}

	public void setAngle(double t) {
		if(Math.abs(t) >= 360) {
			t = t % 360;
			if(t < 0) {
				t += 360;
			}
		}
		this.t = t;
		tball.angle = t;
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