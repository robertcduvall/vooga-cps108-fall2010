package vooga.games.tronlegacy;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.io.IOException;

import com.golden.gamedev.GameLoader;


import vooga.engine.core.Game;
import vooga.engine.factory.LevelManager;
import vooga.engine.overlay.OverlayCreator;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameStateManager;

/**
 * @author BrentSodman
 */

public class Blah extends Game {

	private static final int GAME_WIDTH = 480;
	private static final int GAME_HEIGHT = 480;

	MainGameState playState = new MainGameState();
	PauseState pauseState = new PauseState(this);
	MenuState menuState;

	public void initResources() {
		super.initResources();
		
		OverlayCreator.setGame(this);
		
		playState.initialize(this);
		menuState = new MenuState(this);
		
		stateManager.addGameState(playState);
		stateManager.addGameState(pauseState);
		stateManager.addGameState(menuState);
		stateManager.activateOnly(menuState);

		playMusic(Resources.getSound("music"));
	}
	


	public void render(Graphics2D g) {
		stateManager.render(g);
	}

	public void update(long elapsedTime) {
		stateManager.update(elapsedTime);
	}

	public void togglePauseGame() {
		if (playState.isActive()) {
			pauseState.addRenderState(playState);
		} else {
			pauseState.removeEverything();
		}
		stateManager.toggle(playState);
		stateManager.toggle(pauseState);
	}

	public void startGame() {
		stateManager.activateOnly(playState);
	}

	public void gameOver() {
		stateManager.activateOnly(menuState);
		playState.initialize(this);
	}

	// placeholder main function
	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new Blah(), new Dimension(GAME_WIDTH, GAME_HEIGHT), false);
		game.start();
	}
}
