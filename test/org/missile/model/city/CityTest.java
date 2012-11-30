package org.missile.model.city;

import static org.junit.Assert.*;

import org.junit.Test;
import org.missile.model.city.City;
import org.missile.model.missile.Missile;

public class CityTest {

	@Test
	public void testReached() {
		City b = new City(200, 480, 30, 50); // (200, 480), (230, 510)
		Missile m = new Missile(210, 0, 210, 500); // (200, 0) , (200, 490)		
		while(m.move() && !containsPoint(b, m.getX(), m.getY())){} // we move the missile to till the end or till it hits the base 
		assertTrue(b.reached(m));
	}

	@Test
	public void testNotReached() {
		City c = new City(200, 480, 30, 50); // (200, 480), (230, 510)
		Missile m = new Missile(200, 0, 200, 500); // (200, 0) , (200, 490)		
		while(m.move() && !containsPoint(c, m.getX(), m.getY())){} // we move the missile to till the end or till it hits the base 
		assertFalse(c.reached(m));
	}
	
	private boolean containsPoint(City b, int x, int y){
		int bx1 = b.getX();
		int bx2 = bx1 + b.getWidth();
		
		int by1 = b.getY();
		int by2 = by1 + b.getHeight();
		
		return x < bx2 && x > bx1 && y < by2 && y > by1;
	}
	
}
