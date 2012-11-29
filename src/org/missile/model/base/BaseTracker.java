package org.missile.model.base;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.missile.model.missile.Missile;
import org.missile.model.template.GameElement;
import org.missile.model.template.GameElementTracker;

public class BaseTracker extends GameElementTracker  {
	

	public void aimGun(int x,int y) throws NoBasesLeftException{
		getClosestBase(x, y).aimGun(x, y);
	}
	

	public Missile shootFromClosestBase(int x, int y) throws NoBasesLeftException {	
		Base b = getClosestBase(x, y);
		return new Missile(b.getX(), b.getY(), x, y, 5); 
	}
	
	private Base getClosestBase(int x, int y) throws NoBasesLeftException{
		Base b = null;
		Iterator<GameElement> iterator = elements.iterator();
		while(iterator.hasNext()) {
			Base base = (Base) iterator.next();
			if (b == null || b.distanceBase(x, y) > base.distanceBase(x, y))
				b = base;
		}
		if(b == null){
			throw new NoBasesLeftException();
		}
		return b;
	}
	
}
