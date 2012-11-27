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
	private Vector<Missile> enemyMissiles;
	private Vector<Missile> missiles;
	private Vector<Explosion> explosions;
	private Vector<City> cities;

	private Canvas v;

	public Game() {
		
		enemyMissiles = new Vector<Missile>();
		missiles = new Vector<Missile>();
		explosions = new Vector<Explosion>();
		
		v = new Canvas(this);
		base = new Base(230, 490, 10, 20, 50);
		addElement(base, null);
		
		cities = new Vector<City>();
		setCities();
		
	}

	public void startGame() {
		Thread c = new Thread(v);
		c.start();
	}

	private void setCities() {
		addElement(new City(41, 450, 41, 50), cities);
		
		int x = 0;
		for (int i = 0; i < 3; i++) {
			x = (50 + x) + 50 * 2;
			addElement(new City(x, 450, 41, 50), cities);
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
			int bx = (int) ((Math.random() * 1000) % 480 + 20);
			int ex = (int) ((Math.random() * 1000) % 480 + 20);
			addElement(new Missile(bx, 0, ex, 500, 0.5), enemyMissiles);
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
		addElement(new Missile(base.getX(), base.getY(), x, y, 5), missiles);
	}

	public void aimGun(int x, int y) {
		base.aimGun(x, y);
	}
	
	private void addElement(Drawable element, List collection){
		if(collection != null) collection.add(element);
		v.addScreenElement(element);
		element.addDrawer(v);
	}
	
	private void removeElement(Drawable d, List collection){
		if(collection != null) collection.remove(d);
		v.deleteScreenElement(d);
	}
	
	private void removeAllElement(List d, List collection){
		if(collection != null) collection.removeAll(d);
		v.deleteScreenAllElement(d);
	}
}
