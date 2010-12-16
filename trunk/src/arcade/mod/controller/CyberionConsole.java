package arcade.mod.controller;

import arcade.mod.view.ConsoleView;
import vooga.engine.core.Game;

public class CyberionConsole extends GameConsole {

	public CyberionConsole(Game game) {
		super();
		PLAY_STATE = 4;
		stateManager = game.getGameStateManager();
		playState = stateManager.getGameState(PLAY_STATE);
		allSprites = playState.getSprites();
		myCommandFactory = new CyberionCommandFactory();
	}

}
