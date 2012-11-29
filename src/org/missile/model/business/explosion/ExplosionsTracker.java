package org.missile.model.business.explosion;

import org.missile.model.GameElement;
import org.missile.model.GameElementObserver;
import org.missile.model.GameElementTracker;
import org.missile.model.business.missile.Missile;

public class ExplosionsTracker extends GameElementTracker implements GameElementObserver {

	public void check() {
		for (int i = 0; i < elements.size(); i++) {
			Explosion e = (Explosion) elements.get(i);
			if (!e.move())
				remove(e);
		}
	}

	@Override
	public void addedGameElement(GameElement c) {
		
	}

	@Override
	public void removedGameElement(GameElement c) {
		if(c instanceof Missile){
			add(((Missile)c).getExplosion());
		}
	}

}
