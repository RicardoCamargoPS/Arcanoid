package com.ricardo.entidades;

import java.awt.Color;
import java.awt.Graphics;
import com.ricardo.main.Game;

public class Player extends GameObject {			
	
	public boolean  isMoving;
	private int dx;
	private double vel;
	private Color cor = Color.blue;
	
	
	public Player() {
		initPlayer();		
	}

	public void initPlayer(){
		
		this.px = 61;
		this.py = 295;
		this.width = 60;
		this.height = 6;
		dx = 0;
		vel = 0.4;
	}
	public void resetPlayer(){
		initPlayer();
	}

	@Override
	public void tick() {
		
		if(ladoEsq() <= 0) {
			px = 0;
		} else if (ladoDir() >= Game.getWidth()) {
			px = Game.getWidth() - width;
		}		
		
		if(isMoving) {
			px +=  dx * vel * 6;
		}
				
	}

	@Override
	public void render(Graphics g) {
		g.setColor(cor);
		g.fillRect(px, py, width, height);		

		int r = cor.getRGB();
		r -= 022031000;		

		g.setColor(new Color(r));
		g.drawRect(px, py, width , height);
	}

	public void setVel(double vel) {
		this.vel = vel;
	}
	
	public void setMoving(boolean moving) {
		this.isMoving = moving;
	}

	@Override
	public String toString() {
		return super.toString() + vel;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

}
