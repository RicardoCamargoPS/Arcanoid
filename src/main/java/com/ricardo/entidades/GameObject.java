package com.ricardo.entidades;

import java.awt.Graphics;
import java.awt.Rectangle;


public abstract class  GameObject extends Rectangle{

	private static final long serialVersionUID = 1L;
	public int px, py, width, height;

	abstract public void tick();

	abstract public void render(Graphics g);

	public int getPx() {
		return px;
	}

	public void setPx(int px) {
		this.px = px;
	}

	public int getPy() {
		return py;
	}

	public void setPy(int py) {
		this.py = py;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public double getHeight() {
		return  height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Rectangle bounds() {
		return new Rectangle(px, py, width, height);
	}

	public int ladoDir() {

		return px + width;

	}
	public int ladoEsq() {

		return px;

	}
	public int ladoCim() {

		return py;

	}
	public int ladoBai() {

		return py + height;

	}


}
