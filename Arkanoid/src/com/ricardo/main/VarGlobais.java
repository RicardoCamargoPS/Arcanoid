package com.ricardo.main;

public class VarGlobais {	
	
	private static int gameWidth = 230, gameHeight = 300, gameEscala = 3;
	
	private static int pxPlayer = gameWidth / 2 - 20, pyPlayer = gameHeight - 15;
	
	private static int pxBola = gameWidth / 2 - 3, pyBola = gameHeight - 23;
	
	private static int pxGrade = 11, pyGrade = 20;
	
	private static boolean isRunning = false;	
   	
	private static int  pxUiScore = gameWidth - 80, pyUiScore = 10;
	
	private static int pxUiVida = gameWidth - 210, pyUiVida = 10;
	
	private static int vida = 3;
	

	public static int getPxPlayer() {
		return pxPlayer;
	}
	public static int getPyPlayer() {
		return pyPlayer;
	}

	public static int getPxBola() {
		return pxBola;
	}
	public static int getPyBola() {
		return pyBola;
	}
	public static int getPxGrade() {
		return pxGrade;
	}
	public static int getPyGrade() {
		return pyGrade;
	}
	public static int getPxUiScore() {
		return pxUiScore;
	}

	public static int getPyUiScore() {
		return pyUiScore;
	}
	
	public static int getPxUiVida() {
		return pxUiVida;
	}

	public static int getPyUiVida() {
		return pyUiVida;
	}
	
	public static boolean isRunning() {
		return isRunning;
	}

	public static void setRunning(boolean Running) {
		isRunning = Running;
	}
	
	public static void setVida(int v) {
		vida = v;
	}
	
	public static int getVida() {
		return vida;
	}

	public static int getGameWidth() {
		return gameWidth;
	}

	public static int getGameHeight() {
		return gameHeight;
	}

	public static int getGameEscala() {
		return gameEscala;
	}
	
	

}
