package com.ricardo.main;

import com.ricardo.entidades.Bloco;
import com.ricardo.entidades.Bola;
import com.ricardo.entidades.GameObject;
import com.ricardo.entidades.Player;

public class TesteColisao {


	boolean isIntersecting(GameObject obj1, GameObject obj2) {
		return obj1.ladoDir() >= obj2.ladoEsq() && obj1.ladoEsq() <= obj2.ladoDir()
				&& obj1.ladoBai() >= obj2.ladoCim() && obj1.ladoCim() <= obj2.ladoBai();
	}
	


	public void blocoColisao(Bloco bloco, Bola bola) {
		if (!isIntersecting(bloco, bola))
			return;

		double overlapLeft = bola.ladoDir() - bloco.ladoEsq();
		double overlapRight = bloco.ladoDir() - bola.ladoEsq();
		double overlapTop = bola.ladoBai() - bloco.ladoCim();
		double overlapBottom = bloco.ladoBai() - bola.ladoCim();

		boolean ballFromLeft = overlapLeft < overlapRight;
		boolean ballFromTop = overlapTop < overlapBottom;

		double minOverlapX = ballFromLeft ? overlapLeft : overlapRight;
		double minOverlapY = ballFromTop ? overlapTop : overlapBottom;

		if (minOverlapX < minOverlapY) {
			bola.dx = ballFromLeft ? -1 : 1;
		} else {
			bola.dy = ballFromTop ? -1 : 1;
		}
		bloco.def--;		
		//System.out.println(bloco);
	}

	public void playerColisao(Player jogador, Bola bola) {
		if (!isIntersecting(jogador, bola))
			return;

		double overlapLeft = bola.ladoDir() - jogador.ladoEsq();
		double overlapRight = jogador.ladoDir() - bola.ladoEsq();
		boolean ballFromLeft = overlapLeft < overlapRight;
		//System.out.println(ballFromLeft);

		if (ballFromLeft) {
			bola.dx *= ballFromLeft ? -1 : 1;
		}
		
		bola.dy *= -1;
	}
	
	public void fundoColisao(Bola bola) {	

		if(bola.ladoBai() > Game.getHeight()) {
			Game.PlayerVida.tiraVidas();
			Game.PlayerVida.updateVidas();
			
		}
		
	}
}
