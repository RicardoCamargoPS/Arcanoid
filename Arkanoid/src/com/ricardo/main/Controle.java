package com.ricardo.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controle implements KeyListener{
	
	public Controle() {
	
		
		addKeyListener(this);
	}
	

	private void addKeyListener(Controle controle) {
		// TODO Auto-generated method stub
		
	}


	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {			
			// gs.setRunning(true);
			// VarGlobais.setRunning(true);
			// isPaused = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {			
			// gs.setRunning(false);aa
			// VarGlobais.setRunning(false);
			// isPaused = true;
		}		

		if(e.getKeyCode() == KeyEvent.VK_UP) {
			//if(seletor.getPy() > VarGlobais.getGameHeight() / 2) {
			// seletor.setPy(VarGlobais.getGameHeight() / 2);
		}

		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			//if(seletor.getPy() == VarGlobais.getGameHeight() / 2) {
			// seletor.setPy(VarGlobais.getGameHeight() / 2 + 24);
		}

		if(e.getKeyCode() == KeyEvent.VK_D) {
			Game.player.setMuve(true); 
			Game.player.setDx(1);
			
		}

		if(e.getKeyCode() == KeyEvent.VK_A) {	
			Game.player.setMuve(true); 		
			Game.player.setDx(-1);
				 
		}
	}

	public void keyReleased(KeyEvent e) {

		if(e.getKeyCode() == KeyEvent.VK_D) {
			Game.player.setDx(0);
			Game.player.setMuve(false);

		}else if(e.getKeyCode() == KeyEvent.VK_A) {
			Game.player.setDx(0);
			Game.player.setMuve(false); 

		}
	}

}
