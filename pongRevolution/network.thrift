namespace java network

enum TPowerUp {
	NONE = 0;
	SPEED = 1;
	INVIS = 2;
	SHADOW = 3;
	MAGNET = 4;
	EXTEND = 5;
	PUSH = 6;
	LASER = 7;
	WALL = 8;
}

enum TPlayer {
	NONE = 0;
	RED_ONE = 1;
	RED_TWO = 2;
	BLUE_ONE = 3;
	BLUE_TWO = 4;
}

struct TPosition {
	1: double xPos;
	2: double yPos;
}

struct TPaddle {
	1: double radius;
	2: double angle;
	3: double length;
	4: TPlayer player;
	5: bool isInvisible;
	6: bool isSpeedup;
	7: bool isMagnetic;
}

struct TBall {
	1: list<TPosition> positions;
	2: TPowerUp type;
	3: TPlayer player;
	4: bool isShadow;
}

struct TGameState {
	1: list<TPaddle> paddles;
	2: list<TBall> balls;
	3: i32 redScore;
	4: i32 blueScore;
	5: bool isLaserRed;
	6: bool isLaserBlue;
	7: bool isWall;
	8: TPowerUp playerUp;
	9: TPowerUp allyUp; 
	10: list<TPosition> connections;
}

struct TLobbyState {
	1: string redOne;
	2: string redTwo;
	3: string blueOne;
	4: string blueTwo;
	5: string chat;
}

struct TSettings {
	1: i32 ballRadius;
	2: i32 arenaRadius;
	3: i32 timerRefresh;
}

service TCommandServer {
	oneway void pickTeam(1:TPlayer preferred, 2:string username, 3: bool join),

	oneway void sendChat(1:string message, 2: string username),

	oneway void moveLeft(1:TPlayer requester),

	oneway void moveRight(1:TPlayer requester),

	oneway void usePowerUp(1:TPlayer requester),

	oneway void jump(1:TPlayer requester),
}

service TPollServer {
	TGameState poll(1:TPlayer requester),

	TLobbyState pollLobby(1:string username),

	TSettings getSettings(1:string username),
}
