package org.missile;

import org.missile.controller.Game;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Game c = new Game(500,500,2,4);
		c.startGame();
	}

}
