package arcade.mod.controller;

import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;
import vooga.engine.state.PauseGameState;

public class PauseCommand implements IConsoleCommand {

	@Override
	public void performCommand(String myInput) {
		GameStateManager gm = GameConsole.stateManager;
		GameState gs = new PauseGameState((GameConsole.playState),
				"Paused");
		gm.addGameState(gs);
		gm.switchTo(gs);
	}
}
