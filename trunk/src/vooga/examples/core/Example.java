package vooga.examples.core;

import vooga.engine.core.Game;

/**
 * Example Game class
 * 
 * @author Yang
 */
public class Example extends Game {

	@Override
	public void initResources() {
		super.initResources();
		// Setup game states
		ExampleState1 state1 = new ExampleState1();
		state1.initialize();
		ExampleState2 state2 = new ExampleState2();
		state2.initialize();
		// Add game states to game state manager
		getGameStateManager().addGameState(state1, state2);
	}

	public static void main(String[] args) {
		launch(new Example(), "player");
	}

}
