package com.ricardo.windowns;


import java.awt.Canvas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import com.ricardo.entidades.Bloco;
import com.ricardo.entidades.Bola;
import com.ricardo.entidades.GameObject;
import com.ricardo.entidades.Player;
import com.ricardo.main.VarGlobais;
import com.ricardo.ui.UIMenu;
import com.ricardo.ui.UIScore;
import com.ricardo.ui.UISeletor;

public class Display {

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	
	private Canvas canvas;
	private JFrame janela;
	
	public Display(String title, int width, int height) {
		
		canvas = new Canvas();
		
		canvas.setSize(new Dimension (VarGlobais.getGameWidth() * VarGlobais.getGameEscala(), VarGlobais.getGameHeight() * VarGlobais.getGameEscala()));
		
		janela = new JFrame(title);
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
