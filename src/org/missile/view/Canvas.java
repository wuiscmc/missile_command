package org.missile.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;

import org.missile.controller.Game;
import org.missile.model.Base;
import org.missile.model.City;
import org.missile.model.Explosion;
import org.missile.model.LogicObserver;
import org.missile.model.Missile;

public class Canvas extends JFrame implements Runnable, Drawer, LogicObserver {
	
	private Image img;
	private Graphics dbg;
	private Game controller;
	private List<Drawable> screenElement;

	/**
	 * @param args
	 */
	public Canvas(Game c, int width, int height) {
		
		controller = c;
		
		setSize(width, height);
		setTitle("Missile command");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				controller.shootMissile(e.getX(), e.getY());
			};
		});

		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				controller.aimGun(e.getX(), e.getY());
			}
		});
		
	}


	public void run() {
		int baseHeight = 50, baseWidth = 50, gunHeight = 20; 
		
		controller.addBase(150, getHeight() - baseHeight, baseWidth, baseHeight, gunHeight);
		controller.addBase(275, getHeight() - baseHeight, baseWidth, baseHeight, gunHeight);
		controller.addBase(400, getHeight() - baseHeight, baseWidth, baseHeight, gunHeight);
		
		
		
		while (true) {
			try {
				int ix = (int) ((Math.random() * 1000) % getWidth() - 20 + 20);
				int dx = (int) ((Math.random() * 1000) % getWidth() - 20 + 20);
				controller.shootEnemyMissile(ix, 0, dx, 500);
				controller.moveElements();
				Thread.sleep(15);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
	public void paint(Graphics g) {
		img = createImage(getWidth(), getHeight());
		dbg = img.getGraphics();
		paintComponent(dbg);
		g.drawImage(img, 0, 0, this);
	}



	public void paintComponent(Graphics g) {
		if(screenElement != null){
			for (int i = 0; i < screenElement.size(); i++) {
				//screenElement.get(i).draw(g);
				drawElement(g, screenElement.get(i));
			}
		}
		repaint();
	}

	@Override
	public void drawElement(Graphics g, Drawable d) {
		if (d instanceof Base) {
			Base b = (Base) d;
			g.drawLine(b.getIx(), b.getIy(), b.getX(), b.getY());
			g.drawRect(b.getBx(), b.getBy(), b.getWidth(), b.getHeigth());
		}
		
		if (d instanceof Explosion) {
			Explosion e = (Explosion) d;
			g.fillOval(e.getX() - e.getR() / 2, e.getY() - e.getR() / 2, e.getR(), e.getR());
		}
		
		if (d instanceof Missile) {
			Missile m = (Missile) d;
			g.drawLine(m.getIX(), m.getIY(), m.getX(), m.getY());
		}

		if (d instanceof City) {
			City c = (City) d;
			g.drawRect(c.getX(), c.getY(), c.getWidth(), c.getHeight());
		}
	}

	@Override
	public void newElement(Drawable d) {
		if(screenElement == null) screenElement = new Vector<Drawable>();
		screenElement.add(d);
		
	}


	@Override
	public void removeElement(Drawable d) {
		screenElement.remove(d);
	}


	@Override
	public void removeElementCollection(List<Drawable> list) {
		screenElement.removeAll(list);
	}


}
