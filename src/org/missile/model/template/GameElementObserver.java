package org.missile.model.template;

/**
 * Interface which defines the needed methods to be implemented for the
 * observers of the {@link GameElementTracker}
 * <p>
 * The subscribers will be notified when a element has been added or removed and
 * therefore they could act in consecuence.
 * 
 * @author luizcarlos
 * @see GameElement
 */
public interface GameElementObserver {
	
	void addedGameElement(GameElement c);

	void removedGameElement(GameElement c);

}
