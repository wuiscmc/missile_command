package org.missile.model;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.missile.model.city.City;


public class GameElementTracker implements Iterable<GameElement> {
	
	protected List<GameElement> elements;
	protected List<GameElementObserver> observers;
	
	public GameElementTracker(){
		observers = new Vector<GameElementObserver>();
		elements = new Vector<GameElement>();
	}
	
	public void add(GameElement b){
		elements.add(b);
		notifyAdded(b);
	}
	
	public void remove(GameElement b){
		elements.remove(b);
		notifyRemoved(b);
	}
	
	public void removeAll(List<GameElement> elements){
		this.elements.removeAll(elements);
		for(GameElement b: elements){
			notifyRemoved(b);
		}
	}
	
	public void notifyAdded(GameElement b){
		for (GameElementObserver observer : observers) {
			observer.addedGameElement(b);
		}
	}
	
	public void notifyRemoved(GameElement b){
		for (GameElementObserver observer : observers) {
			observer.removedGameElement(b);
		}
	}

	public void addObserver(GameElementObserver observer) {
		observers.add(observer);
	}
	
	public void removeObserver(GameElementObserver observer) {
		observers.remove(observer);
	}

	@Override
	public Iterator<GameElement> iterator(){
		return elements.iterator();
	}

	
	
}
