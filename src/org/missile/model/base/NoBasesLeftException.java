package org.missile.model.base;

public class NoBasesLeftException extends Exception {
	
    private String strValue;

	public NoBasesLeftException() {
		strValue = "There are no bases left. Game over";
	}

	public String toString() {
	   return "Exception: " + strValue;
    }
}
