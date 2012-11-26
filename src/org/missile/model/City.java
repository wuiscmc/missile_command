package org.missile.model;

import java.awt.Graphics;
import java.awt.Rectangle;

public class City implements Drawable {
	private boolean destroyed;
	private Rectangle r;
	public City(int x, int y , int w, int h){
		r = new Rectangle(x,y,w,h);
		destroyed = false;
	}
	
	public void draw(Graphics g){
		g.drawRect(r.x, r.y, r.width, r.height);
	}
	
	public Rectangle getRectangle(){
		return r;
	}
	
	public void setDestroyed(boolean destroyed){
		this.destroyed = destroyed;
	}
	
	public boolean getDestroyed(){
		return destroyed;
	}
}
