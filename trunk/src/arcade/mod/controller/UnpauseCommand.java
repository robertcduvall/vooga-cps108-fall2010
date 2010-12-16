package arcade.mod.controller;

import vooga.engine.state.GameStateManager;

/**
 * Simple implementation of a command
 * unpauses game
 * @author vitorolivier
 *
 */
public class UnpauseCommand implements IConsoleCommand {
	@Override
	public void performCommand(String myInput) {
		GameStateManager gm = GameConsole.stateManager;
		gm.switchTo(GameConsole.playState);

	}

}
