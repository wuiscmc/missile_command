package org.missile.model;

import java.util.List;
import java.util.Vector;

import org.missile.view.Drawable;

/**
 * Logic representation for a explosion.
 * <p>
 * A explosion is represented by a circle which expands itself until it reaches
 * a certain limit, when it disappears.
 * 
 * @author Luis Carlos Mateos
 * @see Drawable
 */
public class Explosion implements Drawable {
	private int x, y, r;

	/**
	 * Defines the initial radius of the explosion. By default, 1.
	 */
	private static final int MINIMUM_EXPLOSION_RADIUS = 1;

	/**
	 * Defines the maximum radius of the explosion, after which it will
	 * dissapear. By default, 50.
	 */
	private static final int MAXIMUM_EXPLOSION_RADIUS = 50;

	/**
	 * Defines how fast the explosion will expand itself. By default, 1.
	 */
	private static final int EXPANSION_SPEED = 1;

	/**
	 * Constructor of the class.
	 * <p>
	 * Initializes also the radio of the explosion.
	 * 
	 * @param x
	 *            explosion x coordinate
	 * @param y
	 *            explosion y coordinate
	 */
	public Explosion(int x, int y) {
		this.x = x;
		this.y = y;
		r = MINIMUM_EXPLOSION_RADIUS;
	}

	/**
	 * Increases the radius of the explosion and therefore, it gets bigger.
	 */
	public void move() {
		r += EXPANSION_SPEED;
	}

	/**
	 * Defines if the explosion is done.
	 * 
	 * @return true if the radius is higher than the MAXIMUM_EXPLOSION_RADIUS.
	 * @return false if the explosion can still grow.
	 */
	public boolean done() {
		return r >= MAXIMUM_EXPLOSION_RADIUS;
	}

	/**
	 * Getter of the x coordinate of the explosion
	 * 
	 * @return an integer containing the x coordinate of the explosion
	 */
	public int getX() {
		return x;
	}

	/**
	 * Getter of the y coordinate of the explosion
	 * 
	 * @return an integer containing the y coordinate of the explosion
	 */
	public int getY() {
		return y;
	}

	/**
	 * Getter of the current radius of the explosion
	 * 
	 * @return an integer containing the current radius
	 */
	public int getR() {
		return r;
	}

	/**
	 * Checks if there are any missiles within the area of the explosion. In
	 * that case they would explode as well.
	 * 
	 * @param missiles
	 *            a List of {@link Missile}
	 */
	public List<Drawable> collisions(List<Missile> missiles) {
		List<Drawable> explodedMissiles = new Vector<Drawable>();
		for (Missile m : missiles) {
			double a = getY() - m.getY();
			double b = getX() - m.getX();
			double r = getR();

			if ((a * a + b * b) < (r * r)) {
				m.setExploded(true);
			}
		}
		return explodedMissiles;
	}

}