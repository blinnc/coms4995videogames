package pongRevolution;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import network.TBall;
import network.TDirection;
import network.TGameState;
import network.TPaddle;
import network.TPlayer;
import network.TPosition;
import network.TPowerUp;

public class Game {	
	private List<ServerBall> ballList, ballListCopy;
	private ServerPaddle[] paddleArray;
	private int redScore, blueScore;
	private int ballSpawnCount;
	private int numPlayers;
	private Point2D[] pointsTest;
	
	public Game() {
		ballList = new ArrayList<ServerBall>();
		ballListCopy = new ArrayList<ServerBall>();
		paddleArray = new ServerPaddle[5];
		numPlayers = 0;
		for(int i = 0; i < paddleArray.length; i++) {
			paddleArray[i] = null;
		}
		resetGame();
	}
	
	public void resetGame() {
		ballList.clear();
		redScore = 0;
		blueScore = 0;
		ballSpawnCount = GameSettings.GAME_START_DELAY;
	}
	
	public void startGame() {
		numPlayers = 5;
	}
	
	public void movePaddle(TPlayer requester, TDirection dir) {
		paddleArray[requester.getValue()].setDirection(dir);
	}
	
	public void jumpPaddle(TPlayer requester) {
		paddleArray[requester.getValue()].jump();
	}
	
	public void usePowerup(TPlayer requester) {
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
		if(GameSettings.SPAWN_TOWARDS_LOSER) {
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
				ball = new ServerBall();
			}
			else {
				ball = new ServerBall(paddle.getT());
			}
		}
		else {
			ball = new ServerBall();
		}
		
		ballList.add(ball);
	}
	
	public void updateGame() {
		ballSpawnCount --;
		movePaddles();
		moveBalls();
		if(ballSpawnCount < 0 && numPlayers >= 4) {
			spawnBall();
			ballSpawnCount = GameSettings.BALL_RELEASE_INTERVAL;
		}
		synchronized (this) {
			ballListCopy.clear();
			ballListCopy.addAll(ballList);
		}
	}
	
	/**
	 * Updates the balls to their next position
	 */
	public void moveBalls() {
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
						ball.setAngle(180 + 2 * paddleArray[i].getT() - ball.getAngle());
						ball.setLastHit(paddleArray[i].getPlayer());
					}
				} else if (ball.contains(points[1])) {
					if (points[1].distance(new Point2D.Double(paddleArray[i].getX(), paddleArray[i].getY())) <= paddleDiagonal) {
						ball.setAngle(180 + 2 * paddleArray[i].getT() - ball.getAngle());
						ball.setLastHit(paddleArray[i].getPlayer());
					}
				} else if (ball.contains(points[2])) {
					if (points[2].distance(new Point2D.Double(paddleArray[i].getX(), paddleArray[i].getY())) <= paddleDiagonal) {
						if (ball.getX() <= points[0].getX() && ball.getX() >= points[1].getX() || ball.getX() >= points[0].getX() && ball.getX() <= points[1].getX()) {
							if (ball.getY() <= points[0].getY() && ball.getY() >= points[1].getY() || ball.getY() >= points[0].getY() && ball.getY() <= points[1].getY()) {		
								double angle = GameSettings.PADDLE_BOUNCE_ANGLE;
								if (GameSettings.DEFAULT_BOUNCE_ANGLE) {
									angle = Math.atan(2 * GameSettings.PADDLE_HEIGHT / (GameSettings.PADDLE_LENGTH - GameSettings.PADDLE_TOP));
								}
								double difference = paddleArray[i].getT() + 180 + Math.toDegrees(angle) - (ball.getAngle() - 180);
								if (difference > 180) {
									difference = 360 - difference;
								}
								ball.setAngle((ball.getAngle() - 180) + 2 * Math.abs(difference));
								ball.setLastHit(paddleArray[i].getPlayer());
							}
						}
					}
				} else if (ball.contains(points[3])) {
					if (points[3].distance(new Point2D.Double(paddleArray[i].getX(), paddleArray[i].getY())) <= paddleDiagonal) {
						if (ball.getX() <= points[0].getX() && ball.getX() >= points[1].getX() || ball.getX() >= points[0].getX() && ball.getX() <= points[1].getX()) {
							if (ball.getY() <= points[0].getY() && ball.getY() >= points[1].getY() || ball.getY() >= points[0].getY() && ball.getY() <= points[1].getY()) {		
								double angle = GameSettings.PADDLE_BOUNCE_ANGLE;
								if (GameSettings.DEFAULT_BOUNCE_ANGLE) {
									angle = Math.atan(2 * GameSettings.PADDLE_HEIGHT / (GameSettings.PADDLE_LENGTH - GameSettings.PADDLE_TOP));
								}
								double difference = paddleArray[i].getT() - 180 - Math.toDegrees(angle) - (ball.getAngle() - 180);
								if (difference > 180) {
									difference = 360 - difference;
								}
								ball.setAngle((ball.getAngle() - 180) - 2 * Math.abs(difference));
								ball.setLastHit(paddleArray[i].getPlayer());
							}
						}
					}
				}
			}
		}
	}
	
	public TGameState getState() {
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
		
		synchronized (this) {
			for(ServerBall ball : ballListCopy) {
				balls.add(ball.getTball());
			}
		}
		
		List<TPosition> connections = new ArrayList<TPosition>();
		if (pointsTest!= null) {
			connections.add(new TPosition(pointsTest[0].getX(), pointsTest[0].getY()));
			connections.add(new TPosition(pointsTest[1].getX(), pointsTest[1].getY()));
			connections.add(new TPosition(pointsTest[2].getX(), pointsTest[2].getY()));
			connections.add(new TPosition(pointsTest[3].getX(), pointsTest[3].getY()));
		}
		
		
		return new TGameState(paddles,balls,redScore,blueScore,false,false,false,TPowerUp.NONE,TPowerUp.NONE, connections, new ArrayList<String>());
	}
}
