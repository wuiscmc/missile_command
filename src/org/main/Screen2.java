/**
 * 
 */
package org.main;

import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

/**
 * @author lcm
 *
 */
public class Screen2 extends JFrame implements Runnable{
	
	private int x,y;
	private Image dbImage;
	private Graphics dbg;
	private int xDirection;
	private int yDirection;
	
	/**
	 * @throws HeadlessException
	 */
	public Screen2() throws HeadlessException {
		setTitle("theGame");
		setSize(500, 500);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		x = 5;
		y = 5;
		
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				setxDirection(0);
				setyDirection(0);
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				
				switch(arg0.getKeyCode()) {
					case KeyEvent.VK_UP :
						setyDirection(-1);
						break;
						
					case KeyEvent.VK_DOWN :
						setyDirection(1);
						break;
						
					case KeyEvent.VK_LEFT :
						setxDirection(-1);
						break;
						
					case KeyEvent.VK_RIGHT :
						setxDirection(1);
						break;
				}
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void paint(Graphics g){
		dbImage = createImage(getWidth(), getHeight());
		dbg = dbImage.getGraphics();
		
		paintComponent(dbg);
		g.drawImage(dbImage,0,0,this);
	}
	
	public void paintComponent(Graphics g){
		g.fillOval(x, y, 20, 20);
		repaint();
	}

	@Override
	public void run() {
		while(true){
			
			try {
				move();
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private void move() {
		x+= xDirection;
		y+= yDirection;
		if(x >= 480) x = 480;
		if(x <= 0 ) x = 0;
		if(y >= 480 ) y = 480;
		if(y <= 20 ) y = 20;
	}

	public void setxDirection(int xDirection) {
		this.xDirection = xDirection;
	}

	public void setyDirection(int yDirection) {
		this.yDirection = yDirection;
	}

	
	

}
