package com.robolucha.game.vo;

import java.util.ArrayList;
import java.util.List;

import com.robolucha.models.LuchadorPublicState;
import com.robolucha.models.ScoreVO;

public class MatchRunStateVO {
	
	public static final MatchRunStateVO EMPTY = new MatchRunStateVO();
	
	public List<EventVO> events;
	public List<BulletVO> bullets;
	public List<PunchVO> punches;
	public List<LuchadorPublicState> luchadores;
	public List<SceneComponentVO> sceneComponents;
	public List<ScoreVO> scores;
	public long clock;

	public MatchRunStateVO() {
		luchadores = new ArrayList<LuchadorPublicState>();
		bullets = new ArrayList<BulletVO>();
		punches = new ArrayList<PunchVO>();
		events = new ArrayList<EventVO>();
		scores = new ArrayList<ScoreVO>();
		sceneComponents = new ArrayList<SceneComponentVO>();
	}

}
