package vooga.games.grandius.states;

import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import vooga.engine.control.KeyboardControl;
import vooga.engine.core.PlayField;
import vooga.engine.event.EventPool;
import vooga.engine.factory.LevelManager;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.games.grandius.DropThis;
import vooga.games.grandius.Player;
import vooga.games.grandius.events.FireBlackHoleEvent;
import vooga.games.grandius.events.FireHorizontalEvent;
import vooga.games.grandius.events.FireMissileEvent;
import vooga.games.grandius.events.FireVerticalEvent;
import vooga.games.grandius.events.ZipsterFireEvent;

public class PlayState extends GameState {
	private static DropThis myGame;
	private Player player;
	private EventPool eventPool;
	//private boolean reacherShieldsDepleted;
//	private boolean skipLevel = false;
	private LevelManager myLevelManager;
	private KeyboardControl playerControl;
	private static final String PLAYER_CLASS = Resources.getString("playerClass");
	
	public PlayState(LevelManager levelManager, DropThis game) {
		myLevelManager = levelManager;
		myGame = game;
	}
	
	@Override
	public void initialize() {
		PlayField newField = myLevelManager.loadFirstLevel();
		player = (Player) newField.getGroup("playerGroup").getSprites()[0];
		initControls();
		initEvents();
		eventPool.addEvent(new ZipsterFireEvent(myGame, player, newField.getGroup("enemyGroup"), this));
		this.addUpdatePlayField(newField);
		this.addRenderPlayField(newField);
	}
	
	@Override
	public void update(long elapsedTime){
		super.update(elapsedTime);
		playerControl.update();
		eventPool.checkEvents();
		checkCheats();
//		updateScreenSprites(); //TODO is this method needed?
		//updateEntities();
		//checkBossParts(); //TODO turn into rules?
	}
	
	/**
	 * Initializes the specific Events handled in the Grandius PlayState.
	 */
	private void initEvents() {
		eventPool = new EventPool();
		eventPool.addEvent(new FireHorizontalEvent(myGame, player, this));
		eventPool.addEvent(new FireVerticalEvent(myGame, player, this));
		eventPool.addEvent(new FireMissileEvent(myGame, player, this));
		eventPool.addEvent(new FireBlackHoleEvent(myGame, player, this));
	}
		
	/**
	 * Initializes the controls relevant to the Player.
	 */
	public void initControls() {
		playerControl = new KeyboardControl(player, myGame);
		playerControl.addInput(KeyEvent.VK_LEFT, "moveLeft", PLAYER_CLASS);
		playerControl.addInput(KeyEvent.VK_RIGHT, "moveRight", PLAYER_CLASS);
		playerControl.addInput(KeyEvent.VK_UP, "moveUp", PLAYER_CLASS);
		playerControl.addInput(KeyEvent.VK_DOWN, "moveDown", PLAYER_CLASS);
	}
		
	private void checkCheats() {
		if (myGame.keyPressed(KeyEvent.VK_ENTER)) {
			JFrame frame = new JFrame();
			String userInput = (String) JOptionPane.showInputDialog(frame,
					"Enter a cheat code:", "Cheats", JOptionPane.PLAIN_MESSAGE);
			if (userInput.equals(Resources.getString("invincibility")))
				player.setInvincible();
			else if (userInput.equals(Resources.getString("skipLevel")))
				player.skipLevel();
			else if (userInput.equals(Resources.getString("extraPoints")))
				player.updateScore(1000000);
			else if (userInput.equals(Resources.getString("extraCash")))
				player.updateCash(5000);
			else if (userInput.equals(Resources.getString("activateMissile")))
				player.setMissileActive();
		}
	}
	
	//TODO this method is being used for collision handling also
	public Player getPlayer() {
		return player;
	}
		
}