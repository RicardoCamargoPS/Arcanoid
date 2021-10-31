package com.ricardo.entidades;

import java.awt.Color;
import java.awt.Graphics;
import com.ricardo.main.VarGlobais;

public class Player extends GameObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public boolean direita = false, esquerda = false, isMuve = false;
	private static int dx = 1;
	private static double vel = 0.4;
	
	public Player(int px, int py) {
		
		this.px = px;
		this.py = py;
		width = 40;
		height = 12;
		
	}

	@Override
	public void tick() {
		
		if(ladoEsq() <= 0) {
			px = 0;
		} else if (ladoDir() >= VarGlobais.getGameWidth()) {
			px = VarGlobais.getGameWidth() - width;
		}		
		
		if(isMuve)
			px +=  dx * vel * 6;
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(px, py, width, height);		
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
	
}
