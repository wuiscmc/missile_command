package org.missile.model.template;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * Template for a {@link GameElement} collection handler.
 * <p>
 * This class represents a list of GameElements. It notifies to its subscribers
 * when an element is created or removed. In that way, we can asynchronously
 * update the state of any object that listens to this changes.
 * <p>
 * It would be useful in case we would like to create a statistics or scores
 * punctuation system in real time for example.
 * 
 * @author Luis Carlos Mateos
 * 
 * @see GameElement
 */
public class GameElementTracker implements Iterable<GameElement> {

	protected List<GameElement> elements;
	protected List<GameElementObserver> observers;

	public GameElementTracker() {
		observers = new Vector<GameElementObserver>();
		elements = new Vector<GameElement>();
	}

	/**
	 * Adds a new GameElement to the collection and notifies to the subscribers.
	 * 
	 * @param b
	 *            {@link GameElement} to be added
	 */
	public void add(GameElement b) {
		elements.add(b);
		notifyAdded(b);
	}

	/**
	 * Removes a GameElement from the collection and notifies to the
	 * subscribers.
	 * 
	 * @param b
	 *            {@link GameElement} to be removed
	 */
	public void remove(GameElement b) {
		elements.remove(b);
		notifyRemoved(b);
	}

	/**
	 * Removes a list of GameElements from the collection and sends a
	 * notification per element to all the subscribers
	 * 
	 * @param elements
	 *            List of {@link GameElement} to be removed
	 */
	public void removeAll(List<GameElement> elements) {
		this.elements.removeAll(elements);
		for (GameElement b : elements) {
			notifyRemoved(b);
		}
	}

	/**
	 * @return integer. the size of the collection list
	 */
	public int size(){
		return elements.size();
	}
	
	/**
	 * It notifies to the observers about a new element was added to the
	 * collection
	 * 
	 * @param b
	 *            {@link GameElement} added
	 */
	public void notifyAdded(GameElement b) {
		for (GameElementObserver observer : observers) {
			observer.addedGameElement(b);
		}
	}

	/**
	 * It notifies to the observers about an element was removed from the
	 * collection
	 * 
	 * @param b
	 *            {@link GameElement} removed
	 */
	public void notifyRemoved(GameElement b) {
		for (GameElementObserver observer : observers) {
			observer.removedGameElement(b);
		}
	}

	/**
	 * Allows to a {@link GameElementObserver} to subscribe to the notifications
	 * of this class
	 * 
	 * @param b
	 *            {@link GameElementObserver} to subscribe 
	 */
	public void addObserver(GameElementObserver observer) {
		observers.add(observer);
	}

	
	/**
	 * Removes a {@link GameElementObserver} from the susbcription list
	 * 
	 * @param b
	 *            {@link GameElementObserver} to remove 
	 */
	public void removeObserver(GameElementObserver observer) {
		observers.remove(observer);
	}

	@Override
	public Iterator<GameElement> iterator() {
		return elements.iterator();
	}

}
