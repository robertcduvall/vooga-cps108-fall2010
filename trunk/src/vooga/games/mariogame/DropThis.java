package vooga.games.mariogame;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import vooga.engine.core.Game;
import vooga.engine.factory.LevelManager;
import vooga.engine.resource.Resources;
import vooga.engine.state.PauseGameState;
import vooga.games.mariogame.GameEndState;
import vooga.games.mariogame.MainMenuState;

import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.GameFontManager;
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

	PauseGameState pauseState;
	GamePlayState playState;
	public GameFontManager fontManager;
	private LevelManager levelManager;

	public static void main(String[] args) throws IOException {
		launch(new DropThis());

		// TODO: Game teams should now convert their old imagelist.txt,
		// soundlist.txt,
		// etc. files into the
		// new and improved resources.xml file format by following the example
		// code in
		// vooga.examples.resource.resourcesxmlexample


	}

	public void initResources() {
		super.initResources();
		initFonts();
		initLevelManager();
	}
	
	private void initLevelManager() {
		levelManager = new LevelManager(this);
		String levelFilesDirectory = Resources.getString("levelFilesDirectory");
		String levelNamesFile = Resources.getString("levelNamesFile");
		levelManager.makeLevels(levelFilesDirectory,levelNamesFile);
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

	public void pauseGame() {
		stateManager.activateOnly(pauseState);		
	}
	
	public void resumeGame() {		
		stateManager.activateOnly(playState);
	}

	public void initGameStates() {
		super.initGameStates();
		MainMenuState mainMenu = new MainMenuState(Resources.getImage("Mario Menu BG"), fontManager);
		MainMenuState pauseState = new MainMenuState(Resources.getImage("PauseBG"), fontManager);
		GameEndState loseState = new GameEndState(Resources.getImage("LoseBG"),"FAIL!", fontManager);
		GameEndState winState = new GameEndState(Resources.getImage("WinBG"),"YOU WIN!", fontManager);
		MainMenuState levelFinishedState = new MainMenuState(Resources.getImage("LevelFinishedBG"), fontManager);
		GamePlayState playState = new GamePlayState(this);
		stateManager.addGameState(mainMenu, pauseState, loseState, winState, levelFinishedState, playState);
	}
}
