package org.missile.model.base;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.missile.model.missile.Missile;
import org.missile.model.template.GameElement;
import org.missile.model.template.GameElementTracker;

/**
 * {@link Base} collection handler.
 * <p>
 * This class is a type of {@link GameElementTracker} which controls a
 * {@link Base} collection. It knows how to calculate the closest base to a
 * certain point and where to shoot the defense missiles from.
 * 
 * @author Luis Carlos Mateos
 * @see Missile
 * @see GameElement
 * @see GameElementTracker
 * 
 */
public class BaseTracker extends GameElementTracker {

	/**
	 * Aims the closest base gun to the (x,y) point.
	 * 
	 * @param x
	 *            x coordinate of the point
	 * @param y
	 *            y coordinate of the point
	 * @throws NoBasesLeftException
	 *             when there are no bases left in the game and we call this
	 *             method
	 */
	public void aimGun(int x, int y) throws NoBasesLeftException {
		getClosestBase(x, y).aimGun(x, y);
	}

	/**
	 * Shoots a missile with the closest gun to the (x,y) point.
	 * 
	 * @param x
	 *            x coordinate of the point to shoot
	 * @param y
	 *            y coordinate of the point to shoot
	 * @return the {@link Missile} thrown to the specified point.
	 * @throws NoBasesLeftException
	 *             when there are no bases left in the game and we call this
	 *             method
	 */
	public Missile shootFromClosestBase(int x, int y)
			throws NoBasesLeftException {
		Base b = getClosestBase(x, y);
		return new Missile(b.getGunCX(), b.getGunCY(), x, y, 5);
	}

	/**
	 * Helper that calculates which is the closest {@link Base} to the (x,y)
	 * point.
	 * 
	 * @param x
	 *            x coordinate of the point
	 * @param y
	 *            y coordinate of the point
	 * @return the closest {@link Base} to the (x,y) point.
	 * @throws NoBasesLeftException
	 *             when there are no bases left in the game.
	 */
	public Base getClosestBase(int x, int y) throws NoBasesLeftException {
		Base b = null;
		Iterator<GameElement> iterator = elements.iterator();
		while (iterator.hasNext()) {
			Base base = (Base) iterator.next();
			if (b == null || b.distanceBase(x, y) > base.distanceBase(x, y))
				b = base;
		}
		if (b == null) {
			throw new NoBasesLeftException();
		}
		return b;
	}

}
