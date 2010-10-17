package vooga.games.marioclone;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.IOException;

import vooga.engine.core.Game;
import vooga.engine.player.control.KeyboardControl;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameStateManager;

import com.golden.gamedev.GameLoader;
import com.golden.gamedev.engine.BaseIO;
import com.golden.gamedev.engine.BaseLoader;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.background.ColorBackground;

/**
 * 
 * @author David Herzka, Cameron McCallie, Andrew Brown
 * 
 *         Our game takes after the popular Mario game series, but with a twist!
 *         In our version, the user controls a monster, who attempts to rid the
 *         world of all marios by jumping on them. This project was written and
 *         developed with the VOOGA game engine, in Duke University's Computer
 *         Science 108 class.
 * 
 */

public class MarioClone extends Game {

	private static final int WIDTH = 1024;
	private static final int HEIGHT = 768;

	private KeyboardControl myControl;
	private MarioSprite myMario;
	private GameStateManager myGameStateManager;
	private GamePlayState myGamePlayState;
	private MainMenuState myMenuState;
	private GameEndState myLoseState, myWinState;

	public static void main(String[] args) throws IOException {
		GameLoader gl = new GameLoader();
		MarioClone game = new MarioClone();
		gl.setup(game, new Dimension(WIDTH, HEIGHT), false);
		gl.start();
	}

	public void initResources() {
		Resources.setGame(this);
		GameFont myGameFont = fontManager.getFont(getImages("images/font.png",
				20, 3),
				" !            .,0123456789:   -? ABCDEFGHIJKLMNOPQRSTUVWXYZ ");
		bsLoader = new BaseLoader(new BaseIO(MarioClone.class), Color.white);

		try {
			Resources.loadFile("src/vooga/games/marioclone/resourcelist.txt");
		} catch (IOException e) {
			System.out.println("Error - could not load file.");
		}

		myGameStateManager = new GameStateManager();

		myMario = new MarioSprite("mario", "regular", Resources
				.getImage("MarioR"), Resources.getImage("MarioL"));
		myGamePlayState = new GamePlayState(myMario, WIDTH, HEIGHT);

		fontManager.putFont("GAMEOVER", myGameFont);
		fontManager.putFont("MENU", myGameFont);

		myMenuState = new MainMenuState(WIDTH, HEIGHT, fontManager);
		myLoseState = new GameEndState(new ColorBackground(Color.red), "LOSE",
				fontManager);
		myWinState = new GameEndState(new ColorBackground(Color.blue), "WIN",
				fontManager);

		myGameStateManager.addGameState(myMenuState);
		myGameStateManager.addGameState(myGamePlayState);
		myGameStateManager.addGameState(myLoseState);
		myGameStateManager.addGameState(myWinState);

		myGameStateManager.switchTo(myMenuState);

		myControl = new KeyboardControl(myMario, this);

		myControl.addInput(KeyEvent.VK_D, "moveRight",
				"vooga.games.marioclone.MarioSprite");
		myControl.addInput(KeyEvent.VK_A, "moveLeft",
				"vooga.games.marioclone.MarioSprite");
		myControl.addInput(KeyEvent.VK_W, "jumpCmd",
				"vooga.games.marioclone.MarioSprite");

	}

	@Override
	public void update(long elapsedTime) {
		myControl.update();
		myGameStateManager.update(elapsedTime);

		GamePlayState.State nextState;

		if (myGamePlayState.isActive()) {
			nextState = myGamePlayState.nextState();

			switch (nextState) {
			case Win:
				myGameStateManager.switchTo(myWinState);
				break;
			case Lose:
				myGameStateManager.switchTo(myLoseState);
				break;
			}
		}

		if (myMenuState.isActive()) {
			if (keyPressed(KeyEvent.VK_SPACE))
				myGameStateManager.switchTo(myGamePlayState);
		}

		if (myWinState.isActive() || myLoseState.isActive()) {
			if (keyPressed(KeyEvent.VK_SPACE)) {
				myMario.setHealth(myMario.getMaxHealth());
				myMario.setActive(true);
				myGamePlayState.init();
				myGameStateManager.switchTo(myGamePlayState);
			}
		}

	}

	@Override
	public void render(Graphics2D g) {
		myGameStateManager.render(g);
	}

}
