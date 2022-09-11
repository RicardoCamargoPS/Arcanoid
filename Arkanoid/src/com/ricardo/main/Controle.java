package com.ricardo.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controle implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {		
		
		
	}


	@Override
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
			
			System.out.println("apertando a tecla d");
			Game.player.setMoving(true); 
			Game.player.setDx(1);
			
		}
		if(e.getKeyCode() == KeyEvent.VK_A) {	
			System.out.println("apertando a tecla a");
			Game.player.setMoving(true); 		
			Game.player.setDx(-1);
				 
		}
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		
if(e.getKeyCode() == KeyEvent.VK_D) {
			Game.player.setMoving(false); 
			Game.player.setDx(0);
			
		}
		if(e.getKeyCode() == KeyEvent.VK_A) {
			Game.player.setMoving(false); 		
			Game.player.setDx(0);
				 
		}	
		
	}

}
