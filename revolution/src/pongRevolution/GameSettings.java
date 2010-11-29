package pongRevolution;

import network.TPlayer;

public class GameSettings {
	public static final int CLOCK_INTERVAL = 15;
	
	public static final int GAME_START_DELAY = 3000 / CLOCK_INTERVAL;
	public static final int BALL_RELEASE_INTERVAL = 2000 / CLOCK_INTERVAL;
	
	// Arena settings
	public static final int ARENA_RADIUS = 278;
	public static final double BALL_OUT_BUFFER = 22;
	public static final double STARTING_POSITIONS[] = {0, 0, 180, 90, 270};
	
	// Paddle settings
	public static final double PADDLE_LENGTH = 84;
	public static final double PADDLE_TOP = 40;
	public static final double PADDLE_HEIGHT = 14;
	public static final double PADDLE_VELOCITY = 1.5;
	public static final double PADDLE_SPEEDUP = PADDLE_VELOCITY * 2;
	
	public static final double PADDLE_JUMP_INIT_VELOCITY = 8;
	public static final double PADDLE_JUMP_ACCEL = 0.3;
	
	// Ball settings
	public static final int BALL_RADIUS = 5;
	public static final int COMBO_SCORE[] = {0, 10, 20, 40, 70, 110};
	public static final double INITIAL_SPEED = 3.5;
	public static final double COMBO_SPEED[] = {INITIAL_SPEED, 
												INITIAL_SPEED + .75, 
												INITIAL_SPEED + .75 * 2, 
												INITIAL_SPEED + .75 * 3,
												INITIAL_SPEED + .75 * 4,
												INITIAL_SPEED + .75 * 5,};
	
	public static final int COMBO_SLOTS[][] = {		{0, 0, 0, 0, 0},
													{0, 0, 0, 0, 0},
													{0, 4, 0, 0, 0},
													{0, 4, 7, 0, 0},
													{0, 3, 6, 9, 0},
													{0, 3, 6, 9, 12} };
	
	public static final int BALL_REHIT_TIME = 20;

	
	public static boolean isRed(TPlayer player) {
		return player == TPlayer.RED_ONE || player == TPlayer.RED_TWO;
	}

	public static boolean isBlue(TPlayer player) {
		return player == TPlayer.BLUE_ONE || player == TPlayer.BLUE_TWO;
	}
}
