package org.missile.model.base;

/**
 * Defines the NoBasesLeftException.
 * <p>
 * This exception is meant to be thrown when the number of bases is 0
 * 
 * @author Luis Carlos Mateos
 * 
 */
public class NoBasesLeftException extends Exception {

	/**
	 * It prints a message stating that there are no bases.
	 */
	public String toString() {
		return "Exception: There are no bases left. Game over";
	}
}
