package org.missile.model;

import org.missile.view.Drawable;

public class Explosion extends Drawable{
	private int x, y, r;
	
	public Explosion(int x, int y) {
		this.x = x;
		this.y = y;
		r = 1;
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