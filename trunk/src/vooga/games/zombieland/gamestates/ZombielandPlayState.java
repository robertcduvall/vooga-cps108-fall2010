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
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.games.zombieland.collision.BZCollisionManager;
import vooga.games.zombieland.collision.HICollisionManager;
import vooga.games.zombieland.collision.PZCollisionManager;
import vooga.games.zombieland.collision.WallBoundManager;
import vooga.games.zombieland.items.HealthItem;
import vooga.games.zombieland.items.Item;
import vooga.games.zombieland.items.WeaponItem;
import vooga.games.zombieland.weapons.Bullet;
import vooga.games.zombieland.Blah;
import vooga.games.zombieland.Constants;
import vooga.games.zombieland.Shooter;
import vooga.games.zombieland.Zombie;
import vooga.games.zombieland.ZombielandResources;

public class ZombielandPlayState extends GameState implements Constants{

	private static Blah currentGame;

	private Shooter player;
	private PlayField playField;
	private Timer timer;
	private KeyboardControl control;
	Random random;

	private OverlayString overlayGameOverString;
	private OverlayString overlayPauseString;
	private OverlayStat overlayLevelStat;
	private OverlayTracker tracker;

	private Stat<Integer> statLevel;
	private int level;
	private int zombiesAppeared;

	public ZombielandPlayState(Blah game) {
		super();
		currentGame = game;
		
		initialize();
	}
	
	/**
	 * setup 
	 */
	public void initialize() {
		OverlayCreator.setGame(currentGame);
		tracker = OverlayCreator
				.createOverlays(XML_PATH);
		random = new Random();
	
		initializePlayer();
		initEnvironment();
		initOverlays();
		setListeners();
	}
	
	/**
	 * Set the player with initial weapons and stats
	 */
	private void initializePlayer() {

		Stat<Integer> initHealth = tracker.getStat("initHealth", new Integer(0));
		Stat<Integer> initAmmo = tracker.getStat("initAmmo", new Integer(0));
		Stat<Integer> initScore = tracker.getStat("initScore", new Integer(0));

		player = new Shooter("Hero", "Down", currentGame, initHealth, initAmmo,
				initScore);
	}

//	/**
//	 * This method returns the Pause string
//	 * 
//	 * @return
//	 */
//	public OverlayString getOverlayPauseString() {
//		return overlayPauseString;
//	}

	/**
	 * This method initializes the zombies, bullets, players, overlays
	 * SpriteGroup, set them up with their respective collision managers, and
	 * associate these managers with playField.
	 */
	private void initEnvironment() {
		playField = new PlayField();

		//Set up the music
		String audiofile = ZombielandResources.getString("gamemusic");
		currentGame.playMusic(audiofile);

		//Set up the game background
		BufferedImage sandbg = ZombielandResources.getImage("sandbg");
		ImageBackground background = new ImageBackground(sandbg, GAME_WIDTH,
				GAME_HEIGHT);
		playField.setBackground(background);

		String spritegroupslist = ZombielandResources.getString("spritegroupslist");
		String delim = ZombielandResources.getString("delim");
		String[] spritegroups = spritegroupslist.split(delim);
		
		for(int i = 0; i < spritegroups.length; i++)
		{
			SpriteGroup currentGroup = new SpriteGroup(spritegroups[i]);
			playField.addGroup(currentGroup);
		}
		
		//Have not used reflections to be able to call on objects. I 
		//have not look into how to do that yet.
		PZCollisionManager playerZombieManager = new PZCollisionManager();
		playField.getGroup("Players").add(player);
		WallBoundManager entityWallManager = new WallBoundManager(background);
		BZCollisionManager bulletZombieManager = new BZCollisionManager();
		HICollisionManager humanItemManager = new HICollisionManager();
		
		playField.addCollisionGroup(playField.getGroup("Players"), playField.getGroup("Zombies"), playerZombieManager);
		playField.addCollisionGroup(playField.getGroup("Players"), playField.getGroup("Players"), entityWallManager);
		playField.addCollisionGroup(playField.getGroup("Bullets"), playField.getGroup("Zombies"), bulletZombieManager);
		playField.addCollisionGroup(playField.getGroup("Players"), playField.getGroup("Items"), humanItemManager);
		
		int delay = ZombielandResources.getInt("timer");
		timer = new Timer(delay);

		level = ZombielandResources.getInt("startLevel");
	}

