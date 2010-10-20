package vooga.games.zombieland;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import vooga.engine.core.Game;
import vooga.engine.overlay.*;
import vooga.engine.player.control.KeyboardControl;
import vooga.engine.state.*;

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

public class DropThis extends Game implements IZombielandConstants {

	private static final int GAME_WIDTH = 700;
	private static final int GAME_HEIGHT = 500;
	private static long defaultAnimationDelay = 300;
	private static final int ZOMBIES_PER_LEVEL = 25;

	private AnimatedSprite shooterImage;
	private ImageBackground background;
	private Shooter player;
	private PlayField playField;
	private Timer timer;
	private KeyboardControl control;

	/**
	 * We choose to jave BufferedImage[] files as private variables because we
	 * have a method called addZombie(). If we try to get the images every time
	 * from the resource bundle (i.e. making these variables local), we run into
	 * the problem of clogging the performance of the game.
	 */
	private BufferedImage[] playerDefaultImage;

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

	private OverlayBar overlayHealthBar;
	private OverlayString overlayHealthString;
	private OverlayStat overlayScoreString;
	private OverlayString overlayGameOverString;
	private OverlayString overlayPauseString;
	private OverlayStat overlayAmmoString;
	private OverlayStat overlayLevelString;

	private GameStateManager stateManager;
	private GameState play;
	private GameState pause;

	private Stat<Integer> statLevel;
	private int level;
	private int zombiesAppeared;
	private int zombieHealth;
	private int zombieDamage;

	/**
	 * This method initializes all the resources in the game: set up the Image
	 * files, initializes collections and establishes stats for the game.
	 */
	public void initResources() {

		initImages();
		initPlayer();
		initEnvironment();
		initOverlays();

		setListeners();
		initZombies();
		stateManager.activateOnly(play);

	}

	/**
	 * This method initializes zombie health, zombie damage and starting number
	 * zombies.
	 */
	private void initZombies() {
		resetZombiesCount();
		zombieHealth = parseInt("startZombieHealth");
		zombieDamage = parseInt("startZombieDamage");
	}

	/**
	 * This method initializes the zombies, bullets, players, overlays
	 * SpriteGroup, set them up with their respective collision managers, and
	 * associate these managers with playField.
	 */
	private void initEnvironment() {
		playField = new PlayField();

		String sandbackgroundpath = bundle.getString("sandbg");
		BufferedImage sandbg = getImage(sandbackgroundpath);
		background = new ImageBackground(sandbg, GAME_WIDTH, GAME_HEIGHT);

		SpriteGroup zombies = new SpriteGroup("Zombies");
		SpriteGroup bullets = new SpriteGroup("Bullets");
		SpriteGroup items = new SpriteGroup("Items");
		SpriteGroup players = new SpriteGroup("Players");
		SpriteGroup overlays = new SpriteGroup("Overlays");

		playField.addGroup(zombies);
		playField.addGroup(bullets);
		playField.addGroup(items);
		playField.addGroup(players);
		playField.addGroup(overlays);

		PZCollisionManager playerZombieManager = new PZCollisionManager();
		players.add(player);

		playField.addCollisionGroup(players, zombies, playerZombieManager);

		WallBoundManager entityWallManager = new WallBoundManager(background);
		playField.addCollisionGroup(players, players, entityWallManager);

		BZCollisionManager bulletZombieManager = new BZCollisionManager();
		playField.addCollisionGroup(bullets, zombies, bulletZombieManager);

		HICollisionManager humanItemManager = new HICollisionManager();
		playField.addCollisionGroup(players, items, humanItemManager);

		int delay = parseInt("timer");
		timer = new Timer(delay);

		stateManager = new GameStateManager();
		play = new ZombieGameStates();
		pause = new ZombieGameStates();

		stateManager.addGameState(play);
		stateManager.addGameState(pause);

		play.addGroup(players);
		play.addGroup(zombies);
		play.addGroup(bullets);
		play.addGroup(items);

		playField.setBackground(background);

		level = parseInt("startLevel");
	}

	/**
	 * This method initializes the overlay system: the overlayHealthString, the
	 * OverlayHealthBar, the OverlayScoreString, the overlayAmmoString, the
	 * OverlayLevelString, overllayPauseString.
	 */
	public void initOverlays() {
		initOverlayHealthString();
		initOverlayHealthBar();
		initOverlayScoreString();
		initOverlayAmmoString();
		initOverlayLevelString();
		initOverlayPauseString();
	}

	/**
	 * This initializes each overly item, setting their location, state, and
	 * adding it to the playfield
	 * 
	 * @param overlay
	 *            overlay item
	 * @param item
	 *            overlay item name
	 * @param active
	 *            overlay item state
	 */
	private void initializeOverlayItem(Overlay overlay, String item,
			boolean active) {
		int x = parseInt(item + "X");
		int y = parseInt(item + "Y");
		overlay.setLocation(x, y);
		overlay.setActive(active);
		SpriteGroup overlays = playField.getGroup("Overlays");
		overlays.add(overlay);
	}

