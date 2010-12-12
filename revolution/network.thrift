namespace java network

enum TPowerUp {
	NONE = 0;
	SPEED = 1;
	INVIS = 2;
	STUN = 3;
    STUNNED = 4;
}

enum TPlayer {
	NONE = 0;
	RED_ONE = 1;
	RED_TWO = 2;
	BLUE_ONE = 3;
	BLUE_TWO = 4;
}

enum TDirection {
	NONE = 0;
	RIGHT = 1;
	LEFT = 2;
}

struct TPosition {
	1: double xPos;
	2: double yPos;
}

struct TCollision {
    1: TPlayer player;
    2: i32 ballCombo;
    3: i32 id;
    4: i32 decay;
}

struct TPower {
    1: i32 id;
    2: TPowerUp type;
    3: i32 decay;
}

struct TPaddle {
	1: double radius;
	2: double angle;
	3: TPlayer player;
	4: TPower store;
    5: TPower used;
}

struct TBall {
	1: list<TPosition> positions;
	2: TPower store;
	3: TPlayer player;
    4: i32 id;
    5: double angle;
    6: i32 decay;
}

struct TGameState {
	1: list<TPaddle> paddles;
	2: list<TBall> balls;
	3: i32 redScore;
	4: i32 blueScore;
    5: i32 spawning;
    6: list<TBall> out;
    7: list<TCollision> collisions;
	8: list<TPosition> connections;
    9: list<string> message;
}

struct TSettings {
	1: i32 ballRadius;
	2: i32 arenaRadius;
	3: i32 timerRefresh;
	4: TPlayer color;
    5: list<i32> combos;
}

service TNetworkServer {
	TSettings getSettings(1:TPlayer preferred),

	TGameState poll(1:TPlayer requester),

	oneway void move(1:TPlayer requester, 2:TDirection dir),

	oneway void usePowerUp(1:TPlayer requester),

	oneway void jump(1:TPlayer requester),
}
