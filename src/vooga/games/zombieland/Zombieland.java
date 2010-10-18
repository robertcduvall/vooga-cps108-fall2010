package vooga.games.zombieland;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.ResourceBundle;

import vooga.engine.core.Game;
import vooga.engine.overlay.*;
import vooga.engine.player.control.KeyboardControl;

import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.background.ImageBackground;
import com.golden.gamedev.util.ImageUtil;

/**
 * @date 10-8-10
 * @author Aaron Choi, Jimmy Mu, Yang Su
 * @description: The Zombieland game is a game in which the player controls a
 *               shooter that has access to a few different types of weapons
 *               with limited ammo. The objective is to stay alive and kill as
 *               many zombies as you can. Zombies are spawned regularly. When a
 *               zombie is killed, there's a chance that an bonus item will be
 *               dropped.
 * 
 * 
 */

public class Zombieland extends Game {

	private static final String PLAYER_CLASS = "vooga.games.zombieland.Shooter";
	private static final String MAIN_RESOURCES_PATH = "vooga.games.zombieland.MainResources";
	private static final int GAME_WIDTH = 700;
	private static final int GAME_HEIGHT = 500;
	private static long defaultAnimationDelay = 300;

	private AnimatedSprite shooterImage;
	private ImageBackground background;
	private Shooter player;
	private int maxHealth;

	private SpriteGroup zombies;
	private SpriteGroup players;
	private SpriteGroup bullets;
	private SpriteGroup items;

	private PlayField playField;
	private Timer timer;
	private KeyboardControl control;

	private AnimatedSprite zombieUpImage;
	private AnimatedSprite zombieDownImage;
	private AnimatedSprite zombieLeftImage;
	private AnimatedSprite zombieRightImage;

	private AnimatedSprite zombieAttackUpImage;
	private AnimatedSprite zombieAttackDownImage;
	private AnimatedSprite zombieAttackLeftImage;
	private AnimatedSprite zombieAttackRightImage;

	private AnimatedSprite zombieDeathImage;

	private int startZombieHealth;
	private int startZombieDamage;
	private int startZombieDelay;

	private BufferedImage bulletImage;
	private BufferedImage shotGunImage;
	private BufferedImage assaultRifleImage;
	private BufferedImage healthImage;

	// private ZZCollisionManager zombieZombieManager;
	private PZCollisionManager playerZombieManager;
	private BZCollisionManager bulletZombieManager;
	private WallBoundManager entityWallManager;
	private HICollisionManager humanItemManager;

	private OverlayBar overlayHealthBar;
	private OverlayString overlayHealthString;
	private OverlayStat overlayScoreString;
	private OverlayString overlayGameOverString;
	private OverlayStat overlayAmmoString;
	private OverlayStat overlayLevelString;

	private Stat<Integer> statLevel;
	private int level;
	private int zombiesAppeared;
	private final static int ZOMBIES_PER_LEVEL = 25;
	private int zombieHealth;
	private int zombieDamage;

	private String delim;
	private ResourceBundle bundle;

	public void initResources() {
		//
		//
		bulletImage = getImage("resources/bullet.png");
		shotGunImage = getImage("resources/shotgun.png");
		assaultRifleImage = getImage("resources/assaultRifle.png");
		healthImage = getImage("resources/Health.png");

		// RESOURCES
		bundle = ResourceBundle.getBundle(MAIN_RESOURCES_PATH);
		delim = bundle.getString("delim");

		initializePlayer();
		initializeZombie();

		initializeGameEnvironment();
		resetOverlay();
		setListeners();
		initializePlayField();

	}

	public void resetOverlay() {
		resetOverlayHealthString(bundle);
		resetOverlayHealthBar(bundle);
		resetOverlayScoreString(bundle);
		resetOverlayAmmoString(bundle);
		resetOverlayLevelString(bundle);
	}

	private void resetOverlayLevelString(ResourceBundle bundle) {

		int overlayLevelStringX = parseInt(bundle, "overlayLevelStringX");
		int overlayLevelStringY = parseInt(bundle, "overlayLevelStringY");

		overlayLevelString = new OverlayStat("Level ", statLevel);
		overlayLevelString
				.setLocation(overlayLevelStringX, overlayLevelStringY);
		overlayLevelString.setActive(false);
	}

	private void resetOverlayAmmoString(ResourceBundle bundle) {

		int overlayAmmoStringX = parseInt(bundle, "overlayAmmoStringX");
		int overlayAmmoStringY = parseInt(bundle, "overlayAmmoStringY");
		String overlayAmmoStringMessage = bundle
				.getString("overlayAmmoStringMessage");

		overlayAmmoString = new OverlayStat(overlayAmmoStringMessage,
				player.getStatAmmo());
		overlayAmmoString.setColor(Color.BLUE);
		overlayAmmoString.setLocation(overlayAmmoStringX, overlayAmmoStringY);
	}

