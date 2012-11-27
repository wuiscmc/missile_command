package org.missile.controller;


import org.missile.model.Logic;
import org.missile.view.Canvas;


/**
 * Controller of the application. 
 * This class will handle all the requests of the view layer and send them to the model, where they will be processed.  
 */
public class Game {

	private Logic logic;
	private Canvas canvas;

	public Game(Logic logic) {
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
	   * Sets the coordinates for a new base and send the creation order to the model.
	   * @param x xCoordinate for the bottom left point of the base
	   * @param y yCoordinate for the bottom left point of the base
	   * @param width Width of the base
	   * @param height Height of the base
	   * @param gunHeigth The height for the base's gun
	   */
	public void addBase(int x, int y, int width, int height, int gunHeigth){
		logic.setBase(x, y, width, height, gunHeigth);
	}
	
	 /**
	   * Sets the coordinates for a new city and sends creation order to the model.
	   * @param x xCoordinate for the bottom left point of the base
	   * @param y yCoordinate for the bottom left point of the base
	   * @param width Width of the base
	   * @param height Height of the base
	   */
	public void addCity(int x, int y, int width, int height){
		logic.setCity(x,y,width, height);
	}

	 /**
	   * Checks for any collisions and moves the elements
	   */
	public void moveElements() {
		logic.moveElements();
	}
	
	 /**
	   * Launches an enemy missile
	   * @params coordinates and speed of the enemy missile
	   */
	public void shootEnemyMissile(int x0, int y0, int x1, int y1) {
		logic.shootEnemyMissile(x0, y0, x1, y1);
	}
	
	/**
	   * Launches an ally missile.
	   * Coordinates and speed of the missile
	   * @params x xCoordinate targets point 
	   * @params y yCoordinate targets point
	   */
	public void shootMissile(int x, int y) {
		logic.shootAllytMissile(x,y);
	}
	
	public void aimGun(int x, int y) {
		logic.aimGun(x, y);
	}
	
	
}
