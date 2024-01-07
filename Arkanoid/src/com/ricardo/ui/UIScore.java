package com.ricardo.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class UIScore {
	
	String text = "Score:";
	static int score = 0;	
	int px, py;
	Font font;
	
	public UIScore(int px, int py) {
		this.py = py;
		this.px = px;
		font = new Font("Courier New", Font.PLAIN, 12);		
		font = font.deriveFont(12f);
	}
	void updateScor() {		
		text = "Score: " + score;		
	}
	public void increaseScore(int scor) {
		score += scor;
		updateScor();
	}
	public void resetScore() {
		score = 0;
	}
	public static int getScore(){
		return score;
	}
	

	public void render(Graphics g) {				
		g.setColor(Color.white);
		g.setFont(font);		
		g.drawString(text, px,	py);		

	}
	

}
