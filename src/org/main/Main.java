package org.main;



public class Main  {
    public static void main( String [] args ) {
    	Screen screen = new Screen();
    	Thread th = new Thread(screen);
    	th.start();
    }
}
