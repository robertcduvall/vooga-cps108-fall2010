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

public class MarioClone extends Game {

	private static final int WIDTH = 1024;
	private static final int HEIGHT = 768;

	private KeyboardControl myControl;
	private MarioSprite mario;
	private GameStateManager gsm;
	private GamePlayState gamePlayState;
	private MainMenuState menuState;
	private GameEndState loseState, winState;

	public static void main(String[] args) throws IOException {
		GameLoader gl = new GameLoader();
		MarioClone game = new MarioClone();
		gl.setup(game, new Dimension(WIDTH, HEIGHT), false);
		gl.start();
	}


	public void initResources() {

		// Code and image lovingly borrowed from Grandius group - thanks guys!
		GameFont myGameFont = fontManager.getFont(getImages("images/font.png",
				20, 3), " !            .,0123" + "456789:   -? ABCDEFG"
				+ "HIJKLMNOPQRSTUVWXYZ ");

		Resources.setGame(this);
		bsLoader = new BaseLoader(new BaseIO(MarioClone.class), Color.white);

		try {
			Resources.loadFile("src/vooga/games/marioclone/resourcelist.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}

		gsm = new GameStateManager();

		mario = new MarioSprite("mario", "regular", Resources
				.getImage("MarioR"), Resources.getImage("MarioL"));
		gamePlayState = new GamePlayState(mario, WIDTH, HEIGHT);

//		GameFont font = fontManager.getFont(new Font(Font.SANS_SERIF,
//				Font.PLAIN, 48));
		fontManager.putFont("GAMEOVER", myGameFont);
		fontManager.putFont("MENU", myGameFont);

		menuState = new MainMenuState(WIDTH, HEIGHT, fontManager);

		loseState = new GameEndState(new ColorBackground(Color.red), "LOSE",
				fontManager);
		winState = new GameEndState(new ColorBackground(Color.blue), "WIN",
				fontManager);

		gsm.addGameState(menuState);
		gsm.addGameState(gamePlayState);
		gsm.addGameState(loseState);
		gsm.addGameState(winState);

		gsm.switchTo(menuState);

		myControl = new KeyboardControl(mario, this);

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
		gsm.update(elapsedTime);

		GamePlayState.State nextState;
		
		if(gamePlayState.isActive()) {
			nextState = gamePlayState.nextState();
			
			switch(nextState) {
			case Win:
				gsm.switchTo(winState);
				break;
			case Lose:
				gsm.switchTo(loseState);
				break;
			}
		}

		if (menuState.isActive()) {
			if (keyPressed(KeyEvent.VK_SPACE))
				gsm.switchTo(gamePlayState);
		}

		if (winState.isActive() || loseState.isActive()) {
			if (keyPressed(KeyEvent.VK_SPACE)) {
				mario.setHealth(MarioSprite.DEFAULT_HEALTH);
				mario.setActive(true);
				gamePlayState.init();
				gsm.switchTo(gamePlayState);
			}
		}

	}

	@Override
	public void render(Graphics2D g) {
		gsm.render(g);
	}

}
