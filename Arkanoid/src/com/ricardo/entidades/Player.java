package com.ricardo.entidades;

import java.awt.Color;
import java.awt.Graphics;

import com.ricardo.main.Game;
import com.ricardo.main.TesteColisao;
import com.ricardo.main.VarGlobais;

public class Player extends GameObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static boolean  isMoving;
	private static int dx = 0;
	private static double vel = 0.4;
	private TesteColisao estaColidindo;
	
	
	public Player(int px, int py) {
		
		this.px = px;
		this.py = py;
		this.width = 40;
		this.height = 12;
		
		estaColidindo = new TesteColisao();
				
	}

	@Override
	public void tick() {
		
		if(ladoEsq() <= 0) {
			px = 0;
		} else if (ladoDir() >= VarGlobais.getGameWidth()) {
			px = VarGlobais.getGameWidth() - width;
		}		
		
		if(isMoving) {
			px +=  dx * vel * 6;
		}
		
		estaColidindo.playerColisao(this, Game.bola);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(px, py, width, height);		
	}
	
	public void setWidth(int width) {
		this.width = width;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public void setVel(double vel) {
		this.vel = vel;
	}
	
	public void setMoving(boolean moving) {
		this.isMoving = moving;
	}
	
}
