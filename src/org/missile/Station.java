package org.missile;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;

public class Station {

	private int x,y;
	private Rectangle missile;
	private boolean shot;
	
	public Station(){
		
	}
	
	public void draw(Graphics g){
		g.drawRect(150, 20, 150, 10);
	}
	
	
	

}
