package vooga.games.marioclone;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import vooga.engine.core.Game;
import vooga.engine.overlay.OverlayStat;
import vooga.engine.overlay.OverlayTracker;
import vooga.engine.overlay.Stat;
import vooga.engine.player.control.KeyboardControl;
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
	private List<MarioPlayField> myLevels;
	MarioLevelFactory myLevelFactory = new MarioLevelFactory();


	private int myCurrentLevel;

	private KeyboardControl myControl;

	public enum State {
		Win, Lose, Continue
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
		myCurrentLevel = 0;
		myLevels = new ArrayList<MarioPlayField>();
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
		 if (!myLevels.get(myCurrentLevel).getMario().isActive()) {
		 return State.Lose;
		 } else if (myLevels.get(myCurrentLevel).isFinished()) {
		 if (myCurrentLevel >= myLevels.size() - 1)
		 return State.Win;
		 else {
		 myCurrentLevel++;
		 return State.Continue;
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
		myLevels.get(myCurrentLevel).update(t);

		myControl.update();

	}

	/**
	 * This method initializes all necessary variables, such as playfields,
	 * backgrounds, enemies, and tiles for the start of the game.
	 */

	public void init() {
		for (int i = 0; i < 3; i++) {
			makeLevel(i);
		}
		setUpKeyboard();
		myGame.playMusic(Resources.getSound("MarioSong"));
	}

	public Long getScore() {
		return new Long(myLevels.get(myCurrentLevel).getMario().getScore());
	}
	
	private void makeLevel(int i) {
		// File map = new File("src/vooga/games/marioclone/resources/maps/map"
		// + Integer.toString(i) + ".txt");
		// if (map == null) {
		// System.out.println("problem");
		// }
		// MarioLevel level = new MarioLevel(map, (i + 1),
		// game, myScoreOverlay, myEnemiesKilled, myLivesOverlay, myLives);
		// if (level == null) {
		// System.out.println("problem");
		// }
		// myLevels.add(level);

		File levelFile = new File(Resources.getString("level"
				+ Integer.toString(i)));
		if (levelFile == null)
			System.out.println("No level here.  Problem?");

		MarioPlayField pf = (MarioPlayField) myLevelFactory.getPlayfield(levelFile);
		pf.setLevel(i+1);
		myLevels.add(pf);
	}

	/**
	 * Main render method that renders the backgrounds and playfield.
	 * 
	 */

	public void render(Graphics2D g) {
		super.render(g);
		myLevels.get(myCurrentLevel).render(g);
	}


	private void setUpKeyboard() {
		myControl = new KeyboardControl(((MarioPlayField) myLevels
				.get(myCurrentLevel)).getMario(), myGame);

		myControl.addInput(KeyEvent.VK_D, "moveRight",
				"vooga.games.marioclone.MarioSprite");
		myControl.addInput(KeyEvent.VK_A, "moveLeft",
				"vooga.games.marioclone.MarioSprite");
		myControl.addInput(KeyEvent.VK_W, "jumpCmd",
				"vooga.games.marioclone.MarioSprite");
		for (int i = KeyEvent.VK_A; i <= KeyEvent.VK_Z; i++) {
			if (i == KeyEvent.VK_D || i == KeyEvent.VK_A || i == KeyEvent.VK_W)
				continue;
			myControl.setParams(new Class[] { char.class });
			myControl.addInput(i, "cheat",
					"vooga.games.marioclone.MarioSprite", (char) i);
		}

	}

}
