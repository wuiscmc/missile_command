package org.missile;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;

public class View extends JFrame implements Runnable {

	private Image img;
	private Graphics dbg;

	private Controller controller;
	private List<Drawable> screenElement;
	/**
	 * @param args
	 */
	public View(Controller c) {
		controller = c;
		setSize(500, 500);
		setTitle("Missile command");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screenElement = new Vector<Drawable>();
		
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
	
	
	public void paint(Graphics g) {
		img = createImage(getWidth(), getHeight());
		dbg = img.getGraphics();
		paintComponent(dbg);
		g.drawImage(img, 0, 0, this);
	}

	
	public void addScreenElement(Drawable d){
		screenElement.add(d);
	}
	
	public void deleteScreenElement(Drawable d){
		screenElement.remove(d);
	}
	
	
	public void paintComponent(Graphics g) {
		for(Drawable d: screenElement)
			d.draw(g);
		repaint();
	}


	public void run() {
		while (true) {
			try {
				controller.fireEnemy();
				controller.moveMissiles();

				Thread.sleep(15);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
}
