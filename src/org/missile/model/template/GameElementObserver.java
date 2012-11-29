package org.missile.model.template;

import org.missile.model.city.City;

public interface GameElementObserver {
	void addedGameElement(GameElement c);
	void removedGameElement(GameElement c);

}
