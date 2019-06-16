package com.robolucha.runner.code;

public interface LuchadorScriptFacade {

	void move(int amount);

	void stop();

	void reset();

	void turn(int amount);

	void turnGun(int amount);

	void fire(int amount);

	void punch();

	void fix(int lutchador);

	// TODO: restrict String size
	void say(String message);

	// TODO: restrict String size
	void debug(String message);

	// TODO: restrict String size
	void warning(String message);

	// TODO: restrict String size
	void danger(String message);

	String getLastCall();

}