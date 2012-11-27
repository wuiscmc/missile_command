package org.missile;

import org.missile.controller.Game;
import org.missile.model.Logic;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Game c = new Game(new Logic());
		c.startGame();
	}

}