	private void resetOverlayScoreString(ResourceBundle bundle) {
		int overlayScoreStringX = parseInt(bundle, "overlayScoreStringX");
		int overlayScoreStringY = parseInt(bundle, "overlayScoreStringY");
		String overlayAmmoStringMessage = bundle
				.getString("overlayScoreStringMessage");

		overlayScoreString = new OverlayStat(overlayAmmoStringMessage,
				player.getScore());
		overlayScoreString
				.setLocation(overlayScoreStringX, overlayScoreStringY);
	}

	private void resetOverlayHealthBar(ResourceBundle bundle) {

		int overlayHealthBarX = parseInt(bundle, "overlayHealthBarX");
		int overlayHealthBarY = parseInt(bundle, "overlayHealthBarY");

		overlayHealthBar = new OverlayBar(player.getHealth(), player
				.getHealth().getStat());
		overlayHealthBar.setColor(Color.GREEN);
		overlayHealthBar.setLocation(overlayHealthBarX, overlayHealthBarY);
	}

	private void resetOverlayHealthString(ResourceBundle bundle) {

		int overlayHealthStringX = parseInt(bundle, "overlayHealthStringX");
		int overlayHeatlhStringY = parseInt(bundle, "overlayHealthStringY");

		overlayHealthString = new OverlayString("Health: ", Color.BLUE);
		overlayHealthString.setLocation(overlayHealthStringX,
				overlayHeatlhStringY);
	}

	private void initializeGameEnvironment() {

		level = parseInt(bundle, "startLevel");
		// Zombie.resetzombiesAppeared();

		int delay = parseInt(bundle, "timer");
		timer = new Timer(delay);

		statLevel = new Stat<Integer>(level);
		// random = new Random();

		String sandbackgroundpath = bundle.getString("sandbg");
		BufferedImage sandbg = getImage(sandbackgroundpath);
		background = new ImageBackground(sandbg, GAME_WIDTH, GAME_HEIGHT);

	}

	private void initializePlayField() {
		playField = new PlayField();

		zombies = new SpriteGroup("Zombies");
		bullets = new SpriteGroup("Bullets");
		items = new SpriteGroup("Items");
		players = new SpriteGroup("Players");
		players.add(player);

		playField.addGroup(players);
		playField.addGroup(zombies);
		playField.addGroup(bullets);
		playField.addGroup(items);
		playField.add(overlayLevelString);
		playField.setBackground(background);

		PZCollisionManager playerZombieManager = new PZCollisionManager();
		WallBoundManager entityWallManager = new WallBoundManager(background);
		BZCollisionManager bulletZombieManager = new BZCollisionManager();
		HICollisionManager humanItemManager = new HICollisionManager();

		playField.addCollisionGroup(players, items, humanItemManager);
		playField.addCollisionGroup(bullets, zombies, bulletZombieManager);
		playField.addCollisionGroup(players, players, entityWallManager);
		playField.addCollisionGroup(players, zombies, playerZombieManager);
	}

	private void initializePlayer() {
		double playerDefaultX = parseDouble(bundle, "playerDefaultX");
		double playerDefaultY = parseDouble(bundle, "playerDefaultY");
		int maxHealth = parseInt(bundle, "maxHealth");
		int playerRank = parseInt(bundle, "playerRank");

		BufferedImage[] defaultImage = getBufferedImageArray(
				MAIN_RESOURCES_PATH, "Down", delim);
		AnimatedSprite playerSprite = new AnimatedSprite(defaultImage,
				playerDefaultX, playerDefaultY);

		player = new Shooter("Hero", "Down", playerSprite, maxHealth,
				playerRank, this);

		// Player animations
		String[] list = { "Down", "Up", "Left", "Right" };
		for (int i = 0; i < list.length; i++) {
			AnimatedSprite animation = getInitializedAnimatedSprite(
					MAIN_RESOURCES_PATH, list[i], delim);
			player.mapNameToSprite(list[i], animation);
		}
	}

