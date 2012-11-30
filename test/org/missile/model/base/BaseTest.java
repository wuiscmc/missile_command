package org.missile.model.base;

import static org.junit.Assert.*;

import org.junit.Test;
import org.missile.model.base.Base;
import org.missile.model.missile.Missile;

public class BaseTest {

	
	@Test
	public void testAimGun() {
		int gundx = 200, gundy = 200, gunLength = 20;
		Base b = new Base(200, 480, 30, 50, 20);
		
		double angle = calculateAngle(b.getGunIX(), b.getGunIY(), gundx, gundy);
		
		double guncx = Math.cos(angle) * gunLength + b.getGunIX();
		double guncy = Math.sin(angle) * gunLength + b.getGunIY();
		
		b.aimGun(gundx, gundy);
		
		assertEquals((int) guncx, b.getGunCX());
		assertEquals((int) guncy, b.getGunCY());
	}

	@Test
	public void testDistanceBase() {
		Base b = new Base(200, 480, 30, 50, 20);
		
		int middlex = (b.getWidth() / 2) + b.getBX();
		int middley = (b.getHeight() / 2) + b.getBY();
		
		double distance = vectorialDistance(middlex, middley, 280, 280);

		assertTrue(distance == b.distanceBase(280, 280));
	}

	@Test
	public void testReached() {
		Base b = new Base(200, 480, 30, 50, 20); // (200, 480), (230, 510)
		Missile m = new Missile(210, 0, 210, 500); // (200, 0) , (200, 490)		
		while(m.move() && !containsPoint(b, m.getX(), m.getY())){} // we move the missile to till the end or till it hits the base 
		
		assertTrue(b.reached(m));
	}
	
	@Test
	public void testNotReached() {
		Base b = new Base(200, 480, 30, 50, 20); // (200, 480), (230, 510)
		Missile m = new Missile(200, 0, 200, 460); // (200, 0) , (200, 460)
		while(m.move() && !containsPoint(b, m.getX(), m.getY())){} // we move the missile to till the end or till it hits the base

		assertFalse(b.reached(m));
	}

	
	
	private double vectorialDistance(int x1, int y1, int x2, int y2) {
		int dx = x1 - x2;
		int dy = y1 - y2;
		return Math.sqrt(dx * dx + dy * dy);
	}

	private double calculateAngle(int x1, int y1, int x2, int y2) {
		double dx = x1 - x2;
		double dy = y1 - y2;
		return Math.atan2(dy, dx) + Math.PI;
	}

	private boolean containsPoint(Base b, int x, int y){
		int bx1 = b.getBX();
		int bx2 = bx1 + b.getWidth();
		
		int by1 = b.getBY();
		int by2 = by1 + b.getHeight();
		
		return x < bx2 && x > bx1 && y < by2 && y > by1;
	}

}
