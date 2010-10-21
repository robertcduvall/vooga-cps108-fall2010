package vooga.games.zombieland;

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

import vooga.engine.overlay.Overlay;
import vooga.engine.overlay.OverlayBar;
import vooga.engine.overlay.OverlayStat;
import vooga.engine.overlay.OverlayString;
import vooga.engine.overlay.Stat;
import vooga.engine.player.control.KeyboardControl;
import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;
import vooga.games.zombieland.collision.BZCollisionManager;
import vooga.games.zombieland.collision.HICollisionManager;
import vooga.games.zombieland.collision.PZCollisionManager;
import vooga.games.zombieland.collision.WallBoundManager;
import vooga.games.zombieland.items.HealthItem;
import vooga.games.zombieland.items.Item;
import vooga.games.zombieland.items.WeaponItem;
import vooga.games.zombieland.weapons.Bullet;

public class ZombielandPlayState extends GameState {

	private static final String PLAYER_CLASS = "vooga.games.zombieland.Shooter";
	private static final int ZOMBIES_PER_LEVEL = 25;
	private static int GAME_WIDTH;
	private static int GAME_HEIGHT;
	private static DropThis currentGame;

	private Shooter player;
	private PlayField playField;
	private Timer timer;
	private KeyboardControl control;

	private OverlayBar overlayHealthBar;
	private OverlayString overlayHealthString;
	private OverlayStat overlayScoreString;
	private OverlayString overlayGameOverString;
	private OverlayString overlayPauseString;
	private OverlayStat overlayAmmoString;
	private OverlayStat overlayLevelString;

	private GameStateManager stateManager;
	private GameState zombielandPlayState;
	private GameState zombielandPauseState;

	private Stat<Integer> statLevel;
	private int level;
	private int zombiesAppeared;

	public ZombielandPlayState(DropThis game, int screen_width, int screen_height) {
		super();
		GAME_WIDTH = screen_width;
		GAME_HEIGHT = screen_height;
		currentGame = game;

		zombielandPlayState = new GameState();
		zombielandPauseState = new GameState();

		//where do we properly put initialize()?
		initialize();
	}

	@Override
	public void initialize() {
		player = new Shooter("Hero", "Down", currentGame);
		initEnvironment();
		initOverlays();

		setListeners();
		stateManager.activateOnly(zombielandPlayState);

	}

	/**
	 * This method initializes the zombies, bullets, players, overlays
	 * SpriteGroup, set them up with their respective collision managers, and
	 * associate these managers with playField.
	 */
	private void initEnvironment() {
		playField = new PlayField();

		BufferedImage sandbg = ZombielandResources.getImage("sandbg");
		
		ImageBackground background = new ImageBackground(sandbg, GAME_WIDTH, GAME_HEIGHT);

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

		int delay = ZombielandResources.getInt("timer");
		timer = new Timer(delay);

		stateManager = new GameStateManager();

		stateManager.addGameState(zombielandPlayState);
		stateManager.addGameState(zombielandPauseState);

		zombielandPlayState.addGroup(players);
		zombielandPlayState.addGroup(zombies);
		zombielandPlayState.addGroup(bullets);
		zombielandPlayState.addGroup(items);

		playField.setBackground(background);

		level = ZombielandResources.getInt("startLevel");
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
		int x = ZombielandResources.getInt(item + "X");
		int y = ZombielandResources.getInt(item + "Y");
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
		
		String pauseString = ZombielandResources.getString("overlayPauseString");
		
		overlayPauseString = new OverlayString(pauseString, Color.BLACK);
		initializeOverlayItem(overlayPauseString, "overlayPauseString", false);
	}

	/**
	 * This method is responsible for displaying the level string while the game
	 * is playing.
	 */
	private void initOverlayLevelString() {
		
		String levelString = ZombielandResources.getString("overlayLevelString");
		statLevel = new Stat<Integer>(level);
		overlayLevelString = new OverlayStat(levelString, statLevel);
		initializeOverlayItem(overlayLevelString, "overlayLevelString", false);
	}

	/**
	 * This method is responsible for initializing the Ammo string that is
	 * displayed during game play.
	 */
	private void initOverlayAmmoString() {
		String overlayName = "overlayAmmoString";
		String ammoString = ZombielandResources.getString(overlayName);
		overlayAmmoString = new OverlayStat(ammoString,
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
		String scoreString = ZombielandResources.getString(overlayName);
		overlayScoreString = new OverlayStat(scoreString,
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
		String healthString = ZombielandResources.getString("overlayHealthString");	
		overlayHealthString = new OverlayString(healthString, Color.BLUE);
		initializeOverlayItem(overlayHealthString, overlayName, true);
	}


	/**
	 * This method is responsible for initializing the pictures associated with
	 * bulletImage, shotGunImage, assaultRifleImage, and healthImage
	 */
	//private void initImages() {

	//	bulletImage = ZombielandResources.getImage("bulletImage");
	//	shotGunImage = ZombielandResources.getImage("shotGunImage");
	//	assaultRifleImage = ZombielandResources.getImage("assaultRifleImage");
	//	healthImage = ZombielandResources.getImage("healthImage");
	//}

	/**
	 * update all components of the ZombieLand game. This method checks to see
	 * if more zombies can be added or if the level has been completed.
	 */

	@Override
	public void update(long elapsedTime) {
		if (currentGame.bsInput.getKeyPressed() == KeyEvent.VK_P) {
			stateManager.toggle(zombielandPauseState);
			stateManager.toggle(zombielandPlayState);
			overlayPauseString.setActive(!overlayPauseString.isActive());
		}

		stateManager.update(elapsedTime);

		if (zombielandPlayState.isActive()) {
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
//					updateZombieStats();
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

		double zombieLimitingFactor = ZombielandResources.getDouble("zombieLimitingFactor");

		return zombiesAppeared < ZOMBIES_PER_LEVEL * level
		* zombieLimitingFactor;
	}

	/**
	 * Add a zombie to the world. The position is randomly picked. The health
	 * and damage will increase every level.
	 */
	public void addZombie() {

		Zombie newZombie = new Zombie("New", "Moving", level, player, currentGame);
		
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

		int weapon1 = ZombielandResources.getInt("weapon1");
		int weapon2 = ZombielandResources.getInt("weapon2");
		int healthKit = ZombielandResources.getInt("healthKit");

		Item item;
		switch (choice) {
		case 0:
			BufferedImage assaultRifleImage = ZombielandResources.getImage("assaultRifleImage");
			item = new WeaponItem(player, new Sprite(assaultRifleImage),
					weapon1, x, y);
			break;
		case 1:
			BufferedImage shotGunImage = ZombielandResources.getImage("shotGunImage");
			item = new WeaponItem(player, new Sprite(shotGunImage), weapon2, x,
					y);
			break;
		case 2:
			BufferedImage healthImage = ZombielandResources.getImage("healthImage");
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

		control = new KeyboardControl(player, currentGame);
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
	@Override
	public void render(Graphics2D g) {
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
		overlayGameOverString = new OverlayString("GAME OVER\nFinal Score: "
				+ player.getScore(), Color.BLACK);
		currentGame.stop();
	}

}
