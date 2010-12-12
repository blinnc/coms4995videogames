package client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.List;

public class Score {
	int time;
	int life;
	int combo;
	int[] values;
	double angle;
	double center;
	double radius;
	double initX;
	double initY;
	Color c;
	
	static final int startSize = 13;
	static final int startLife = 65;
	int[] fade = {255, 255, 255, 255, 255};
	
	static final double[] angleOffset = {Math.PI/90, -Math.PI / 180, Math.PI / 180, -Math.PI / 90, 0};
	static final Font[] plain = { new Font("Courier New", Font.PLAIN, startSize),
			new Font("Courier New", Font.PLAIN, startSize + 1), 
			new Font("Courier New", Font.PLAIN, startSize + 2), 
			new Font("Courier New", Font.PLAIN, startSize + 3), 
			new Font("Courier New", Font.PLAIN, startSize + 4) };
	
	public Score(int combo, double initX, double initY, List<Integer> values, double angle, double center, double radius, Color c) {
		this.time = 0;
		this.combo = combo;
		this.initX = initX;
		this.initY = initY;
		this.life = startLife + 3 * combo;
		this.values = new int[combo];
		for (int i = 0; i < combo; i++) {
			this.values[i] = values.get(i);
		}
		this.angle = Math.atan(- initY / initX);
		if (initX < 0) {
			this.angle = this.angle + Math.PI;
		}
		this.center = center;
		this.radius = Math.sqrt(initX * initX + initY * initY) + 10;
		this.c = c;
	}
	
	public void increment() {
		time++;
		if (time < 3 * combo - 1) {
			radius += 3.5;
		} else if (time % 3 == 0){
			radius++;
		}
		int left = (life - time);
		for (int i = 0; i < fade.length; i++) {
			if (left < 70 + combo * 2 - i * 5) {
				fade[i] -= 3;
			}
		}
	}
	
	public void show(Graphics2D g) {
		for (int i = 0; i < combo && i * 3 < time; i++) {
			if (c == Color.RED){
				g.setColor(new Color(fade[fade.length - combo + i], 0, 0));
			} else if (c == Color.BLUE){
				g.setColor(new Color(0, 0, fade[fade.length - combo + i]));
			}
				g.setFont(plain[i]);
			g.drawString(values[i] + "",
					- startSize / 2 + getX(radius - (i * (8 + i)), angle + angleOffset[angleOffset.length - combo + i]),
					startSize / 2 + getY(radius - (i * (8 + i)), angle + angleOffset[angleOffset.length - combo + i]));
		}
	}
	
	public boolean remove() {
		return (time > life);
	}
	
	private float getX(double r, double a) {
		return (float) (r * Math.cos(a) + center);
	}
	private float getY(double r, double a) {
		return (float) (center + (r * Math.sin(a) ) );
	}
}
