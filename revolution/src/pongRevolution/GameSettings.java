package pongRevolution;

import network.TPlayer;

public class GameSettings {
	public static final int CLOCK_INTERVAL = 15;
	
	public static final int GAME_START_DELAY = 3000 / CLOCK_INTERVAL;
	public static final int BALL_RELEASE_INTERVAL = 1000 / CLOCK_INTERVAL;
	
	// Arena settings
	public static final int ARENA_RADIUS = 300;
	public static final double STARTING_POSITIONS[] = {0, 0, 180, 90, 270};
	
	// Paddle settings
	public static final double PADDLE_LENGTH = 40;
	public static final double PADDLE_TOP = 40;
	public static final double PADDLE_HEIGHT = 20;
	public static final double PADDLE_VELOCITY = 1.5;
	public static final double PADDLE_SPEEDUP = PADDLE_VELOCITY * 2;
	
	public static final double PADDLE_JUMP_INIT_VELOCITY = 4.5;
	public static final double PADDLE_JUMP_ACCEL = 0.07;
	
	// Ball settings
	public static final int BALL_RADIUS = 5;
	public static final int COMBO_SCORE[] = {0, 10, 20, 40, 70, 110};
	public static final double COMBO_SPEED[] = {2, 2.75, 3.5, 4.25, 5, 5.75};
	

	
	public static boolean isRed(TPlayer player) {
		return player == TPlayer.RED_ONE || player == TPlayer.RED_TWO;
	}

	public static boolean isBlue(TPlayer player) {
		return player == TPlayer.BLUE_ONE || player == TPlayer.BLUE_TWO;
	}
}
