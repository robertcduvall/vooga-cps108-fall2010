package arcade.mod.controller;

import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;

public class StateManagerConsole extends Console {

	static GameStateManager stateManager;
	static GameState playState;
	public static final int PLAY_STATE = 4;

	public StateManagerConsole(GameStateManager gm) {
		super();
		stateManager = gm;
		playState = stateManager.getGameState(PLAY_STATE);
	}
}