	public void initializeZombie() {

		startZombieHealth = parseInt(bundle, "startZombieHealth");
		startZombieDamage = parseInt(bundle, "startZombieDamage");
		startZombieDelay = parseInt(bundle, "startZombieDelay");

		// Zombie animations
		String delim = bundle.getString("delim");
		zombieDownImage = getInitializedAnimatedSprite(MAIN_RESOURCES_PATH,
				"ZombieDown", delim);
		zombieUpImage = getInitializedAnimatedSprite(MAIN_RESOURCES_PATH,
				"ZombieUp", delim);
		zombieLeftImage = getInitializedAnimatedSprite(MAIN_RESOURCES_PATH,
				"ZombieLeft", delim);
		zombieRightImage = getInitializedAnimatedSprite(MAIN_RESOURCES_PATH,
				"ZombieRight", delim);
		zombieAttackDownImage = getInitializedAnimatedSprite(
				MAIN_RESOURCES_PATH, "ZombieAttackDown", delim);
		zombieAttackUpImage = getInitializedAnimatedSprite(MAIN_RESOURCES_PATH,
				"ZombieAttackUp", delim);
		zombieAttackLeftImage = getInitializedAnimatedSprite(
				MAIN_RESOURCES_PATH, "ZombieAttackLeft", delim);
		zombieAttackRightImage = getInitializedAnimatedSprite(
				MAIN_RESOURCES_PATH, "ZombieAttackRight", delim);
		zombieDeathImage = getInitializedAnimatedSprite(MAIN_RESOURCES_PATH,
				"ZombieDeath", delim, startZombieDelay, false);
	}

	private BufferedImage[] getBufferedImageArray(String bundleURL, String key,
			String delim) {
		String value = bundle.getString(key);
		String[] list = value.split(delim);
		BufferedImage[] images = new BufferedImage[list.length];

		for (int i = 0; i < list.length; i++) {
			String imageLocation = bundle.getString(list[i]);
			images[i] = getImage(imageLocation);
		}

		return images;
	}

	// /**
	// * Initialize a set of images to be animated.
	// *
	// * @param images
	// * a set of images to be made into an animated sprite
	// * @return initialized animated sprite
	// */
	// private AnimatedSprite getInitializedAnimatedSprite(BufferedImage[]
	// images) {
	// return getInitializedAnimatedSprite(images, defaultAnimationDelay, true);
	// }
	//
	// /**
	// *
	// * Initialize a set of images to be animated.
	// *
	// * @param images
	// * a set of images to be made into an animated sprite
	// * @param delay
	// * animation delay
	// * @param loop
	// * loop the animation
	// * @return initialized animated sprite
	// */
	// private AnimatedSprite getInitializedAnimatedSprite(BufferedImage[]
	// images,
	// long delay, boolean loop) {
	// AnimatedSprite sprite = new AnimatedSprite(images);
	// initializeAnimatedSprite(sprite, delay, loop);
	// return sprite;
	// }

	private AnimatedSprite getInitializedAnimatedSprite(String bundleURL,
			String key, String delim) {
		return getInitializedAnimatedSprite(bundleURL, key, delim,
				defaultAnimationDelay, true);
	}

	private AnimatedSprite getInitializedAnimatedSprite(String bundleURL,
			String key, String delim, long delay, boolean loop) {
		AnimatedSprite sprite = new AnimatedSprite(getBufferedImageArray(
				bundleURL, key, delim));
		initializeAnimatedSprite(sprite, delay, loop);
		return sprite;
	}

	/**
	 * Set the attributes for an animated sprite
	 * 
	 * @param sprite
	 *            animated sprite object
	 * @param delay
	 *            animation delay
	 * @param loop
	 *            loop animation
	 */
	private void initializeAnimatedSprite(AnimatedSprite sprite, long delay,
			boolean loop) {
		sprite.getAnimationTimer().setDelay(delay);
		sprite.setAnimationFrame(0, sprite.getImages().length - 1);
		sprite.setAnimate(true);
		sprite.setLoopAnim(loop);
	}

	private double parseDouble(ResourceBundle bundle, String keyName) {
		String string = bundle.getString(keyName);
		return Double.parseDouble(string);
	}

	private int parseInt(ResourceBundle bundle, String keyName) {
		String string = bundle.getString(keyName);
		return Integer.parseInt(string);
	}

	/**
	 * update all components of the game
	 */
	public void update(long elapsedTime) {
		playField.update(elapsedTime);
		control.update();
		player.update(elapsedTime);
		overlayHealthBar.update(elapsedTime);
		overlayHealthString.update(elapsedTime);
		overlayScoreString.update(elapsedTime);
		overlayAmmoString.update(elapsedTime);

		if (moreZombieCanBeAdded()) {
			if (timer.action(elapsedTime)) {
				System.out.print(zombiesAppeared);
				addZombie();
				zombiesAppeared++;
			}
		} else {
			statLevel.setStat(level + 1);
			overlayLevelString.update(elapsedTime);
			overlayLevelString.setActive(true);
			playField.update(elapsedTime);
			if (timer.action(elapsedTime)) {
				updateDelay(level);
				zombiesAppeared = 0;
				level++;
				player.resetLevelScore();
				overlayLevelString.setActive(false);
			}
		}
	}

