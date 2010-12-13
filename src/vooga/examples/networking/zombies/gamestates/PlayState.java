package vooga.examples.networking.zombies.gamestates;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;

import vooga.engine.overlay.*;
import vooga.engine.control.KeyboardControl;
import vooga.engine.core.PlayField;
import vooga.engine.event.EventPool;
import vooga.engine.factory.LevelParser;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.engine.util.SoundPlayer;
import vooga.examples.networking.zombies.*;
import vooga.examples.networking.zombies.events.*;

public class PlayState extends GameState implements Constants {

	private static final String PLAY_XML_PATH = "src/vooga/examples/networking/zombies/resources/levels/baseLevel.xml";

	private Blah currentGame;

	private Shooter player;
	private Shooter otherPlayer;
	private PlayField playField;
	private Timer timer;
	private KeyboardControl control;
	private KeyboardControl control2;
	private EventPool eventPool;

	AddZombieEvent addZombies;
	private OverlayTracker levelTracker;
	private int level;

	public PlayState(Blah game) {
		currentGame = game;
	}

	/**
	 * setup
	 */
	public void initialize() {

		OverlayCreator.setGame(currentGame);
		levelTracker = OverlayCreator.createOverlays(STATES_XML_PATH);
		LevelParser parser = new LevelParser();
		playField = parser.getPlayfield(PLAY_XML_PATH, currentGame);
		this.addPlayField(playField);
		setupPlayer();
		resetLevel();
		initOverlays();
		initEnvironment();
		setListeners();
	}

	private void resetLevel() {
		level = 0;
	}

	private void setupPlayer() {
		player = (Shooter) playField.getGroup("Players").getSprites()[0];
		player.setConnection(connection);
		otherPlayer = (Shooter) playField.getGroup("Players").getSprites()[1];
	}

	/**
	 * This method initializes the zombies, bullets, players, overlays
	 * SpriteGroup, set them up with their respective collision managers, and
	 * associate these managers with playField.
	 */
	private void initEnvironment() {

		eventPool = new EventPool();
		SpriteGroup items = playField.getGroup("Items");
		AddRandomItemEvent additems = new AddRandomItemEvent(playField, player,
				items);

		addZombies = new AddZombieEvent(playField.getGroup("Zombies"));

		LevelEndEvent endLevel = new LevelEndEvent(player, this, addZombies,additems);

		SpriteGroup bullets = playField.getGroup("Bullets");
		AddBulletsEvent addbullets = new AddBulletsEvent(bullets);
		player.setBulletListener(addbullets);

		eventPool.addEvent(additems);
		eventPool.addEvent(addbullets);
		eventPool.addEvent(addZombies);
		eventPool.addEvent(endLevel);

		int delay = Resources.getInt("timer");
		timer = new Timer(delay);

		SoundPlayer.playMusic(playField.getMusic(0));
	}

	/**
	 * This method returns the current level in play.
	 * 
	 * @return
	 */
	public int getLevel() {
		return level;
	}

	public void setLevel(int newlevel) {
		level = newlevel;
	}

	/**
	 * This method initializes the overlay system: the overlayHealthString, the
	 * OverlayHealthBar, the OverlayScoreString, the overlayAmmoString, the
	 * OverlayLevelString, overllayPauseString.
	 */
	private void initOverlays() {

		OverlayStat overlayLevelStat = (OverlayStat) levelTracker
				.getOverlay("levels");
		overlayLevelStat.setActive(false);
	}

	/**
	 * Get the int level stat
	 * 
	 * @return
	 */
	public Stat<Integer> getStatLevel() {
		return levelTracker.getStat("initLevel", new Integer(0));
	}

	/**
	 * get the OverlayStat object for the level stat
	 * 
	 * @return
	 */
	public OverlayStat getLevelStatOverlay() {
		return (OverlayStat) levelTracker.getOverlay("levels");
	}

