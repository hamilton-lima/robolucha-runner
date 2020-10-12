package com.robolucha.models;

/**
 * publishes the state the to script
 * 
 * @author hamiltonlima
 *
 */
public class LuchadorPublicState {

	public long id;
	public long team;

	public String name;
	public int x;
	public int y;

	public int life;
	public int angle;
	public int gunAngle;

	public double fireCoolDown;
	public long lastOnfound;

	public int k;
	public int d;
	public int score;
	public boolean npc;

	@Override
	public String toString() {
		return "LuchadorPublicState [id=" + id + ", team=" + team + ", name=" + name + ", x=" + x + ", y=" + y
				+ ", life=" + life + ", angle=" + angle + ", gunAngle=" + gunAngle + ", fireCoolDown=" + fireCoolDown
				+ ", lastOnfound=" + lastOnfound + ", k=" + k + ", d=" + d + ", score=" + score + ", npc=" + npc + "]";
	}

}
