package com.ricardo.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

import com.ricardo.entidades.*;
import com.ricardo.ui.*;
//import mainGame.VarGlobais;

public class MainGame extends Canvas implements Runnable, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage layer = new BufferedImage(VarGlobais.getGameWidth(), VarGlobais.getGameHeight(), BufferedImage.TYPE_INT_RGB);
	static Bola bola;
	static Player player;
	public static List<Bloco> blocos = new ArrayList<Bloco>();
	static UIScore PlayerScore;
	static UIMenu novo, continuar;
	static UISeletor seletor;

	public MainGame() {
		this.setPreferredSize(new Dimension(VarGlobais.getGameWidth() * VarGlobais.getGameEscala(), VarGlobais.getGameHeight() * VarGlobais.getGameEscala()));
		this.addKeyListener(this);

		novo = new UIMenu(VarGlobais.getPxUiMenu(), VarGlobais.getPyUiMenu(), "Novo Jogo");
		continuar = new UIMenu(VarGlobais.getPxUiMenu(), VarGlobais.getPyUiMenu() + 23, "Continuar");
		seletor = new UISeletor(VarGlobais.getPxUiSeletor(), VarGlobais.getPyUiSeletor(), "<" );
		PlayerScore = new UIScore(VarGlobais.getPxUiScore(), VarGlobais.getPyUiScore());

		player = new Player(VarGlobais.getPxPlayer(), VarGlobais.getPyPlayer());
		bola = new Bola(VarGlobais.getPxBola(), VarGlobais.getPyBola(), 7, 7);
		geraBlocos();
	}



	public static void main(String[] args) {

		MainGame game = new MainGame();
		JFrame janela = new JFrame("Pong Game");
		janela.setResizable(false);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.add(game);
		janela.pack();
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);

		new Thread(game).start();

	}

	public void tick() {

		bola.tick();
		player.tick();
		testCollision(player, bola);
		for(int x = 0; x < blocos.size(); x++) {
			testCollision(blocos.get(x), bola);
			if(blocos.get(x).def == 0) {
				blocos.remove(x);
				PlayerScore.increaseScore(10);
			}
		}	
	}

	public void render() {


		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = layer.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, VarGlobais.getGameWidth(), VarGlobais.getGameHeight());

		if(VarGlobais.isRunning()) {

			VarGlobais.setVisibleMenu(false);
			bola.render(g);
			player.render(g);
			PlayerScore.render(g);

			for(int x = 0; x < blocos.size(); x++) {

				blocos.get(x).render(g);
			}
		}
		else {
			novo.render(g);
			continuar.render(g);
			seletor.render(g);
		}

		g = bs.getDrawGraphics();
		g.drawImage(layer, 0, 0, VarGlobais.getGameWidth() * VarGlobais.getGameEscala(), VarGlobais.getGameHeight() * VarGlobais.getGameEscala(), null);
		bs.show();

	}


	@Override
	public void run() {

		while(true) {
			tick();
			render();
			try {
				Thread.sleep(1000/60);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}

	public void geraBlocos() {
		int px = VarGlobais.getPxGrade(), py = VarGlobais.getPyGrade();
		for(int x = 1; x <= 9; x++) {
			for(int y = 0; y <= 5; y++ ) {

				blocos.add(new Bloco(px, py, 21, 13, 3, Color.blue));
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

		//

		//scoreboard.increaseScore();

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

		if(mBrick.def == 0) {
			blocos.remove(this);
		}
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
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}



	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {

			VarGlobais.setRunning(true);
			VarGlobais.setVisibleMenu(false);

		}
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {

			VarGlobais.setRunning(false);
			VarGlobais.setVisibleMenu(true);
		}		

		if(e.getKeyCode() == KeyEvent.VK_UP) {
			if(seletor.getPy() > VarGlobais.getGameHeight() / 2) {
				seletor.setPy(VarGlobais.getGameHeight() / 2);
			}

		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			if(seletor.getPy() == VarGlobais.getGameHeight() / 2) {
				seletor.setPy(VarGlobais.getGameHeight() / 2 + 24);
			}

		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			player.direita = true;
			player.isMuve = true;
			player.setDx(1);
		}else if(e.getKeyCode() == KeyEvent.VK_A) {			
			player.esquerda = true;
			player.isMuve = true;
			player.setDx(-1);
		}
	}



	@Override
	public void keyReleased(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_D) {
			player.direita = false ;
			player.isMuve = false;
			player.setDx(0);
		}else if(e.getKeyCode() == KeyEvent.VK_A) {
			player.esquerda = false;
			player.isMuve = false;
			player.setDx(0);
		}

	}

}
