package org.missile.model;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class MissileTest {
	
    
	@BeforeClass 
    public static void testSetup(){
	   // Preparation of the unit tests
    }
  
    @AfterClass 
    public static void testCleanup(){
	   // Teardown for data used by the unit tests
    }

	  
	@Test
	public void testMove() {
		Missile tester;

		tester = new Missile(250, 500, 250, 0);

		int x = 250, y = 500;
		double dx = tester.getDX(), dy = tester.getDY();
		
		x = x + (int)dx;
		y = y - (int)dy;
		
		tester.move();
		assertEquals(x, tester.getX());
		assertEquals(y, tester.getY());
		
		x = x + (int)dx;
		y = y - (int)dy;
		tester.move();
		assertEquals(x, tester.getX());
		assertEquals(y, tester.getY());
		
		
		tester = new Missile(250, 0, 250, 500);
		
		x = 250; y = 0;
		dx = tester.getDX();
		dy = tester.getDY();
		
		x = x + (int)dx;
		y = y + (int)dy;
		tester.move();
		assertEquals(x, tester.getX());
		assertEquals(y, tester.getY());
		
		x = x + (int)dx;
		y = y + (int)dy;
		tester.move();
		assertEquals(x, tester.getX());
		assertEquals(y, tester.getY());  
	}

	@Test
	public void testDone() {
		Missile tester = new Missile(250,0,250,500);
		while(tester.getY() != 500) tester.move();
		assertTrue(tester.done());
	}

	@Test
	public void testCollisions() {
		Missile tester = new Missile(270,500,270,0);
		City c = new City(250, 0, 50, 50);
		while(!tester.done()){
			tester.move();
		}
		c.containsPoint(tester.getX(), tester.getY());
	}


}
