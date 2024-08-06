package com.ricardo.windowns;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.ricardo.main.Game;

public class Controle implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {		
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {			
			if(Game.menuStatos == "MENU") {
				Game.menu.enter = true;
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {			
			Game.menuStatos = "MENU";
			Game.menu.pause = true;
		}		

		if(e.getKeyCode() == KeyEvent.VK_UP) {
			if(Game.menuStatos == "MENU") {
				Game.menu.up = true;
			}
		}

		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			if(Game.menuStatos == "MENU") {
				Game.menu.down = true;
			}
		}

		if(e.getKeyCode() == KeyEvent.VK_D) {			
			Game.player.setMoving(true); 
			Game.player.setDx(1);
			
		}
		if(e.getKeyCode() == KeyEvent.VK_A) {			
			Game.player.setMoving(true); 		
			Game.player.setDx(-1);
				 
		}
		if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			
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
