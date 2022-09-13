package com.ricardo.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Menu {

	public String[] options = {"novo jogo","carregar jogo","sair"};

	public int currentOption = 0;
	public int maxOption = options.length - 1;

	public static boolean up,down,enter;

	public static boolean pause = false;


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
				Game.gameStatos = "NORMAL";
				pause = false;
			}else if(options[currentOption] == "sair") {
				System.exit(1);
			}
		}
	}

	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		//g2.setColor(new Color(0,0,0,100));
		//g2.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial",Font.BOLD,14));		
		g.drawString(">Arkanoid<", VarGlobais.getGameWidth() / 2 - 47 , VarGlobais.getGameHeight() - 200);

		//Opcoes de menu
		g.setColor(Color.red);
		g.setFont(new Font("arial",Font.BOLD,10));
		if(pause == false)
			g.drawString("Novo jogo", VarGlobais.getGameWidth() / 2 - 30, 160);
		else
			g.drawString("Resumir", VarGlobais.getGameWidth() / 2 - 25, 160);

		g.drawString("Carregar jogo", VarGlobais.getGameWidth() / 2 - 40, 180);
		g.drawString("Sair", VarGlobais.getGameWidth() / 2 - 15, 200);

		if(options[currentOption] == "novo jogo") {
			g.drawString(">", VarGlobais.getGameWidth()/ 2 - 50, 160);
		}else if(options[currentOption] == "carregar jogo") {
			g.drawString(">", VarGlobais.getGameWidth() / 2 - 60, 180);
		}else if(options[currentOption] == "sair") {
			g.drawString(">", VarGlobais.getGameWidth() / 2 - 35, 200);
		}
	}

}
