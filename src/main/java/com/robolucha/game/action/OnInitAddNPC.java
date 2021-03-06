package com.robolucha.game.action;

import java.util.Iterator;

import org.apache.log4j.Logger;

import com.robolucha.game.vo.MatchInitVO;
import com.robolucha.runner.MatchRunner;
import com.robolucha.runner.MatchRunnerListener;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.swagger.client.model.ModelGameComponent;
import io.swagger.client.model.ModelGameDefinition;

public class OnInitAddNPC implements Consumer<MatchInitVO>, MatchRunnerListener {

	private Logger logger = Logger.getLogger(OnInitAddNPC.class);

	private MatchRunner runner;
	private Disposable disposable;

	public void subscribe(MatchRunner runner) {
		this.runner = runner;
		this.disposable = runner.getOnInit().subscribe(this, new ErrorHandler());
	}

	/**
	 * procura por NPC relacionados a gamedefinition em questao
	 */
	@Override
	public void accept(MatchInitVO init) throws Exception {
		// TODO Auto-generated method stub

		logger.info("START add NPC to match: " + runner.getThreadName());
		logger.info("Matchrunner = " + runner);

		ModelGameDefinition def = runner.getGameDefinition();

		Iterator<ModelGameComponent> iterator = def.getGameComponents().iterator();
		while (iterator.hasNext()) {
			ModelGameComponent npc = iterator.next();

			try {
				runner.addNPC(npc);
				logger.info("gamecomponent add to the match: " + npc.getName());
			} catch (Exception e) {
				logger.error("Error adding NPC to match", e);
			}

		}

		logger.info("END add NPC to match: " + runner.getThreadName());
	}

	@Override
	public void dispose() {
		this.disposable.dispose();
	}

	@Override
	public boolean isDisposed() {
		return this.disposable.isDisposed();
	}

	protected class ErrorHandler implements Consumer<Throwable> {
		@Override
		public void accept(Throwable error) {
			logger.error("Error from OnInitAddNPC", error);
		}
	}

}
