package org.missile;

import java.awt.Graphics;
import java.util.List;
import java.util.Vector;


public class Base implements Drawable{

	private double x,y;
	private int ix, iy;
	private double angle; 
	private int bx, by, heigth, width, gunLength;

	
	public Base(int bx, int by, int heigth, int width, int gunLength){
		
		this.bx = bx;
		this.by = by;
		this.heigth = heigth;
		this.width = width;
		this.gunLength = gunLength;
		
		ix = this.bx+(this.width/2);
		iy = this.by;
		
		x = ix;
		y = iy+this.gunLength;	
	}
	
	public void draw(Graphics g){
		g.drawLine(ix, iy, (int)x, (int)y);
		g.drawRect(bx, by, width, heigth);
	}
	
	private void recalculate(){
		double dy = iy - y;
		double dx = ix - x;
		
		angle = Math.atan2(dy, dx) + Math.PI;
		
		x =  Math.cos(angle) * gunLength + ix;
		y =  Math.sin(angle) * gunLength + iy;
	}
	
	
	public void aimGun(int x, int y){
		this.x = x; 
		this.y = y;
		recalculate();
	}
	
	public int getX(){
		return (int)x;
	}
	
	public int getY(){
		return (int)y;
	}
	
	
	
	
	
}
