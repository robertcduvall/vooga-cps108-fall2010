package vooga.games.mariogame;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import com.golden.gamedev.object.GameFontManager;
import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.engine.state.MenuGameState;
import vooga.engine.state.PauseGameState;
import vooga.games.mariocloneold.GameEndState;
import vooga.games.mariocloneold.MainMenuState;

import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.font.SystemFont;

/**
 * 
 * @author David Herzka, Cameron McCallie, Andrew Brown
 * 
 *         Our game takes after the popular Mario game series. This project was
 *         written and developed with the VOOGA game engine, in Duke
 *         University's Computer Science 108 class.
 * 
 */

public class DropThis extends Game {

	public PauseGameState gamePause;
	public GameFontManager fontManager;

	public static void main(String[] args) throws IOException {
		GameLoader gl = new GameLoader();
		DropThis game = new DropThis();

		// TODO: Game teams should now convert their old imagelist.txt,
		// soundlist.txt,
		// etc. files into the
		// new and improved resources.xml file format by following the example
		// code in
		// vooga.examples.resource.resourcesxmlexample

		// TODO: How do we format the config.properties file for width, height,
		// etc?

		game.launch(game);
	}

	public void initResources() {
		initFonts();
		super.initResources();
	}

	private void initFonts() {
		fontManager = new GameFontManager();
		GameFont menuFont = fontManager.getFont(getImages(
				"resources/images/font.png", 20, 3),
				" !            .,0123456789:   -? ABCDEFGHIJKLMNOPQRSTUVWXYZ ");
		SystemFont gameOverFont = new SystemFont(new Font(Font.SANS_SERIF,
				Font.BOLD, 200));
		gameOverFont.setColor(Color.white);

		fontManager.putFont("GAMEOVER", gameOverFont);
		fontManager.putFont("MENU", menuFont);
	}

	public void initGameStates() {
		super.initGameStates();
		MainMenuState mainMenu = new MainMenuState(Resources
				.getImage("Mario Menu BG"), fontManager);
		MainMenuState pauseState = new MainMenuState(Resources
				.getImage("PauseBG"), fontManager);
		GameEndState loseState = new GameEndState(Resources.getImage("LoseBG"),
				"FAIL!", fontManager);
		GameEndState winState = new GameEndState(Resources.getImage("WinBG"),
				"YOU WIN!", fontManager);
		MainMenuState levelFinishedState = new MainMenuState(Resources
				.getImage("LevelFinishedBG"), fontManager);
		GamePlayState playState = new GamePlayState(this);
		stateManager.addGameState(mainMenu, pauseState, loseState, winState,
				levelFinishedState, playState);
	}
}
