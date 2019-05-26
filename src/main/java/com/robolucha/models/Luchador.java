package com.robolucha.models;

import com.robolucha.shared.Const;

import io.swagger.client.model.MainGameComponent;

public class Luchador extends MainGameComponent {

	private LuchadorCoach coach;

	public Luchador() {
		super();
	}

	@Override
	public String toString() {
		return "Luchador{" +
				super.toString() +
				"coach=" + coach +
				'}';
	}

	public LuchadorCoach getCoach() {
		return coach;
	}

	public void setCoach(LuchadorCoach coach) {
		this.coach = coach;
	}

}
