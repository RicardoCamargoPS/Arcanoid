package com.ricardo.entidades;

import java.awt.Color;
import java.awt.Graphics;
import com.ricardo.main.VarGlobais;

public class Bola extends GameObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int dx = 1, dy = 1;
	public double vel = 0.25;
	

	public Bola(int px, int py, int width, int height) {
		this.px = px;
		this.py = py; 
		this.width = width; 
		this.height = height;
	}

	@Override
	public void tick() {
		
		px += dx * vel * (1000/120);
		py += dy * vel * (1000/60);
		
		if(ladoDir() > VarGlobais.getGameWidth()) {			
			dx *= -1;			
		}
		else if(ladoEsq() < 0) {
			dx *= -1;
		}
		if(ladoBai() > VarGlobais.getGameHeight()) {
			dy *= -1;
		}
		else if(ladoCim() < 0) {
			dy *= -1;
		}
		
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(px, py, width, height);
	}

}
