package com.ricardo.main;

public class GameStatus {
	
	private boolean isRunning = true;
	private boolean isPaused = false;
	public boolean isNewGame = true;
	public GameStatus() {
		
		if(isRunning) {
			isPaused = false;
		}
		if(isPaused) {
			isRunning = false;
		}
	}
	public boolean isRunning() {
		return isRunning;
	}
	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	public boolean isPaused() {
		return isPaused;
	}
	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}
//	public boolean isNewGame() {
//		return isNewGame;
//	}
//	public void setNewGame(boolean isNewGame) {
//		this.isNewGame = isNewGame;
//	}
	
	
//	
	
	

}
