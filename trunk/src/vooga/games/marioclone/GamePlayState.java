package vooga.games.marioclone;

import java.awt.Graphics2D;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import vooga.engine.core.Game;
import vooga.engine.overlay.OverlayStat;
import vooga.engine.overlay.Stat;
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
	private int myWidth;
	private int myHeight;
	private List<MarioLevel> myLevels;
	private Stat<Integer> myEnemiesKilled;
	private OverlayStat myScoreOverlay;
	private Stat<Integer> myLives;
	private OverlayStat myLivesOverlay;

	private int myCurrentLevel;

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

	public GamePlayState(int width, int height, Game game) {
		myGame = game;
		myCurrentLevel = 0;
		this.myWidth = width;
		this.myHeight = height;
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
		} else if (myLevels.get(myCurrentLevel).getLevelFinished()) {
			if (myCurrentLevel >= myLevels.size()-1)
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
	}


	/**
	 * This method initializes all necessary variables, such as playfields,
	 * backgrounds, enemies, and tiles for the start of the game.
	 */

	public void init() {
		myEnemiesKilled = new Stat<Integer>(new Integer(0));
		myScoreOverlay = new OverlayStat("Score: ",
				myEnemiesKilled);
		myLives = new Stat<Integer>(new Integer(0));
		myLivesOverlay = new OverlayStat("Lives: ",
				myLives);
		for(int i=0; i<3; i++){
			makeLevel(i, myGame);
		}
	}
	
	private void makeLevel(int i, Game game){
		MarioLevel level = new MarioLevel(new File(
		"src/vooga/games/marioclone/map"+Integer.toString(i)+".txt"), myWidth, myHeight, game, myScoreOverlay, myEnemiesKilled, 
		myLivesOverlay, myLives);
		myLevels.add(level);
	}

	/**
	 * Method extended from GameState class used to initialize Levels.
	 * 
	 */

	public void initialize() {
		myLevels = new ArrayList<MarioLevel>();
	}

	/**
	 * Main render method that renders the backgrounds and playfield.
	 * 
	 */

	public void render(Graphics2D g) {
		super.render(g);
		myLevels.get(myCurrentLevel).render(g);
	}

}
