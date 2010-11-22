package vooga.games.zombieland.gamestates;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.background.ImageBackground;

import vooga.engine.overlay.OverlayCreator;
import vooga.engine.overlay.OverlayStat;
import vooga.engine.overlay.OverlayString;
import vooga.engine.overlay.OverlayTracker;
import vooga.engine.overlay.Stat;
import vooga.engine.control.KeyboardControl;
import vooga.engine.event.EventPool;
import vooga.engine.factory.LevelParser;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.games.zombieland.*;
import vooga.games.zombieland.events.AddZombieEvent;
import vooga.games.zombieland.events.LevelEndEvent;
import vooga.games.zombieland.items.*;
import vooga.games.zombieland.weapons.*;

public class PlayState extends GameState implements Constants{

	private static Blah currentGame;

	private Shooter player;
	private PlayField playField;
	private Timer timer;
	private KeyboardControl control;
	private KeyboardControl control2;
	private EventPool eventPool;
	private Random random;

	private OverlayString overlayGameOverString;
	private OverlayStat overlayLevelStat;
	private OverlayTracker tracker;
	private OverlayTracker levelTracker;

	private AddZombieEvent addZombies;
	private LevelEndEvent endLevel;

	private Stat<Integer> statLevel;
	private int level;

	public PlayState(Blah game) {
		currentGame = game;
	}

	/**
	 * setup 
	 */
	public void initialize() {
		OverlayCreator.setGame(currentGame);
		tracker = OverlayCreator
		.createOverlays(XML_PATH);
		levelTracker = OverlayCreator.createOverlays(STATES_XML_PATH);
		random = new Random();
		LevelParser parser = new LevelParser();
		playField = parser.getPlayfield(
				"src/vooga/games/zombieland/resources/levels/level1.xml",
				currentGame);
		player = (Shooter) playField.getGroup("Players").getSprites()[0];
		player.setState(this);
		
		initOverlays();
		initEnvironment();

		setListeners();
	}

	/**
	 * This method initializes the zombies, bullets, players, overlays
	 * SpriteGroup, set them up with their respective collision managers, and
	 * associate these managers with playField.
	 */
	private void initEnvironment() {

		eventPool = new EventPool();
		addZombies = new AddZombieEvent(playField.getGroup("Zombies"));
		endLevel = new LevelEndEvent(player, this, levelZombies());
		createZombies();	

		eventPool.addEvent(addZombies);
		eventPool.addEvent(endLevel);

		int delay = Resources.getInt("timer");
		timer = new Timer(delay);


	}

	public void createZombies() {
		statLevel.setStat(level + 1);
		level++;
		overlayLevelStat.setActive(true);

		for (int i = 0; i< levelZombies(); i++)
		{
			addZombies.addEnemy(new Zombie("New", level, player, this));
		}
		endLevel.updateDeaths(levelZombies());
	}

	public int levelZombies() {
		return (int) (Zombie.zombiesPerLevel() * level* (Double)Resources.getDouble("zombieLimitingFactor"));
	}

