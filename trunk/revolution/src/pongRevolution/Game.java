package pongRevolution;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import network.TBall;
import network.TCollision;
import network.TDirection;
import network.TGameState;
import network.TPaddle;
import network.TPlayer;
import network.TPosition;
import network.TPowerUp;

public class Game {	
	private enum Status {
		WAITING, FIVE, FOUR, THREE, TWO, ONE, PLAYING, WIN
	}
	
	private List<ServerBall> ballList, ballListCopy;
	private List<TBall> ballsOut;
	private List<TCollision> collisions;
	private ServerPaddle[] paddleArray;
	private int redScore, blueScore;
	private int ballSpawnCount;
	private int numPlayers;
	
	private int collisionCount;
	private int ballCount;
	
	private Point2D[] pointsTest;
	private TGameState state;
	private Status status;
	
	public Game() {
		ballList = Collections.synchronizedList(new ArrayList<ServerBall>());
		ballListCopy = Collections.synchronizedList(new ArrayList<ServerBall>());
		ballsOut = Collections.synchronizedList(new ArrayList<TBall>());
		collisions = Collections.synchronizedList(new ArrayList<TCollision>());
		paddleArray = new ServerPaddle[5];
		numPlayers = 0;
		collisionCount = 1;
		ballCount = 0;
		for(int i = 0; i < paddleArray.length; i++) {
			paddleArray[i] = null;
		}
		resetGame();
	}
	
	public void resetGame() {
		ballList.clear();
		ballListCopy.clear();
		ballsOut.clear();
		collisions.clear();
		redScore = 0;
		blueScore = 0;
		
		ballSpawnCount = GameSettings.GAME_START_DELAY;
		status = Status.WAITING;
	}
	
	public void startGame() {
		resetGame();
		numPlayers = 5;
	}
	
	public void movePaddle(TPlayer requester, TDirection dir) {
		paddleArray[requester.getValue()].setDirection(dir);
	}
	
	public void jumpPaddle(TPlayer requester) {
		paddleArray[requester.getValue()].jump();
	}
	
	public void usePowerup(TPlayer requester) {
		ServerPaddle paddle = paddleArray[requester.getValue()];
		
		if(paddle.getPower().type == TPowerUp.STUN) {
			for(int i = 1; i < paddleArray.length; i++) {
				if(i == requester.getValue()) {
					continue;
				}
				ServerPaddle p = paddleArray[i];
				if(p == null) {
					continue;
				}
				
				if(isInStunRange(paddle, p)) {
					p.stun(paddle.getPower());
				}
			}
		}
		
		paddle.usePowerup();
	}
	
	private boolean isInStunRange(ServerPaddle stunner, ServerPaddle stunee) {
		return getDifference(stunner.getT(), stunee.getT()) < GameSettings.HORIZONTAL_STUN_RANGE &&
				Math.abs(stunner.getR() - stunee.getR()) < GameSettings.VERTICAL_STUN_RANGE;
	}
	
	private double getDifference(double a, double b) {
		if(Math.abs(a - b) <= 180) {
			return Math.abs(a - b);
		}
		else {
			if(a < b) {
				a += 360;
				return a - b;
			}
			else {
				b += 360;
				return b - a;
			}
		}
	}
	
	public TPlayer registerPlayer(TPlayer team) {
		TPlayer player = team;		
		if(GameSettings.isRed(team)) {
			if(paddleArray[TPlayer.RED_ONE.getValue()] == null) {
				player = TPlayer.RED_ONE;
				paddleArray[player.getValue()] = new ServerPaddle(player);
				numPlayers++;
			}
			else if(paddleArray[TPlayer.RED_TWO.getValue()] == null) {
				player = TPlayer.RED_TWO;
				paddleArray[player.getValue()] = new ServerPaddle(player);
				numPlayers++;
			}
			else {
				player = TPlayer.NONE;
			}
		}
		else if(GameSettings.isBlue(team)){
			if(paddleArray[TPlayer.BLUE_ONE.getValue()] == null) {
				player = TPlayer.BLUE_ONE;
				paddleArray[player.getValue()] = new ServerPaddle(player);
				numPlayers++;
			}
			else if(paddleArray[TPlayer.BLUE_TWO.getValue()] == null) {
				player = TPlayer.BLUE_TWO;
				paddleArray[player.getValue()] = new ServerPaddle(player);
				numPlayers++;
			}
			else {
				player = TPlayer.NONE;
			}
		}
		else {
			player = TPlayer.NONE;
		}
		System.out.println("Registed new player: " + player);
		
		return player;
	}
	
