package com.ricardo.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class UISeletor {
	
	private String text = ""; 
	int px, py;
	Font font;

	public UISeletor(int px, int py, String opcao) {
		this.py = py;	
		this.px = px;
		this.text = opcao;
		font = new Font("Courier New", Font.PLAIN, 12);		
		font = font.deriveFont(22f);
	}
	
	public void render(Graphics g) {				
		g.setColor(Color.white);
		g.setFont(font);		
		g.drawString(text, px,	py);		

	}

	public int getPy() {
		return py;
	}

	public void setPy(int py) {
		this.py = py;
	}
	
	

}
