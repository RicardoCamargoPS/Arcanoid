package com.ricardo.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import com.ricardo.entidades.*;
import com.ricardo.fases.Gerador_fase;
import com.ricardo.ui.*;
import com.ricardo.windowns.Display;

public class Game implements Runnable{

	private Display janela;
	private Thread thread;		
	static UIMenu novo, continuar;
	static UISeletor seletor;	
	public static UIScore PlayerScore;
	public static UIVida PlayerVida;
	private BufferedImage layer;
	public static Bola bola;
	static Player player;
	private Gerador_fase fase;		
	private TesteColisao colisao;

	public static String gameStatos = "MENU";
	public static Menu menu;
	private boolean showMessageGameOver = true;
	private int framesGameOver = 0;
	private boolean restartGame = false;

	/*
	 *     foi necessario criar um array de cores para que o gerador de fases funcionace perfeitamente,
	 *     sendo que para funcionar as novas fases tem que ser criadas com as mesmas cores.
	 *     na pasta res tem um arquivo de paleta de cores com nome de paleta.png e um arquivo txt com o
	 *     codigo RGBa das mesma.
	 * 
	 */

	public static Color[] cores; 

	public Game() {

		cores = new Color[8];

		/**************************NSTANCIA DA JANELA*************************************/
		janela = new Display("Arkanoid", VarGlobais.getGameWidth(), VarGlobais.getGameHeight());


		/**************************INSTANCIA DO PLAYER*************************************/
		player = new Player(VarGlobais.getPxPlayer(), VarGlobais.getPyPlayer());

		/**************************INSTANCIA DA BOLA***************************************/
		bola = new Bola(VarGlobais.getPxBola(), VarGlobais.getPyBola(), 7, 7);	

		fase = new Gerador_fase();

		/**************************CRIACAO DE  OUTRAS INSTANCIAS*************************************/

		/********LAYER*********/
		layer = new BufferedImage(VarGlobais.getGameWidth(), VarGlobais.getGameHeight(), BufferedImage.TYPE_INT_RGB);

		/*******MENU*****/
		menu = new Menu();

		/************************************PONTUACAO**********************************/
		PlayerScore = new UIScore(VarGlobais.getPxUiScore(), VarGlobais.getPyUiScore());

		/************************************VIDAS*************************************/
		PlayerVida = new UIVida(VarGlobais.getPxUiVida(), VarGlobais.getPyUiVida());

		colisao = new TesteColisao();

	}

	/******************************FUNCAO RESPONSAVEL PELA LOGICA DO JOGO***********************************/
	public void tick() {

		if(gameStatos == "NORMAL") {	
			player.tick();		
			fase.tick();
			bola.tick();

		}
		else if(gameStatos == "GAME OVER") {

		}
		else if(gameStatos == "MENU") {			
			menu.tick();

		}

		PlayerVida.tick();
		colisao.playerColisao(player, bola);
		colisao.fundoColisao(bola);


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
		fase.render(g);

		PlayerScore.render(g);
		PlayerVida.render(g);

		if(gameStatos == "GAME OVER") {

		}
		else if(gameStatos == "MENU") {
			menu.render(g);
		}

		g = bs.getDrawGraphics();
		g.drawImage(layer, 0, 0, VarGlobais.getGameWidth() * VarGlobais.getGameEscala(), VarGlobais.getGameHeight() * VarGlobais.getGameEscala(), null);
		g.dispose();
		bs.show();
	}		


	@Override
	public void run() {	
		geraCores();

		fase.geraFases("fase1.png");
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
		else {
			thread = new Thread(this);			
			VarGlobais.setRunning(true);
		}
		thread.start();
		VarGlobais.setRunning(true);
	}

	public synchronized void stop() {
		if(thread == null) return;
		VarGlobais.setRunning(false);
		try {
			thread.join();
		} catch (InterruptedException e) {

			e.printStackTrace();
		};


	}

	public void geraCores() {
		cores[0] = new Color(106,190, 48,255);
		cores[1] = new Color(215,123,186,255);
		cores[2] = new Color(91,110,225,255);
		cores[3] = new Color(251,242, 54,255);
		cores[4] = new Color(118, 66,138,255);
		cores[5] = new Color(223,113, 38,255);
		cores[6] = new Color(172, 50, 50,255);
		cores[7] = new Color(105,106,106,255);

	}






}
