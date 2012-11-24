package org.missile;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JFrame;

public class Canvas extends JFrame implements Runnable {

	private Base base;
	private Image img;
	private Graphics dbg;
	private boolean shoot;
	private Vector<Missile> missiles;
	private Vector<Explosion> explosions;
	private Vector<City> cities;
	/**
	 * @param args
	 */
	public Canvas() {

		missiles = new Vector<Missile>();
		explosions = new Vector<Explosion>();
		
		base = new Base(230, 490, 10, 20, 50);
		
		cities = new Vector<City>();
		cities.add(new City(41,450,41,50));
		int x = 0;
		for(int i = 0; i< 3; i ++){
			x = (50 + x) + 50*2;
			cities.add(new City(x,450,41,50));
			
		}
		
		setSize(500, 500);
		setTitle("Missile command");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				base.aimGun(e.getX(),e.getY());
				
				missiles.add(new Missile(base.getX(), base.getY(), e.getX(), e.getY(), 5));
			};
		});
		
		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				base.aimGun(e.getX(),e.getY());
			}
			
		});
	}

	public void paint(Graphics g) {
		img = createImage(getWidth(), getHeight());
		dbg = img.getGraphics();
		paintComponent(dbg);
		g.drawImage(img, 0, 0, this);
		base.draw(g);
	}
	
	public void paintComponent(Graphics g) {

		for (int i = 0; i < explosions.size(); i++) {
			explosions.get(i).draw(g);
		}
		
		for (int i = 0; i < cities.size(); i++) {
			if(!cities.get(i).getDestroyed())
				cities.get(i).draw(g);
			else
				cities.removeElementAt(i);
				
		}
		
		for (int i = 0; i < missiles.size(); i++) {
			missiles.get(i).draw(g);
		}

		repaint();
	}

	private void fireEnemy() {
		if (Math.random() * 100 > 99.5) {
			int bx = (int) ((Math.random() * 1000) % 480 + 20);
			int ex = (int) ((Math.random() * 1000) % 480 + 20);
			missiles.add(new Missile(bx, 0, ex, 500, 0.5));
		}
	}

	private void explodeMissile(Missile m) {
		explosions.add(new Explosion(m.getX(), m.getY()));
	}

	private void collisions(Explosion e) {
		for (Missile m : missiles) {
			double a = e.getY() - m.getY();
			double b = e.getX() - m.getX();
			
			if ( Math.sqrt((a*a + b*b)) <  e.getR() / 3) {
				m.exploded(true);
			}
		}
	}

	private void reachedCity(Missile m){
		for(City c: cities){
			if (c.getRectangle().contains(m.getX(), m.getY())){
				m.exploded(true);
				c.setDestroyed(true);
			}
				
		}
	}
	
	private void moveMissiles() {
		
		for (int i = 0; i < missiles.size(); i++) {
			if (missiles.get(i).done()) {
				Missile m = missiles.get(i);
				explodeMissile(m); 
				missiles.removeElement(m);
			} else {
				missiles.get(i).move();
				reachedCity(missiles.get(i));
			}
		}

		for (int i = 0; i < explosions.size(); i++) {
			if (explosions.get(i).done()) {
				explosions.removeElementAt(i);
			} else {
				collisions(explosions.get(i));
				explosions.get(i).move();
			}
		}
		
	}
	
	public void run() {
		while (true) {

			try {
				fireEnemy();
				moveMissiles();

				Thread.sleep(15);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public class City{
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
}
