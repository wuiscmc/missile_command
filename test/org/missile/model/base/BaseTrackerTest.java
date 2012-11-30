package org.missile.model.base;

import static org.junit.Assert.*;

import org.junit.Test;
import org.missile.model.base.Base;
import org.missile.model.base.BaseTracker;
import org.missile.model.base.NoBasesLeftException;

public class BaseTrackerTest {

	
	@Test
	public void testGetClosestBase() {
		BaseTracker baseTracker = new BaseTracker();
		
		Base base1 = new Base(50, 430, 20, 70, 20);
		Base base2 = new Base(275, 420, 20, 80, 20);
		
		baseTracker.add(base1); //(50, 430), (70, 500)
		baseTracker.add(base2); //(275, 420), (290, 500)
		
		int x = 60, y = 590;
		
		double d1 = distanceBasePoint(base1, x, y);
		double d2 = distanceBasePoint(base2, x, y);
		
		Base closest, shouldClosest = null; 
		if(d1 < d2)
			closest = base1;
		else 
			closest = base2;
		
		try {
			shouldClosest = baseTracker.getClosestBase(x, y);
		} catch (NoBasesLeftException e) {
			shouldClosest = null;
		}
		
		assertEquals(closest, shouldClosest);
	}
	
	
	@Test
	public void testNoBasesLeft() {
		int x = 60, y = 590;
		BaseTracker baseTracker = new BaseTracker();
		Base shouldClosest = new Base(50, 430, 20, 70, 20);
		
		try {
			shouldClosest = baseTracker.getClosestBase(x, y);
		} catch (NoBasesLeftException e) {
			shouldClosest = null;
		}
		
		assertEquals(null, shouldClosest);
	}
	
	
	private double vectorialDistance(int x1, int y1, int x2, int y2) {
		int dx = x1 - x2;
		int dy = y1 - y2;
		return Math.sqrt(dx * dx + dy * dy);
	}
	
	private double distanceBasePoint(Base base, int x, int y){
		int bx = base.getBX() + (base.getWidth() / 2); 
		int by = base.getBY() + (base.getHeight() / 2);
		return vectorialDistance(bx, by, x, y);
	}

}
