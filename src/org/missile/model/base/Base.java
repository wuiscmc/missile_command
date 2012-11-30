package org.missile.model.base;

import org.missile.model.missile.Missile;
import org.missile.model.missile.MissileHitable;
import org.missile.model.template.GameElement;

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

	private double guncx, guncy, angle;
	private int basex, basey, height, width, gunix, guniy, gunLength;

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

		this.basex = bx;
		this.basey = by;
		this.height = height;
		this.width = width;
		this.gunLength = gunLength;

		gunix = this.basex + (this.width / 2);
		guniy = this.basey;

		guncx = gunix;
		guncy = guniy + this.gunLength;
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
		double dx = gunix - x;
		double dy = guniy - y;
		angle = Math.atan2(dy, dx) + Math.PI;
		
		this.guncx = Math.cos(angle) * gunLength + gunix;
		this.guncy = Math.sin(angle) * gunLength + guniy;
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
		int dx = (basex + (width / 2)) - x;
		int dy = (basey + (height / 2)) - y;
		return Math.sqrt(dx * dx + dy * dy);
	}

	/**
	 * 
	 * @return integer. current x axis coordinate of the guns pointer
	 */
	public int getGunCX() {
		return (int) guncx;
	}

	/**
	 * 
	 * @return integer. current y axis coordinate of the guns pointer
	 */
	public int getGunCY() {
		return (int) guncy;
	}

	/**
	 * 
	 * @return integer. initial x axis coordinate of the guns pointer
	 */
	public int getGunIX() {
		return gunix;
	}

	/**
	 * 
	 * @return integer. initial y axis coordinate of the guns pointer
	 */
	public int getGunIY() {
		return guniy;
	}

	/**
	 * 
	 * @return integer. bottom left point x axis coordinate of the base
	 */
	public int getBX() {
		return basex;
	}

	/**
	 * 
	 * @return integer. bottom left point y axis coordinate of the base
	 */
	public int getBY() {
		return basey;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	@Override
	public boolean reached(Missile m) {
		return containsPoint(m.getX(), m.getY());
	}

	/**
	 * Helper method that calculates whether a point is within the base area.
	 * 
	 * @param x
	 *            integer. x axis coordinate of the point
	 * @param y
	 *            integer. y axis coordinate of the point
	 * @return true if the point is within the base
	 */
	private boolean containsPoint(int x, int y) {
		int x2 = basex + width;
		int y2 = basey + height;
		return (x < x2 && x > basex) && (y < y2 && y > basey);
	}
}
