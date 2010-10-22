package vooga.games.marioclone;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameStateManager;

import com.golden.gamedev.GameLoader;
import com.golden.gamedev.engine.BaseIO;
import com.golden.gamedev.engine.BaseLoader;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.background.ColorBackground;
import com.golden.gamedev.object.background.ImageBackground;
import com.golden.gamedev.object.font.SystemFont;

/**
 * 
 * @author David Herzka, Cameron McCallie, Andrew Brown
 * 
 *         Our game takes after the popular Mario game series. This project was written and
 *         developed with the VOOGA game engine, in Duke University's Computer
 *         Science 108 class.
 * 
 */

public class DropThis extends Game {

	private static final int WIDTH = 1024;
	private static final int HEIGHT = 768;

	private GameStateManager myGameStateManager;
	private GamePlayState myGamePlayState;
	private MainMenuState myMenuState, myPausedState, myLevelFinishedState;
	private GameEndState myLoseState, myWinState;
	
	public static void main(String[] args) throws IOException {
		GameLoader gl = new GameLoader();
		DropThis game = new DropThis();
		Resources.loadInt("Height",HEIGHT);
		Resources.loadInt("Width",WIDTH);
		gl.setup(game, new Dimension(WIDTH, HEIGHT), false);
		gl.start();
	}

	public void initResources() {
		bsMusic.setAudioPolicy(bsMusic.SINGLE_REPLAY);
		bsMusic.setExclusive(true);
		Resources.setGame(this);
		GameFont menuFont = fontManager.getFont(getImages("resources/images/font.png",
				20, 3),
				" !            .,0123456789:   -? ABCDEFGHIJKLMNOPQRSTUVWXYZ ");
		bsLoader = new BaseLoader(new BaseIO(DropThis.class), Color.white);

		Resources.setDefaultPath("src/vooga/games/marioclone/resources/");
		
		try {
			Resources.loadPropertiesFile("game.properties");
		} catch (IOException e) {
			System.out.println("Error - could not load file.");
		}

		myGameStateManager = new GameStateManager();

		myGamePlayState = new GamePlayState(this);

		SystemFont gameOverFont = new SystemFont(new Font(Font.SANS_SERIF,
				Font.BOLD, 200));
		gameOverFont.setColor(Color.white);

		fontManager.putFont("GAMEOVER", gameOverFont);
		fontManager.putFont("MENU", menuFont);

		myMenuState = new MainMenuState(Resources.getImage("MenuBG"),fontManager);
		myPausedState = new MainMenuState(Resources.getImage("PauseBG"),fontManager);
		myLoseState = new GameEndState(Resources.getImage("LoseBG"), "FAIL!",
				fontManager);
		myWinState = new GameEndState(Resources.getImage("WinBG"), "YOU WIN!",
				fontManager);
		myLevelFinishedState = new MainMenuState(Resources.getImage("LevelFinishedBG"),fontManager);

		myGameStateManager.addGameState(myMenuState);
		myGameStateManager.addGameState(myPausedState);
		myGameStateManager.addGameState(myGamePlayState);
		myGameStateManager.addGameState(myLoseState);
		myGameStateManager.addGameState(myWinState);
		myGameStateManager.addGameState(myLevelFinishedState);
		
		myGameStateManager.switchTo(myMenuState);
		
		

	}

	@Override
	public void update(long elapsedTime) {
		myGameStateManager.update(elapsedTime);

		GamePlayState.State nextState;

		if (myGamePlayState.isActive()) {
			nextState = myGamePlayState.nextState();

			switch (nextState) {
			case Win:
				bsMusic.setLoop(false);
				myWinState.setScore(myGamePlayState.getScore());
				myGameStateManager.switchTo(myWinState);
				playMusic(Resources.getSound("Win"));
				break;
			case Lose:
				bsMusic.setLoop(false);
				myLoseState.setScore(myGamePlayState.getScore());
				myGameStateManager.switchTo(myLoseState);
				playMusic(Resources.getSound("Lose"));
				break;
			case FinishedLevel:
				bsMusic.setLoop(false);
				myGameStateManager.switchTo(myLevelFinishedState);
				break;
			}
			if (keyPressed(KeyEvent.VK_P)){
				myGameStateManager.switchTo(myPausedState);
			} else{
				nextState = myGamePlayState.nextState();
			}
		}
		else if (myPausedState.isActive()){
			if (keyPressed(KeyEvent.VK_P)){
				bsMusic.setLoop(true);
				myGameStateManager.switchTo(myGamePlayState);
			}
		}
		else if (myMenuState.isActive()) {
			if (keyPressed(KeyEvent.VK_SPACE)) {
				myGameStateManager.switchTo(myGamePlayState);
			}
		}
		else if (myWinState.isActive() || myLoseState.isActive()) {
			if (keyPressed(KeyEvent.VK_SPACE)) {
				bsMusic.setLoop(true);
				myGamePlayState.init();
				myGameStateManager.switchTo(myGamePlayState);
			}
		}
		else if(myLevelFinishedState.isActive()){
			if (keyPressed(KeyEvent.VK_SPACE)) {
				bsMusic.setLoop(true);
				myGameStateManager.switchTo(myGamePlayState);
			}
		}

	}

	@Override
	public void render(Graphics2D g) {
		myGameStateManager.render(g);
	}

}
