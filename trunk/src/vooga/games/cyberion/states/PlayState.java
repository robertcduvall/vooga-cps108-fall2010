package vooga.games.cyberion.states;

import java.awt.Color;
import java.awt.event.KeyEvent;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.background.ColorBackground;

import vooga.engine.control.KeyboardControl;
import vooga.engine.core.PlayField;
import vooga.engine.event.EventPool;
import vooga.engine.factory.LevelManager;
import vooga.engine.overlay.Stat;
import vooga.engine.state.GameState;
import vooga.games.cyberion.DropThis;
import vooga.games.cyberion.events.PlayerFireEvent;
import vooga.games.cyberion.sprites.playership.PlayerShip;
import vooga.games.grandius.events.FireBlackHoleEvent;
import vooga.games.grandius.events.FireHorizontalEvent;
import vooga.games.grandius.events.FireMissileEvent;
import vooga.games.grandius.events.FireVerticalEvent;
import vooga.games.grandius.events.LevelCompleteEvent;
import vooga.games.grandius.events.ZipsterFireEvent;

/**
 * Player state
 * 
 * @author Harris.He
 * 
 */

public class PlayState extends GameState {

	private LevelManager myLevelManager;
	private DropThis myGame;
	private KeyboardControl playerControl;
	private EventPool eventPool;
	private PlayerShip player;
	private String PLAYER_CLASS = "vooga.games.cyberion.sprites.playership.PlayerShip";
	private PlayField newField;
	
	public PlayState(LevelManager levelManager, DropThis game) {
		myLevelManager = levelManager;
		myGame = game;

	}

	public void initialize() {
		newField = myLevelManager.loadFirstLevel();
		player = (PlayerShip) newField.getGroup("playerGroup").getSprites()[0];
		initControls();
		initEvents();
		addPlayField(newField);
	}

	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		eventPool.checkEvents();
	}

	/**
	 * Initializes the specific Events handled in the Grandius PlayState.
	 */
	public void initEvents() {
		eventPool = new EventPool();
		eventPool.addEvent(new PlayerFireEvent(myGame, player, this));
	}

	public void initControls() {
		playerControl = new KeyboardControl(player, myGame);
		playerControl.addInput(KeyEvent.VK_LEFT, "moveLeft", PLAYER_CLASS);
		playerControl.addInput(KeyEvent.VK_RIGHT, "moveRight", PLAYER_CLASS);
		playerControl.addInput(KeyEvent.VK_UP, "moveUp", PLAYER_CLASS);
		playerControl.addInput(KeyEvent.VK_DOWN, "moveDown", PLAYER_CLASS);
		newField.addControl("playerGroup", playerControl);
	}

	// TODO this method is being used for collision handling also
	public PlayerShip getPlayer() {
		return player;
	}
	
	public PlayField getPlayField(){
		return newField;
	}

}
