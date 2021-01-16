package com.robolucha.game.vo;

import java.io.Serializable;

public class ArenaVO implements Serializable {

	private static final long serialVersionUID = -4994404110374108802L;
	public int height;
	public int width;
	public String name;
	public long gameId;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getGameId() {
		return gameId;
	}

	public void setGameId(long gameId) {
		this.gameId = gameId;
	}

	public ArenaVO(){
		
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
}
