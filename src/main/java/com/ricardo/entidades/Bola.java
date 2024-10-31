package com.ricardo.entidades;

import java.awt.Color;
import java.awt.Graphics;

import com.ricardo.main.Game;

public class Bola extends GameObject {

	private int dx, dy;
	private int vel;


	public Bola() {
		initBola();		
	}	

	public void initBola(){
		
		this.px = 88;
		this.py = 281; 
		this.width = 7; 
		this.height = 7;
		dx = 1;
		dy = 1;
		vel = 3;
	}
	public void resetBola(){
		initBola();
	}

	@Override
	public void tick() {
		
		px += dx * vel;
		py += dy * vel;
		
		if(ladoDir() > Game.getWidth()) {			
			dx *= -1;			
		}
		else if(ladoEsq() < 0) {
			dx *= -1;
		}
		else if(ladoCim() < 0.5) {
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

	@Override
	public String toString() {
		return super.toString() + dx + dy;
	}



}
