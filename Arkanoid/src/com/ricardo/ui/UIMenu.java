package com.ricardo.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class UIMenu {
	
	
	private String text = ""; 
	int px, py;
	Font font;

	public UIMenu(int px, int py, String opcao) {
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
}
