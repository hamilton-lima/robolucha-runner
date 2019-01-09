package com.robolucha.runner.luchador.lua;

public interface ScriptFacade {

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

	// TODO: restrict String size
	void setHeadColor(String color);

	// TODO: restrict String size
	void setBodyColor(String color);

	String getLastCall();

}