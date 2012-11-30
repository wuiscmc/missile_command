package org.missile.model.explosion;

import org.missile.model.missile.Missile;
import org.missile.model.missile.MissileHitable;
import org.missile.model.template.GameElement;

/**
 * Logic representation for a explosion.
 * <p>
 * A explosion is represented by a circle which expands itself until it reaches
 * a certain limit, when it disappears. A explosion may be "hit" by a Missile,
 * therefore we implement the {@link MissileHitable}, then we can nest as many
 * explosions as we need.
 * 
 * @author Luis Carlos Mateos
 * @see GameElement
 * @see MissileHitable
 */
public class Explosion extends GameElement implements MissileHitable {
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
	 * Expands the explosion if it hasnt reached its limit.
	 * 
	 * @return true if the explosion could be expanded
	 */
	public boolean move() {
		boolean canMove = r < MAXIMUM_EXPLOSION_RADIUS;
		if (canMove)
			r += EXPANSION_SPEED;
		return canMove;
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
	 * @return the MAXIMUM_EXPLOSION_RADIUS value
	 * 
	 */
	public static int getMaxiumExplosionRadius(){
		return MAXIMUM_EXPLOSION_RADIUS;
	}
	
	
	@Override
	public boolean reached(Missile m) {
		boolean contains = false;
		double a = y - m.getY();
		double b = x - m.getX();

		if ((a * a + b * b) < ((r * r) / 2))
			contains = true;
		return contains;
	}

}