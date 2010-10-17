package vooga.games.zombieland;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;

import vooga.engine.core.Game;
import vooga.engine.overlay.*;
import vooga.engine.player.control.KeyboardControl;
import vooga.engine.resource.ResourceHandler;
import vooga.engine.resource.Resources;

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
 */

public class Zombieland extends Game {

	private static final String PLAYER_CLASS = "vooga.games.zombieland.Shooter";
	private static final String MAIN_RESOURCES_PATH = "vooga.games.zombieland.MainResources";
	private static final int GAME_WIDTH = 700;
	private static final int GAME_HEIGHT = 500;
	private static long defaultAnimationDelay = 300;
	private final static int ZOMBIES_PER_LEVEL = 25;

	private int level;
	private Random random;
	private Shooter player;
	private Timer timer;
	private KeyboardControl control;
	private PlayField playField;
	private ImageBackground background;
	private Stat<Integer> statLevel;

	private OverlayBar overlayHealthBar;
	private OverlayString overlayHealthString;
	private OverlayStat overlayScoreString;
	private OverlayString overlayGameOverString;
	private OverlayStat overlayAmmoString;
	private OverlayStat overlayLevelString;

	public void initResources() {
		// RESOURCES

		ResourceBundle bundle = ResourceBundle.getBundle(MAIN_RESOURCES_PATH);
		String delim = bundle.getString("delim");

		String Down = bundle.getString("Down");
		BufferedImage[] playerDownImage = getBufferedImageArray(
				MAIN_RESOURCES_PATH, "Down", delim);
		AnimatedSprite shooterImage = new AnimatedSprite(playerDownImage, 350,
				250);

		player = new Shooter("Hero", "Down", shooterImage, 200, 0, this);

		// Player animations
		String[] list = { "Down", "Up", "Left", "Right" };
		for (int i = 0; i < list.length; i++) {
			String value = bundle.getString(list[i]);
			BufferedImage[] currentImage = getBufferedImageArray(
					MAIN_RESOURCES_PATH, list[i], delim);
			AnimatedSprite animation = getInitializedAnimatedSprite(currentImage);
			player.mapNameToSprite(list[i], animation);
		}

		// INITIALIZATIONS
		resetInitialValues();
		resetOverlay();
		setListeners();

		BufferedImage sandbg = getImage("resources/sandbackground.png");
		background = new ImageBackground(sandbg, GAME_WIDTH, GAME_HEIGHT);

		SpriteGroup zombies = new SpriteGroup("Zombies");
		SpriteGroup bullets = new SpriteGroup("Bullets");
		SpriteGroup items = new SpriteGroup("Items");
		SpriteGroup players = new SpriteGroup("Players");
		playField = new PlayField();

		players.add(player);
		playField.add(player);
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

	private Double parseDouble(String keyName) {
		ResourceBundle bundle = ResourceBundle.getBundle(MAIN_RESOURCES_PATH);
		String string = bundle.getString(keyName);
		return Double.parseDouble(string);
	}

	private Integer parseInt(String keyName) {
		ResourceBundle bundle = ResourceBundle.getBundle(MAIN_RESOURCES_PATH);
		String string = bundle.getString(keyName);
		return Integer.parseInt(string);
	}

	private void resetOverlay() {
		overlayHealthString = new OverlayString("Health: ", Color.BLUE);
		overlayHealthString.setLocation(5, 10);
		overlayHealthBar = new OverlayBar(player.getStatHealth(),
				player.getHealth());
		overlayHealthBar.setColor(Color.GREEN);
		overlayHealthBar.setLocation(80, 18);
		overlayScoreString = new OverlayStat("Kills: ", player.getStatScore());
		overlayScoreString.setLocation(385, 12);
		overlayAmmoString = new OverlayStat("Ammo: ", player.getStatAmmo());
		overlayAmmoString.setColor(Color.BLUE);
		overlayAmmoString.setLocation(470, 12);
		overlayLevelString = new OverlayStat("Level ", statLevel);
		overlayLevelString.setLocation(GAME_WIDTH / 2 - 60,
				GAME_HEIGHT / 2 - 10);
		overlayLevelString.setActive(false);
	}

	/**
	 * set up listeners for keyboard controls
	 */
	public void setListeners() {
		control = new KeyboardControl(player, this);

		control.addInput(KeyEvent.VK_LEFT, "goLeft", PLAYER_CLASS, null);
		control.addInput(KeyEvent.VK_RIGHT, "goRight", PLAYER_CLASS, null);
		control.addInput(KeyEvent.VK_UP, "goUp", PLAYER_CLASS, null);
		control.addInput(KeyEvent.VK_DOWN, "goDown", PLAYER_CLASS, null);
		control.addInput(KeyEvent.VK_SPACE, "shoot", PLAYER_CLASS, null);

		control.setParams(new Class[] { int.class });
		control.addInput(KeyEvent.VK_1, "switchWeapons", PLAYER_CLASS, 0);
		control.setParams(new Class[] { int.class });
		control.addInput(KeyEvent.VK_2, "switchWeapons", PLAYER_CLASS, 1);
		control.setParams(new Class[] { int.class });
		control.addInput(KeyEvent.VK_3, "switchWeapons", PLAYER_CLASS, 2);
	}

	private void resetInitialValues() {
		level = 1;
		Zombie.resetZombieCount();
		timer = new Timer(2000);
		statLevel = new Stat<Integer>(level);
		random = new Random();
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
				addZombie();
			}
		} else if (levelFinished()) {
			statLevel.setStat(level + 1);
			overlayLevelString.update(elapsedTime);
			overlayLevelString.setActive(true);
			playField.update(elapsedTime);
			if (timer.action(elapsedTime)) {

				updateDelay(level);
				Zombie.updateStats(level);
				Zombie.resetZombieCount();

				level++;
				player.resetLevelScore();
				overlayLevelString.setActive(false);

			}
		}
	}

	private boolean moreZombieCanBeAdded() {
		return Zombie.getZombieCount() < ZOMBIES_PER_LEVEL * level * 0.5;
	}

	private boolean levelFinished() {
		return player.getLevelScore() == Zombie.getZombieCount();
	}

	private void updateDelay(int currentLevel) {
		long newDelay = (long) ((2000 / currentLevel) * 1.5);
		timer.setDelay(newDelay);
	}

	private BufferedImage[] getBufferedImageArray(String bundleURL, String key,
			String delim) {
		ResourceBundle bundle = ResourceBundle.getBundle(bundleURL);

		String value = bundle.getString(key);
		String[] list = value.split(delim);
		BufferedImage[] images = new BufferedImage[list.length];

		for (int i = 0; i < list.length; i++) {
			String imageLocation = bundle.getString(list[i]);
			images[i] = getImage(imageLocation);
		}

		return images;
	}

	/**
	 * Add a zombie to the world. The position is randomly picked. The health
	 * and damage will increase every level.
	 */
	public void addZombie() {

		// Zombie animations
		ResourceBundle bundle = ResourceBundle.getBundle(MAIN_RESOURCES_PATH);

		String delim = bundle.getString("delim");

		BufferedImage[] zombieDownImage = getBufferedImageArray(
				MAIN_RESOURCES_PATH, "ZombieDown", delim);
		BufferedImage[] zombieUpImage = getBufferedImageArray(
				MAIN_RESOURCES_PATH, "ZombieUp", delim);
		BufferedImage[] zombieLeftImage = getBufferedImageArray(
				MAIN_RESOURCES_PATH, "ZombieLeft", delim);
		BufferedImage[] zombieRightImage = getBufferedImageArray(
				MAIN_RESOURCES_PATH, "ZombieRight", delim);

		BufferedImage[] zombieAttackUpImage = getBufferedImageArray(
				MAIN_RESOURCES_PATH, "ZombieAttackUp", delim);
		BufferedImage[] zombieAttackDownImage = getBufferedImageArray(
				MAIN_RESOURCES_PATH, "ZombieAttackDown", delim);
		BufferedImage[] zombieAttackLeftImage = getBufferedImageArray(
				MAIN_RESOURCES_PATH, "ZombieAttackLeft", delim);
		BufferedImage[] zombieAttackRightImage = getBufferedImageArray(
				MAIN_RESOURCES_PATH, "ZombieAttackRight", delim);
		BufferedImage[] zombieDeath = getBufferedImageArray(
				MAIN_RESOURCES_PATH, "ZombieDeath", delim);

		int zombieHealth = 25;
		int zombieDamage = 5;

		Zombie newZombie = new Zombie("New", "Moving",
				getInitializedAnimatedSprite(zombieDownImage),
				getInitializedAnimatedSprite(zombieUpImage),
				getInitializedAnimatedSprite(zombieLeftImage),
				getInitializedAnimatedSprite(zombieRightImage), player, this,
				zombieHealth, zombieDamage);

		newZombie.mapNameToSprite("AttackLeft",
				getInitializedAnimatedSprite(zombieAttackLeftImage));
		newZombie.mapNameToSprite("AttackRight",
				getInitializedAnimatedSprite(zombieAttackRightImage));
		newZombie.mapNameToSprite("AttackUp",
				getInitializedAnimatedSprite(zombieAttackUpImage));
		newZombie.mapNameToSprite("AttackDown",
				getInitializedAnimatedSprite(zombieAttackDownImage));

		newZombie.mapNameToSprite("ZombieDeath",
				getInitializedAnimatedSprite(zombieDeath, 500, false));

		newZombie.setX(Math.random() * GAME_WIDTH);
		newZombie.setY(Math.random() * GAME_HEIGHT);

		Zombie.updateZombieCount();

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
	public void addBullet(Bullet bullet, double angle) {
		bullet.getCurrentSprite().setImage(
				ImageUtil.rotate(bullet.getImage(), (int) angle));
		bullet.setActive(true);
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

		BufferedImage bulletImage = getImage("resources/bullet.png");
		BufferedImage shotGunImage = getImage("resources/shotgun.png");
		BufferedImage assaultRifleImage = getImage("resources/assaultRifle.png");
		BufferedImage healthImage = getImage("resources/Health.png");

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
		SpriteGroup items = playField.getGroup("Item");
		items.add(item);
	}

	/**
	 * Initialize a set of images to be animated.
	 * 
	 * @param images
	 *            a set of images to be made into an animated sprite
	 * @return initialized animated sprite
	 */
	private AnimatedSprite getInitializedAnimatedSprite(BufferedImage[] images) {
		AnimatedSprite sprite = new AnimatedSprite(images);
		initializeAnimatedSprite(sprite, defaultAnimationDelay, true);
		return sprite;
	}

	/**
	 * 
	 * Initialize a set of images to be animated.
	 * 
	 * @param images
	 *            a set of images to be made into an animated sprite
	 * @param delay
	 *            animation delay
	 * @param loop
	 *            loop the animation
	 * @return initialized animated sprite
	 */
	private AnimatedSprite getInitializedAnimatedSprite(BufferedImage[] images,
			long delay, boolean loop) {
		AnimatedSprite sprite = new AnimatedSprite(images);
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