	public void spawnBall() {
		ServerBall ball;
		if(GameSettings.SPAWN_BALL_TOWARDS_LOSER) {
			boolean one = Math.random() < 0.5;
			ServerPaddle paddle;
			if(redScore > blueScore) {
				if(one) {
					paddle = paddleArray[TPlayer.BLUE_ONE.getValue()];
				}
				else {
					paddle = paddleArray[TPlayer.BLUE_TWO.getValue()];
				}
			}
			else if(redScore < blueScore) {
				if(one) {
					paddle = paddleArray[TPlayer.RED_ONE.getValue()];
				}
				else {
					paddle = paddleArray[TPlayer.RED_TWO.getValue()];
				}
			}
			else {
				paddle = null;
			}
			
			if(paddle == null) {
				ball = new ServerBall(ballCount);
			}
			else {
				ball = new ServerBall(ballCount, paddle.getT());
			}
		}
		else {
			ball = new ServerBall(ballCount);
		}
		
		ballList.add(ball);
	}
	
	private void nextBallID() {
		ballCount = (ballCount / 10 + 1) * 10;
		if(GameSettings.ENABLE_POWERUPS && Math.random() < GameSettings.POWERUP_SPAWN_RATE) {
			int pow = (int)(Math.random() * 3) + 1;
			ballCount += pow;
		}
	}
	
	public void updateGame() {
		movePaddles();
		if(redScore >= GameSettings.POINTS_FOR_WIN || blueScore >= GameSettings.POINTS_FOR_WIN) {
			status = Status.WIN;
			numPlayers = -1;
			ballList.clear();
			ballListCopy.clear();
		}
		
		if(numPlayers >= 4) {
			moveBalls();
			ballSpawnCount--;
			if((status == Status.WAITING || status == Status.WIN) && ballSpawnCount < 5000 / GameSettings.CLOCK_INTERVAL) {
				status = Status.FIVE;
			}
			else if(status == Status.FIVE && ballSpawnCount < 4000 / GameSettings.CLOCK_INTERVAL) {
				status = Status.FOUR;
			}
			else if(status == Status.FOUR && ballSpawnCount < 3000 / GameSettings.CLOCK_INTERVAL) {
				status = Status.THREE;
			}
			else if(status == Status.THREE && ballSpawnCount < 2000 / GameSettings.CLOCK_INTERVAL) {
				status = Status.TWO;
			}
			if(status == Status.TWO && ballSpawnCount < 1000 / GameSettings.CLOCK_INTERVAL) {
				status = Status.ONE;
			}
			
			if(ballSpawnCount < 0) {
				if(status == Status.ONE){
					status = Status.PLAYING;
				}
				spawnBall();
				ballSpawnCount = GameSettings.BALL_RELEASE_INTERVAL;
			}
			else if(ballSpawnCount == GameSettings.BALL_SPAWN_WARNING) {
				nextBallID();
			}
		}
		
		ballListCopy.clear();
		ballListCopy.addAll(ballList);
		
		List<TCollision> garbage = new ArrayList<TCollision>();
		for(TCollision c : collisions) {
			if(c.decay > 0) {
				c.decay--;
			}
			else {
				garbage.add(c);
			}
		}
		for(TCollision c : garbage) {
			collisions.remove(c);
		}
		
		makeGameState();
	}
	
