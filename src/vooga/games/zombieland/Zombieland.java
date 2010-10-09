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

import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.background.ColorBackground;
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
	private static double defaultX = 100;
	private static double defaultY = 100;
	private static long defaultAnimationDelay = 300;

	private AnimatedSprite shooterImage;
	private ImageBackground background;
	private Shooter player;

	private SpriteGroup zombies;
	private SpriteGroup players;
	private SpriteGroup bullets;
	private SpriteGroup items;

	private PlayField playField;
	private Timer timer;
	private KeyboardControl control;

	private BufferedImage[] playerUpImage;
	private BufferedImage[] playerDownImage;
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

	private BufferedImage[] zombieDeath;

	private BufferedImage bulletImage;
	private BufferedImage shotGunImage;
	private BufferedImage assaultRifleImage;
	private BufferedImage healthImage;

	private ZZCollisionManager zombieZombieManager;
	private PZCollisionManager playerZombieManager;
	private BZCollisionManager bulletZombieManager;
	private WallBoundManager entityWallManager;
	private HICollisionManager humanItemManager;

	private OverlayBar overlayHealthBar;
	private OverlayString overlayHealthString;
	private OverlayStat overlayScoreString;
	private OverlayString overlayGameOverString;
	private OverlayStat overlayAmmoString;

	public void initResources() {
		// RESOURCES

		// Player animations
		playerDownImage = new BufferedImage[] {
				getImage("resources/Down1.png"),
				getImage("resources/Down2.png"),
				getImage("resources/Down3.png"),
				getImage("resources/Down4.png") };
		playerUpImage = new BufferedImage[] { getImage("resources/up1.png"),
				getImage("resources/up2.png"), getImage("resources/up3.png"),
				getImage("resources/up4.png") };
		playerLeftImage = new BufferedImage[] {
				getImage("resources/left1.png"),
				getImage("resources/left2.png"),
				getImage("resources/left3.png"),
				getImage("resources/left4.png") };
		playerRightImage = new BufferedImage[] {
				getImage("resources/right1.png"),
				getImage("resources/right2.png"),
				getImage("resources/right3.png"),
				getImage("resources/right4.png") };
		// Zombie animations
		zombieDownImage = new BufferedImage[] {
				getImage("resources/ZombieDown1.png"),
				getImage("resources/ZombieDown2.png"),
				getImage("resources/ZombieDown3.png") };
		zombieUpImage = new BufferedImage[] {
				getImage("resources/ZombieUp1.png"),
				getImage("resources/ZombieUp2.png"),
				getImage("resources/ZombieUp3.png") };
		zombieLeftImage = new BufferedImage[] {
				getImage("resources/ZombieLeft1.png"),
				getImage("resources/ZombieLeft2.png"),
				getImage("resources/ZombieLeft3.png") };
		zombieRightImage = new BufferedImage[] {
				getImage("resources/ZombieRight1.png"),
				getImage("resources/ZombieRight2.png"),
				getImage("resources/ZombieRight3.png") };
		zombieAttackUpImage = new BufferedImage[] {
				getImage("resources/ZombieAttackUp1.png"),
				getImage("resources/ZombieAttackUp2.png"),
				getImage("resources/ZombieAttackUp3.png") };
		zombieAttackDownImage = new BufferedImage[] {
				getImage("resources/ZombieAttackDown1.png"),
				getImage("resources/ZombieAttackDown2.png"),
				getImage("resources/ZombieAttackDown3.png") };
		zombieAttackLeftImage = new BufferedImage[] {
				getImage("resources/ZombieAttackLeft1.png"),
				getImage("resources/ZombieAttackLeft2.png"),
				getImage("resources/ZombieAttackLeft3.png") };
		zombieAttackRightImage = new BufferedImage[] {
				getImage("resources/ZombieAttackRight1.png"),
				getImage("resources/ZombieAttackRight2.png"),
				getImage("resources/ZombieAttackRight3.png") };
		zombieDeath = new BufferedImage[] {
				getImage("resources/ZombieDeath1.png"),
				getImage("resources/ZombieDeath2.png"),
				getImage("resources/ZombieDeath3.png") };

		bulletImage = getImage("resources/bullet.png");
		shotGunImage = getImage("resources/shotgun.png");
		assaultRifleImage = getImage("resources/assaultRifle.png");
		healthImage = getImage("resources/Health.png");

		// INITIALIZATIONS
		BufferedImage sandbg = getImage("resources/sandbackground.png");
		background = new ImageBackground(sandbg, GAME_WIDTH, GAME_HEIGHT);

		shooterImage = new AnimatedSprite(playerDownImage, 350, 250);
		player = new Shooter("Hero", "Down", shooterImage, 100, 0, this);
		player.mapNameToSprite("Up",
				getInitializedAnimatedSprite(playerUpImage));
		player.mapNameToSprite("Left",
				getInitializedAnimatedSprite(playerLeftImage));
		player.mapNameToSprite("Right",
				getInitializedAnimatedSprite(playerRightImage));
		player.mapNameToSprite("Down",
				getInitializedAnimatedSprite(playerDownImage));

		overlayHealthString = new OverlayString("Health: ", Color.BLUE);
		overlayHealthString.setLocation(5, 10);
		overlayHealthBar = new OverlayBar(player.getStatHealth(), 100);
		overlayHealthBar.setColor(Color.GREEN);
		overlayHealthBar.setLocation(80, 18);
		overlayScoreString = new OverlayStat("Kills: ", player.getStatScore());
		overlayScoreString.setLocation(385, 12);
		overlayAmmoString = new OverlayStat("Ammo: ", player.getStatAmmo());
		overlayAmmoString.setColor(Color.BLUE);
		overlayAmmoString.setLocation(470, 12);

		zombies = new SpriteGroup("Zombies");
		bullets = new SpriteGroup("Bullets");
		items = new SpriteGroup("Items");
		playField = new PlayField();
		control = new KeyboardControl(player, this);
		timer = new Timer(2000);

		playField.add(player);
		playField.addGroup(zombies);
		playField.addGroup(bullets);
		playField.addGroup(items);
		playField.setBackground(background);
		setListeners();

		// zombieZombieManager = new ZZCollisionManager();
		// playField.addCollisionGroup(zombies, zombies,
		// zombieZombieManager);

		playerZombieManager = new PZCollisionManager();
		players = new SpriteGroup("Players");
		players.add(player);
		playField.addCollisionGroup(players, zombies, playerZombieManager);

		entityWallManager = new WallBoundManager(background);
		playField.addCollisionGroup(players, players, entityWallManager);

		bulletZombieManager = new BZCollisionManager();
		playField.addCollisionGroup(bullets, zombies, bulletZombieManager);

		humanItemManager = new HICollisionManager();
		playField.addCollisionGroup(players, items, humanItemManager);

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

		if (timer.action(elapsedTime)) {
			addZombie();
		}

	}

	/**
	 * Add a zombie to the world. The position is randomly picked.
	 */
	public void addZombie() {
		Zombie newZombie = new Zombie("New", "Moving",
				getInitializedAnimatedSprite(zombieDownImage),
				getInitializedAnimatedSprite(zombieUpImage),
				getInitializedAnimatedSprite(zombieLeftImage),
				getInitializedAnimatedSprite(zombieRightImage), player, this);

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
