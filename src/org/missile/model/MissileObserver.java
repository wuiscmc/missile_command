package org.missile.model;

public interface MissileObserver {
	public void newMissile(Missile e);

	public void removedMissile(Missile e);
}
