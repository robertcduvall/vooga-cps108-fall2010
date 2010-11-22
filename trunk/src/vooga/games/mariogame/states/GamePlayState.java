package vooga.games.mariogame.states;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Collection;

import vooga.engine.control.Control;
import vooga.engine.control.KeyboardControl;
import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.core.BetterSprite;
import vooga.engine.overlay.OverlayCreator;
import vooga.engine.overlay.OverlayTracker;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.engine.util.SoundPlayer;
import vooga.engine.event.EventPool;
import vooga.engine.factory.LevelManager;
import vooga.examples.event.demo2.HumanKilledbyZombieEvent;
import vooga.games.mariogame.sprites.MarioSprite;
import vooga.games.mariogame.events.LoseEvent;

import com.golden.gamedev.object.SpriteGroup;

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
	private static final int NUM_LEVELS = 3;

	private int myCurrentLevel;

	private KeyboardControl myControl;
	private EventPool myEvents;
	private LevelManager levelManager;
	public Collection<PlayField> myLevels;

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
	
	private void initLevelManager() {
		levelManager = new LevelManager(myGame);
		String levelFilesDirectory = Resources.getString("LevelFilesDirectory");
		String levelNamesFile = Resources.getString("LevelNamesFile");
		levelManager.makeLevels(levelFilesDirectory,levelNamesFile);
		myLevels = levelManager.getAllPlayFields();
		myCurrentLevel = 0;
	}

	/**
	 * This method is responsible for updating the main game playfield, which
	 * contains all of the game sprites.
	 * 
	 * @param t
	 *            - the time constant that the engine uses for updating.
	 */

	public void update(long t) {
		System.out.println(getLevel().getMusic(0));

		super.update(t);
		getLevel().update(t);
		myEvents.checkEvents();
	}

	/**
	 * This method initializes all necessary variables, such as playfields,
	 * backgrounds, enemies, and tiles for the start of the game.
	 */

	public void init() {
		initLevelManager();
		SoundPlayer.setGame(myGame);
		SoundPlayer.playMusic(getLevel().getMusic(0));
		myCurrentLevel = 0;
		setUpKeyboard();
		initOverlays();
		initEvents();
	}
	
	public void initEvents(){
		myEvents = new EventPool();
		LoseEvent lose = new LoseEvent((MarioSprite)getLevel().getGroup("marioGroup").getActiveSprite(),myGame);
		myEvents.addEvent(lose);
	}
	
	public void initOverlays(){
		OverlayCreator overlayCreator = new OverlayCreator();
		OverlayTracker overlayTracker = overlayCreator.createOverlays("src/vooga/games/mariogame/resources/overlays/GameOverlays.xml");
		SpriteGroup myOverlays = overlayTracker.getOverlayGroup("MainMenuGroup");
		PlayField overlayField = new PlayField();
		this.getRenderField().add(overlayField);
		this.getUpdateField().add(overlayField);
	}

	public int getScore() {
		return (Integer) ((BetterSprite)getLevel().getGroup("marioGroup").getActiveSprite()).getStat("Score").getStat();
	}

	/**
	 * Main render method that renders the backgrounds and playfield.
	 * 
	 */

	public void render(Graphics2D g) {
		super.render(g);
		getLevel().render(g);
		scrollLevel();
	}
	
	public void scrollLevel(){
		getLevel().getBackground().setToCenter(getLevel().getGroup("marioGroup").getActiveSprite());
	}

	public int getCurrentLevel() {
		return myCurrentLevel;
	}

	private void setUpKeyboard() {
		Control playerControl = new KeyboardControl((BetterSprite)(getLevel().getGroup("marioGroup").getActiveSprite()), myGame);
		playerControl.addInput(KeyEvent.VK_D, "moveRight", "vooga.games.mariogame.sprites.MarioSprite");
		playerControl.addInput(KeyEvent.VK_A, "moveLeft", "vooga.games.mariogame.sprites.MarioSprite");
		playerControl.addInput(KeyEvent.VK_W, "jumpCmd", "vooga.games.mariogame.sprites.MarioSprite");
		getLevel().addControl("mario", playerControl);
		
		Control gameControl = new KeyboardControl(myGame,myGame);
		gameControl.addInput(KeyEvent.VK_P, "pauseGame", "vooga.games.mariogame.DropThis");
		getLevel().addControl("pause", gameControl);
		
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
	
	private PlayField getLevel(){
		return (PlayField)myLevels.toArray()[myCurrentLevel];
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

}