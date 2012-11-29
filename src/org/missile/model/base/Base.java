package org.missile.model.base;

import org.missile.model.missile.Missile;
import org.missile.model.missile.MissileHitable;
import org.missile.model.template.GameElement;
import org.missile.view.Drawable;

/**
 * Represents a base in the system.
 * <p>
 * A base is a type of {@link GameElement} represented by the container and a
 * gun, which is a line that stands in the middle of the the base container and
 * has a certain length. Since the bases can be destroyed by a missile it is
 * required to implement {@link MissileHitable} 
 * 
 * @author Luis Carlos Mateos
 * @see GameElement
 * @see MissileHitable
 */
public class Base extends GameElement implements MissileHitable {

	private double x, y;
	private int ix, iy;
	private double angle;
	private int bx, by, height, width, gunLength;

	/**
	 * Constructor of the class.
	 * <p>
	 * Initializes the parameters and sets the gun origin in the middle of the
	 * base.
	 * 
	 * @param bx
	 *            integer. base's bottom left x axis coordinate.
	 * @param by
	 *            integer. base's bottom left y axis coordinate.
	 * @param height
	 *            integer. height of the base
	 * @param width
	 *            integer. width of the base
	 * @param gunLength
	 *            integer. integer guns length
	 */
	public Base(int bx, int by, int width, int height, int gunLength) {

		this.bx = bx;
		this.by = by;
		this.height = height;
		this.width = width;
		this.gunLength = gunLength;

		ix = this.bx + (this.width / 2);
		iy = this.by;

		x = ix;
		y = iy + this.gunLength;
	}

	/**
	 * Sets the current (x, y) target point for the gun and recalculates its
	 * angle of shoot.
	 * 
	 * @param x
	 *            integer. x axis coordinates
	 * @param y
	 *            integer. y axis coordinates
	 */
	public void aimGun(int x, int y) {
		this.x = x;
		this.y = y;
		double dy = iy - this.y;
		double dx = ix - this.x;

		angle = Math.atan2(dy, dx) + Math.PI;

		this.x = Math.cos(angle) * gunLength + ix;
		this.y = Math.sin(angle) * gunLength + iy;
	}

	/**
	 * Calculates the distance between the base and a certain point.
	 * 
	 * @param x
	 *            integer. x coordinate of the point.
	 * @param y
	 *            integer. y coordinate of the point.
	 * @return double. the distance between the point and the base.
	 */
	public double distanceBase(int x, int y) {
		int dx = (bx + width / 2) - x;
		int dy = (by + height / 2) - y;
		return Math.sqrt(dx * dx + dy * dy);
	}

	/**
	 * 
	 * @return integer. current x axis coordinate of the guns pointer
	 */
	public int getX() {
		return (int) x;
	}

	/**
	 * 
	 * @return integer. current y axis coordinate of the guns pointer
	 */
	public int getY() {
		return (int) y;
	}

	/**
	 * 
	 * @return integer. initial x axis coordinate of the guns pointer
	 */
	public int getIx() {
		return ix;
	}

	/**
	 * 
	 * @return integer. initial y axis coordinate of the guns pointer
	 */
	public int getIy() {
		return iy;
	}

	/**
	 * 
	 * @return integer. bottom left point x axis coordinate of the base
	 */
	public int getBx() {
		return bx;
	}

	/**
	 * 
	 * @return integer. bottom left point y axis coordinate of the base
	 */
	public int getBy() {
		return by;
	}

	public int getHeigth() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	@Override
	public boolean reached(Missile m) {
		return containsPoint(m.getX(), m.getY());
	}

	public boolean containsPoint(int x, int y) {
		int x2 = bx + width;
		int y2 = by + height;
		return (x < x2 && x > bx) && (y < y2 && y > by);
	}
}
