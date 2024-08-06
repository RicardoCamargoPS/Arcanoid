package com.ricardo.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.ricardo.main.Game;

public class Menu {

	public String[] options = {"novo jogo","carregar jogo","sair"};

	public int currentOption = 0;
	public int maxOption = options.length - 1;

	public boolean up,down,enter;

	public boolean pause = false;
	public boolean agardando = false;



	public void tick() {
		if(up) {
			up = false;
			currentOption--;
			if(currentOption < 0)
				currentOption = maxOption;
		}
		if(down) {
			down = false;
			currentOption++;
			if(currentOption > maxOption)
				currentOption = 0;
		}
		if(enter) {
			enter = false;
			if(options[currentOption] == "novo jogo" || options[currentOption] == "continuar") {
				Game.menuStatos = "Running";
				pause = false;
			}else if(options[currentOption] == "sair") {
				System.exit(1);
			}
		}
	}

	public void render(Graphics g) {		
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial",Font.BOLD,14));		
		g.drawString(" Arkanoid ", Game.getWidth () / 2 - 47 , Game.getHeight() - 200);

		g.setColor(Color.red);
		g.setFont(new Font("arial",Font.BOLD,10));
		if(pause == false)
			g.drawString("Novo jogo",  Game.getWidth () / 2 - 30, 160);
		else
			g.drawString("Resumir",  Game.getWidth () / 2 - 25, 160);

		g.drawString("Carregar jogo",  Game.getWidth () / 2 - 40, 180);
		g.drawString("Sair",  Game.getWidth () / 2 - 15, 200);

		if(options[currentOption] == "novo jogo") {
			g.drawString(">",  Game.getWidth () / 2 - 50, 160);
		}else if(options[currentOption] == "carregar jogo") {
			g.drawString(">",  Game.getWidth () / 2 - 60, 180);
		}else if(options[currentOption] == "sair") {
			g.drawString(">",  Game.getWidth () / 2 - 35, 200);
		}
	}

}
