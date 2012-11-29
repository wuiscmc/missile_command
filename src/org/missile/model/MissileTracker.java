package org.missile.model;

import java.util.List;
import java.util.Vector;

import org.missile.view.Drawable;

public class MissileTracker {
	
	private List<Missile> missiles;
	private List<City> cities;
	private List<MissileObserver> observers;
	private ExplosionsTracker explosionTracker;
	
	public MissileTracker(List<City> cities){
		this.cities = cities;
		explosionTracker = new ExplosionsTracker(missiles);
		missiles = new Vector<Missile>();
		observers = new Vector<MissileObserver>();
	}
	
	
	public void movemissiles() {
		List<Drawable> collisions = new Vector<Drawable>();
		
		for (int i = 0 ; i < missiles.size(); i++ ) {
			Missile m = missiles.get(i);
			if (m.done()) {
				removeMissile(m);
			} else {
				m.move();
				collisions.addAll(m.collisions(cities));
			}
		}
		
		for(Drawable m : collisions){
			((Missile)m).setExploded(true);
			addMissile(((Missile)m).getMissile());
		}
	}
	
	
	public void asdf(){
		for (int i = 0; i < missiles.size(); i++) {
			Missile m = missiles.get(i);
			if (m.done()) {
				
				//explosionsTracker.addExplosion(m.getExplosion());
				removeMissile(m);
										
			} else {
				m.move();
				//List<Drawable> destroyedCities = m.collisions(cities);
				//cities.removeAll(destroyedCities);
				
				//notifyCollectionRemoved(destroyedCities); // we remove all the
															// destroyedCities
															// from the system.
			}
		}
	}
	
	public void removeMissile(Missile e){
		missiles.remove(e);
		for(MissileObserver observer: observers){
			observer.removedMissile(e);
		}
	}
	
	public void addMissile(Missile e){
		missiles.add(e);
		for(MissileObserver observer: observers){
			observer.newMissile(e);
		}
	}
	
	
	public void addObserver(MissileObserver observer){
		observers.add(observer);
	}
	
	public void removeObserver(MissileObserver observer){
		observers.remove(observer);
	}
	
}
