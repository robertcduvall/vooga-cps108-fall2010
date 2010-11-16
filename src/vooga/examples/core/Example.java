package vooga.examples.core;

import java.awt.Dimension;

import com.golden.gamedev.GameLoader;

import vooga.engine.core.Game;
/**
 * @author Yang
 */
public class Example extends Game {

	@Override
	public void initResources() {
		super.initResources();

		getGameStateManager().addGameState(new ExampleState1(),
				new ExampleState2());
	}
	
	public static void main(String[] args){
		launch(new Example());
//		GameLoader game = new GameLoader();
//		game.setup(new Example(), new Dimension(600, 400), false);
//		game.start();
	}

}
