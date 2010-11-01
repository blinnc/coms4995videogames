package pongRevolution;

public class GameSettings {
	public static final int CLOCK_INTERVAL = 15;
	
	public static final int GAME_START_DELAY = 3000;
	
	// Arena settings
	public static final double ARENA_RADIUS = 250;
	public static final double STARTING_POSITIONS[] = {0, 0, 90, 180, 270};
	
	// Paddle settings
	public static final double PADDLE_LENGTH = 40;
	public static final double PADDLE_HEIGHT = 20;
	public static final double PADDLE_VELOCITY = 1.5;
	
	// Ball settings
	public static final int COMBO_SCORE[] = {0, 10, 20, 40, 70, 110};
	public static final double COMBO_SPEED[] = {5, 7, 10, 15, 20, 25};
}
