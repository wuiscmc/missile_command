package org.missile.model.missile;

/**
 * 
 * This interface defines the methods needed for a class to be reached by a
 * missile
 * 
 * @author Luis Carlos Mateos
 * 
 */
public interface MissileHitable {
	/**
	 * Header of reached function.
	 * 
	 * @param m
	 *            {@link Missile} which will be checked for impact within the
	 *            MissileHittable object.
	 * @return true if the missile has reached the object.
	 */
	public boolean reached(Missile m);
}
