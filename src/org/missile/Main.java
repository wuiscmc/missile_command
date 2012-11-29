package org.missile;

import org.missile.controller.GameController;
import org.missile.model.GameEngine;


public class Main {
	/**
	 * Main function of the system
	 * <p>
	 * It initializes {@link GameController} and passes a reference to a {@link GameEngine} instance. 
	 * @param args
	 * @see GameController
	 * @see GameEngine
	 */
	public static void main(String[] args) {
		GameController c = new GameController(new GameEngine());
		c.startGame();
	}

}
