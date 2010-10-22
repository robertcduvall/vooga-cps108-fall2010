package vooga.games.tronlegacy;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.io.IOException;

import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ColorBackground;

import vooga.engine.core.Game;
import vooga.engine.overlay.OverlayCreator;
import vooga.engine.overlay.OverlayTracker;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;

/**
 * @author BrentSodman
 * 
 *         Like Tron++
 * 
 *         Currently the levels and graphics are very simplistic, really there
 *         just to demonstrate that the APIs work.
 * 
 */

public class Blah extends Game {

	private static final int GAME_WIDTH = 480;
	private static final int GAME_HEIGHT = 480;
	private static final String DEFAULT_FILEPATH = "src/vooga/games/tronlegacy/resources/";

	MainGameState playState = new MainGameState();
	PauseState pauseState = new PauseState(this);
	MenuState menuState = new MenuState(this);

	GameStateManager stateManager = new GameStateManager();

	public void initResources() {

		Resources.initialize(this);
		Resources.setDefaultPath(DEFAULT_FILEPATH);

		OverlayCreator.setGame(this);

		try {
			Resources.loadPropertiesFile("game.properties");
		} catch (IOException e) {
			System.out.println("Error loading images from filepath: "
					+ DEFAULT_FILEPATH);
			this.stop();
		}

		playState.initialize(this);
		stateManager.addGameState(playState);
		stateManager.addGameState(pauseState);
		stateManager.addGameState(menuState);
		menuState.activate();
		playState.deactivate();
		pauseState.deactivate();

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

			playState.deactivate();
			pauseState.activate();
		} else {
			pauseState.removeAllGroups();

			playState.activate();
			pauseState.deactivate();
		}
	}

	public void startGame() {
		menuState.deactivate();
		playState.activate();
	}

	public void gameOver() {
		playState.deactivate();
		playState = new MainGameState();
		menuState.activate();
	}

	// placeholder main function
	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new Blah(), new Dimension(GAME_WIDTH, GAME_HEIGHT), false);
		game.start();
	}
}