	/**
	 * This method initializes the overlay system: the overlayHealthString, the
	 * OverlayHealthBar, the OverlayScoreString, the overlayAmmoString, the
	 * OverlayLevelString, overllayPauseString.
	 */
	private void initOverlays() {
		statLevel = levelTracker.getStat("initLevel", new Integer(0));
		overlayLevelStat = levelTracker.getOverlay("levels", overlayLevelStat);
		overlayLevelStat.setActive(false);
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
	 * Load the image for a bullet with the correct orientation with respect to
	 * the shooter and add it to the screen
	 * 
	 * @param bullet
	 *            a bullet instantiated by the shooter
	 * @param angle
	 *            the orientation of the bullet (in degrees)
	 */
	public void addBullet(Bullet bullet) {
		SpriteGroup bullets = playField.getGroup("Bullets");
		bullets.add(bullet);
	}

	/**
	 * Spawn a random item at position (x,y)
	 * 
	 * @param x
	 *            x coordinate
	 * @param y
	 *            y coordinate
	 */
	public void addRandomItem(double x, double y) {

		int choice = random.nextInt(3);

		String weaponchoices = Resources.getString("weaponchoices");
		String delim = Resources.getString("delim");

		String[] options = weaponchoices.split(delim);
		String option = options[choice];

		BufferedImage itemimage = Resources.getImage(option);
		SpriteGroup items = playField.getGroup("Items");
		int healthOption = Resources.getInt("healthOption");

		if (choice == healthOption) {
			getHealthItem(x, y, option, itemimage, items);
		} else {
			getWeaponItem(x, y, option, itemimage, items);
		}

	}

	/**
	 * This method returns a gun item and adds ammo for the player.
	 * 
	 * @param x
	 * @param y
	 * @param option
	 * @param itemimage
	 * @param items
	 */
	private void getWeaponItem(double x, double y, String option,
			BufferedImage itemimage, SpriteGroup items) {
		int weaponoption = Resources.getInt(option);
		Item newGun = new WeaponItem(player, new Sprite(itemimage),
				weaponoption, x, y);
		items.add(newGun);
		newGun.setActive(true);
	}

	/**
	 * This method returns a health kit and adds 100 health for the player.
	 * 
	 * @param x
	 * @param y
	 * @param option
	 * @param itemimage
	 * @param items
	 */
	private void getHealthItem(double x, double y, String option,
			BufferedImage itemimage, SpriteGroup items) {
		int health = Resources.getInt(option);
		Item healthkit = new HealthItem(player, new Sprite(itemimage), health,
				x, y);
		items.add(healthkit);
		healthkit.setActive(true);
	}

	/**
	 * set up listeners for keyboard controls
	 */
	public void setListeners() {

		control = new KeyboardControl(player, currentGame);
		control2 = new KeyboardControl(currentGame, currentGame);
		control.addInput(KeyEvent.VK_LEFT, "goLeft", PLAYER_CLASS, null);
		control.addInput(KeyEvent.VK_RIGHT, "goRight", PLAYER_CLASS, null);
		control.addInput(KeyEvent.VK_UP, "goUp", PLAYER_CLASS, null);
		control.addInput(KeyEvent.VK_DOWN, "goDown", PLAYER_CLASS, null);
		control.addInput(KeyEvent.VK_SPACE, "shoot", PLAYER_CLASS, null);


		control.addInput(KeyEvent.VK_1, "switchWeapons", PLAYER_CLASS, new Class[] {int.class});
		control.setParams(KeyEvent.VK_1, 0);

		control.addInput(KeyEvent.VK_2, "switchWeapons", PLAYER_CLASS, new Class[] {int.class});
		control.setParams(KeyEvent.VK_2, 1);

		control.addInput(KeyEvent.VK_3, "switchWeapons", PLAYER_CLASS, new Class[] {int.class});
		control.setParams(KeyEvent.VK_3, 2);

		control2.addInput(KeyEvent.VK_P, "pause", MAIN_CLASS);
	}

	/**
	 * update all components of the ZombieLand game. This method checks to see
	 * if more zombies can be added or if the level has been completed.
	 */
	public void update(long elapsedTime) {
		playField.update(elapsedTime);
		control.update();
		control2.update();
		if (timer.action(elapsedTime)) {
			overlayLevelStat.setActive(false);
			addZombies.timeUp();
		}
		eventPool.checkEvents();
	}

	/**
	 * render the graphics component in the game
	 */
	public void render(Graphics2D g) {
		playField.render(g);
		if (overlayLevelStat.isActive()) {
			overlayLevelStat.render(g);
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
	 * Render game over screen
	 * 
	 * @param g
	 */
	private void renderGameOver(Graphics2D g) {

		int overlayStringX = Resources.getInt("overlayStringX");
		int overlayStringY = Resources.getInt("overlayStringY");

		overlayGameOverString = new OverlayString("GAME OVER", Color.BLACK);
		overlayGameOverString.setLocation(overlayStringX, overlayStringY);
		overlayGameOverString.render(g);
		endGame();
	}

	/**
	 * Stop the game altogether
	 */
	private void endGame() {
		currentGame.end();
	}

}
