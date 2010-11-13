package pongRevolution;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import network.TBall;
import network.TGameState;
import network.TPaddle;
import network.TPlayer;
import network.TPowerUp;

public class Game {	
	private List<ServerBall> ballList;
	private ServerPaddle[] paddleArray;
	private int redScore, blueScore;
	private int ballSpawnCount;
	private boolean hasPlayers;
	
	public Game() {
		ballList = new ArrayList<ServerBall>();
		paddleArray = new ServerPaddle[5];
		hasPlayers = false;
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
		// TODO: wipe any paddle attributes
	}
	
	public void movePaddle(TPlayer requester, boolean clockwise) {
		paddleArray[requester.getValue()].move(clockwise);
	}
	
	public void jumpPaddle(TPlayer requester) {
		paddleArray[requester.getValue()].jump();
	}
	
	public void usePowerup(TPlayer requester) {
		// TODO: check which powerup the player has, apply effects, some sort of timer
	}
	
	public TPlayer registerPlayer(TPlayer team) {
		TPlayer player = team;		
		if(team == TPlayer.RED_ONE || team == TPlayer.RED_TWO) {
			if(paddleArray[TPlayer.RED_ONE.getValue()] == null) {
				player = TPlayer.RED_ONE;
				paddleArray[player.getValue()] = new ServerPaddle(player);
			}
			else if(paddleArray[TPlayer.RED_TWO.getValue()] == null) {
				player = TPlayer.RED_TWO;
				paddleArray[player.getValue()] = new ServerPaddle(player);
			}
			else {
				player = TPlayer.NONE;
			}
		}
		else if(team == TPlayer.BLUE_ONE || team == TPlayer.BLUE_TWO){
			if(paddleArray[TPlayer.BLUE_ONE.getValue()] == null) {
				player = TPlayer.BLUE_ONE;
				paddleArray[player.getValue()] = new ServerPaddle(player);
			}
			else if(paddleArray[TPlayer.BLUE_TWO.getValue()] == null) {
				player = TPlayer.BLUE_TWO;
				paddleArray[player.getValue()] = new ServerPaddle(player);
			}
			else {
				player = TPlayer.NONE;
			}
		}
		else {
			player = TPlayer.NONE;
		}
		System.out.println("Registed new player: " + player);
		hasPlayers = player != TPlayer.NONE;
		return player;
	}
	
	public void spawnBall() {
		if(hasPlayers) {
			System.out.println("New ball released");
			ballList.add(new ServerBall());
		}
		System.out.println("New ball released");
		ballList.add(new ServerBall());
	}
	
	public void updateGame() {
		ballSpawnCount --;
		moveBalls();
		if(ballSpawnCount < 0) {
			spawnBall();
			ballSpawnCount = GameSettings.BALL_RELEASE_INTERVAL;
		}
	}
	
	/**
	 * Updates the balls to their next position
	 */
	public void moveBalls() {
		List<ServerBall> garbage = new ArrayList<ServerBall>();
		for(ServerBall ball : ballList) {
			ball.move();
			if(ball.getR() > GameSettings.ARENA_RADIUS) {	// TODO: temporary until we figure out collision
				garbage.add(ball);
			}
			//System.out.println("Ball: (" + ball.getX() + "," + ball.getY() + ")");
		}
		for(ServerBall ball : garbage) {
			ballList.remove(ball);
		}
		checkCollision();
	}
	
	/**
	 * Updates the paddles to their next position (up/down-wise)
	 */
	public void movePaddles() {
		
	}
	
	/**
	 * Checks for collision
	 */
	public void checkCollision() {
		for (int i = 1; i < 5; i++) {
			if(paddleArray[i] == null) {
				continue;
			}
			System.out.println("Paddle: (" + paddleArray[i].getX() + "," + paddleArray[i].getY() + ")");
			System.out.println(paddleArray[i].getT());
			for (ServerBall ball : ballList) {
				Point2D[] points = paddleArray[i].getConnectionPoints(new Point2D.Double(ball.getX(), ball.getY()));
				double paddleDiagonal = Math.sqrt(Math.pow(GameSettings.PADDLE_HEIGHT / 2, 2) + Math.pow(GameSettings.PADDLE_LENGTH / 2, 2));
				double paddleDiagonal2 = Math.sqrt(Math.pow(GameSettings.PADDLE_HEIGHT / 2, 2) + Math.pow(GameSettings.PADDLE_TOP / 2, 2));
				
				if (ball.contains(points[0])) {
					if (points[0].distance(new Point2D.Double(paddleArray[i].getX(), paddleArray[i].getY())) <= paddleDiagonal2) {
						ball.setT(Math.PI + 2 * paddleArray[i].getT() - ball.getT());
					}
				}
				if (ball.contains(points[1])) {
					if (points[1].distance(new Point2D.Double(paddleArray[i].getX(), paddleArray[i].getY())) <= paddleDiagonal) {
						ball.setT(Math.PI + 2 * paddleArray[i].getT() - ball.getT());
					}
				}
				if (ball.contains(points[2])) {
					if (points[2].distance(new Point2D.Double(paddleArray[i].getX(), paddleArray[i].getY())) <= paddleDiagonal) {
						if (!points[0].equals(points[1])) {
							double angle = Math.atan(2 * GameSettings.PADDLE_HEIGHT / (GameSettings.PADDLE_LENGTH - GameSettings.PADDLE_TOP));
							ball.setT(Math.PI + 2 * (paddleArray[i].getT() + Math.PI + angle) - ball.getT());
						}
					}
				}
				if (ball.contains(points[3])) {
					if (points[3].distance(new Point2D.Double(paddleArray[i].getX(), paddleArray[i].getY())) <= paddleDiagonal) {
						if (!points[0].equals(points[1])) {
							double angle = Math.atan(2 * GameSettings.PADDLE_HEIGHT / (GameSettings.PADDLE_LENGTH - GameSettings.PADDLE_TOP));
							ball.setT(Math.PI + 2 * (paddleArray[i].getT() + Math.PI + angle) - ball.getT());
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
		for(ServerBall ball : ballList) {
			balls.add(ball.getTball());
		}
		return new TGameState(paddles,balls,0,0,false,false,false,TPowerUp.NONE,TPowerUp.NONE);
	}
}
