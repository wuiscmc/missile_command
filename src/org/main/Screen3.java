package org.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;


public class Screen3 extends JFrame implements Runnable{
	private int x,y;
	private int xDirection, yDirection;
	;
	private Image dbImage;
	private Graphics dbg;
	Rectangle bullet;
	int bx, by;
	private boolean readyToFire, shot = false;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Screen3(){
		//addKeyListener(new AL());
		addKeyListener(new AL());
		setTitle("theGame");
		setSize(500, 500);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		x = getWidth() / 2;
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
		//Rectangle r = new Rectangle(rx, ry, 20, 20);
		//Rectangle r2 = new Rectangle(175,75,10,10);
		g.fillRect(x - 20, getHeight() - 20, 50, 10);
		g.fillRect(x , getHeight() - 30, 10, 10);
		
		g.setColor(Color.RED);
		if(shot){
			g.setColor(Color.black);
			g.fillRect(bullet.x, bullet.y, bullet.width, bullet.height);
		}
		repaint();
	}

	public void shoot(){
		if(shot){
			bullet.y--;
		}
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
			if(keyCode == KeyEvent.VK_SPACE){
				if(bullet == null){
					readyToFire = true;
				}else{ // x , getHeight() - 30
					bx = x;
					by = getHeight() - 30;
					bullet = new Rectangle(bx,by,3,5);
					shot = true;
				}
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
			if(keyCode == KeyEvent.VK_SPACE){
				readyToFire = false;
				if(bullet.y <= -5 ){
					bullet = new Rectangle(0,0,0,0);
					shot = false;
					readyToFire = false;
				}
			}
			
		}	
	}
	
	

	private void move(){
		x+= xDirection;
		y+= yDirection;
	}

	@Override
	public void run() {
		while(true){
			
			try {
				shoot();
				move();
				Thread.sleep(5);
			} catch (InterruptedException e) {
				System.out.println("Error");
				e.printStackTrace();
			}
		}
	}
	
}