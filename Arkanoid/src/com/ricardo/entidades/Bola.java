package com.ricardo.entidades;

import java.awt.Color;
import java.awt.Graphics;
import com.ricardo.main.VarGlobais;

public class Bola extends GameObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int dx = 1, dy = 1;
	public double vel = 3;
	

	public Bola(int px, int py, int width, int height) {
		this.px = px;
		this.py = py; 
		this.width = width; 
		this.height = height;
	}

	@Override
	public void tick() {
		
		px += dx * vel;
		py += dy * vel;
		
		if(ladoDir() > VarGlobais.getGameWidth()) {			
			dx *= -1;			
		}
		else if(ladoEsq() < 0) {
			dx *= -1;
		}
		else if(ladoCim() < 0) {
			dy *= -1;
		}
		
		
	}

	@Override
	public void render(Graphics g) {

		g.setColor(new Color(232, 184, 41));
		g.fillOval(px, py, width , height);

		g.setColor(Color.white);
		g.fillOval(px+3, py+1, width - 4, height - 4);
	}

}
