package org.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

public class Mouse2 extends JFrame implements Runnable{
	private int x,y;
	private Image dbImage;
	private Graphics dbg;
	private boolean mouseDragged;
	
	public Mouse2() {
		x = 20;
		y = 20;
		setTitle("game");
		setSize(200,200);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				x = e.getX();
				y = e.getY();
				e.consume();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void paint(Graphics g){
		dbImage = createImage(getWidth(),getHeight());
		dbg = dbImage.getGraphics();
		paintComponent(dbg);
		g.drawImage(dbImage, 0,0, this);
	}
	
	public void paintComponent(Graphics g){
		g.drawLine(getWidth()/2, getHeight(), x, y);

		repaint();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}



}
