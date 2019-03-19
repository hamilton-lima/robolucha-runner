package com.robolucha.runner.luchador;

public class LuchadorCommand {

	private String command;

	private double originalValue;
	private double value;
	private int direction;
	private int speed;
	private String codeName;

	public LuchadorCommand(String codeName, String command, double originalValue, int speed) {
		this.codeName = codeName;
		this.command = command;
		this.originalValue = originalValue;
		this.value = this.originalValue;
		this.speed = speed;
		calculateDirection();

		// corrige valor para poder consumir
		if (this.value < 0) {
			this.value = this.value * -1;
		}
	}

	private void calculateDirection() {
		if (this.originalValue < 0) {
			this.direction = -1;
		} else {
			this.direction = 1;
		}
	}

	// TODO should the key contain direction now? with the implementation of the LuchadorCommandQueue?
	public String getKey() {
		return this.command + "." + this.direction;
	}

	public boolean empty() {
		if (value <= 0) {
			return true;
		}
		return false;
	}

	public double consume(double delta) {
		double fragment = this.speed * delta;

		if ((this.value - fragment) < 0) {
			fragment = this.value;
			this.value = 0;
		} else {
			this.value = this.value - fragment;
		}

		return fragment * this.direction;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public double getOriginalValue() {
		return originalValue;
	}

	public void setOriginalValue(double originalValue) {
		this.originalValue = originalValue;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void consumeAll() {
		value = 0;
	}

	public String getCodeName() {
		return codeName;
	}

	@Override
	public String toString() {
		return "LuchadorCommand [command=" + command + ", originalValue=" + originalValue + ", value=" + value
				+ ", direction=" + direction + ", speed=" + speed + ", codeName=" + codeName + "]";
	}

}
