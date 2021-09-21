package com.ricardo.entidades;

import java.awt.Color;
import java.awt.Graphics;

public class Bloco extends GameObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color cor;
	public int def;
	
	
	public Bloco(int px, int py, int width, int height, int def, Color cor) {
		
		this.px = px;
		this.py = py;
		this.width = width;
		this.height = height;
		this.def = def;
		this.cor = cor;
		
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(cor);
		g.fillRect(px, py, width, height);
		
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}
}
