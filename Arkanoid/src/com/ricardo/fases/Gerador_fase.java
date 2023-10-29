package com.ricardo.fases;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.ricardo.entidades.Bloco;
import com.ricardo.main.Game;
import com.ricardo.main.TesteColisao;
import com.ricardo.main.VarGlobais;

public class Gerador_fase {

	public static List<Bloco> blocos;
	
	private TesteColisao estaColidindo;

	private int largura, altura;


	public Gerador_fase() {		
		
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

	public void geraFases(String path) {		

		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			largura = map.getWidth();
			altura = map.getHeight();
			int px = VarGlobais.getPxGrade(), py = VarGlobais.getPyGrade();
			int def = 1;
			for(int xx = 0; xx < largura ; xx++) {
				for(int yy = 0; yy < altura; yy++) {	

					if(map.getRGB(xx, yy) != 0) {
						if(map.getRGB(xx, yy) == Game.cores[0].getRGB()) {
							def = 1;		

						}else if(map.getRGB(xx, yy) == Game.cores[1].getRGB())
						{
							def	= 2; 
						}else if(map.getRGB(xx, yy) == Game.cores[2].getRGB()) {
							def = 3; 
						}else if(map.getRGB(xx, yy) ==	Game.cores[3].getRGB()) { 
							def = 4; 
						}else if(map.getRGB(xx, yy) == Game.cores[4].getRGB()) { 
							def = 5; 
						}else if(map.getRGB(xx, yy) == Game.cores[5].getRGB()) { 
							def = 6; 
						}else if(map.getRGB(xx, yy) == Game.cores[6].getRGB()) { 
							def	= 7; 
						}else if(map.getRGB(xx, yy) == Game.cores[7].getRGB()) { 
							def = 8; 
						} 

						blocos.add(new Bloco(px,py, 18, 9, def, Game.cores[def - 1]));
											
					}
					py += 10;					
				} 
				px += 19; 
				py = VarGlobais.getPyGrade(); }


		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}	
	public void score() {		
		Game.PlayerScore.increaseScore(10);		
	}

}
