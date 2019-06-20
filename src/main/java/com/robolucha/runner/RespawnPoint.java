package com.robolucha.runner;

public class RespawnPoint {
	public int x;
	public int y;
	public int angle;
	public int gunAngle;

	public RespawnPoint(int x, int y, int angle, int gunAngle) {
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.gunAngle = gunAngle;
	}

	public String toString() {
		return "RespawnPoint [x=" + x + ", y=" + y + ", angle=" + angle + ", gunAngle=" + gunAngle + "]";
	}

}