	/**
	 * This method initializes the PauseString, which is displayed when the game
	 * is paused.
	 */
	private void initOverlayPauseString() {
		overlayPauseString = new OverlayString("PAUSE", Color.BLACK);
		initializeOverlayItem(overlayPauseString, "overlayPauseString", false);
	}

	/**
	 * This method is responsible for displaying the level string while the game
	 * is playing.
	 */
	private void initOverlayLevelString() {
		statLevel = new Stat<Integer>(level);
		overlayLevelString = new OverlayStat("Level ", statLevel);
		initializeOverlayItem(overlayLevelString, "overlayLevelString", false);
	}

	/**
	 * This method is responsible for initializing the Ammo string that is
	 * displayed during game play.
	 */
	private void initOverlayAmmoString() {
		String overlayName = "overlayAmmoString";
		String overlayAmmoStringMessage = bundle.getString(overlayName
				+ "Message");
		overlayAmmoString = new OverlayStat(overlayAmmoStringMessage,
				player.getStatAmmo());
		overlayAmmoString.setColor(Color.BLUE);

		initializeOverlayItem(overlayAmmoString, overlayName, true);
	}

	/**
	 * This method is responsible for initializing the Score String as an
	 * increasing number during the game play.
	 */
	private void initOverlayScoreString() {
		String overlayName = "overlayScoreString";
		String overlayAmmoStringMessage = bundle.getString(overlayName
				+ "Message");
		overlayScoreString = new OverlayStat(overlayAmmoStringMessage,
				player.getScore());
		initializeOverlayItem(overlayScoreString, overlayName, true);
	}

	/**
	 * This method is responsible for initializing the health bar that is
	 * displayed during game play.
	 */
	private void initOverlayHealthBar() {
		overlayHealthBar = new OverlayBar(player.getHealth(), player
				.getHealth().getStat());
		overlayHealthBar.setColor(Color.GREEN);
		initializeOverlayItem(overlayHealthBar, "overlayHealthBar", true);
	}

	/**
	 * This method is responsible for initializing the message "Health: " on the
	 * screen during game play
	 */
	private void initOverlayHealthString() {
		String overlayName = "overlayHealthString";
		String overlayHealthStringMessage = bundle.getString(overlayName
				+ "Message");
		overlayHealthString = new OverlayString(overlayHealthStringMessage,
				Color.BLUE);
		initializeOverlayItem(overlayHealthString, overlayName, true);
	}

	/**
	 * This method is responsible for initializing the player and all of its
	 * animated images.
	 */
	private void initPlayer() {
		double playerDefaultX = parseDouble("playerDefaultX");
		double playerDefaultY = parseDouble("playerDefaultY");

		int maxHealth = parseInt("maxHealth");

		playerDefaultImage = getBufferedImageArray("Down");
		shooterImage = new AnimatedSprite(playerDefaultImage, playerDefaultX,
				playerDefaultY);
		player = new Shooter("Hero", "Down", shooterImage, maxHealth, 0, this);

		String[] list = { "Down", "Up", "Left", "Right" };
		for (int i = 0; i < list.length; i++) {
			AnimatedSprite animation = getInitializedAnimatedSprite(list[i]);
			player.mapNameToSprite(list[i], animation);
		}
	}

	/**
	 * This method is responsible for initializing the pictures associated with
	 * bulletImage, shotGunImage, assaultRifleImage, and healthImage
	 */
	private void initImages() {
		// Zombie animations
		initZombie();
		bulletImage = getImage(bundle.getString("bulletImage"));
		shotGunImage = getImage(bundle.getString("shotGunImage"));
		assaultRifleImage = getImage(bundle.getString("assaultRifleImage"));
		healthImage = getImage(bundle.getString("healthImage"));
	}

	/**
	 * This method initializes all of the animated sprite images for a zombie.
	 */
	private void initZombie() {
		zombieDownImage = getBufferedImageArray("ZombieDown");
		zombieUpImage = getBufferedImageArray("ZombieUp");
		zombieLeftImage = getBufferedImageArray("ZombieLeft");
		zombieRightImage = getBufferedImageArray("ZombieRight");
		zombieAttackUpImage = getBufferedImageArray("ZombieAttackUp");
		zombieAttackDownImage = getBufferedImageArray("ZombieAttackDown");
		zombieAttackLeftImage = getBufferedImageArray("ZombieAttackLeft");
		zombieAttackRightImage = getBufferedImageArray("ZombieAttackRight");
		zombieDeathImage = getBufferedImageArray("ZombieDeath");
	}

	/**
	 * This method allows the user to parse the value associated with the
	 * keyName to a double. Specifically, this method does this by drawing the
	 * value associated with the keyName from the global ResourceBundle bundle.
	 * 
	 * @param keyName
	 * @return
	 */
	public double parseDouble(String keyName) {
		String string = bundle.getString(keyName);
		return Double.parseDouble(string);
	}

	/**
	 * This method allows the user to parse the value associated with the
	 * keyName to an integer. Specifically, this method does this by drawing the
	 * value associated with the keyName from the global ResourceBundle bundle.
	 * 
	 * @param keyName
	 * @return
	 */
	public int parseInt(String keyName) {
		String string = bundle.getString(keyName);
		return Integer.parseInt(string);
	}

