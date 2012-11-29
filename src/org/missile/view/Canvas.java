package org.missile.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;

import org.missile.controller.GameController;
import org.missile.model.GameEngineObserver;
import org.missile.model.base.Base;
import org.missile.model.base.NoBasesLeftException;
import org.missile.model.city.City;
import org.missile.model.explosion.Explosion;
import org.missile.model.missile.Missile;

/**
 * View layer of the application.
 * 
 * <p>
 * This class draws a JFrame, displays the elements of the game and listens for
 * user actions. It knows as well how to render the different business objects.
 * 
 * 
 * @author Luis Carlos Mateos
 * 
 */

public class Canvas extends JFrame implements Runnable, GameEngineObserver {

	private Image img;
	private Graphics dbg;
	private GameController controller;
	private List<Drawable> screenElement;
	private boolean end; 
	/**
	 * Constructor of the class
	 * 
	 * @param c
	 *            reference to {@link GameController}
	 * @param width
	 *            width of the canvas
	 * @param height
	 *            width of the canvas
	 */
	public Canvas(GameController c, int width, int height) {
		end = false;
		controller = c;
		setSize(width, height);
		setTitle("Missile command");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/**
		 * The mouse will be the input system for this game, so we will listen
		 * for it's coordinates. Nevertheless, to change it and play with the
		 * keyboard would not be a problem
		 */
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				try{
					controller.shootMissile(event.getX(), event.getY());
				}
				catch(NoBasesLeftException exception){
					end = true;
				}
			};
		});

		/**
		 * We listen for the mouse movements so we can aim the guns in real
		 * time.
		 * 
		 */
		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				try{
					controller.aimGun(e.getX(), e.getY());
				}
				catch(NoBasesLeftException exception){
					end = true;
				}
			}
		});

	}

	/**
	 * Displays the different game elements on the screen.
	 * 
	 */
	public void run() {

		controller.addBase(150, 400, 10, 100, 10);
		controller.addBase(275, 400, 10, 100, 10);
		controller.addBase(400, 400, 10, 100, 10);

		controller.addCity(50, 450, 50, 50);

		while (!end) {
			int ix = (int) ((Math.random() * 1000) % getWidth() - 20 + 20);
			int dx = (int) ((Math.random() * 1000) % getWidth() - 20 + 20);
			controller.shootEnemyMissile(ix, 0, dx, 500);
			controller.moveElements();
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}

	/**
	 * Paint: Double buffering handler
	 * <p>
	 * That means that we need to create an offscreen image, draw to that image
	 * using the image's graphics object, and then calls drawImage using the
	 * target window's graphics object and the offscreen image.
	 * 
	 * <p>
	 * <a href=
	 * "http://docs.oracle.com/javase/tutorial/extra/fullscreen/doublebuf.html"
	 * >Double buffering documentation</a>
	 * 
	 */
	public void paint(Graphics g) {
		img = createImage(getWidth(), getHeight());
		dbg = img.getGraphics();
		paintComponent(dbg);
		g.drawImage(img, 0, 0, this);
	}

	/**
	 * Draws all the screen elements on the JFrame and repaints the screen.
	 * 
	 * @param g
	 *            double buffer Graphics element. Needed to draw all the screen
	 *            elements
	 */
	public void paintComponent(Graphics g) {
		if (screenElement != null && !end) {
			for (int i = 0; i < screenElement.size(); i++) {
				drawElement(g, screenElement.get(i));
			}
		}
		else{
			dbg.drawString("Game over", getWidth()/2 - 20 , getHeight()/2 );
		}
		repaint();
	}

	/**
	 * It renders the {@link Drawable} object in the Canvas.
	 * 
	 * @param g
	 *            {@link Graphics} instance that renders the {@link Drawable}
	 *            object
	 * @param d
	 *            {@link Drawable} object to be rendered.
	 */
	public void drawElement(Graphics g, Drawable d) {
		if (d instanceof Base) {
			Base b = (Base) d;
			g.drawLine(b.getIx(), b.getIy(), b.getX(), b.getY());
			g.drawRect(b.getBx(), b.getBy(), b.getWidth(), b.getHeigth());
		}

		if (d instanceof Explosion) {
			Explosion e = (Explosion) d;
			g.fillOval(e.getX() - e.getR() / 2, e.getY() - e.getR() / 2,
					e.getR(), e.getR());
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

	/**
	 * It adds a new screen element to the Canvas. As well it initializes
	 * screensElements on demand.
	 * 
	 * @param d
	 *            {@link Drawable} object to be added to the canvas
	 */
	public void newElement(Drawable d) {
		if (screenElement == null)
			screenElement = new Vector<Drawable>();
		screenElement.add(d);
	}

	/**
	 * It removes an object from the Canvas
	 * 
	 * @param d
	 *            {@link Drawable} object to be removed
	 */
	public void removeElement(Drawable d) {
		screenElement.remove(d);
	}

	/**
	 * It removes a collection of objects from the Canvas
	 * 
	 * @param list
	 *            List of {@link Drawable} to be removed
	 */
	public void removeElementCollection(List<Drawable> list) {
		screenElement.removeAll(list);
	}

}
