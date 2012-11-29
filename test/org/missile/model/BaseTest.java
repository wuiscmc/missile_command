package org.missile.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class BaseTest {

	@Test
	public void testAimGun() {
		Base b = new Base(200, 480, 30, 50, 20);
		int x = 0, y = 0;
		double dy = 200 + x;
		double dx = 200 + y;

		double angle = Math.atan2(dy, dx) + Math.PI;

		double gx = Math.cos(angle) * 20 + 200;
		double gy = Math.sin(angle) * 20 + 480;
		
		b.aimGun(x, y);
		
		assertEquals((int)gx, b.getX());
		assertEquals((int)gy, b.getY());
	}

	@Test
	public void testDistanceBase() {
		Base b = new Base(200, 480, 30, 50, 20);
		int x = 0, y = 0; 
		int dx = (200 + 50 / 2) - x;
		int dy = (480 + 50 / 2) - y;
		double distance = Math.sqrt(dx * dx + dy * dy);
		
		assertTrue(distance == b.distanceBase(0, 0));
	}

}
