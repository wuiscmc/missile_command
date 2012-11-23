package org.missile;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JFrame;

public class Canvas extends JFrame implements Runnable{
	
	private int x, y;
	private Rectangle r;
	private Station station;
	private boolean ready;
	private Image img;
	private Graphics dbg;
	private boolean shoot;
	private Vector<Missile> missiles;
	
	/**
	 * @param args
	 */
	public Canvas() {
		
		missiles = new Vector<Missile>();

		setSize(500,500);
		setTitle("Missile command");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e){
				missiles.add(new Missile(250, 500, e.getX(), e.getY(), 5));
			};
		});
				
	}
	
	public void paint(Graphics g){
		img = createImage(getWidth(), getHeight());
		dbg = img.getGraphics();
		paintComponent(dbg);
		g.drawImage(img,0,0,this);
	}
	
	public void paintComponent(Graphics g){		
		
		for(int i = 0; i < missiles.size(); i ++){
			missiles.get(i).draw(g);
		}
		
		repaint();
	}
	
	
	private void fireEnemy(){
		if(Math.random()*100 > 99){
			int bx = (int) ((Math.random()*1000)%480 + 20);
			int ex = (int) ((Math.random()*1000)%480 + 20);
			missiles.add(new Missile(bx,0,ex,500, 0.5));
		}
	}
	
	private void moveMissiles(){
		for(int i = 0; i < missiles.size(); i ++){
			if(!missiles.get(i).done()){
				missiles.get(i).move();
			}else{
				
				Missile m = missiles.remove(i);
				m = null;
			}
		}
	}
	
	public void run(){
		
		while(true){
			
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


}
