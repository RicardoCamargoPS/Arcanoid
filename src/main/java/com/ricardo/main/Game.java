package com.ricardo.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import com.ricardo.entidades.*;
import com.ricardo.fases.Construtor_fase;
import com.ricardo.ui.*;
import com.ricardo.utils.GameStutusEnum;
import com.ricardo.windowns.Display;

public class Game implements Runnable{

	private static final int WIDTH = 192, HEIGHT = 305, SCALE = 2;
		
	private boolean isRunning = false;
	private BufferedImage layer;
	private Display janela;
	private Thread thread;				
	private TesteColisao colisao;

	public static Construtor_fase fase;
	public static UIScore PlayerScore;
	public static UIVida PlayerVida;
	//public GameStutusEnum gameStatos;
	public static String menuStatos = "Menu";
	public static Bola bola;
	public static Player player;
	public static Menu menu;
	
	
	
	public Game() {
					
		janela = new Display("Arkanoid", WIDTH, HEIGHT);		
		player = new Player();		
		bola = new Bola();	
		fase = new Construtor_fase();		
		layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);		
		menu = new Menu();	
		PlayerScore = new UIScore(10, 10);		
		PlayerVida = new UIVida(100, 10);
		colisao = new TesteColisao();
	}

	
	public void tick() {

		if( menuStatos.equals(GameStutusEnum.Running.name())) {	
			player.tick();		
			fase.tick();
			bola.tick();

		}
		else if(menuStatos.equals(GameStutusEnum.Menu.name()) ||
			   menuStatos.equals(GameStutusEnum.Paused.name()) ||
			   menuStatos.equals(GameStutusEnum.GameOver.name())) {
				menu.tick();
		}
		
		PlayerVida.tick();
		colisao.playerColisao(player, bola);
		colisao.fundoColisao(bola);

	}

	public void render() {
		BufferStrategy bs = janela.getBufferStrategy();
		if(bs == null) {
			janela.createBufferStrategy();
			bs = janela.getBufferStrategy();
		}

		Graphics g = layer.getGraphics();

		g.setColor(Color.white);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.black);
		//g.setColor(new Color(198,198,198));
		g.fillRect(5, 15, WIDTH - 10 , HEIGHT - 16);

		bola.render(g);
		player.render(g);
		fase.render(g);

		PlayerScore.render(g);
		PlayerVida.render(g);

		if(menuStatos.equals(GameStutusEnum.Menu.name()) ||
			   menuStatos.equals(GameStutusEnum.Paused.name()) ||
			   menuStatos.equals(GameStutusEnum.GameOver.name())) {
				menu.render(g);
		}
		

		g = bs.getDrawGraphics();
		g.drawImage(layer, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		g.dispose();
		bs.show();
	}		


	@Override
	public void run() {	

		fase.geraFases("fase1.png");
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double deltaTime = 0;

		long nowTime;
		long lastTime = System.nanoTime();

		while (isRunning) {

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
			isRunning = true;
		}
		thread.start();
		isRunning = true;
	}

	public synchronized void stop() {
		if(thread == null) return;
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {

			e.printStackTrace();
		};
	}

	public static int getWidth(){
		return WIDTH;
	}

	public static int 	getHeight(){
		return HEIGHT;
	}
}
