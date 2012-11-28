package org.missile;

import org.missile.controller.Game;
import org.missile.model.Logic;


public class Main {
	/**
	 * Main function of the system
	 * <p>
	 * It initializes {@link Game} and passes a reference to a {@link Logic} instance. 
	 * @param args
	 * @see Game
	 * @see Logic
	 */
	public static void main(String[] args) {
		Game c = new Game(new Logic());
		c.startGame();
	}

}