	/**
	 * This method allows the user to read in the String associated with a key
	 * value and then split the value base on the regular expression delim to
	 * get different frames of the same animation associated with one animated
	 * sprite.
	 * 
	 * @param bundleURL
	 * @param key
	 * @param delim
	 * @return
	 */
	private BufferedImage[] getBufferedImageArray(String key) {
		String value = bundle.getString(key);
		String[] list = value.split(DELIM);
		BufferedImage[] images = new BufferedImage[list.length];

		for (int i = 0; i < list.length; i++) {
			String imageLocation = bundle.getString(list[i]);
			images[i] = getImage(imageLocation);
		}
		return images;
	}

	/**
	 * Initialize a set of images to be animated.
	 * 
	 * @param key
	 *            names of the images in the resourceBundle
	 * @return initialized animated sprite
	 */
	private AnimatedSprite getInitializedAnimatedSprite(String key) {
		return getInitializedAnimatedSprite(getBufferedImageArray(key));
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
	 * update all components of the ZombieLand game. This method checks to see
	 * if more zombies can be added or if the level has been completed.
	 */
	public void update(long elapsedTime) {
		if (bsInput.getKeyPressed() == KeyEvent.VK_P) {
			stateManager.toggle(pause);
			stateManager.toggle(play);
			overlayPauseString.setActive(!overlayPauseString.isActive());
		}

		stateManager.update(elapsedTime);

		if (play.isActive()) {
			playField.update(elapsedTime);
			control.update();
			if (moreZombieCanBeAdded()) {
				if (timer.action(elapsedTime)) {
					addZombie();
				}
			} else if (levelCompleted()) {
				statLevel.setStat(level + 1);
				overlayLevelString.update(elapsedTime);
				overlayLevelString.setActive(true);
				playField.update(elapsedTime);

				if (timer.action(elapsedTime)) {

					setNewDelay();
					updateZombieStats();
					resetZombiesCount();

					level++;

					player.resetLevelScore();
					overlayLevelString.setActive(false);
				}
			}
		}
	}

	private boolean levelCompleted() {
		return player.getLevelScore() == zombiesAppeared;
	}

	/**
	 * This method sets the new Delay Time
	 */
	private void setNewDelay() {

		int timeInterval = parseInt("timeInterval");
		double delayFactor = parseDouble("delayFactor");

		timer.setDelay((long) (timeInterval / level * delayFactor));
	}

	/**
	 * Make the zombieCount start back from zero.
	 */
	private void resetZombiesCount() {
		zombiesAppeared = 0;
	}

	/**
	 * Increase the zombie's health and damage.
	 */
	private void updateZombieStats() {

		double zombieStatMultiplier = parseDouble("zombieStatMultiplier");

		zombieHealth = (int) (zombieHealth * level / zombieStatMultiplier);
		zombieDamage = (int) (zombieDamage + level / zombieStatMultiplier);
	}

	private boolean moreZombieCanBeAdded() {

		double zombieLimitingFactor = parseDouble("zombieLimitingFactor");

		return zombiesAppeared < ZOMBIES_PER_LEVEL * level
				* zombieLimitingFactor;
	}

	/**
	 * Add a zombie to the world. The position is randomly picked. The health
	 * and damage will increase every level.
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
				getInitializedAnimatedSprite(zombieDeathImage), zombieHealth,
				zombieDamage, player, this);

		newZombie.setX(Math.random() * GAME_WIDTH);
		newZombie.setY(Math.random() * GAME_HEIGHT);
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
	public void addBullet(Bullet bullet, double angle) {
		bullet.getCurrentSprite().setImage(
				ImageUtil.rotate(bulletImage, (int) angle));
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

		Random random = new Random();
		int choice = random.nextInt(3);

		int weapon1 = parseInt("weapon1");
		int weapon2 = parseInt("weapon2");
		int healthKit = parseInt("healthKit");

		Item item;
		switch (choice) {
		case 0:
			item = new WeaponItem(player, new Sprite(assaultRifleImage),
					weapon1, x, y);
			break;
		case 1:
			item = new WeaponItem(player, new Sprite(shotGunImage), weapon2, x,
					y);
			break;
		case 2:
			item = new HealthItem(player, new Sprite(healthImage), healthKit,
					x, y);
			break;

		default:
			item = null;
		}
		item.setActive(true);

		SpriteGroup items = playField.getGroup("Items");
		items.add(item);
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
	 * render the graphics component in the game
	 */
	public void render(Graphics2D g) {
		background.render(g);
		playField.render(g);
		stateManager.render(g);
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

		int overlayStringX = parseInt("overlayStringX");
		int overlayStringY = parseInt("overlayStringY");

		overlayGameOverString = new OverlayString("GAME OVER", Color.BLACK);
		overlayGameOverString.setLocation(overlayStringX, overlayStringY);
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
		game.setup(new DropThis(), new Dimension(GAME_WIDTH, GAME_HEIGHT),
				false);
		game.start();
	}

}