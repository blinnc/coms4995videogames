package pongRevolution;

import java.util.ArrayList;
import java.util.List;

public class Command {
	public enum Type {
		LEFT,RIGHT,JUMP,POWERUP
	}
	
	public List<Type> commandBuffer = new ArrayList<Type>();
}
