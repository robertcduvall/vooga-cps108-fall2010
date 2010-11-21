package vooga.games.mariogame;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.Collection;

import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.factory.LevelManager;
import vooga.engine.factory.LevelParser;
import vooga.engine.resource.Resources;
import vooga.engine.state.PauseGameState;
import vooga.games.mariogame.states.GameEndState;
import vooga.games.mariogame.states.GamePlayState;
import vooga.games.mariogame.states.MainMenuState;

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

	MainMenuState pauseState;
	GamePlayState playState;
	public GameFontManager fontManager;
	private LevelManager levelManager;
	public Collection<PlayField> myLevels;
	public String myName;

	public static void main(String[] args) throws IOException {
		launch(new DropThis());
	}

	public void initResources() {
		initFonts();
		super.initResources();
		initLevelManager();
		initStates();

	}
	
	private void initLevelManager() {
		levelManager = new LevelManager(this);
		String levelFilesDirectory = Resources.getString("LevelFilesDirectory");
		String levelNamesFile = Resources.getString("LevelNamesFile");
		levelManager.makeLevels(levelFilesDirectory,levelNamesFile);
		myLevels = levelManager.getAllPlayFields();
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
		System.out.println("pausing game");
		stateManager.switchTo(pauseState);		
	}
	
	public void resumeGame() {
		stateManager.switchTo(playState);
	}

	public void initStates() {
		System.out.println("creating states");
		LevelParser levelParser = new LevelParser();
		PlayField loseField = levelParser.getPlayfield(Resources.getString("losexml"),this);
		PlayField winField = levelParser.getPlayfield(Resources.getString("winxml"),this);
		MainMenuState mainMenu = new MainMenuState(this, Resources.getImage("Mario Menu BG"), fontManager);
		pauseState = new MainMenuState(this, Resources.getImage("Pause BG"), fontManager);
		GameEndState loseState = new GameEndState(loseField);
		GameEndState winState = new GameEndState(winField);
		MainMenuState levelFinishedState = new MainMenuState(this, Resources.getImage("Level Complete BG"), fontManager);
		playState = new GamePlayState(this,(PlayField)myLevels.toArray()[0]);
		stateManager.addGameState(mainMenu, pauseState, loseState, winState, levelFinishedState, playState);
		stateManager.switchTo(mainMenu);
	}
}
