package arcade.mod.controller;

import vooga.engine.state.GameStateManager;

public class UnpauseCommand implements IConsoleCommand {
	@Override
	public void performCommand(String myInput) {
		GameStateManager gm = GameConsole.stateManager;
		gm.switchTo(GameConsole.playState);

	}

}
