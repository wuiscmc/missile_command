package org.missile.model;

public interface ExplosionObserver {
	public void newExplosion(Explosion e);

	public void removedExplosion(Explosion e);
}
