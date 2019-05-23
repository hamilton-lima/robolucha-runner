package com.robolucha.models;

import org.apache.log4j.Logger;

import com.robolucha.runner.MatchRunner;
import com.robolucha.shared.Const;

/**
 * player state during the game
 * 
 * @author hamiltonlima
 *
 */
public class LuchadorMatchState {

	public long id;
	public String name;

	public double x;
	public double y;

	public double life;
	public double angle;
	public double gunAngle;

	public void setLastOnfound(long lastOnfound) {
		this.lastOnfound = lastOnfound;
	}

	public double fireCoolDown;
	public long lastOnfound;

	public ScoreVO score;
	public LuchadorPublicState publicState;

	public LuchadorMatchState() {
		publicState = new LuchadorPublicState();
		score = new ScoreVO();
	}

	public LuchadorPublicState getPublicState() {

		publicState.id = id;
		publicState.name = name;
		publicState.x = (int) x;
		publicState.y = (int) y;
		publicState.life = (int) life;
		publicState.angle = (int) angle;
		publicState.gunAngle = (int) gunAngle;
		publicState.fireCoolDown = fireCoolDown;
		publicState.lastOnfound = lastOnfound;
		publicState.k = score.getK();
		publicState.d = score.getD();
		publicState.score = score.getScore();

		return publicState;
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

	public void setName(String name) {
		this.name = name;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getLife() {
		return life;
	}

	public void setLife(double life) {
		this.life = life;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public double getGunAngle() {
		return gunAngle;
	}

	public void setGunAngle(double gunAngle) {
		this.gunAngle = gunAngle;
	}

	public double getFireCoolDown() {
		return fireCoolDown;
	}

	public void setFireCoolDown(double fireCoolDown) {
		this.fireCoolDown = fireCoolDown;
	}

	public ScoreVO getScore() {
		return score;
	}

	public void setScore(ScoreVO score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "LuchadorMatchState [id=" + id + ", name=" + name + ", x=" + x + ", y=" + y + ", life=" + life
				+ ", angle=" + angle + ", gunAngle=" + gunAngle + ", fireCoolDown=" + fireCoolDown + ", score=" + score
				+ ", publicState=" + publicState + "]";
	}

}
