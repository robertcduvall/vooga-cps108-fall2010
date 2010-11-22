package vooga.games.cyberion.events;

import vooga.engine.event.IEventHandler;
import vooga.engine.state.GameStateManager;

public class GameOverEvent implements IEventHandler {
	GameStateManager gameStateManager;

	public GameOverEvent(GameStateManager gm) {
		gameStateManager = gm;
	}

	@Override
	public boolean isTriggered() {

		return true;
	}

	@Override
	public void actionPerformed() {
		gameStateManager.activateOnly(gameStateManager.getGameState(4));
	}
}
