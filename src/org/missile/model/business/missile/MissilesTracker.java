package org.missile.model.business.missile;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.missile.model.GameElementTracker;
import org.missile.view.Drawable;

public class MissilesTracker extends GameElementTracker {

	public void check() {
		for (int i = 0; i < elements.size(); i++) {
			Missile m = (Missile) elements.get(i);
			if (!m.move())
				remove(m);
		}
	}

	public List<MissileHitable> hits(Iterator iterator) {
		List<MissileHitable> hits = new Vector<MissileHitable>();
		while (iterator.hasNext()) {
			MissileHitable mh = (MissileHitable) iterator.next();
			if (hits(mh))
				hits.add(mh);
		}
		return hits;
	}

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

}
