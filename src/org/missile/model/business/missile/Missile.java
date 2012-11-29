package org.missile.model.business.missile;

import java.util.List;
import java.util.Vector;

import org.missile.model.GameElement;
import org.missile.model.business.explosion.Explosion;
import org.missile.view.Drawable;

/**
 * Logic representation for a missile.
 * <p>
 * A missile is represented by a line which begins at a certain point and then
 * it expands until it reaches a goal point or explodes.
 * 
 * @author Luis Carlos Mateos
 * @see Drawable
 */
public class Missile extends GameElement{

	private int ix, iy, gx, gy;
	private double cx, cy;
	private double dx, dy;
	private double speed;
	private boolean explode;

	/**
	 * Defines the default speed of a missile. By default, 5.
	 */
	private static final int DEFAULT_SPEED = 10;

	/**
	 * Constructor of the Missile class.
	 * <p>
	 * It initializes all the parameters of the missile and calculates a
	 * template needed by the guidance missile guidance system. The movement of
	 * the missile follows the <a
	 * href="http://en.wikipedia.org/wiki/Pythagorean_theorem"> Pythagorean
	 * Theorem</a>. 
	 * 
	 * @param ix
	 *            origin x coordinate
	 * @param iy
	 *            origin y coordinate
	 * @param gx
	 *            destination x coordinate
	 * @param gy
	 *            destination x coordinate
	 * @param speed
	 *            speed
	 */
	public Missile(int ix, int iy, int gx, int gy, double speed) {
		this.speed = speed;
		this.ix = ix;
		this.iy = iy;
		this.gx = gx;
		this.gy = gy;
		this.explode = false;
		cx = ix;
		cy = iy;

		dx = gx - ix;
		dy = Math.abs(gy - iy);

		double distance = Math.sqrt(dx * dx + dy * dy);
		dx = (dx / distance) * this.speed;
		dy = (dy / distance) * this.speed;
	}

	public Missile(int ix, int iy, int gx, int gy) {
		this(ix, iy, gx, gy, DEFAULT_SPEED);
	}

	/**
	 * Moves the missile towards its target.
	 * <p>
	 * Since we have calculated the delta coordinates, we only need to add the
	 * delta value to the current point to know where exactly is the next point.
	 * <p>
	 * It is important to note that this model assumes that the Canvas top-left
	 * coordinate is (0,Y_MAX) and its bottom-right coordinate is (X_MAX,0)
	 */
	public boolean move() {
		boolean canMove = true;
		
		cx = cx + dx;
		if (iy <= gy)
			cy = cy + dy; // means its fired from the top of the window
		else
			cy = cy - dy; // its fired from the bottom
		
		if(done()){
			canMove = false;
		}
		
		return canMove;
	}

	/**
	 * Calculates whether the missile has reached its target or it has marked as
	 * exploded previously
	 * 
	 * @return true if the missile has reached its target or has exploded
	 */
	private boolean done() {
		boolean done = false;
		if (ix >= gx) {
			done = cx <= gx;
		} else {
			done = cx > gx;
		}
		boolean doney = false;
		if (iy <= gy)
			// means its fired from the top of the window
			doney = gy - cy <= 0;
		else
			// its fired from the bottom
			doney = cy - gy < 0;

		return (done && doney) || explode;
	}


	public int getIX(){
		return ix;
	}
	
	public int getIY(){
		return iy;
	}
	
	/**
	 * Getter for cx
	 * 
	 * @return integer. current y coordinate
	 */
	public int getX() {
		return (int) cx;
	}

	/**
	 * Getter for cy
	 * 
	 * @return integer. the current y coordinate
	 */
	public int getY() {
		return (int) cy;
	}


	/**
	 * Getter for dx
	 * @return double. delta y axis coordinate of the missile.
	 */
	public double getDY(){
		return dy;
	}


	/**
	 * @return a {@link Explosion}�that occurs at the point of the missile.
	 */
	public Explosion getExplosion() {
		return new Explosion(getX(), getY());
	}

	public boolean hits(MissileHitable c) {
		boolean hits = c.reached(this);
		if(hits) explode = true;
		return hits;
	}

	
};