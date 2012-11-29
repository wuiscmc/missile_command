package org.missile.model.explosion;

import org.missile.model.base.Base;
import org.missile.model.missile.Missile;
import org.missile.model.template.GameElement;
import org.missile.model.template.GameElementObserver;
import org.missile.model.template.GameElementTracker;

/**
 * This class is a type of {@link GameElementTracker} which controls a
 * {@link Explosion} collection. It knows what to do when a {@link Explosion}
 * has ended.
 * <p>
 * Whenever a {@link Missile} reaches its destiny or hits a
 * {@link MissileHitable} element, it needs to explode.
 * 
 * @author Luis Carlos Mateos
 * @see GameElementObserver
 * @see GameElementTracker
 * 
 */
public class ExplosionsTracker extends GameElementTracker implements
		GameElementObserver {

	/**
	 * Checks whether the status of the current explosions in the system. In
	 * case that one of them has reached it maximum size, it needs to be
	 * removed.
	 */
	public void check() {
		for (int i = 0; i < elements.size(); i++) {
			Explosion e = (Explosion) elements.get(i);
			if (!e.move())
				remove(e);
		}
	}

	/**
	 * This is left intentionally in blank, since we dont want to process the
	 * added {@link Missile} signal.
	 */
	@Override
	public void addedGameElement(GameElement c) {

	}

	/**
	 * When a {@link GameElement} is removed from the system, this will check
	 * whether the object is a {@link Missile} and create a new
	 * {@link Explosion} then.
	 */
	@Override
	public void removedGameElement(GameElement c) {
		if (c instanceof Missile) {
			add(((Missile) c).getExplosion());
		}
	}

}
