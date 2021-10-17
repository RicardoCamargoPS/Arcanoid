package com.ricardo.main;

public class VarGlobais {	
	
	private static int gameWidth = 220, gameHeight = 300, gameEscala = 2;
	private static int pxPlayer = gameWidth / 2 - 20, pyPlayer = gameHeight - 15, pxBola = gameWidth / 2, pyBola = gameHeight / 2 + 20;
	private static int pxGrade = 10, pyGrade = 20;
	
	
	private static int pxUiMenu = gameWidth / 4, pyUiMenu = gameHeight / 2, pxUiScore = gameWidth - 100, pyUiScore = 10, pxUiSeletor = gameWidth / 2 + 70, pyUiSeletor = gameHeight / 2;
	
	private static boolean isRunning = false;

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

	public static int getPxUiMenu() {
		return pxUiMenu;
	}

	public static int getPyUiMenu() {
		return pyUiMenu;
	}
	public static int getPxUiScore() {
		return pxUiScore;
	}

	public static int getPyUiScore() {
		return pyUiScore;
	}

	public static int getPxUiSeletor() {
		return pxUiSeletor;
	}

	public static int getPyUiSeletor() {
		return pyUiSeletor;
	}
	public static boolean isRunning() {
		return isRunning;
	}

	public static void setRunning(boolean Running) {
		isRunning = Running;
	}

//	public static boolean isVisibleMenu() {
//		return visibleMenu;
//	}
//
//	public static void setVisibleMenu(boolean visible) {
//		visibleMenu = visible;
//	}

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
