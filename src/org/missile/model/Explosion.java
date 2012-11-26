package org.missile.model;

import java.awt.Graphics;

public class Explosion implements Drawable {
	private int x, y, r;
	private int speed;

	public Explosion(int x, int y) {
		this.x = x;
		this.y = y;
		speed = 1;
		r = 1;
	}

	public void draw(Graphics g) {
		g.fillOval(x - r / 2, y - r / 2, r, r);
	}

	public void move() {
		r += 1;
	}

	public boolean done() {
		return r >= 50;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getR() {
		return r;
	}

}