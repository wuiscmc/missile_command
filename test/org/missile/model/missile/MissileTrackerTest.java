package org.missile.model.missile;

import static org.junit.Assert.*;

import org.junit.Test;
import org.missile.model.city.City;
import org.missile.model.explosion.ExplosionsTracker;
import org.missile.model.missile.Missile;
import org.missile.model.missile.MissileHitable;
import org.missile.model.missile.MissilesTracker;

public class MissileTrackerTest {

	@Test
	public void shouldItAddMissiles() {
		MissilesTracker missileTracker = new MissilesTracker();
		
		missileTracker.add(new Missile(0,0,500,500));
		missileTracker.add(new Missile(20,20,250,250));
		missileTracker.add(new Missile(40,40,300,100));
		missileTracker.add(new Missile(60,60,90,90));
	}

	@Test
	public void shouldTheMissilesBeRemovedWhenTheyFinish() {
		MissilesTracker missileTracker = new MissilesTracker();
		
		missileTracker.add(new Missile(0,0,500,500));
		missileTracker.add(new Missile(20,20,250,250));
		missileTracker.add(new Missile(40,40,300,100));
		missileTracker.add(new Missile(60,60,90,90));
		
		for(int i = 0; i < 1000; i++)
			missileTracker.check();
		
		assertEquals(0, missileTracker.size());
	}

	@Test
	public void shouldTheMissilesExplodeWhenTheyFinish() {
		MissilesTracker missileTracker = new MissilesTracker();
		ExplosionsTracker explosionsTracker = new ExplosionsTracker();
		missileTracker.addObserver(explosionsTracker);
		
		missileTracker.add(new Missile(0,0,500,500));
		missileTracker.add(new Missile(20,20,250,250));
		missileTracker.add(new Missile(40,40,300,100));
		missileTracker.add(new Missile(60,60,90,90));
		
		
		for(int i = 0; i < 1000; i++)
			missileTracker.check();
		
		assertEquals(4, explosionsTracker.size());
	}

	
	@Test
	public void testHitsMissileHitable() {
		MissilesTracker missileTracker = new MissilesTracker();
		
		missileTracker.add(new Missile(0,0,500,500));
		missileTracker.add(new Missile(20,20,250,250));
		
		MissileHitable c = new City(200,200,200,200);
		boolean hit = false;
		for(int i = 0; i < 1000; i ++){
			if(missileTracker.hits(c))
				hit = true;
			missileTracker.check();
		}
		
		assertTrue(hit);
	}
	


}
