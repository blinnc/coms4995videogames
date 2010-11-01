namespace java network

enum BallType {
	RED = 1;
	BLUE = 2;
	SPEED = 3;
	INVIS = 4;
	SHADOW = 5;
	MAGNET = 6;
	EXTEND = 7;
	PUSH = 8;
	LASER = 9;
	WALL = 10;
}

enum Player {
	RED_ONE = 1;
	RED_TWO = 2;
	BLUE_ONE = 3;
	BLUE_TWO = 4;
}

struct Position {
	1: double xPos;
	2: double yPos;
}

struct Paddle {
	1: double radius;
	2: double angle;
	3: Player player;
	4: bool isInvisible;
	5: bool isSpeedup;
	6: bool isMagnetic;
}

struct Ball {
	1: list<Position> positions;
	2: BallType type;
	3: bool isShadow;
}

struct GameState {
	1: list<Paddle> paddles;
	2: list<Ball> balls;
	3: i32 redScore;
	4: i32 blueScore;
	5: bool isLaserRed;
	6: bool isLaserBlue;
	7: bool isWall;
}

struct Settings {
	1: i32 ballRadius;
}

service PongServer {
	Settings getSettings(),

	GameState poll(1:Player requester),

	oneway void moveLeft(1:Player requester),

	oneway void moveRight(1:Player requester),

	oneway void usePowerUp(1:Player requester),

	oneway void jump(1:Player requester),
}