	/**
	 * This method initializes the overlay system: the overlayHealthString, the
	 * OverlayHealthBar, the OverlayScoreString, the overlayAmmoString, the
	 * OverlayLevelString, overllayPauseString.
	 */
	public void initOverlays() {
		
//		int overlayXMLlocation = Resources.getInt("overlayXMLlocation");
//		int statLevelXMLlocation = Resources.getInt("statLevelXMLlocation");
//		int levelStatXMLlocation = Resources.getInt("levelStatXMLlocation");
//		int pauseStringXMLlocation = Resources.getInt("pauseStringXMLlocation");
//		int gameOverStringXMLlocation = Resources.getInt("gameOverStringXMLlocation");
				
		SpriteGroup overlays = tracker.getOverlayGroup("PlayStateOverlays");
		statLevel = tracker.getStat("initLevel", new Integer(0));
	
		overlayLevelStat = tracker.getOverlay("levels", overlayLevelStat);
		overlayLevelStat.setActive(false);
//		overlayPauseString = tracker.getStringOverlays().get(pauseStringXMLlocation);
//		overlayPauseString.setActive(false);
		overlayGameOverString = tracker.getOverlay("gameOver", overlayGameOverString);
		overlayGameOverString.setActive(false);
		playField.addGroup(overlays);
	}

	private boolean levelCompleted() {
		return player.getLevelScore() == zombiesAppeared;
	}

	/**
	 * This method sets the new Delay Time
	 */
	private void setNewDelay() {
		int timeInterval = ZombielandResources.getInt("timeInterval");
		double delayFactor = ZombielandResources.getDouble("delayFactor");

		timer.setDelay((long) (timeInterval / level * delayFactor));
	}

	/**
	 * Make the zombieCount start back from zero.
	 */
	private void resetZombiesCount() {
		zombiesAppeared = 0;
	}

	private boolean moreZombieCanBeAdded() {

		double zombieLimitingFactor = ZombielandResources
				.getDouble("zombieLimitingFactor");

		return zombiesAppeared < Zombie.zombiesPerLevel() * level
				* zombieLimitingFactor;
	}

	/**
	 * Add a zombie to the world. The position is randomly picked. The health
	 * and damage will increase every level.
	 */
	public void addZombie() {
		Zombie newZombie = new Zombie("New", "Moving", level, player,
				currentGame);
		zombiesAppeared++;
		SpriteGroup zombies = playField.getGroup("Zombies");
		zombies.add(newZombie);
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

		String weaponchoices = ZombielandResources.getString("weaponchoices");
		String delim = ZombielandResources.getString("delim");

		String[] options = weaponchoices.split(delim);
		String option = options[choice];

		BufferedImage itemimage = ZombielandResources.getImage(option);
		SpriteGroup items = playField.getGroup("Items");
		int healthOption = ZombielandResources.getInt("healthOption");

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
		int weaponoption = ZombielandResources.getInt(option);
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
		int health = ZombielandResources.getInt(option);
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
	}

	/**
	 * update all components of the ZombieLand game. This method checks to see
	 * if more zombies can be added or if the level has been completed.
	 */
	public void update(long elapsedTime) {
	
		if (isActive()) {
			playField.update(elapsedTime);
			control.update();
			if (moreZombieCanBeAdded()) {
				if (timer.action(elapsedTime)) {
					addZombie();
				}
			} else if (levelCompleted()) {
				statLevel.setStat(level + 1);
				overlayLevelStat.update(elapsedTime);
				overlayLevelStat.setActive(true);
				playField.update(elapsedTime);
	
				if (timer.action(elapsedTime)) {
	
					setNewDelay();
					resetZombiesCount();
	
					level++;
	
					player.resetLevelScore();
					overlayLevelStat.setActive(false);
				}
			}
		}
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
			renderGameOver(g);
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

		int overlayStringX = ZombielandResources.getInt("overlayStringX");
		int overlayStringY = ZombielandResources.getInt("overlayStringY");

		overlayGameOverString = new OverlayString("GAME OVER", Color.BLACK);
		overlayGameOverString.setLocation(overlayStringX, overlayStringY);
		overlayGameOverString.render(g);
		endGame();
	}

	/**
	 * Stop the game altogether
	 */
	private void endGame() {
		currentGame.stop();
	}

}
