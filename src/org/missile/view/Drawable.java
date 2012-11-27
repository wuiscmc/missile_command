package org.missile.view;

import java.awt.Graphics;
import java.util.List;
import java.util.Vector;

public abstract class Drawable {
	
	private List<Drawer> drawers;
	
	public void draw(Graphics g){
		for(Drawer d: drawers){
			d.drawElement(g, this);
		}
	}
	
	public void addDrawer(Drawer d){
		if(drawers == null) drawers = new Vector<Drawer>();
		drawers.add(d);
	}
	
	public void removeDrawer(Drawer d){
		drawers.remove(d);
	}
}
