package org.missile.model;

import org.missile.view.Drawable;

public class Base extends Drawable {

	private double x, y;
	private int ix, iy;
	private double angle;
	private int bx, by, heigth, width, gunLength;

	public Base(int bx, int by, int heigth, int width, int gunLength) {

		this.bx = bx;
		this.by = by;
		this.heigth = heigth;
		this.width = width;
		this.gunLength = gunLength;

		ix = this.bx + (this.width / 2);
		iy = this.by;

		x = ix;
		y = iy + this.gunLength;
	}

	private void recalculate() {
		double dy = iy - y;
		double dx = ix - x;

		angle = Math.atan2(dy, dx) + Math.PI;

		x = Math.cos(angle) * gunLength + ix;
		y = Math.sin(angle) * gunLength + iy;
	}


	public void aimGun(int x, int y) {
		this.x = x;
		this.y = y;
		recalculate();
	}
	
	public double distanceBase(int x, int y){
		int dx = (bx + width/2) - x; 
		int dy = (by + heigth/2) - y;
		return Math.sqrt(dx*dx + dy*dy);
	}

	
	public int getX() {
		return (int) x;
	}

	public int getY() {
		return (int) y;
	}

	public int getIx() {
		return ix;
	}

	public int getIy() {
		return iy;
	}

	public int getBx() {
		return bx;
	}

	public int getBy() {
		return by;
	}

	public int getHeigth() {
		return heigth;
	}

	public int getWidth() {
		return width;
	}

}
