package org.missile.controller;

import java.util.List;
import java.util.Vector;

import org.missile.model.Base;
import org.missile.model.City;
import org.missile.model.Explosion;
import org.missile.model.Missile;
import org.missile.view.Canvas;
import org.missile.view.Drawable;

public class Game {

	private Base base;
	private List<Missile> enemyMissiles;
	private List<Missile> missiles;
	private List<Explosion> explosions;
	private List<City> cities;
	private List<Base> bases;

	private Canvas v;

	public Game(int width, int height, int nBases, int nCities) {

		enemyMissiles = new Vector<Missile>();
		missiles = new Vector<Missile>();
		explosions = new Vector<Explosion>();
		v = new Canvas(this, width, height);

		bases = new Vector<Base>();
		setBases(130, height - 10 ,10, 10, 2);
		
		
		cities = new Vector<City>();
		setCities(41, height - 50, 41, 50, 2);
	}

	public void startGame() {
		Thread c = new Thread(v);
		c.start();
	}
	
	private void setBases(int x0, int y, int w, int h, int nBases) {
		addElement(new Base(x0, y, w, h, 50), bases);
		
		int x = x0;
		for (int i = 0; i < nBases; i++)
			x = (w + x) + w * 3;
			addElement(new Base(x, y, w, h, 50), bases);
	}
	
	private void setCities(int x0, int y, int w, int h, int nCities) {
		addElement(new City(x0, y, w, h), cities);

		int x = x0;
		for (int i = 0; i < nCities - 1; i++) {
			x = (w + x) + w * 2;
			addElement(new City(x, y, w, h), cities);
		}
	}

	public void moveMissiles() {

		for (int i = 0; i < missiles.size(); i++) {
			Missile m = missiles.get(i);
			if (m.done()) {
				explodeMissile(m);
				removeElement(m, missiles);
			} else {
				m.move();
			}
		}

		for (int i = 0; i < enemyMissiles.size(); i++) {
			Missile m = enemyMissiles.get(i);
			if (m.done()) {
				explodeMissile(m);
				removeElement(m, enemyMissiles);
			} else {
				m.move();
				removeAllElement(reachedCity(m), cities);
			}
		}

		for (int i = 0; i < explosions.size(); i++) {
			Explosion e = explosions.get(i);
			if (e.done()) {
				removeElement(e, explosions);
			} else {
				e.move();
				collisions(e);
			}
		}
	}

	public void fireEnemy() {
		if (Math.random() * 100 > 99.5) {
			int bx = (int) ((Math.random() * 1000) % v.getWidth() - 20 + 20);
			int ex = (int) ((Math.random() * 1000) % v.getWidth() - 20 + 20);
			addElement(new Missile(bx, 0, ex, v.getWidth() - 20, 0.5),
					enemyMissiles);
		}
	}

	private void explodeMissile(Missile m) {
		addElement(new Explosion(m.getX(), m.getY()), explosions);
	}

	private void collisions(Explosion e) {
		for (Missile m : enemyMissiles) {
			double a = e.getY() - m.getY();
			double b = e.getX() - m.getX();
			double r = e.getR();

			if ((a * a + b * b) < (r * r)) {
				m.exploded(true);
			}

		}
	}

	private List<Drawable> reachedCity(Missile m) {
		List<Drawable> citiesExploded = new Vector<Drawable>();
		for (City c : cities) {
			if (c.containsPoint(m.getX(), m.getY())) {
				m.exploded(true);
				c.setDestroyed(true);
				citiesExploded.add(c);
			}
		}
		return citiesExploded;
	}

	public void shootMissile(int x, int y) {

		Base b = getClosestBase(x, y);
		addElement(new Missile(b.getX(), b.getY(), x, y, 5), missiles);
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

	private void addElement(Drawable element, List collection) {
		if (collection != null)
			collection.add(element);
		v.addScreenElement(element);
		element.addDrawer(v);
	}

	private void removeElement(Drawable d, List collection) {
		if (collection != null)
			collection.remove(d);
		v.deleteScreenElement(d);
	}

	private void removeAllElement(List d, List collection) {
		if (collection != null)
			collection.removeAll(d);
		v.deleteScreenAllElement(d);
	}
}
