package org.main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;


public class Screen extends JFrame implements Runnable{
	private int x,y;
	private int xDirection, yDirection;
	private Image dbImage;
	private Graphics dbg;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Screen(){
		addKeyListener(new AL());
		setTitle("theGame");
		setSize(500, 500);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		x = 20;
		y = 20;
	}
	

	public void setxDirection(int xDirection) {
		this.xDirection = xDirection;
	}

	public void setyDirection(int yDirection) {
		this.yDirection = yDirection;
	}

	public void paint(Graphics g){
		dbImage = createImage(getWidth(), getHeight());
		dbg = dbImage.getGraphics();
		paintComponent(dbg);
		g.drawImage(dbImage, 0, 0, this);
	}
	
	public void paintComponent(Graphics g){
		g.fillOval(x, y, 20, 20);
		repaint();
	}
	
	public class AL extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			if(keyCode == KeyEvent.VK_LEFT){
				setxDirection(-1);
			}
			if(keyCode == KeyEvent.VK_RIGHT){
				setxDirection(1);
			}
			if(keyCode == KeyEvent.VK_UP){
				setyDirection(-1);
			}
			
			if(keyCode == KeyEvent.VK_DOWN){
				setyDirection(+1);
			}	
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			int keyCode = e.getKeyCode();
			if(keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT){
				setxDirection(0);
			}
			if(keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN ){
				setyDirection(0);
			}
			
		}	
	}
	
	private void move(){
		x+= xDirection;
		y+= yDirection;
		if(x >= 480) x = 480;
		if(x <= 0 ) x = 0;
		if(y >= 480 ) y = 480;
		if(y <= 20 ) y = 20;
	}

	@Override
	public void run() {
		while(true){
			
			try {
				move();
				Thread.sleep(1);
			} catch (InterruptedException e) {
				System.out.println("Error");
				e.printStackTrace();
			}
		}
	}
	
}