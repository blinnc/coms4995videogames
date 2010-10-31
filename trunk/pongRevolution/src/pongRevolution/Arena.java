package pongRevolution;

public class Arena {
	public static final double DEFAULT_RADIUS = 300;
	private double radius;

	public Arena() {
		radius = DEFAULT_RADIUS;
	}
	
	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
}
