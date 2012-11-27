package org.missile.model;

import java.util.List;
import java.util.Vector;

import org.missile.view.Drawable;

public class Logic {
	private List<Missile> missiles;
	private List<Explosion> explosions;
	private List<City> cities;
	private List<Base> bases;
	private List<LogicObserver> observers;
	public Logic(){
		missiles = new Vector<Missile>();
		explosions = new Vector<Explosion>();
		bases = new Vector<Base>();
		cities = new Vector<City>();
		observers = new Vector<LogicObserver>();
	}
	
	
	public void setBase(int x, int y, int width, int height, int gunHeigth){
		addElement(new Base(x, y, width, height, gunHeigth), bases);
	}

	public void setCity(int x, int y, int width, int height){
		addElement(new City(x, y, width, height), cities);
	}

	
	 /**
	   * Checks for any collisions and moves the elements
	   */
	public void moveElements() {

		for (int i = 0; i < missiles.size(); i++) {
			Missile m = missiles.get(i);
			if (m.done()) {
				addElement(m.explodeMissile(), explosions);
				removeElement(m, missiles);
			} else {
				m.move();
				removeAllElement(m.collisions(cities), cities);
			}
		}
		
		for (int i = 0; i < explosions.size(); i++) {
			Explosion e = explosions.get(i);
			if (e.done()) {
				removeElement(e, explosions);
			} else {
				e.move();
				e.collisions(missiles);
			}
		}
	}
	
	public void shootEnemyMissile(int x0, int y0, int x1, int y1) {
		if (Math.random() * 100 > 99.5) {
			addElement(new Missile(x0, y0, x1, y1, 0.5), missiles);
		}
		
	}
	
	
	
	public void aimGun(int x, int y) {
		getClosestBase(x, y).aimGun(x, y);
	}
	
	
	private Base getClosestBase(int x, int y) {
		Base b = null;
		for (Base base : bases) {
			if (b == null || b.distanceBase(x, y) > base.distanceBase(x, y))
				b = base;
		}
		return b;
	}
	
	public void shootAllytMissile(int x, int y) {
		Base b = getClosestBase(x, y);
		addElement(new Missile(b.getX(), b.getY(), x, y, 5), missiles);
	}

	
	public void registerObserver(LogicObserver observer){
		observers.add(observer);
	}
	
	public void removeObserver(LogicObserver observer){
		observers.remove(observer);
	}
	
	
	private void addElement(Drawable element, List list){
		list.add(element);
		for(LogicObserver observer: observers)
			observer.newElement(element);
	}
	
	private void removeElement(Drawable element, List list){
		list.remove(element);
		for(LogicObserver observer: observers){
			observer.removeElement(element);
		}
	}
	
	private void removeAllElement(List<Drawable> collection, List list){
		list.removeAll(collection);
		for(LogicObserver observer: observers){
			observer.removeElementCollection(collection);
		}
	}


	
}
