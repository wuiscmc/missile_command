package org.missile.model;

import org.missile.view.Drawable;

/**
 * Represents a city in the system.
 * <p>
 * A city is represented by a rectangle. They might be destroyed by missiles.
 * 
 * @author Luis Carlos Mateos
 * @see Drawable
 */

public class City implements Drawable {
	private boolean destroyed;
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
		destroyed = false;
	}

	/**
	 * Helper method that informs whether a point is inside the city
	 * 
	 * @param x
	 *            x coordinate of the point
	 * @param y
	 *            y coordinate of the point
	 * @return true if the point is within the city
	 */
	public boolean containsPoint(int x, int y) {
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

	/**
	 * Setter of destroyed
	 * 
	 * @param destroyed
	 *            boolean containing the new destroyed value.
	 */
	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}

	/**
	 * Getter of destroyed
	 * 
	 * @return boolean destroyed. whether the city has been destroyed or not.
	 */
	public boolean getDestroyed() {
		return destroyed;
	}
}
