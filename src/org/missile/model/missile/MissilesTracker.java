package org.missile.model.missile;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.missile.model.base.Base;
import org.missile.model.template.GameElementTracker;

/**
 * {@link Missile} collection handler
 * <p>
 * This class is a type of {@link GameElementTracker} which controls a
 * {@link Missile} collection. It knows when to move a missile and how to
 * calculate the hits with another game elements.
 * 
 * @author luizcarlos
 * @see Missile
 * @see GameElement
 * @see GameElementTracker
 */
public class MissilesTracker extends GameElementTracker {

	/**
	 * Iterates over the missile list and check if they still can move. In case
	 * they don't, we will remove them.
	 * 
	 */
	public void check() {
		for (int i = 0; i < elements.size(); i++) {
			Missile m = (Missile) elements.get(i);
			if (!m.move())
				remove(m);
		}
	}

	/**
	 * Returns whether the missiles impact with a given game element.
	 * 
	 * @param element
	 *            MissileHitable element to be checked.
	 * @return true if any missile of the collection has impacted with the
	 *         element.
	 */
	public boolean hits(MissileHitable element) {
		boolean reached = false;

		for (int i = 0; i < elements.size(); i++) {
			Missile m = (Missile) elements.get(i);
			if (m.hits(element)) {
				reached = true;
			}
		}
		return reached;
	}

	/**
	 * Calculates whether the missiles have impacted with any element of a list
	 * 
	 * @param iterator
	 *            over the list of elements to be checked for impact.
	 * @return the list of {@MissileHitable} which have
	 *         impacted with a missile
	 */
	public List<MissileHitable> hits(Iterator iterator) {
		List<MissileHitable> hits = new Vector<MissileHitable>();
		while (iterator.hasNext()) {
			MissileHitable mh = (MissileHitable) iterator.next();
			if (hits(mh))
				hits.add(mh);
		}
		return hits;
	}

}
