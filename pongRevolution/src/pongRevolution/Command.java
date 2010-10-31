package pongRevolution;

import java.util.List;

public class Command {
	enum Type {
		LEFT,RIGHT,JUMP,POWERUP
	}
	
	public List<Type> commandBuffer;
}
