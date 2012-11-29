package org.missile.controller;

import org.missile.model.GameEngine;
import org.missile.view.Canvas;

/**
 * <p>
 * Controller of the application.
 * 
 * <p>
 * This class will handle all the requests of the view layer and send them to
 * the model, where they will be processed.
 * 
 * @author Luis Carlos Mateos
 * 
 */
public class GameController {

	private GameEngine logic;
	private Canvas canvas;

	public GameController(GameEngine logic) {
		this.logic = logic;
		canvas = new Canvas(this, 500, 500);
		this.logic.registerObserver(canvas);
	}

	/**
	 * Starts a new instance of the game
	 * 
	 */
	public void startGame() {
		Thread c = new Thread(canvas);
		c.start();
	}

	/**
	 * Sets the coordinates for a new base and send the creation order to the
	 * model.
	 * 
	 * @param x
	 *            base's bottom left point x coordinate
	 * @param y
	 *            base's bottom left point y coordinate
	 * @param width
	 *            width of the base
	 * @param height
	 *            height of the base
	 * @param gunHeigth
	 *            the height for the base's gun
	 */
	public void addBase(int x, int y, int width, int height, int gunHeigth) {
		logic.setBase(x, y, width, height, gunHeigth);
	}

	/**
	 * Sets the coordinates for a new city and sends creation order to the
	 * model.
	 * 
	 * @param x
	 *            integer. base's bottom left x axis coordinate.
	 * @param y
	 *            integer. base's bottom left y axis coordinate.
	 * @param width
	 *            integer. width of the base
	 * @param height
	 *            integer. height of the base
	 * 
	 */
	public void addCity(int x, int y, int width, int height) {
		logic.setCity(x, y, width, height);
	}

	/**
	 * Checks for any collisions and moves the elements
	 */
	public void moveElements() {
		logic.moveElements();
	}

	/**
	 * Sends the order to the model to launch an enemy missile
	 * 
	 * @param x0
	 *            integer. origin x axis coordinate.
	 * @param y0
	 *            integer. origin y axis coordinate.
	 * @param x1
	 *            integer. destination x axis coordinate.
	 * @param y1
	 *            integer. destination y axis coordinate.
	 */
	public void shootEnemyMissile(int x0, int y0, int x1, int y1) {
		logic.shootEnemyMissile(x0, y0, x1, y1);
	}

	/**
	 * Launches an ally missile.
	 * 
	 * @param x
	 *            x axis coordinate targets point
	 * @param y
	 *            y axis coordinate targets point
	 */
	public void shootMissile(int x, int y) {
		logic.shootAllytMissile(x, y);
	}

	public void aimGun(int x, int y) {
		logic.aimGun(x, y);
	}

}
