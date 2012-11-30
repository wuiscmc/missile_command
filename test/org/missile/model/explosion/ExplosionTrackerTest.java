package org.missile.model.explosion;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;
import org.missile.model.explosion.Explosion;
import org.missile.model.explosion.ExplosionsTracker;
import org.missile.model.template.GameElement;

public class ExplosionTrackerTest {

	@Test
	public void afterFirstCheckAllTheElementsShouldBeAbleToMoveAgain() {
		ExplosionsTracker explosionTracker = new ExplosionsTracker();
		explosionTracker.add(new Explosion(10,10));
		explosionTracker.add(new Explosion(20,40));
		explosionTracker.add(new Explosion(50,50));
		explosionTracker.add(new Explosion(70,70));
		
		explosionTracker.check();
		
		for(Iterator<GameElement> i = explosionTracker.iterator(); i.hasNext(); ){
			assertTrue(((Explosion)i.next()).move());
		}
		assertEquals(4, explosionTracker.size());
		
	}

	@Test
	public void shouldCheckRemoveTheExplosionsAsTheyFinish() {
		ExplosionsTracker explosionTracker = new ExplosionsTracker();
		explosionTracker.add(new Explosion(10,10));
		explosionTracker.add(new Explosion(20,40));
		
		
		for(int i = 0; i < Explosion.getMaxiumExplosionRadius() / 3 ; i ++)
			explosionTracker.check();
		
		explosionTracker.add(new Explosion(50,50));
		explosionTracker.add(new Explosion(70,70));
		
		assertEquals(4, explosionTracker.size());
		
		for(int i = 0; i < Explosion.getMaxiumExplosionRadius() - 1 ; i++)
			explosionTracker.check();
		
		assertEquals(2, explosionTracker.size());
	}
}