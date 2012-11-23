package org.missile;

import java.awt.Graphics;

public class Missile {
	private static int DEFAULT_SPEED = 10;
	private int ix, iy, gx, gy;
	private double cx, cy;
	private double dx, dy;
	private double speed;
	
	public Missile(int ix, int iy, int gx, int gy, double speed) {
		this.speed = speed;
		this.ix = ix;
		this.iy = iy;
		this.gx = gx;
		this.gy = gy;
		setParameters();
	}

	public Missile(int ix, int iy, int gx, int gy) {
		this(ix,iy,gx,gy, DEFAULT_SPEED);
	}

	private void setParameters() {
		cx = ix;
		cy = iy;

		dx = gx - ix;
		dy = Math.abs(gy - iy);

		double distance = Math.sqrt(dx * dx + dy * dy);
		dx = (dx / distance) * speed;
		dy = (dy / distance) * speed;
	}

	public void draw(Graphics g) {
		g.drawLine(ix, iy, (int) cx, (int) cy);
	}

	public void move() {
		cx = cx + dx;
		if (iy <= gy) 
			cy = cy + dy; // means its fired from the top of the window
		else
			cy = cy - dy; // its fired from the bottom
	}

	public boolean done() {
		boolean done = false;
		if (ix >= gx) {
			done = cx <= gx;
		} else {
			done = cx > gx;
		}
		boolean doney = false;
		if (iy <= gy)
			// means its fired from the top of the window
			doney = gy - cy <= 0;
		else
			// its fired from the bottom
			doney = cy - gy < 0;

		return done && doney;
	}

};