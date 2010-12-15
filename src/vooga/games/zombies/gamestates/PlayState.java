package vooga.games.zombies.gamestates;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;

import vooga.engine.networking.client.Username;
import vooga.engine.overlay.*;
import vooga.engine.control.KeyboardControl;
import vooga.engine.core.PlayField;
import vooga.engine.event.EventPool;
import vooga.engine.factory.LevelParser;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.engine.util.SoundPlayer;
import vooga.games.zombies.*;
import vooga.games.zombies.events.*;

public class PlayState extends GameState implements Constants {
	private String PLAY_XML_PATH;

	protected Blah currentGame;

	protected Shooter player;
	protected PlayField playField;
	protected Timer timer;
	protected KeyboardControl control;
	protected KeyboardControl control2;
	protected EventPool eventPool;

	protected AddZombieEvent addZombies;
	AddRandomItemEvent addItems;
	protected OverlayTracker levelTracker;
	protected int level;

	public PlayState(Blah game, String xmlPath) {
		currentGame = game;
		PLAY_XML_PATH = xmlPath;
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
		timer = new Timer(level);
		setupPlayer();
		resetLevel();
		initOverlays();
		initEnvironment();
		setListeners();
	 }

	 private void resetLevel() {
		 level = 0;
	 }

	 protected void setupPlayer() {
	 }

	 /**
	  * This method initializes the zombies, bullets, players, overlays
	  * SpriteGroup, set them up with their respective collision managers, and
	  * associate these managers with playField.
	  */
	 protected void initEnvironment() {

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
		 control.addInput(KeyEvent.VK_8, "killOtherPlayer", PLAYER_CLASS);

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

	 public int getPlayerHealth(Shooter player) {
		 return player.getHealth().getStat();
	 }

	 /**
	  * render the graphics component in the game
	  */
	  public void render(Graphics2D g) {
		 playField.render(g);
		 if (getLevelStatOverlay().isActive()) {
			 getLevelStatOverlay().render(g);
		 }
	  }

}