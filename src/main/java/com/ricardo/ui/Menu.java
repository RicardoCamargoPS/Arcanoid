package com.ricardo.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.ricardo.main.Game;

public class Menu {

	public String[] menuMainOptions = {"Novo jogo","Carregar jogo","Sair"};
	public String[] menuPausedOpitions = {"Continuar", "Salvar", "Sair"};
	public String[] menuGameOverOpitions = {"Novo jogo", "Sair"};

	public int currentOption = 0;
	public int maxOption = 0;

	public boolean up,down,enter;

	public boolean pause = false;
	public boolean agardando = false;



	public void tick() {
		if (Game.menuStatos == "Menu"){
			maxOption = menuMainOptions.length - 1;
		} 
		if(Game.menuStatos == "Paused"){
			maxOption = menuPausedOpitions.length - 1;
		} 
		if(Game.menuStatos == "GameOver"){
			maxOption = menuGameOverOpitions.length - 1;
		}

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
			if(menuMainOptions[currentOption] == "Novo jogo" || menuPausedOpitions[currentOption] == "Continuar") {
				Game.menuStatos = "Running";
				pause = false;
			} else if(menuMainOptions[currentOption] == "Carregar jogo"){

			} else if(menuMainOptions[currentOption] == "Sair") {
				System.exit(1);
			}
			else if(menuPausedOpitions[currentOption] == "Salvar"){

			} else if(menuMainOptions[currentOption] == "Sair") {
				
			}
			else if(menuGameOverOpitions[currentOption] == "Novo jogo"){

			} else if(menuGameOverOpitions[currentOption] == "Sair") {
			
			}
		}
		
	}

	public void render(Graphics g) {		
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial",Font.PLAIN,22));		
		g.drawString(" Arkanoid ", Game.getWidth () / 2 - 47 , Game.getHeight() - 150);

		g.setFont(new Font("arial",Font.PLAIN,14));

		if (Game.menuStatos == "Menu"){
			g.drawString("Novo jogo",  Game.getWidth () / 3, 170);
			g.drawString("Carregar jogo",  Game.getWidth () / 3, 190);
			g.drawString("Sair",  Game.getWidth () / 3, 210);

			g.setColor(Color.RED);
			if(menuMainOptions[currentOption] == "Novo jogo") {
				g.drawString(">",  Game.getWidth () / 4, 170);
			}else if(menuMainOptions[currentOption] == "Carregar jogo") {
				g.drawString(">",  Game.getWidth () / 4, 190);
			}else if(menuMainOptions[currentOption] == "Sair") {
				g.drawString(">",  Game.getWidth () / 4, 210);
			}

		} else if(Game.menuStatos == "Paused"){
			g.drawString("Continuar",  Game.getWidth () / 3, 170);
			g.drawString("Salvar",  Game.getWidth () / 3, 190);
			g.drawString("Sair",  Game.getWidth () / 3, 210);

			g.setColor(Color.RED);
			if(menuPausedOpitions[currentOption] == "Continuar") {
				g.drawString(">",  Game.getWidth () / 4, 170);
			}else if(menuPausedOpitions[currentOption] == "Salvar") {
				g.drawString(">",  Game.getWidth () / 4, 190);
			}else if(menuPausedOpitions[currentOption] == "Sair") {
				g.drawString(">",  Game.getWidth () / 4, 210);
			}


		} else if(Game.menuStatos == "GameOver"){
			g.drawString("Novo jogo",  Game.getWidth () / 2 - 40, 180);
			g.drawString("Sair",  Game.getWidth () / 2 - 15, 200);

			if(menuMainOptions[currentOption] == "Novo jogo") {
				g.drawString(">",  Game.getWidth () / 2 - 50, 160);
			}else if(menuMainOptions[currentOption] == "Sair") {
				g.drawString(">",  Game.getWidth () / 2 - 35, 200);
			}

		}				
	}

}
