package org.main;



public class Main  {
    public static void main( String [] args ) {
    	Screen3 screen = new Screen3();
    	Thread th = new Thread(screen);
    	th.start();
    }
}
