package vooga.games.cyberion.states;

import java.awt.Color;
import java.awt.event.KeyEvent;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.background.ColorBackground;

import vooga.engine.control.KeyboardControl;
import vooga.engine.core.PlayField;
import vooga.engine.event.EventPool;
import vooga.engine.factory.LevelManager;
import vooga.engine.overlay.Stat;
import vooga.engine.state.GameState;
import vooga.games.cyberion.DropThis;
import vooga.games.cyberion.events.EnemyFireEvent;
import vooga.games.cyberion.events.GameOverEvent;
import vooga.games.cyberion.events.PlayerFireEvent;
import vooga.games.cyberion.sprites.enemyship.EnemyShip;
import vooga.games.cyberion.sprites.playership.PlayerShip;
import vooga.games.cyberion.events.LevelCompleteEvent;

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
	private Sprite[] enemy;
	private String PLAYER_CLASS = "vooga.games.cyberion.sprites.playership.PlayerShip";
	private PlayField newField;

	public PlayState(LevelManager levelManager, DropThis game) {
		myLevelManager = levelManager;
		myGame = game;
	

	}

	public void initialize() {
		newField = myLevelManager.loadFirstLevel();
		player = (PlayerShip) newField.getGroup("playerGroup").getSprites()[0];
		enemy = newField.getGroup("enemyGroup").getSprites();
		initControls();
		initEvents();
		addPlayField(newField);
	}

	public PlayState nextLevel() {
		this.removeEverything();
		if (myLevelManager.getCurrentLevel()==myLevelManager.getNumLevels())
		{
			System.out.println("END");
			return null;
		}
		else
		{
		newField = myLevelManager.loadNextLevel();
		newField.getGroup("playerGroup").add(player);
		enemy = newField.getGroup("enemyGroup").getSprites();
		initControls();
		initEvents();
		Stat<Integer> tempStat = (Stat<Integer>) player.getStat("levelStat");
		tempStat.setStat(tempStat.getStat()+1); 
		addPlayField(newField);
		return this;
		}
	}

	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		eventPool.checkEvents();
	}

	/**
	 * Initializes the specific Events handled in the PlayState.
	 */
	public void initEvents() {
		eventPool = new EventPool();
		eventPool.addEvent(new PlayerFireEvent(myGame, player, this));
		eventPool.addEvent(new EnemyFireEvent(myGame, enemy, this));
		eventPool.addEvent(new LevelCompleteEvent(myGame, this, newField
				.getGroup("enemyGroup")));
		eventPool.addEvent(new GameOverEvent(myGame, player));
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

	public PlayField getPlayField() {
		return newField;
	}

}