	private boolean moreZombieCanBeAdded() {
		return zombiesAppeared <= ZOMBIES_PER_LEVEL * level;
	}

	private void updateDelay(int currentLevel) {
		long newDelay = (long) ((2000 / currentLevel) * 1.5);
		timer.setDelay(newDelay);
	}

	/**
	 * Add a zombie to the world. The position is randomly picked. The health
	 * and damage will increase every level.
	 */
	public void addZombie() {
		 Zombie newZombie = new Zombie("New", "Moving",zombieDownImage,zombieUpImage,
                 zombieLeftImage,zombieRightImage, player, this, zombieHealth, zombieDamage);

		newZombie.setX(Math.random() * GAME_WIDTH);
		newZombie.setY(Math.random() * GAME_HEIGHT);

		newZombie.updateStats(level);
		newZombie.setActive(true);

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
	public void addBullet(Bullet bullet, double angle) {
		bullet.getCurrentSprite().setImage(
				ImageUtil.rotate(bulletImage, (int) angle));
		bullet.setActive(true);
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

		Random random = new Random();
		int choice = random.nextInt(3);

		Item item;

		switch (choice) {
		case 0:
			item = new WeaponItem(player, new Sprite(assaultRifleImage), 1, x,
					y);
			break;
		case 1:
			item = new WeaponItem(player, new Sprite(shotGunImage), 2, x, y);
			break;
		case 2:
			item = new HealthItem(player, new Sprite(healthImage), 100, x, y);
			break;

		default:
			item = null;
		}
		item.setActive(true);
		items.add(item);
	}

	/**
	 * set up listeners for keyboard controls
	 */
	public void setListeners() {
		control = new KeyboardControl(player, this);

		String VK_LEFT_METHOD = bundle.getString("VK_LEFT");
		String VK_RIGHT_METHOD = bundle.getString("VK_RIGHT");
		String VK_UP_METHOD = bundle.getString("VK_UP");
		String VK_DOWN_METHOD = bundle.getString("VK_DOWN");
		String VK_SPACE_METHOD = bundle.getString("VK_SPACE");

		control.addInput(KeyEvent.VK_LEFT, VK_LEFT_METHOD, PLAYER_CLASS, null);
		control.addInput(KeyEvent.VK_RIGHT, VK_RIGHT_METHOD, PLAYER_CLASS, null);
		control.addInput(KeyEvent.VK_UP, VK_UP_METHOD, PLAYER_CLASS, null);
		control.addInput(KeyEvent.VK_DOWN, VK_DOWN_METHOD, PLAYER_CLASS, null);
		control.addInput(KeyEvent.VK_SPACE, "shoot", PLAYER_CLASS, null);

		int VK_1_INT = parseInt(bundle, "VK_1");
		int VK_2_INT = parseInt(bundle, "VK_2");
		int VK_3_INT = parseInt(bundle, "VK_3");

		control.setParams(new Class[] { int.class });
		control.addInput(KeyEvent.VK_1, "switchWeapons", PLAYER_CLASS, VK_1_INT);
		control.setParams(new Class[] { int.class });
		control.addInput(KeyEvent.VK_2, "switchWeapons", PLAYER_CLASS, VK_2_INT);
		control.setParams(new Class[] { int.class });
		control.addInput(KeyEvent.VK_3, "switchWeapons", PLAYER_CLASS, VK_3_INT);

	}

	/**
	 * render the game
	 */
	public void render(Graphics2D g) {
		background.render(g);
		playField.render(g);
		overlayHealthBar.render(g);
		overlayHealthString.render(g);
		overlayScoreString.render(g);
		overlayAmmoString.render(g);
		if (overlayLevelString.isActive()) {
			overlayLevelString.render(g);
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
		overlayGameOverString = new OverlayString("GAME OVER", Color.BLACK);
		overlayGameOverString.setLocation(GAME_WIDTH / 2 - 60,
				GAME_HEIGHT / 2 - 10);
		overlayGameOverString.render(g);
		endGame();
	}

	/**
	 * Stop the game altogether
	 */
	private void endGame() {
		overlayGameOverString = new OverlayString("GAME OVER\nFinal Score: "
				+ player.getScore(), Color.BLACK);
		stop();
	}

	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new Zombieland(), new Dimension(GAME_WIDTH, GAME_HEIGHT),
				false);
		game.start();
	}

}
