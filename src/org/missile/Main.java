package org.missile;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread c = new Thread(new Canvas());
		c.start();
	}

}
