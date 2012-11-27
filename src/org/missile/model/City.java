package org.missile.model;

import org.missile.view.Drawable;

public class City extends Drawable {
	private boolean destroyed;
	int x1, y1, width, height;

	public City(int x, int y, int width, int height) {
		x1 = x; 
		y1 = y; 
		this.width = width; 
		this.height = height;
		destroyed = false;
	}

	public boolean containsPoint(int x, int y) {
		int x2 = x1 + width;
		int y2 = y1 + height;
		return (x < x2 && x > x1) && (y < y2 && y > y1);
	}

	public int getX() {
		return x1;
	}

	public int getY() {
		return y1;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}

	public boolean getDestroyed() {
		return destroyed;
	}
}
