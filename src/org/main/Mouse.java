package org.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

public class Mouse extends JFrame implements MouseMotionListener{
	private int x,y;
	private Image dbImage;
	private Graphics dbg;
	private boolean mouseDragged;
	
	public Mouse() {
		x = 20;
		y = 20;
		setTitle("game");
		setSize(200,200);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addMouseMotionListener(this);
	}
	
	public void paint(Graphics g){
		dbImage = createImage(getWidth(),getHeight());
		dbg = dbImage.getGraphics();
		paintComponent(dbg);
		g.drawImage(dbImage, 0,0, this);
	}
	
	public void paintComponent(Graphics g){
		if(mouseDragged){
			g.setColor(Color.DARK_GRAY);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(x,y, 20, 20);
		}else{
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.DARK_GRAY);
			g.fillRect(x,y, 20, 20);
		}

		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		mouseDragged = true;
		e.consume();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		mouseDragged = false;
		e.consume();
		
	}

}
