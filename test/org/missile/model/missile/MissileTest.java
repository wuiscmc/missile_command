package org.missile.model.missile;

import static org.junit.Assert.*;

import org.junit.Test;
import org.missile.model.missile.Missile;

public class MissileTest {

	@Test
	public void shouldTheMissileReachItsDestiny() {
		
		Missile m = new Missile(0,0,500,500,1);
		
		assertEquals(0,m.getX());
		assertEquals(0,m.getY());
	
		while(m.move()){}
		
		assertTrue( m.getX()  <= 500 && 500 <= m.getX()  );
		assertTrue( m.getX()  <= 500 && 500 <= m.getY()  );
	}
	
	

}
