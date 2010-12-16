package arcade.mod.controller;

import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;
import vooga.engine.state.PauseGameState;

public class PauseCommand implements IConsoleCommand {

	@Override
	public void performCommand(String myInput) {
		GameStateManager gm = StateManagerConsole.stateManager;
		GameState gs = new PauseGameState((StateManagerConsole.playState),
				"Paused");
		gm.addGameState(gs);
		gm.switchTo(gs);
	}
}
