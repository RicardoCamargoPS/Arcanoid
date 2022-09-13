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
import com.ricardo.fases.Gerador_fase;
import com.ricardo.ui.*;
import com.ricardo.windowns.Display;

public class Game implements Runnable{

	private Display janela;
	private Thread thread;		
	static UIMenu novo, continuar;
	static UISeletor seletor;	
	private BufferedImage layer;
	public static Bola bola;
	static Player player;

	private Gerador_fase fase;
	public static Color[] cores;

	public static String gameStatos = "MENU";
	private boolean showMessageGameOver = true;
	private int framesGameOver = 0;
	private boolean restartGame = false;

	public static Menu menu;


	public Game() {

		cores = new Color[8];

		/**************************NSTANCIA DA JANELA*************************************/
		janela = new Display("Arkanoid", VarGlobais.getGameWidth(), VarGlobais.getGameHeight());

		/***********************INSTANCIAS DO MENU****************************************/
		novo = new UIMenu(VarGlobais.getPxUiMenu(), VarGlobais.getPyUiMenu(), "Novo Jogo");
		seletor = new UISeletor(VarGlobais.getPxUiSeletor(), VarGlobais.getPyUiSeletor(), "<" );
		continuar = new UIMenu(VarGlobais.getPxUiMenu(), VarGlobais.getPyUiMenu() + 23, "Continuar");


		/**************************INSTANCIA DO PLAYER*************************************/
		player = new Player(VarGlobais.getPxPlayer(), VarGlobais.getPyPlayer());

		/**************************INSTANCIA DA BOLA***************************************/
		bola = new Bola(VarGlobais.getPxBola(), VarGlobais.getPyBola(), 7, 7);	

		fase = new Gerador_fase();

		/**************************CRIACAO DE  OUTRAS INSTANCIAS*************************************/

		/********LAYER*********/
		layer = new BufferedImage(VarGlobais.getGameWidth(), VarGlobais.getGameHeight(), BufferedImage.TYPE_INT_RGB);

		menu = new Menu();
	}

	/******************************FUNCAO RESPONSAVEL PELA LOGICA DO JOGO***********************************/
	public void tick() {
		if(gameStatos == "NORMAL") {
			bola.tick();
			player.tick();		
			fase.tick();
		}
		else if(gameStatos == "GAME OVER") {

		}
		else if(gameStatos == "MENU") {			
			menu.tick();

		}
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
			// TODO Auto-generated catch block
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
