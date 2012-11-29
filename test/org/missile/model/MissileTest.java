package org.missile.model;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Vector;

import org.junit.Test;


public class MissileTest {
	  
	@Test
	public void testMove() {
		Missile tester = new Missile(250, 500, 250, 0);
		int x = 250, y = 500;
		double dx = tester.getDX(), dy = tester.getDY();
		
		for(int i = 0; i < 5; i ++){
			x = x + (int)dx;
			y = y - (int)dy;
			tester.move();
		}
		
		assertEquals(x, tester.getX());
		assertEquals(y, tester.getY());
	}

	@Test
	public void testCollision() {
		
		Missile tester = new Missile(220,500,220,20);
		List<City> cities = new Vector<City>();
		cities.add(new City(210, 0, 30, 50));
		
		while(!tester.done()) 
			tester.move();
		
		List collidedCities = tester.collisions(cities);
		
		List testCollidedCities = new Vector<City>();
		for(City c: cities){
			if(c.containsPoint(tester.getX(), tester.getY()))
				testCollidedCities.add(c);
		}
		
		assertEquals(collidedCities, testCollidedCities);
	}

}

