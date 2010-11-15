package vooga.examples.core;

import vooga.engine.core.Game;
/**
 * Copied resources files from the resource group.
 * @author Yang
 *
 */
public class Example extends Game {

	@Override
	public void initResources() {
		super.initResources();

		getGameStateManager().addGameState(new ExampleState1(),
				new ExampleState2());
	}

}
