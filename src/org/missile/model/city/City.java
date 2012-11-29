package org.missile.model.city;

import org.missile.model.missile.Missile;
import org.missile.model.missile.MissileHitable;
import org.missile.model.template.GameElement;
import org.missile.view.Drawable;

/**
 * Represents a city in the system.
 * <p>
 * A City is a type of {@link GameElement} represented by a rectangle. Since the cities can be destroyed by a
 * missile, it is required to implement the {@link MissileHitable} interface.
 * 
 * @author Luis Carlos Mateos
 * @see GameElement
 * @see MissileHitable
 */

public class City extends GameElement implements MissileHitable {
	int x1, y1, width, height;

	/**
	 * Constructor of the class.
	 * 
	 * @param x
	 *            bottom left x coordinate
	 * @param y
	 *            bottom left y coordinate
	 * @param width
	 *            width of the city
	 * @param height
	 *            height of the city
	 */
	public City(int x, int y, int width, int height) {
		x1 = x;
		y1 = y;
		this.width = width;
		this.height = height;
	}

	/**
	 * Helper method that returns if a point is inside the city
	 * 
	 * @param x
	 *            x coordinate of the point
	 * @param y
	 *            y coordinate of the point
	 * @return true if the point is within the city
	 */
	private boolean containsPoint(int x, int y) {
		int x2 = x1 + width;
		int y2 = y1 + height;
		return (x < x2 && x > x1) && (y < y2 && y > y1);
	}

	/**
	 * Getter of X1
	 * 
	 * @return integer x1. the bottom left x axis coordinate of the city
	 */
	public int getX() {
		return x1;
	}

	/**
	 * Getter of Y1
	 * 
	 * @return integer y1. the bottom left y axis coordinate of the city
	 */
	public int getY() {
		return y1;
	}

	/**
	 * Getter of width
	 * 
	 * @return integer width.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Getter of X1
	 * 
	 * @return integer height.
	 */
	public int getHeight() {
		return height;
	}

	@Override
	public boolean reached(Missile m) {
		return containsPoint(m.getX(), m.getY());
	}
}
