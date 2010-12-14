package vooga.games.mariogame;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.factory.LevelParser;
import vooga.engine.resource.Resources;
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

public class Blah extends Game {

	MainMenuState pauseState;
	GamePlayState playState;
	GameEndState loseState;
	GameEndState winState;
	public GameFontManager fontManager;
	public String myName;

	public static void main(String[] args) throws IOException {
		launch(new Blah(), "Guest");
	}

	public void initResources() {
		initFonts();
		super.initResources();
		initStates();

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

	public void restartGame() {
		playState.restart();
		stateManager.switchTo(playState);
	}
	
	public void pauseGame() {
		stateManager.switchTo(pauseState);		
	}
	
	public void resumeGame() {
		stateManager.switchTo(playState);
	}
	
	public void loseGame(){
		stateManager.switchTo(loseState);
	}
	
	public void winGame(){
		stateManager.switchTo(winState);
	}

	public void initStates() {
		LevelParser levelParser = new LevelParser();

		PlayField mainMenuField = levelParser.getPlayfield(Resources.getString("mainmenuxml"),this);
		MainMenuState mainMenu = new MainMenuState(this,mainMenuField,playState);
		PlayField pauseField = levelParser.getPlayfield(Resources.getString("pausexml"),this);
		pauseState = new MainMenuState(this,pauseField,playState);

		playState = new GamePlayState(this);
		
		PlayField loseField = levelParser.getPlayfield(Resources.getString("losexml"),this);
		PlayField winField = levelParser.getPlayfield(Resources.getString("winxml"),this);
		loseState = new GameEndState(this,loseField,playState);
		winState = new GameEndState(this,winField,playState);
		stateManager.addGameState(mainMenu, pauseState, loseState, winState, playState);
		stateManager.switchTo(mainMenu);
	}
}
