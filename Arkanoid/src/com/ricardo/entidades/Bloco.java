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
	 
	
	public Bloco(int px, int py, int width, int height, int def) {
		
		this.px = px; 
		this.py = py;
		this.width = width;
		this.height = height;
		this.def = def;
		
		
	}

	@Override
	public void tick() {
		
		if(def == 1) {
			cor = Color.green;
		}else if(def == 2){
			cor = Color.pink;
		}else if(def == 3){
			cor = Color.blue;
		}else if(def == 4){
			cor = Color.yellow;
		}else if(def == 5){
			cor = Color.magenta;
		}else if(def == 6){
			cor = Color.orange;
		}else if(def == 7){
			cor = Color.red;
		}else
			cor = Color.gray;
		
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
