package org.missile.model.explosion;

import static org.junit.Assert.*;

import org.junit.Test;
import org.missile.model.explosion.Explosion;

public class ExplosionTest {

	@Test
	public void testMove() {
		Explosion e = new Explosion(10,10);
		assertTrue(e.move());
	}

	@Test
	public void testNotMove() {
		Explosion e = new Explosion(10,10);
		for(int i = 0; i < Explosion.getMaxiumExplosionRadius() * 2; i ++){
			e.move();
		}
		assertFalse(e.move());
	}
	
	
	

}
