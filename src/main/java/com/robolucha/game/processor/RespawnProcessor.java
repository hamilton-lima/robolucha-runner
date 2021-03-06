package com.robolucha.game.processor;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Random;

import org.apache.log4j.Logger;

import com.robolucha.runner.MatchRunner;
import com.robolucha.runner.RespawnPoint;
import com.robolucha.runner.luchador.LuchadorRunner;
import com.robolucha.shared.Calc;

public class RespawnProcessor implements IRespawnProcessor {

	RespawnPoint[] locations;

	private static Logger logger = Logger.getLogger(RespawnProcessor.class);

	public RespawnProcessor(MatchRunner matchRunner) {
		calculateLocations(matchRunner.getGameDefinition().getLuchadorSize(),
				matchRunner.getGameDefinition().getArenaWidth(), matchRunner.getGameDefinition().getArenaHeight());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.robolucha.game.processor.IRespawnPoint#cleanup()
	 */
	@Override
	public void cleanup() {
		for (int i = 0; i < locations.length; i++) {
			locations[i] = null;
		}

		locations = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.robolucha.game.processor.IRespawnPoint#getRespawnPoint(com.robolucha.
	 * runner.luchador.LuchadorRunner, java.util.LinkedHashMap)
	 */
	@Override
	public RespawnPoint getRespawnPoint(LuchadorRunner runner, LinkedHashMap<Integer, LuchadorRunner> runners) {

		int pos = -1;
		boolean lookForPlaceToRespawn = true;
		while (lookForPlaceToRespawn) {
			Random r = new Random();
			pos = r.nextInt(locations.length);
			if (canRespawnHere(locations[pos], runner, runners)) {
				lookForPlaceToRespawn = false;
			}
		}

		return locations[pos];

	}

	/**
	 * verifica se pode fazer respawn em determinada posicao
	 * 
	 * @param point
	 * @param current
	 * @param runners
	 * @return
	 */
	boolean canRespawnHere(RespawnPoint point, LuchadorRunner current, LinkedHashMap<Integer, LuchadorRunner> runners) {

		if (logger.isDebugEnabled()) {
			logger.debug("canRespawnHere : point = " + point);
			logger.debug("canRespawnHere : current = " + current);
		}

		Iterator iterator = runners.keySet().iterator();
		while (iterator.hasNext()) {
			Object key = (Object) iterator.next();
			LuchadorRunner runner = runners.get(key);

			if (logger.isDebugEnabled()) {
				logger.debug(">canRespawnHere : runner = " + runner);
			}

			// nao precisa conferir o ID do current porque o luchador origem
			// nao esta ativo ...esta usando o respawn ora bolas !! :)
			//
			if (runner != null && runner.isActive()) {

				if (Calc.intersectRobot(point.x, point.y, current, runner)) {
					return false;
				}

			}

		}

		return true;
	}

	/**
	 * calcula as posicoes possiveis para respawn
	 * 
	 * @param size
	 * @param width
	 * @param height
	 */
	void calculateLocations(int size, int width, int height) {

		int border = size / 3;

		int lines = ((width - (2 * border)) / size) - 1;
		int columns = ((height - (2 * border)) / size) - 1;

		logger.debug("calculateLocations().lines=" + lines);
		logger.debug("calculateLocations().columns=" + columns);

		locations = new RespawnPoint[lines * columns];
		int pos = 0;
		int line = border + size;
		int column = border + size;

		for (int i = 0; i < lines; i++) {
			for (int j = 0; j < columns; j++) {
				locations[pos++] = new RespawnPoint(line, column, 0, 0);
				column = column + size;
			}
			line = line + size;
			column = border + size;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.robolucha.game.processor.IRespawnPoint#getLocations()
	 */
	@Override
	public RespawnPoint[] getLocations() {
		return locations;
	}

}
