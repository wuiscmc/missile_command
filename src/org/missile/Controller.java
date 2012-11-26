package org.missile;

import java.util.List;
import java.util.Vector;

public class Controller {

	private boolean shoot;
	private Base base;
	private Vector<Missile> enemyMissiles;
	private Vector<Missile> missiles;
	private Vector<Explosion> explosions;
	private Vector<City> cities;
	
	private View v;

	public Controller() {
		v = new View(this);

		enemyMissiles = new Vector<Missile>();
		missiles = new Vector<Missile>();
		explosions = new Vector<Explosion>();
		
		base = new Base(230, 490, 10, 20, 50);
		v.addScreenElement(base);

		cities = new Vector<City>();
		setCities();
	}

	public void startGame() {
		Thread c = new Thread(v);
		c.start();
	}

	private void collisions(Explosion e) {
		for (Missile m : enemyMissiles) {
			double a = e.getY() - m.getY();
			double b = e.getX() - m.getX();

			if (Math.sqrt((a * a + b * b)) < e.getR()) {
				m.exploded(true);
			}
		}
	}

	void moveMissiles() {
		for (int i = 0; i < missiles.size(); i++) {
			Missile m = missiles.get(i);
			if (m.done()) {
				explodeMissile(m);
				missiles.remove(m);
				v.deleteScreenElement(m);
			} else {
				m.move();
			}
		}

		for (int i = 0; i < enemyMissiles.size(); i++) {
			Missile m = enemyMissiles.get(i);
			if (m.done()) {
				explodeMissile(m);
				enemyMissiles.remove(m);
				v.deleteScreenElement(m);
			} else {
				m.move();
				cities.removeAll(reachedCity(m));
			}
		}

		for (int i = 0; i < explosions.size(); i++) {
			Explosion e = explosions.get(i);
			if (e.done()) {
				explosions.remove(e);
				v.deleteScreenElement(e);
			} else {
				e.move();
				collisions(e);
			}
		}
	}

	void fireEnemy() {
		if (Math.random() * 100 > 99.5) {
			int bx = (int) ((Math.random() * 1000) % 480 + 20);
			int ex = (int) ((Math.random() * 1000) % 480 + 20);
			Missile m = new Missile(bx, 0, ex, 500, 0.5);
			enemyMissiles.add(m);
			v.addScreenElement(m);
		}
	}

	private void explodeMissile(Missile m) {
		Explosion e = new Explosion(m.getX(), m.getY());
		explosions.add(e);
		v.addScreenElement(e);
	}

	private void setCities() {
		City c = new City(41, 450, 41, 50);
		cities.add(c);
		v.addScreenElement(c);
		int x = 0;
		for (int i = 0; i < 3; i++) {
			x = (50 + x) + 50 * 2;
			City c2 = new City(x, 450, 41, 50);
			cities.add(c2);
			v.addScreenElement(c2);
		}
	}

	private List<City> reachedCity(Missile m) {
		List<City> citiesExploded = new Vector<City>();
		for (City c : cities) {
			if (c.getRectangle().contains(m.getX(), m.getY())) {
				m.exploded(true);
				c.setDestroyed(true);
				citiesExploded.add(c);
			}
		}
		return citiesExploded;
	}

	public void shootMissile(int x, int y) {
		Missile m = new Missile(base.getX(), base.getY(), x, y, 5);
		missiles.add(m);
		v.addScreenElement(m);
	}

	public void aimGun(int x, int y) {
		base.aimGun(x, y);
	}
}