	/**
	 * Updates the balls to their next position
	 */
	public void moveBalls() {
		List<TBall> tgarbage = new ArrayList<TBall>();
		for(TBall ball : ballsOut) {
			if(ball.decay <= 0) {
				tgarbage.add(ball);
			}
			else {
				ball.decay--;
			}
		}
		for(TBall ball : tgarbage) {
			ballsOut.remove(ball);
		}
		
		List<ServerBall> garbage = new ArrayList<ServerBall>();
		for(ServerBall ball : ballList) {
			ball.move();
			if(ball.isOutsideArena()) {
				garbage.add(ball);
			}
		}
		for(ServerBall ball : garbage) {
			if(GameSettings.isRed(ball.getLastHit())) {
				redScore += GameSettings.COMBO_SCORE[ball.getCombo()];
			}
			else if(GameSettings.isBlue(ball.getLastHit())) {
				blueScore += GameSettings.COMBO_SCORE[ball.getCombo()];
			}
			ballList.remove(ball);
			TBall tball = ball.getTball();
			tball.decay = GameSettings.BALL_DECAY_TIME;
			ballsOut.add(tball);
		}
		
		checkCollision();
	}
	
	/**
	 * Updates the paddles to their next position (up/down-wise)
	 */
	public void movePaddles() {
		for(int i = 1; i < paddleArray.length; i++) {
			if(paddleArray[i] == null) {
				continue;
			}
			paddleArray[i].move();
		}
	}
	
	/**
	 * Checks for collision
	 */
	public void checkCollision() {
		for (int i = 1; i < 5; i++) {
			if(paddleArray[i] == null) {
				continue;
			}
			paddleArray[i].getBounds();
			pointsTest = paddleArray[i].getBoundPoints();
			//System.out.println("Paddle: (" + paddleArray[i].getX() + "," + paddleArray[i].getY() + ")");
			for (ServerBall ball : ballList) {
				if(!ball.canHit(paddleArray[i].getPlayer())) {
					continue;
				}
				
				Point2D[] points = paddleArray[i].getConnectionPoints(new Point2D.Double(ball.getX(), ball.getY()));
//				pointsTest = paddleArray[i].getConnectionPoints(new Point2D.Double(ball.getX(), ball.getY()));
				double paddleDiagonal = Math.sqrt(Math.pow(GameSettings.PADDLE_HEIGHT / 2, 2) + Math.pow(GameSettings.PADDLE_LENGTH / 2, 2));
				double paddleDiagonal2 = Math.sqrt(Math.pow(GameSettings.PADDLE_HEIGHT / 2, 2) + Math.pow(GameSettings.PADDLE_TOP / 2, 2));
				
				if (ball.contains(points[0])) {
					if (points[0].distance(new Point2D.Double(paddleArray[i].getX(), paddleArray[i].getY())) <= paddleDiagonal2) {
						collide(ball, paddleArray[i], 180 + 2 * paddleArray[i].getT() - ball.getAngle());
					}
				} else if (ball.contains(points[1])) {
					if (points[1].distance(new Point2D.Double(paddleArray[i].getX(), paddleArray[i].getY())) <= paddleDiagonal) {
						collide(ball, paddleArray[i], 180 + 2 * paddleArray[i].getT() - ball.getAngle());
					}
				} else if (ball.contains(points[2])) {
					if (points[2].distance(new Point2D.Double(paddleArray[i].getX(), paddleArray[i].getY())) <= paddleDiagonal) {
						if (ball.getX() <= points[0].getX() && ball.getX() >= points[1].getX() || ball.getX() >= points[0].getX() && ball.getX() <= points[1].getX()) {
							if (ball.getY() <= points[0].getY() && ball.getY() >= points[1].getY() || ball.getY() >= points[0].getY() && ball.getY() <= points[1].getY()) {		
								double angle = GameSettings.PADDLE_BOUNCE_ANGLE;
								if (GameSettings.ENABLE_DEFAULT_BOUNCE_ANGLE) {
									angle = Math.atan(2 * GameSettings.PADDLE_HEIGHT / (GameSettings.PADDLE_LENGTH - GameSettings.PADDLE_TOP));
								}
								double difference = paddleArray[i].getT() + 180 + Math.toDegrees(angle) - (ball.getAngle() - 180);
								if (difference > 180) {
									difference = 360 - difference;
								}
								collide(ball, paddleArray[i], (ball.getAngle() - 180) + 2 * Math.abs(difference));
							}
						}
					}
				} else if (ball.contains(points[3])) {
					if (points[3].distance(new Point2D.Double(paddleArray[i].getX(), paddleArray[i].getY())) <= paddleDiagonal) {
						if (ball.getX() <= points[0].getX() && ball.getX() >= points[1].getX() || ball.getX() >= points[0].getX() && ball.getX() <= points[1].getX()) {
							if (ball.getY() <= points[0].getY() && ball.getY() >= points[1].getY() || ball.getY() >= points[0].getY() && ball.getY() <= points[1].getY()) {		
								double angle = GameSettings.PADDLE_BOUNCE_ANGLE;
								if (GameSettings.ENABLE_DEFAULT_BOUNCE_ANGLE) {
									angle = Math.atan(2 * GameSettings.PADDLE_HEIGHT / (GameSettings.PADDLE_LENGTH - GameSettings.PADDLE_TOP));
								}
								double difference = paddleArray[i].getT() - 180 - Math.toDegrees(angle) - (ball.getAngle() - 180);
								if (difference > 180) {
									difference = 360 - difference;
								}
								collide(ball, paddleArray[i], (ball.getAngle() - 180) - 2 * Math.abs(difference));
							}
						}
					}
				}
			}
		}
	}
	
