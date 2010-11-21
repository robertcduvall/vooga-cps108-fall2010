package vooga.games.cyberion.states;

import java.awt.event.KeyEvent;

import vooga.engine.control.KeyboardControl;
import vooga.engine.core.PlayField;
import vooga.engine.event.EventPool;
import vooga.engine.factory.LevelManager;
import vooga.engine.state.GameState;
import vooga.games.cyberion.DropThis;
import vooga.games.cyberion.sprites.playership.PlayerShip;

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
	private String PLAYER_CLASS;
	private PlayField newField;

	public PlayState(LevelManager levelManager, DropThis game) {
		myLevelManager = levelManager;
		myGame = game;

	}

	public void initialize() {
		// spriteGroupSpeedMap = new HashMap<SpriteGroup, Double>();
		newField = myLevelManager.loadFirstLevel();
		player = (PlayerShip) newField.getGroup("playerGroup").getSprites()[0];
		initControls();
		initEvents();
		this.addUpdatePlayField(newField);
		this.addRenderPlayField(newField);
	}

	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		playerControl.update();
		eventPool.checkEvents();
	}

	/**
	 * Initializes the specific Events handled in the Grandius PlayState.
	 */
	public void initEvents() {
		eventPool = new EventPool();
	}

	public void initControls() {
		playerControl = new KeyboardControl(player, myGame);
		playerControl.addInput(KeyEvent.VK_LEFT, "moveLeft", PLAYER_CLASS);
		playerControl.addInput(KeyEvent.VK_RIGHT, "moveRight", PLAYER_CLASS);
		playerControl.addInput(KeyEvent.VK_UP, "moveUp", PLAYER_CLASS);
		playerControl.addInput(KeyEvent.VK_DOWN, "moveDown", PLAYER_CLASS);
	}

	// TODO this method is being used for collision handling also
	public PlayerShip getPlayer() {
		return player;
	}

}
