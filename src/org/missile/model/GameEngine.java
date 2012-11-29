package org.missile.model;

import java.util.List;
import java.util.Vector;

import org.missile.model.business.base.Base;
import org.missile.model.business.base.BaseTracker;
import org.missile.model.business.base.NoBasesLeftException;
import org.missile.model.business.explosion.ExplosionsTracker;
import org.missile.model.business.missile.Missile;
import org.missile.model.business.missile.MissilesTracker;
import org.missile.model.city.City;
import org.missile.view.Drawable;

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
	private static double ENEMY_SHOOTING_FACTOR = 99.5;

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
		missiles.addObserver(this);
		cities.addObserver(this);
		
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
		City c = new City(x, y, width, height);
		cities.add(c);
		notifyElementAdded(c);
	}

	/**
	 * Checks whether the elements of the system have reached their goal and/or
	 * they have collided with another element. 
	 */
	public void moveElements() {
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
	public void registerObserver(GameEngineObserver observer) {
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

	/**
	 * This function stores an element into a list and notifies the observers
	 * about a new element has been created in the system.
	 * 
	 * @param element
	 *            a {@link Drawable} element newly created
	 * @param list
	 *            a generic List
	 */
	private void notifyElementAdded(Drawable element) {
		for (GameEngineObserver observer : observers)
			observer.newElement(element);
	}

	/**
	 * Removes an {@link Drawable} element from the given list and notifies
	 * observers. The removal of an element means that element does not exist
	 * anymore and should not be displayed.
	 * 
	 * @param element
	 *            a {@link Drawable} element which will be removed
	 * @param list
	 *            a generic List
	 */
	private void notifyElementRemoved(Drawable element) {
		for (GameEngineObserver observer : observers) {
			observer.removeElement(element);
		}
	}

	/**
	 * Removes a collection of {@link Drawable} elements from a list and
	 * notifies the model observers.
	 * 
	 * @param collection
	 *            List of {@link Drawable}. The collection which will be removed
	 * @param list
	 *            a generic List
	 */
	private void notifyCollectionRemoved(List<Drawable> collection) {
		for (GameEngineObserver observer : observers) {
			observer.removeElementCollection(collection);
		}
	}



	@Override
	public void addedGameElement(GameElement c) {
		notifyElementAdded(c);
		
	}

	@Override
	public void removedGameElement(GameElement c) {
		notifyElementRemoved(c);
	}

}
