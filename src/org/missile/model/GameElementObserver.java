package org.missile.model;

import org.missile.model.city.City;

public interface GameElementObserver {
	void addedGameElement(GameElement c);
	void removedGameElement(GameElement c);

}