	/**
	 * This method sets the new Delay Time
	 */
	public void setNewDelay() {
		int timeInterval = Resources.getInt("timeInterval");
		double delayFactor = Resources.getDouble("delayFactor");

		timer.setDelay((long) (timeInterval / level * delayFactor));
	}

	/**
	 * set up listeners for keyboard controls
	 */
	public void setListeners() {

		control = new KeyboardControl(player, currentGame);
		control2 = new KeyboardControl(currentGame, currentGame);
		control.addInput(KeyEvent.VK_LEFT, "goLeft", PLAYER_CLASS);
		control.addInput(KeyEvent.VK_RIGHT, "goRight", PLAYER_CLASS);
		control.addInput(KeyEvent.VK_UP, "goUp", PLAYER_CLASS);
		control.addInput(KeyEvent.VK_DOWN, "goDown", PLAYER_CLASS);
		control.addInput(KeyEvent.VK_SPACE, "shoot", PLAYER_CLASS);

		control.addInput(KeyEvent.VK_1, "switchWeapons", PLAYER_CLASS,
				new Class[] { int.class });
		control.setParams(KeyEvent.VK_1, 0);

		control.addInput(KeyEvent.VK_2, "switchWeapons", PLAYER_CLASS,
				new Class[] { int.class });
		control.setParams(KeyEvent.VK_2, 1);

		control.addInput(KeyEvent.VK_3, "switchWeapons", PLAYER_CLASS,
				new Class[] { int.class });
		control.setParams(KeyEvent.VK_3, 2);

		control2.addInput(KeyEvent.VK_P, "pause", MAIN_CLASS);
		playField.addControl("Shooter", control);
		playField.addControl("Game", control2);
	}

	/**
	 * This method gets the current control for the game.
	 * 
	 * @return
	 */
	public KeyboardControl getPlayGameControl() {
		return control;
	}
	
	/**
	 * If the String starts with the Move class identifier then deserialize the String into a Move object and place the piece on the
	 * board. Otherwise set the message to the String we received.
	 * 
	 * @param data data received from the socket
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	@Override
	public void interpretMessage(String data){
		if(data.startsWith(Location.getIdentifier())){
			Location loc = (Location) (Location.deserialize(data));
			otherPlayer.setLocation(loc.getX(), loc.getY());
		}
		else
			setMessage(data);
	}
	
	/**
	 * Called by the GameState API to determine whether or not it should listen for a message from the socket. In this case it returns true if
	 * there everyone is still connected to the socket. <b>Must be overridden to implement networking API.</b>
	 * 
	 * @return whether or not to listen for a message from the socket
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	@Override
	public boolean shouldGetData(){
		return connection.isConnected();
	}

	/**
	 * update all components of the ZombieLand game. This method checks to see
	 * if more zombies can be added or if the level has been completed.
	 */
	public void update(long elapsedTime) {
		if (isActive()) {
			super.update(elapsedTime);
			if (timer.action(elapsedTime)) {
				getLevelStatOverlay().setActive(false);
				addZombies.timeUp();
			}
			eventPool.checkEvents();
		}
	}
	
	public void goUp(){
		otherPlayer.goUp();
	}
	
	public void goDown(){
		otherPlayer.goDown();
	}
	
	public void goLeft(){
		otherPlayer.goLeft();
	}
	
	public void goRight(){
		otherPlayer.goRight();
	}
	
	public void shoot(){
		otherPlayer.shoot();
	}
	
	public void still(){}
	
	/**
	 * render the graphics component in the game
	 */
	public void render(Graphics2D g) {
		playField.render(g);
		if (getLevelStatOverlay().isActive()) {
			getLevelStatOverlay().render(g);
		}
		if (gameOver()) {
			endGame();
		}
	}

	/**
	 * Test if the the player is still active. If no, that means the end game
	 * conditions have been met
	 * 
	 * @return true if the end game conditions have been met
	 */
	private boolean gameOver() {
		return !(player.isActive());
	}

	/**
	 * Stop the game altogether
	 */
	private void endGame() {
		currentGame.end();
	}

}
