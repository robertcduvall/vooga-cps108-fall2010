package arcade.mod.controller;

import vooga.engine.state.GameStateManager;

public class UnpauseCommand implements IConsoleCommand {
	@Override
	public void performCommand(String myInput) {
		GameStateManager gm = StateManagerConsole.stateManager;
		gm.switchTo(StateManagerConsole.playState);

	}

}
