package vooga.games.mariogame.states;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.Collection;

import vooga.engine.control.Control;
import vooga.engine.control.KeyboardControl;
import vooga.engine.core.BetterSprite;
import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.event.EventPool;
import vooga.engine.factory.LevelManager;
import vooga.engine.factory.MapTile;
import vooga.engine.overlay.OverlayCreator;
import vooga.engine.overlay.OverlayTracker;
import vooga.engine.overlay.Stat;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.engine.util.SoundPlayer;
import vooga.games.mariogame.DropThis;
import vooga.games.mariogame.events.LoseEvent;
import vooga.games.mariogame.events.NextLevelEvent;
import vooga.games.mariogame.sprites.MarioSprite;

import com.golden.gamedev.object.Sprite;
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
		initLevelManager();
		initLevel();
	}

	private void initLevelManager() {
		levelManager = new LevelManager(myGame);
		String levelFilesDirectory = Resources.getString("LevelFilesDirectory");
		String levelNamesFile = Resources.getString("LevelNamesFile");
		levelManager.makeLevels(levelFilesDirectory, levelNamesFile);
		myLevels = levelManager.getAllPlayFields();
	}

	/**
	 * This method is responsible for updating the main game playfield, which
	 * contains all of the game sprites.
	 * 
	 * @param t
	 *            - the time constant that the engine uses for updating.
	 */

	public void update(long t) {
		checkForItems();
		super.update(t);
		getLevel().update(t);
		myEvents.checkEvents();
	}
	
	public void nextLevel(){
		if(lastLevel()){
			((DropThis) myGame).winGame();
		}
		else{
			cloneStats();
			removeEverything();
			initLevel();
		}
	}
	
	private void cloneStats(){
		MarioSprite mario = ((MarioSprite)getLevel().getGroup("marioGroup").getActiveSprite());
		int health = mario.getHealth();
		int score = mario.getScore();
		levelManager.loadNextLevel();
		mario = ((MarioSprite)getLevel().getGroup("marioGroup").getActiveSprite());
		mario.setHealth(health);
		mario.setScore(score);
		mario.getStat("level");
		Stat<Integer> stat = ((Stat<Integer>) mario.getStat("level"));
		stat.setStat(levelManager.getCurrentLevel()+1);
	}

	/**
	 * This method initializes all necessary variables, such as playfields,
	 * backgrounds, enemies, and tiles for the start of the game.
	 */

	public void initLevel() {
		SoundPlayer.setGame(myGame);
		SoundPlayer.playMusic(getLevel().getMusic(0));
		setUpKeyboard();
		initOverlays();
		initEvents();
	}

	public void initEvents() {
		myEvents = new EventPool();
		LoseEvent lose = new LoseEvent((MarioSprite)getLevel().getGroup("marioGroup").getActiveSprite(),myGame);
		NextLevelEvent nextLevel = new NextLevelEvent((MarioSprite)getLevel().getGroup("marioGroup").getActiveSprite(), this);
		myEvents.addEvent(lose);
		myEvents.addEvent(nextLevel);
	}

	public void initOverlays() {
		OverlayCreator overlayCreator = new OverlayCreator();
		OverlayTracker overlayTracker = overlayCreator
				.createOverlays("src/vooga/games/mariogame/resources/overlays/GameOverlays.xml");
		SpriteGroup myOverlays = overlayTracker
				.getOverlayGroup("MainMenuGroup");
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
		MarioSprite mario = (MarioSprite) getLevel().getGroup("marioGroup").getActiveSprite();
		if(mario.getX() >= mario.getMaxX())
			getLevel().getBackground().setToCenter(mario);
	}

	private void setUpKeyboard() {
		Control playerControl = new KeyboardControl((BetterSprite)(getLevel().getGroup("marioGroup").getActiveSprite()), myGame);
		playerControl.addInput(KeyEvent.VK_D, "moveRight", "vooga.games.mariogame.sprites.MarioSprite");
		playerControl.addInput(KeyEvent.VK_A, "moveLeft", "vooga.games.mariogame.sprites.MarioSprite");
		playerControl.addInput(KeyEvent.VK_W, "jumpCmd", "vooga.games.mariogame.sprites.MarioSprite");
		getLevel().addControl("mario", playerControl);

		Control gameControl = new KeyboardControl(myGame, myGame);
		gameControl.addInput(KeyEvent.VK_P, "pauseGame",
				"vooga.games.mariogame.DropThis");
		getLevel().addControl("pause", gameControl);

		/*
		 * for (int i = KeyEvent.VK_A; i <= KeyEvent.VK_Z; i++) { if (i ==
		 * KeyEvent.VK_D || i == KeyEvent.VK_A || i == KeyEvent.VK_W) continue;
		 * myControl.setParams(new Class[] { char.class });
		 * myControl.addInput(i, "cheat", "vooga.games.marioclone.MarioSprite",
		 * (char) i); }
		 */
	}
	
	private PlayField getLevel(){
		return (PlayField)myLevels.toArray()[levelManager.getCurrentLevel()];
	}
	
	private boolean lastLevel(){
		return levelManager.getCurrentLevel() == (levelManager.getNumLevels()-1);
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

	private void checkForItems() {
		SpriteGroup items = getLevel().getGroup("itemGroup");
		for (Sprite sprite : getLevel().getGroup("Map Group").getSprites()) {
			if (sprite != null) {
				Sprite item = ((MapTile) sprite).checkItem();
				if (item != null) {
					items.add(item);
				}
			}
		}
	}

}
