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
	private static final int GAME_WIDTH = 700;
	private static final int GAME_HEIGHT = 500;
	private static long defaultAnimationDelay = 300;
	private static final String MAIN_RESOURCES_PATH = "vooga.games.zombieland.MainResources";

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

	private BufferedImage[] playerUpImage;
	private BufferedImage[] playerDefaultImage;
	private BufferedImage[] playerLeftImage;
	private BufferedImage[] playerRightImage;

	private BufferedImage[] zombieUpImage;
	private BufferedImage[] zombieDownImage;
	private BufferedImage[] zombieLeftImage;
	private BufferedImage[] zombieRightImage;

	private BufferedImage[] zombieAttackUpImage;
	private BufferedImage[] zombieAttackDownImage;
	private BufferedImage[] zombieAttackLeftImage;
	private BufferedImage[] zombieAttackRightImage;

	private BufferedImage[] zombieDeathImage;

	private BufferedImage bulletImage;
	private BufferedImage shotGunImage;
	private BufferedImage assaultRifleImage;
	private BufferedImage healthImage;

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

		bundle = ResourceBundle.getBundle(MAIN_RESOURCES_PATH);
		delim = bundle.getString("delim");

		initImages();
		initPlayer();
		initOverlays();
		initEnvironment();

		setListeners();
		initCollisionManagers();
		initZombies();

	}

	private void initZombies() {
		zombiesAppeared = 0;
		zombieHealth = parseInt("startZombieHealth");
		zombieDamage = parseInt("startZombieDamage");
	}

	private void initCollisionManagers() {
		playerZombieManager = new PZCollisionManager();
		players.add(player);
		playField.addCollisionGroup(players, zombies, playerZombieManager);

		entityWallManager = new WallBoundManager(background);
		playField.addCollisionGroup(players, players, entityWallManager);

		bulletZombieManager = new BZCollisionManager();
		playField.addCollisionGroup(bullets, zombies, bulletZombieManager);

		humanItemManager = new HICollisionManager();
		playField.addCollisionGroup(players, items, humanItemManager);
	}

	private void initEnvironment() {
		String sandbackgroundpath = bundle.getString("sandbg");
		BufferedImage sandbg = getImage(sandbackgroundpath);
		background = new ImageBackground(sandbg, GAME_WIDTH, GAME_HEIGHT);


		zombies = new SpriteGroup("Zombies");
		bullets = new SpriteGroup("Bullets");
		items = new SpriteGroup("Items");
		players = new SpriteGroup("Players");
		playField = new PlayField();

		int delay = parseInt("timer");
		timer = new Timer(delay);

		playField.add(player);
		playField.addGroup(zombies);
		playField.addGroup(bullets);
		playField.addGroup(items);
		playField.add(overlayLevelString);
		playField.setBackground(background);

		level = parseInt("startLevel");
	}

	public void initOverlays() {
		initOverlayHealthString(bundle);
		initOverlayHealthBar(bundle);
		initOverlayScoreString(bundle);
		initOverlayAmmoString(bundle);
		initOverlayLevelString(bundle);
	}

	private void initOverlayLevelString(ResourceBundle bundle) {

		int overlayLevelStringX = parseInt("overlayLevelStringX");
		int overlayLevelStringY = parseInt("overlayLevelStringY");

		statLevel = new Stat<Integer>(level);
		overlayLevelString = new OverlayStat("Level ", statLevel);
		overlayLevelString
		.setLocation(overlayLevelStringX, overlayLevelStringY);
		overlayLevelString.setActive(false);
	}

	private void initOverlayAmmoString(ResourceBundle bundle) {

		int overlayAmmoStringX = parseInt("overlayAmmoStringX");
		int overlayAmmoStringY = parseInt("overlayAmmoStringY");
		String overlayAmmoStringMessage = bundle
		.getString("overlayAmmoStringMessage");

		overlayAmmoString = new OverlayStat(overlayAmmoStringMessage,
				player.getStatAmmo());
		overlayAmmoString.setColor(Color.BLUE);
		overlayAmmoString.setLocation(overlayAmmoStringX, overlayAmmoStringY);
	}

	private void initOverlayScoreString(ResourceBundle bundle) {
		int overlayScoreStringX = parseInt("overlayScoreStringX");
		int overlayScoreStringY = parseInt("overlayScoreStringY");
		String overlayAmmoStringMessage = bundle
		.getString("overlayScoreStringMessage");

		overlayScoreString = new OverlayStat(overlayAmmoStringMessage,
				player.getScore());
		overlayScoreString
		.setLocation(overlayScoreStringX, overlayScoreStringY);
	}

	private void initOverlayHealthBar(ResourceBundle bundle) {

		int overlayHealthBarX = parseInt("overlayHealthBarX");
		int overlayHealthBarY = parseInt("overlayHealthBarY");

		overlayHealthBar = new OverlayBar(player.getHealth(), player
				.getHealth().getStat());
		overlayHealthBar.setColor(Color.GREEN);
		overlayHealthBar.setLocation(overlayHealthBarX, overlayHealthBarY);
	}

	private void initOverlayHealthString(ResourceBundle bundle) {
		int overlayHealthStringX = parseInt("overlayHealthStringX");
		int overlayHeatlhStringY = parseInt("overlayHealthStringY");

		overlayHealthString = new OverlayString("Health: ", Color.BLUE);
		overlayHealthString.setLocation(overlayHealthStringX,
				overlayHeatlhStringY);
	}

	private void initPlayer() {
		double playerDefaultX = parseDouble("playerDefaultX");
		double playerDefaultY = parseDouble("playerDefaultY");
		int maxHealth = parseInt("maxHealth");
		playerDefaultImage = getBufferedImageArray(MAIN_RESOURCES_PATH, "Down", delim);
		shooterImage = new AnimatedSprite(playerDefaultImage, playerDefaultX, playerDefaultY);
		player = new Shooter("Hero", "Down", shooterImage, maxHealth, 0, this);

		String[] list = { "Down", "Up", "Left", "Right" };
		for (int i = 0; i < list.length; i++) {
			AnimatedSprite animation = getInitializedAnimatedSprite(
					MAIN_RESOURCES_PATH, list[i], delim);
			player.mapNameToSprite(list[i], animation);
		}
	}


	private void initImages() {
		// Zombie animations
		zombieDownImage = getBufferedImageArray(MAIN_RESOURCES_PATH, "ZombieDown", delim);
		zombieUpImage = getBufferedImageArray(MAIN_RESOURCES_PATH, "ZombieUp", delim);
		zombieLeftImage = getBufferedImageArray(MAIN_RESOURCES_PATH, "ZombieLeft", delim);
		zombieRightImage = getBufferedImageArray(MAIN_RESOURCES_PATH, "ZombieRight", delim);
		zombieAttackUpImage = getBufferedImageArray(MAIN_RESOURCES_PATH, "ZombieAttackUp", delim);
		zombieAttackDownImage = getBufferedImageArray(MAIN_RESOURCES_PATH, "ZombieAttackDown", delim);
		zombieAttackLeftImage = getBufferedImageArray(MAIN_RESOURCES_PATH, "ZombieAttackLeft", delim);
		zombieAttackRightImage = getBufferedImageArray(MAIN_RESOURCES_PATH, "ZombieAttackRight", delim);
		zombieDeathImage = getBufferedImageArray(MAIN_RESOURCES_PATH, "ZombieDeath", delim);
		bulletImage = getImage(bundle.getString("bulletImage"));
		shotGunImage = getImage(bundle.getString("shotGunImage"));
		assaultRifleImage = getImage(bundle.getString("assaultRifleImage"));
		healthImage = getImage(bundle.getString("healthImage"));
	}

	public double parseDouble(String keyName) {
		String string = bundle.getString(keyName);
		return Double.parseDouble(string);
	}
	public int parseInt(String keyName) {
		String string = bundle.getString(keyName);
		return Integer.parseInt(string);
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
		zombies.update(elapsedTime);
		bullets.update(elapsedTime);
		items.update(elapsedTime);
		if (moreZombieCanBeAdded()){
			if (timer.action(elapsedTime)) {
				addZombie();
			}
		}
		else if (player.getLevelScore() == zombiesAppeared){
			statLevel.setStat(level+1);
			overlayLevelString.update(elapsedTime);
			overlayLevelString.setActive(true);
			playField.update(elapsedTime);
			if (timer.action(elapsedTime)){
				timer.setDelay((long) (2000/level*1.5));
				updateZombieStats();
				zombiesAppeared = 0;
				level++;
				player.resetLevelScore();
				overlayLevelString.setActive(false);
			}
		}
	}

	private void updateZombieStats() {
		zombieHealth = (int) (zombieHealth*level/1.5);
		zombieDamage = (int) (zombieDamage+level/1.5);
	}

	private boolean moreZombieCanBeAdded() {
		return zombiesAppeared < ZOMBIES_PER_LEVEL*level*0.5;
	}

	/**
	 * Add a zombie to the world. The position is randomly picked. The health and damage
	 * will increase every level.
	 */
	public void addZombie() {

		Zombie newZombie = new Zombie("New", "Moving",
				getInitializedAnimatedSprite(zombieDownImage),
				getInitializedAnimatedSprite(zombieUpImage),
				getInitializedAnimatedSprite(zombieLeftImage),
				getInitializedAnimatedSprite(zombieRightImage),
				getInitializedAnimatedSprite(zombieAttackDownImage),
				getInitializedAnimatedSprite(zombieAttackUpImage),
				getInitializedAnimatedSprite(zombieAttackLeftImage),
				getInitializedAnimatedSprite(zombieAttackRightImage),
				getInitializedAnimatedSprite(zombieDeathImage),zombieHealth, zombieDamage,player, this);

		newZombie.setX(Math.random() * GAME_WIDTH);
		newZombie.setY(Math.random() * GAME_HEIGHT);
		zombiesAppeared++;
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

		if (overlayLevelString.isActive()){
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