package com.ricardo.entidades;

import java.awt.Color;
import java.awt.Graphics;
import com.ricardo.main.VarGlobais;

public class Player extends GameObject {			
	
	
	private static final long serialVersionUID = 1L;
	public boolean  isMoving;
	private int dx = 0;
	private double vel = 0.4;
	private Color cor = Color.blue;
	
	
	public Player(int px, int py) {
		
		this.px = px;
		this.py = py;
		this.width = 70;
		this.height = 8;
	}

	@Override
	public void tick() {
		
		if(ladoEsq() <= 0) {
			px = 0;
		} else if (ladoDir() >= VarGlobais.getGameWidth()) {
			px = VarGlobais.getGameWidth() - width;
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
	
	public void setWidth(int width) {
		this.width = width;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public void setVel(double vel) {
		this.vel = vel;
	}
	
	public void setMoving(boolean moving) {
		this.isMoving = moving;
	}
	
}
