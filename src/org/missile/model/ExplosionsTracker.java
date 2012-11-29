package org.missile.model;

import java.util.List;
import java.util.Vector;

import org.missile.view.Drawable;

public class ExplosionsTracker {
	
	private List<Explosion> explosions;
	private List<Missile> missiles;
	private List<ExplosionObserver> observers;
	
	public ExplosionsTracker(List<Missile> missiles){
		this.missiles = missiles;
		explosions = new Vector<Explosion>();
		observers = new Vector<ExplosionObserver>();
	}
	
	
	public void moveExplosions() {
		List<Drawable> collisions = new Vector<Drawable>();
		
		for (int i = 0 ; i < explosions.size(); i++ ) {
			Explosion e = explosions.get(i);
			if (e.done()) {
				removeExplosion(e);
			} else {
				e.move();
				collisions.addAll(e.collisions(missiles));
			}
		}
		
		for(Drawable m : collisions){
			((Missile)m).setExploded(true);
			addExplosion(((Missile)m).getExplosion());
		}
	}
	
	
	public void removeExplosion(Explosion e){
		explosions.remove(e);
		for(ExplosionObserver observer: observers){
			observer.removedExplosion(e);
		}
	}
	
	public void addExplosion(Explosion e){
		explosions.add(e);
		for(ExplosionObserver observer: observers){
			observer.newExplosion(e);
		}
	}
	
	
	public void addObserver(ExplosionObserver observer){
		observers.add(observer);
	}
	
	public void removeObserver(ExplosionObserver observer){
		observers.remove(observer);
	}
	
}
