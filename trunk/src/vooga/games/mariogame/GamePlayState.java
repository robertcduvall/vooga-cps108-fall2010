package vooga.games.mariogame;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.File;

import vooga.engine.control.Control;
import vooga.engine.control.KeyboardControl;
import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;

/**
 * 
 * @author David Herzka, Cameron McCallie, Andrew Brown
 * 
 *         This extension of GameState represents the playing or action state of
 *         the MarioClone game. The methods within this class are responsible
 *         for running, updating, and rendering all things pertinent to the
 *         playing state.
 * 
 */

public class GamePlayState extends GameState {

	private Game myGame;
	MarioLevelFactory myLevelFactory;

	private static final int NUM_LEVELS = 3;

	private int myCurrentLevel;
	private MarioPlayField myLevel;

	private KeyboardControl myControl;

	public enum State {
		Win, Lose, Continue, FinishedLevel
	};

	/**
	 * This constructs a GamePlayState by initializing the player as well as the
	 * game dimensions.
	 * 
	 * @param mario
	 *            is a MarioSprite, which represents the user controlled sprite.
	 * @param width
	 *            is the desired width of the game window
	 * @param height
	 *            is the desired height of the game window
	 */

	public GamePlayState(Game game) {
		myGame = game;
		init();
	}

	/**
	 * This method is called in the MarioClone update method in order to check
	 * if any state transitions need to happen.
	 * 
	 * @return This method returns one of three state types: win, loss, or
	 *         continue, depending on the given game conditions.
	 */

	public State nextState() {
		if (myLevel.getMario() == null || !myLevel.getMario().isActive()) {
			return State.Lose;
		} else if (myLevel.isFinished()) {
			if (myCurrentLevel >= NUM_LEVELS - 1) {
				return State.Win;
			} else {
				myCurrentLevel++;
				switchLevel(myCurrentLevel);
				return State.FinishedLevel;
			}
		} else
			return State.Continue;
	}

	/**
	 * This method is responsible for updating the main game playfield, which
	 * contains all of the game sprites.
	 * 
	 * @param t
	 *            - the time constant that the engine uses for updating.
	 */

	public void update(long t) {
		super.update(t);
		myLevel.update(t);
		myControl.update();
	}

	/**
	 * This method initializes all necessary variables, such as playfields,
	 * backgrounds, enemies, and tiles for the start of the game.
	 */

	public void init() {
		myLevelFactory = new MarioLevelFactory();
		myCurrentLevel = 0;
		switchLevel(0);
		setUpKeyboard();
	}

	public int getScore() {
		return (Integer) myLevel.getOverlays().getStat("Score").getStat();
	}

	private void switchLevel(int i) {
		File levelFile = new File(Resources.getString("level"
				+ Integer.toString(i)));

		MarioPlayField pf = (MarioPlayField) myLevelFactory
				.getPlayfield(levelFile);
		pf.setLevel(i + 1);
		myLevel = pf;
		myGame.playMusic(myLevel.getMusic());
	}

	/**
	 * Main render method that renders the backgrounds and playfield.
	 * 
	 */

	public void render(Graphics2D g) {
		super.render(g);
		myLevel.render(g);
	}

	public int getCurrentLevel() {
		return myCurrentLevel;
	}

	private void setUpKeyboard() {
		Control playerControl = new KeyboardControl(((MarioPlayField) myLevel).getMario(), myGame);
		playerControl.addInput(KeyEvent.VK_D, "rotateLeft", "vooga.games.asteroids.sprites.Ship");
		playerControl.addInput(KeyEvent.VK_A, "rotateRight", "vooga.games.asteroids.sprites.Ship");
		playerControl.addInput(KeyEvent.VK_W, "thrust", "vooga.games.asteroids.sprites.Ship");
		myLevel.addControl(playerControl);
		
		/*
		for (int i = KeyEvent.VK_A; i <= KeyEvent.VK_Z; i++) {
			if (i == KeyEvent.VK_D || i == KeyEvent.VK_A || i == KeyEvent.VK_W)
				continue;
			myControl.setParams(new Class[] { char.class });
			myControl.addInput(i, "cheat",
					"vooga.games.marioclone.MarioSprite", (char) i);
		}
		*/
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

}