	private void collide(ServerBall ball, ServerPaddle paddle, double angle) {
		ball.setAngle(angle);
		ball.setLastHit(paddle);
		collisions.add(new TCollision(paddle.getPlayer(), ball.getCombo(), collisionCount++	, GameSettings.COLLISION_DECAY_TIME));
	}
	
	private void makeGameState() {
		List<TPaddle> paddles = new ArrayList<TPaddle>();
		for(int i = 0; i < 5; i++) {
			ServerPaddle paddle = paddleArray[i];
			if(paddle != null) {
				paddles.add(paddle.getTPaddle());
			}
			else {
				paddles.add(new TPaddle());
			}
		}
		
		List<TBall> balls = new ArrayList<TBall>();
		
		for(ServerBall ball : ballListCopy) {
			balls.add(ball.getTball());
		}
		
		List<TPosition> connections = new ArrayList<TPosition>();
		if (pointsTest!= null) {
			connections.add(new TPosition(pointsTest[0].getX(), pointsTest[0].getY()));
			connections.add(new TPosition(pointsTest[1].getX(), pointsTest[1].getY()));
			connections.add(new TPosition(pointsTest[2].getX(), pointsTest[2].getY()));
			connections.add(new TPosition(pointsTest[3].getX(), pointsTest[3].getY()));
		}
		
		List<String> messageList = new ArrayList<String>();
		switch(status) {
		case WIN:
			if(redScore > blueScore) {
				messageList.add("red");
			}
			else {
				messageList.add("blue");
			}
			break;
		case WAITING:
			messageList.add("waiting");
			break;
		case FIVE:
			messageList.add("5");
			break;
		case FOUR:
			messageList.add("4");
			break;
		case THREE:
			messageList.add("3");
			break;
		case TWO:
			messageList.add("2");
			break;
		case ONE:
			messageList.add("1");
			break;
		}
		
		state = new TGameState(paddles, balls, redScore, blueScore, ballCount, ballsOut, collisions, connections, messageList);

		synchronized(this) {
			state = new TGameState(paddles, balls, redScore, blueScore, ballCount, ballsOut, collisions, connections, messageList);
		}
	}
	
	public TGameState getState(TPlayer requester) {
		synchronized(this) {
			return state;
		}
	}
}
