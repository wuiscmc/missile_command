package org.missile.model;

import java.util.List;
import java.util.Vector;

import org.missile.model.base.Base;
import org.missile.model.base.BaseTracker;
import org.missile.model.base.NoBasesLeftException;
import org.missile.model.city.City;
import org.missile.model.explosion.ExplosionsTracker;
import org.missile.model.missile.Missile;
import org.missile.model.missile.MissilesTracker;
import org.missile.model.template.GameElement;
import org.missile.model.template.GameElementObserver;
import org.missile.model.template.GameElementTracker;

/**
 * <p>
 * Model of the application.
 * <p>
 * Contains the logic of the system and know how the business models are related
 * and how to them.
 * <p>
 * This class acts as a facade for the Logic of the application. The reason
 * behind this is the use of the <a
 * href="http://en.wikipedia.org/wiki/Facade_pattern"> Facade Design
 * Pattern</a>.
 * 
 * @author Luis Carlos Mateos
 * 
 */

public class GameEngine implements GameElementObserver {
	
	
	private GameElementTracker cities;
	private BaseTracker bases;
	private ExplosionsTracker explosions; 
	private MissilesTracker missiles; 
	
	/**
	 * List which contains the observers of the events which occur in the game.
	 * These observers will be notified when an ally or enemy missile is
	 * launched, when an explosion happened or when a element has to be removed
	 * of the system.
	 * 
	 */
	private List<GameEngineObserver> observers;

	/**
	 * Defines how improbable is to be attacked. The ease of the game depends
	 * inversely on this value.
	 * 
	 */
	private static double ENEMY_SHOOTING_FACTOR = 98.5;

	/**
	 * Defines how fast a enemy missile will travel.
	 * 
	 */
	private static double ENEMY_MISSILE_DEFAULT_SPEED = 0.5;

	/**
	 * Defines how fast an ally missile will travel.
	 * 
	 */
	private static double ALLY_MISSILE_DEFAULT_SPEED = 5;


	public GameEngine() {
		
		observers = new Vector<GameEngineObserver>();
		
		cities = new GameElementTracker();
		bases = new BaseTracker();
		explosions = new ExplosionsTracker();
		missiles = new MissilesTracker();
		
		bases.addObserver(this);
		explosions.addObserver(this);
		cities.addObserver(this);
		missiles.addObserver(this);
		missiles.addObserver(explosions);	
	}

	/**
	 * Creates a new base
	 * 
	 * @param x
	 *            bottom left point x axis coordinate
	 * @param y
	 *            bottom left point y axis coordinate
	 * @param width
	 * @param height
	 */
	public void addBase(int x, int y, int width, int height, int gunHeigth) {
		bases.add(new Base(x, y, width, height, gunHeigth));
	}

	/**
	 * Creates a new city
	 * 
	 * @param x
	 *            bottom left point x axis coordinate
	 * @param y
	 *            bottom left point y axis coordinate
	 * @param width
	 * @param height
	 */
	public void addCity(int x, int y, int width, int height) {
		cities.add(new City(x, y, width, height));
	}

	/**
	 * Checks the status of the game: explosions, missiles hits, etc. 
	 */
	public void checkGameStatus() {
		missiles.check();
		explosions.check();
		
		List citiesHit  = missiles.hits(cities.iterator());
		cities.removeAll(citiesHit);
		
		List explosionsHit = missiles.hits(explosions.iterator());
		explosions.removeAll(explosionsHit);
		
		List basesHit  = missiles.hits(bases.iterator());
		bases.removeAll(basesHit);
	}


	/**
	 * Shoots an enemy missile with an uncertain probability.
	 * 
	 * @param x0
	 *            origin x axis coordinate
	 * @param y0
	 *            origin y axis coordinate
	 * @param x1
	 *            destination x axis coordinate
	 * @param y1
	 *            destination y axis coordinate
	 * @param shootingFactor
	 *            defines how easy for the enemy is to shoot a missile against
	 *            us. the lower this value is, the harder the game will become.
	 *            A good value is 99.5
	 * @param missileSpeed
	 *            defines how fast a enemy missile will travel. The faster they
	 *            travel, the harder the game is.
	 * 
	 */
	public void shootEnemyMissile(int x0, int y0, int x1, int y1,
			double shootingFactor, double missileSpeed) {
		if (Math.random() * 100 > shootingFactor) {
			missiles.add(new Missile(x0, y0, x1, y1, missileSpeed));
		}
	}

	/**
	 * Shoots an enemy missile with the default parameters
	 * @param x0
	 * @param y0
	 * @param x1
	 * @param y1
	 */
	public void shootEnemyMissile(int x0, int y0, int x1, int y1) {
		shootEnemyMissile(x0, y0, x1, y1, ENEMY_SHOOTING_FACTOR,
				ENEMY_MISSILE_DEFAULT_SPEED);
	}


	/**
	 * Shoots from the closest base an ally missile at one given point.
	 * 
	 * @param x
	 *            x axis coordinate of the shooting point
	 * @param y
	 *            y axis coordinate of the shooting point
	 * @throws NoBasesLeftException 
	 */
	public void shootAllytMissile(int x, int y) throws NoBasesLeftException {
		missiles.add(bases.shootFromClosestBase(x,y));
	}
	
	/**
	 * Gives the order to the closest base to load its gun and aim to a given
	 * coordinates.
	 * 
	 * @param x
	 *            destination x axis coordinate
	 * @param y
	 *            destination y axis coordinate
	 * @throws NoBasesLeftException 
	 */
	public void aimGun(int x, int y) throws NoBasesLeftException {
		bases.aimGun(x, y);
	}
	
	
	/**
	 * Observable model helpers
	 */

	/**
	 * It registers a new observer of the model which will receive notifications
	 * about the state of the system.
	 * 
	 * @param observer
	 */
	public void addObserver(GameEngineObserver observer) {
		observers.add(observer);
	}

	/**
	 * Removes an observer from the observers list
	 * 
	 * @param observer
	 */
	public void removeObserver(GameEngineObserver observer) {
		observers.remove(observer);
	}

	

	@Override
	public void addedGameElement(GameElement c) {
		for (GameEngineObserver observer : observers)
			observer.newElement(c);
		
	}

	@Override
	public void removedGameElement(GameElement c) {
		for (GameEngineObserver observer : observers) {
			observer.removeElement(c);
		}
	}

}
