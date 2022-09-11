package com.ricardo.windowns;


import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

import com.ricardo.main.Controle;
import com.ricardo.main.VarGlobais;



public class Display {

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	
	private Canvas canvas;
	private JFrame janela;
	private Controle controle;
	
	public Display(String title, int width, int height) {
		
		canvas = new Canvas();
		controle = new Controle();
		
		canvas.setSize(new Dimension (VarGlobais.getGameWidth() * VarGlobais.getGameEscala(), VarGlobais.getGameHeight() * VarGlobais.getGameEscala()));
		
		janela = new JFrame(title);
		janela.addKeyListener(controle);
		janela.add(canvas);
		janela.pack();
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setResizable(false);
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);	
		
		
		
	}
	
	public BufferStrategy getBufferStrategy() {
		return canvas.getBufferStrategy();
	}
	
	public void createBufferStrategy() {
		canvas.createBufferStrategy(3);
	}

}
