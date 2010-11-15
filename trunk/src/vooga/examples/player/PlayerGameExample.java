package vooga.examples.player;

import java.awt.Dimension;

import com.golden.gamedev.GameLoader;

import vooga.engine.core.Game;

public class PlayerGameExample extends Game {

	public void initResources() {
		super.initResources();
	}
	
	public static void main(String [] args) {
		GameLoader game = new GameLoader();
		game.setup(new PlayerGameExample(), new Dimension(532, 850), false);
		game.start();
	}
}
