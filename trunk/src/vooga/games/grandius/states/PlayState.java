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
import vooga.games.grandius.events.FireBlackHoleEvent;
import vooga.games.grandius.events.FireHorizontalEvent;
import vooga.games.grandius.events.FireMissileEvent;
import vooga.games.grandius.events.FireVerticalEvent;
import vooga.games.grandius.events.LevelCompleteEvent;
import vooga.games.grandius.events.ZipsterBrokeThroughEvent;
import vooga.games.grandius.events.ZipsterFireEvent;
import vooga.games.grandius.sprites.Player;

public class PlayState extends GameState {
	private static DropThis myGame;
	private Player player;
	private EventPool eventPool;
	//private boolean reacherShieldsDepleted;
//	private boolean skipLevel = false;
	private LevelManager myLevelManager;
	private KeyboardControl playerControl;
	private static final String PLAYER_CLASS = Resources.getString("playerClass");
	PlayField playField;
	
	public PlayField getField() {
		return playField;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public void setField(PlayField newField) {
		this.playField = newField;
		this.addUpdatePlayField(newField);
		this.addRenderPlayField(newField);
	}

	public PlayState(LevelManager levelManager, DropThis game) {
		myLevelManager = levelManager;
		myGame = game;
	}
	
	@Override
	public void initialize() {
		playField = myLevelManager.loadFirstLevel();
		player = (Player) playField.getGroup("playerGroup").getSprites()[0];
		initControls();
		initEvents();
		this.addUpdatePlayField(playField);
		this.addRenderPlayField(playField);
	}
	
	@Override
	public void update(long elapsedTime){
		super.update(elapsedTime);
		playerControl.update();
		eventPool.checkEvents();
		checkCheats();
	}
	
	/**
	 * Initializes the specific Events handled in the Grandius PlayState.
	 */
	public void initEvents() {
		eventPool = new EventPool();
		eventPool.addEvent(new FireHorizontalEvent(myGame, player, this));
		eventPool.addEvent(new FireVerticalEvent(myGame, player, this));
		eventPool.addEvent(new FireMissileEvent(myGame, player, this));
		eventPool.addEvent(new FireBlackHoleEvent(myGame, player, this));
		eventPool.addEvent(new ZipsterFireEvent(myGame, player, playField.getGroup("enemyGroup"), this));
		eventPool.addEvent(new LevelCompleteEvent(myGame, this));
		eventPool.addEvent(new ZipsterBrokeThroughEvent(myGame, playField.getGroup("enemyGroup")));
	}
	
	public EventPool getEventPool() {
		return eventPool;
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
		
	/**
	 * Checks for an "Enter" key-press and prompts the user for a cheat code, taking
	 * appropriate action if the cheat is valid.
	 */
	private void checkCheats() {
		if (myGame.keyPressed(KeyEvent.VK_ENTER)) {
			JFrame frame = new JFrame();
			String userInput = (String) JOptionPane.showInputDialog(frame,
					"Enter a cheat code:", "Cheats", JOptionPane.PLAIN_MESSAGE);
			if (userInput.equals(Resources.getString("invincibility")))
				player.setInvincible();
			else if (userInput.equals(Resources.getString("skipLevel"))) {
				new LevelCompleteEvent(myGame, this).actionPerformed();
			}
			else if (userInput.equals(Resources.getString("extraPoints")))
				player.updateScore(1000000);
			else if (userInput.equals(Resources.getString("extraCash")))
				player.updateCash(5000);
		}
	}
	
	/**
	 * Returns the PlayState's player; this method is used for collision handling.
	 */
	public Player getPlayer() {
		return player;
	}

	public LevelManager getLevelManager() {
		return myLevelManager;
	}
		
}