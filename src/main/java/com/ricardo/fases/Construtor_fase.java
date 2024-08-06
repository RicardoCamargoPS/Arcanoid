package com.ricardo.fases;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.ricardo.entidades.Bloco;
import com.ricardo.main.Game;
import com.ricardo.main.TesteColisao;

public class Construtor_fase {

	private final int POS_X_INIT = 11, POS_Y_INIT = 20;
	public static Color[] cores; 


	public static List<Bloco> blocos;
	
	private TesteColisao estaColidindo;

	private int largura, altura;


	public void setBlocos(){

	}


	public Construtor_fase() {		

		cores = new Color[7];	
		cores[0] = new Color(106,190, 48,255); // Verde
		cores[1] = new Color(215,123,186,255); // Rosa
		cores[2] = new Color(91,110,225,255); // Azul
		cores[3] = new Color(118, 66,138,255); // Roxo
		cores[4] = new Color(223,113, 38,255); // Laranja
		cores[5] = new Color(172, 50, 50,255); // Vermelho
		cores[6] = new Color(105,106,106,255); // Cinza
		
		estaColidindo = new TesteColisao();			
		blocos = new ArrayList<Bloco>();
	}

	public void tick() {		
		for(int x = 0; x < blocos.size(); x++) {
			blocos.get(x).tick();		
			estaColidindo.blocoColisao(blocos.get(x), Game.bola);
		}	
		for(int x = 0; x < blocos.size(); x++) {	
			if(blocos.get(x).def == 0) {
				blocos.remove(x);		
				score();
			}					
		}	
	}

	public void render(Graphics g) {				
		for(int x = 0; x < blocos.size(); x++) {
			blocos.get(x).render(g);

		}		
	}

	public void limpaFase(){

		for(int x = 0; x < blocos.size(); x++) {			
			blocos.remove(x);										
		}	
	}

	public void setFase(List<Bloco> b){
		for(int x = 0; x < b.size(); x++) {
			blocos.add(b.get(x));
		}	
	}

	public void geraFases(String path) {		

		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			largura = map.getWidth();
			altura = map.getHeight();
			int px = POS_X_INIT, py = POS_Y_INIT;
			int def = 1;
			for(int xx = 0; xx < largura ; xx++) {
				for(int yy = 0; yy < altura; yy++) {	

					if(map.getRGB(xx, yy) != 0) {
						if(map.getRGB(xx, yy) == cores[0].getRGB()) {
							def = 1;		

						}else if(map.getRGB(xx, yy) == cores[1].getRGB())
						{
							def	= 2; 
						}else if(map.getRGB(xx, yy) == cores[2].getRGB()) {
							def = 3; 
						}else if(map.getRGB(xx, yy) ==	cores[3].getRGB()) { 
							def = 4; 
						}else if(map.getRGB(xx, yy) == cores[4].getRGB()) { 
							def = 5; 
						}else if(map.getRGB(xx, yy) == cores[5].getRGB()) { 
							def = 6; 
						}else if(map.getRGB(xx, yy) == cores[6].getRGB()) { 
							def	= 7; 
						}else if(map.getRGB(xx, yy) == cores[7].getRGB()) { 
							def = 8; 
						} 

						blocos.add(new Bloco(px,py, 18, 9, def, cores[def - 1]));
											
					}
					py += 10;					
				} 
				px += 19; 
				py = POS_Y_INIT; }


		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}	
	public void score() {		
		Game.PlayerScore.increaseScore(10);		
	}

}
