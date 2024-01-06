package com.ricardo.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class UIVida {
	
	String text = "Vidas:";
	public int vida = 3;	
	int px, py;
	Font font;
	
	public UIVida(int px, int py) {
		this.py = py;
		this.px = px;
		font = new Font("Courier New", Font.PLAIN, 12);		
		font = font.deriveFont(12f);
	}
	public void updateVidas() {		
		text = "Vidas: " + vida;		
	}
	public void somaVidas(int aux) {
		vida += aux;
		updateVidas();
	}
	public void resetVidas() {
		vida = 3;
	}
	
	public void tiraVidas() {
		vida--;
	}
	

	public void render(Graphics g) {				
		g.setColor(Color.white);
		g.setFont(font);		
		g.drawString(text, px,	py);		

	}
	
	public void tick() {
		updateVidas();
	}

}
