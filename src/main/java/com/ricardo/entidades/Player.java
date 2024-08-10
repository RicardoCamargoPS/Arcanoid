package com.ricardo.entidades;

import java.awt.Color;
import java.awt.Graphics;

import com.ricardo.main.Game;

public class Player extends GameObject {			
	
	
	private static final long serialVersionUID = 1L;
	public boolean  isMoving;
	private int dx = 0;
	private double vel = 0.4;
	private Color cor = Color.blue;
	
	
	public Player() {
		
		this.px = 61;
		this.py = 280;
		this.width = 60;
		this.height = 7;
	}

	@Override
	public void tick() {
		
		if(ladoEsq() <= 0) {
			px = 0;
		} else if (ladoDir() >= Game.getWidth()) {
			px = Game.getWidth() - width;
		}		
		
		if(isMoving) {
			px +=  dx * vel * 6;
		}
				
	}

	@Override
	public void render(Graphics g) {
		g.setColor(cor);
		g.fillRect(px, py, width, height);		

		int r = cor.getRGB();
		r -= 022031000;		

		g.setColor(new Color(r));
		g.drawRect(px, py, width , height);
	}

	public void setVel(double vel) {
		this.vel = vel;
	}
	
	public void setMoving(boolean moving) {
		this.isMoving = moving;
	}

	@Override
	public String toString() {
		return super.toString() + isMoving + dx + vel + cor;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

}
