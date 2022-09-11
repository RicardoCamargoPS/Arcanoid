package com.ricardo.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ricardo.entidades.*;
import com.ricardo.ui.*;
import com.ricardo.windowns.Display;

public class Game implements Runnable{

	private Display janela;
	private Thread thread;

	public static List<Bloco> blocos;
	static UIScore PlayerScore;
	static UIMenu novo, continuar;
	static UISeletor seletor;	

	private BufferedImage layer;
	static Bola bola;
	static Player player;
	

	public Game() {

		/**************************NSTANCIA DA JANELA*************************************/
		janela = new Display("Arkanoid", VarGlobais.getGameWidth(), VarGlobais.getGameHeight());

		/***********************INSTANCIAS DO MENU****************************************/
		novo = new UIMenu(VarGlobais.getPxUiMenu(), VarGlobais.getPyUiMenu(), "Novo Jogo");
		seletor = new UISeletor(VarGlobais.getPxUiSeletor(), VarGlobais.getPyUiSeletor(), "<" );
		continuar = new UIMenu(VarGlobais.getPxUiMenu(), VarGlobais.getPyUiMenu() + 23, "Continuar");
		PlayerScore = new UIScore(VarGlobais.getPxUiScore(), VarGlobais.getPyUiScore());

		/**************************INSTANCIA DO PLAYER*************************************/
		player = new Player(VarGlobais.getPxPlayer(), VarGlobais.getPyPlayer());

		/**************************INSTANCIA DA BOLA***************************************/
		bola = new Bola(VarGlobais.getPxBola(), VarGlobais.getPyBola(), 7, 7);	

		/**************************INSTANCIAS DOS BLOCOS**********************************/
		blocos = new ArrayList<Bloco>();

		/**************************CRIACAO DE  OUTRAS INSTANCIAS*************************************/
		
		/********LAYER*********/
		layer = new BufferedImage(VarGlobais.getGameWidth(), VarGlobais.getGameHeight(), BufferedImage.TYPE_INT_RGB);

	}
	
	
	/******************************FUNCAO RESPONSAVEL PELA LOGICA DOS BLOCOS***********************************/
	public void blocosTicks() {
		for(int x = 0; x < blocos.size(); x++) {
			blocos.get(x).tick();
			testCollision(blocos.get(x), bola);					
		}	
		for(int x = 0; x < blocos.size(); x++) {
			if(blocos.get(x).def == 0) {
				blocos.remove(x);		
				score();
			}							
		}
	}
	
	/****************************FUNCAO RESPONSAVEL POR INCREMENTAR A PONTUACAO DO JOGADOR***********************/
	public void score() {		
		PlayerScore.increaseScore(10);		
	}
	
	/******************************FUNCAO RESPONSAVEL PELA LOGICA DO JOGO***********************************/
	public void tick() {
		bola.tick();
		player.tick();
		testCollision(player, bola);	
		blocosTicks();
	}

	/*****************************FUNCAO RESPONSAVEL PELA RENDERIZACAO DO JOGO**********************************/
	public void render() {

		BufferStrategy bs = janela.getBufferStrategy();
		if(bs == null) {
			janela.createBufferStrategy();
			bs = janela.getBufferStrategy();
		}

		Graphics g = layer.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, VarGlobais.getGameWidth(), VarGlobais.getGameHeight());

		bola.render(g);
		player.render(g);
		PlayerScore.render(g);

		for(int x = 0; x < blocos.size(); x++) {
			blocos.get(x).render(g);
		}


		g = bs.getDrawGraphics();
		g.drawImage(layer, 0, 0, VarGlobais.getGameWidth() * VarGlobais.getGameEscala(), VarGlobais.getGameHeight() * VarGlobais.getGameEscala(), null);
		g.dispose();
		bs.show();
	}

	public void geraBlocos() {		

		int px = VarGlobais.getPxGrade(), py = VarGlobais.getPyGrade();
		for(int x = 1; x <= 9; x++) {
			for(int y = 0; y <= 5; y++ ) {
				Random random = new Random();
				int def = random.nextInt(8) + 1;				

				blocos.add(new Bloco(px, py, 21, 13, def));
				py += 14;
			}
			px += 22;
			py = VarGlobais.getPyGrade();
		}
	}	

	boolean isIntersecting(GameObject mA, GameObject mB) {
		return mA.ladoDir() >= mB.ladoEsq() && mA.ladoEsq() <= mB.ladoDir()
				&& mA.ladoBai() >= mB.ladoCim() && mA.ladoCim() <= mB.ladoBai();
	}
	@SuppressWarnings("unlikely-arg-type")
	void testCollision(Bloco mBrick, Bola mBall) {
		if (!isIntersecting(mBrick, mBall))
			return;

		double overlapLeft = mBall.ladoDir() - mBrick.ladoEsq();
		double overlapRight = mBrick.ladoDir() - mBall.ladoEsq();
		double overlapTop = mBall.ladoBai() - mBrick.ladoCim();
		double overlapBottom = mBrick.ladoBai() - mBall.ladoCim();

		boolean ballFromLeft = overlapLeft < overlapRight;
		boolean ballFromTop = overlapTop < overlapBottom;

		double minOverlapX = ballFromLeft ? overlapLeft : overlapRight;
		double minOverlapY = ballFromTop ? overlapTop : overlapBottom;

		if (minOverlapX < minOverlapY) {
			mBall.dx = ballFromLeft ? -1 : 1;
		} else {
			mBall.dy = ballFromTop ? -1 : 1;
		}
		mBrick.def--;		
	}


	void testCollision(Player jogador, Bola bola) {
		if (!isIntersecting(jogador, bola))
			return;

		double overlapLeft = bola.ladoDir() - jogador.ladoEsq();
		double overlapRight = jogador.ladoDir() - bola.ladoEsq();
		boolean ballFromLeft = overlapLeft < overlapRight;

		//double minOverlapX = ballFromLeft ? overlapLeft : overlapRight;

		if (ballFromLeft) {
			bola.dx = ballFromLeft ? -1 : 1;
		}

		bola.dy *= -1;
	}

	@Override
	public void run() {	
		
		geraBlocos();

		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double deltaTime = 0;

		long nowTime;
		long lastTime = System.nanoTime();

		while (VarGlobais.isRunning()) {


			nowTime = System.nanoTime();
			deltaTime += (nowTime - lastTime) / timePerTick;
			lastTime = nowTime;

			if(deltaTime >= 1) {


				tick();
				render();	

				deltaTime = 0;
			}

		}

		stop();

	}

	public synchronized void start() {
		if(thread != null) return;
		thread = new Thread(this);
		thread.start();
		VarGlobais.setRunning(true);

	}

	public synchronized void stop() {
		if(thread == null) return;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};


	}


	



}
