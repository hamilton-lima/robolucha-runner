package com.robolucha.models;

import java.util.LinkedList;
import java.util.List;

public class GameDefinition {

	private long id = 1;
	private String name = "default";
	private int duration = 1200000;
	private int minParticipants = 2;
	private int maxParticipants = 14;

	private int arenaWidth = 1200;
	private int arenaHeight = 600;
	private int bulletSize = 16;
	private int luchadorSize = 60;

	private int fps = 30;
	private int buletSpeed = 120;

	// predefined luchadores that will join the match
	private List<GameComponent> gameComponents;

	// predefined scene elements
	private List<SceneComponent> sceneComponents;

	public GameDefinition() {
		gameComponents = new LinkedList<GameComponent>();
		sceneComponents = new LinkedList<SceneComponent>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDuration() {
		return duration;
	}

	public int getMinParticipants() {
		return minParticipants;
	}

	public void setMinParticipants(int minParticipants) {
		this.minParticipants = minParticipants;
	}

	public int getMaxParticipants() {
		return maxParticipants;
	}

	public void setMaxParticipants(int maxParticipants) {
		this.maxParticipants = maxParticipants;
	}

	public int getArenaWidth() {
		return arenaWidth;
	}

	public void setArenaWidth(int arenaWidth) {
		this.arenaWidth = arenaWidth;
	}

	public int getArenaHeight() {
		return arenaHeight;
	}

	public void setArenaHeight(int arenaHeight) {
		this.arenaHeight = arenaHeight;
	}

	public int getBulletSize() {
		return bulletSize;
	}

	public void setBulletSize(int bulletSize) {
		this.bulletSize = bulletSize;
	}

	public int getLuchadorSize() {
		return luchadorSize;
	}

	public void setLuchadorSize(int luchadorSize) {
		this.luchadorSize = luchadorSize;
	}

	public int getFps() {
		return fps;
	}

	public void setFps(int fps) {
		this.fps = fps;
	}

	public int getBuletSpeed() {
		return buletSpeed;
	}

	public void setBuletSpeed(int buletSpeed) {
		this.buletSpeed = buletSpeed;
	}

	public List<GameComponent> getGameComponents() {
		return gameComponents;
	}

	public void setGameComponents(List<GameComponent> gameComponents) {
		this.gameComponents = gameComponents;
	}

	public List<SceneComponent> getSceneComponents() {
		return sceneComponents;
	}

	public void setSceneComponents(List<SceneComponent> sceneComponents) {
		this.sceneComponents = sceneComponents;
	}

}
