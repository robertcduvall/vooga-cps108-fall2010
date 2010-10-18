package vooga.games.cyberion.CyberionGameMain;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;

import vooga.engine.event.EventManager;
import vooga.engine.player.control.KeyboardControl;
import vooga.engine.resource.Resources;

import vooga.engine.state.GameState;
import vooga.games.cyberion.Collision.EnemyCollidesWithShot;
import vooga.games.cyberion.Collision.PlayerCollidesEnemy;
import vooga.games.cyberion.Collision.PlayerCollidesWall;
import vooga.games.cyberion.Collision.PlayerCollidesWithBonus;
import vooga.games.cyberion.Collision.PlayerCollidesWithShot;
import vooga.games.cyberion.CyberionSprite.Bonus;
import vooga.games.cyberion.CyberionSprite.EnemyShip;
import vooga.games.cyberion.CyberionSprite.EnemyShot;
import vooga.games.cyberion.CyberionSprite.PlayerShip;
import vooga.games.cyberion.CyberionSprite.PlayerShot;
import vooga.games.cyberion.CyberionSprite.StarGroup;

public class MainGameState extends GameState {

	// creates image background
	ImageBackground bg;
	// creates image variables
	private SpriteGroup star;
	private PlayerShip playerShip;
	private PlayerShot playerShot;
	private SpriteGroup playerGroup;
	private SpriteGroup enemyGroup;
	private EnemyShot enemyShot;
	private SpriteGroup bonusGroup;
	// creates necessary collision managers
	private CollisionManager playerCollidesWithEnemy;
	private CollisionManager playerCollidesWithWall;
	private CollisionManager playerCollidesWithBonus;
	private CollisionManager playerCollidesWithShot;
	private CollisionManager enemyCollidesWithShot;
	// initializes constants
	private static final int NUM_SHIPS = 1000;
	private static final int NUM_BONUS = 10;
	private static double SHIP_SPEED = 0.1;
	// creates event manager
	private EventManager eventManager;
	// creates keyboard control
	private KeyboardControl keyboardControl;
	private Resources resources;

	//
	@Override
	public void initialize() {
		initResources();
	}

	// initializes sprites from images
	private void setSprites() {
		// TODO: MAKE THIS WORK:
		// Resources.loadImage("starImage",
		// "vooga/games/cyberion/Resources/star.png");
		// Resources.loadImage("playerShot",
		// "/vooga/src/vooga/games/cyberion/Resources/playerShot.png");
		// Resources.loadImage("enemyShot",
		// "/vooga/src/vooga/games/cyberion/Resources/enemyShot.png");
		// Resources.loadImage("playerShip",
		// "/vooga/src/vooga/games/cyberion/Resources/playerShip.png");
		// Resources.loadImage("enemyShip",
		// "/vooga/src/vooga/games/cyberion/Resources/enemyShip.png");
		// Resources
		// .loadImage("bonus",
		// "/vooga/src/vooga/games/cyberion/Resources/bonus.png");
		// Resources.loadImage("bg",
		// "/vooga/src/vooga/games/cyberion/Resources/bg.png");

		BufferedImage playerShotImage = Resources.getImage("playerShot");
		BufferedImage enemyShotImage = Resources.getImage("enemyShot");
		BufferedImage playerImage = Resources.getImage("playerShip");
		BufferedImage enemyImage = Resources.getImage("enemyShip");
		BufferedImage bonusImage = Resources.getImage("bonus");
		BufferedImage starImage = Resources.getImage("starImage");

		star = new StarGroup("StarGroup", starImage);
		playerGroup = new SpriteGroup("PlayerGroup");
		playerShip = new PlayerShip("playerShip", "normal", new Sprite(
				playerImage));

		playerShip.setEventManager(eventManager);
		keyboardControl = new KeyboardControl();
		keyboardControl = playerShip.setKeyboardControl(keyboardControl);
		playerGroup.add(playerShip);

		enemyGroup = new SpriteGroup("EnemyGroup");
		// randomly generates enemy ships
		for (int i = 0; i < NUM_SHIPS; i++) {
			Random random = new Random();
			Sprite newShip = new EnemyShip(enemyImage, random.nextInt(640),
					-random.nextInt(100000), 1, eventManager);
			newShip.setVerticalSpeed(SHIP_SPEED);
			enemyGroup.add(newShip);
		}

		bonusGroup = new SpriteGroup("BonusGroup");
		// randomly generates bonuses
		for (int i = 0; i < NUM_BONUS; i++) {
			Random random = new Random();
			Bonus newBonus = new Bonus(bonusImage, random.nextInt(640),
					-random.nextInt(10000));
			newBonus.setVerticalSpeed(SHIP_SPEED);
			bonusGroup.add(newBonus);
		}

		playerShot = new PlayerShot("PlayerShot", 0, 0, 480, 640,
				playerShotImage);
		enemyShot = new EnemyShot("EnemyShot", enemyShotImage);

		this.addGroup(star);
		this.addGroup(playerGroup);
		this.addGroup(enemyGroup);
		this.addGroup(bonusGroup);
		this.addGroup(enemyShot);
		this.addGroup(playerShot);

	}

	@Override
	public void update(long elapsedTime) {
//		star.update(elapsedTime);
//		playerGroup.update(elapsedTime);
//		playerShot.update(elapsedTime);
//		enemyGroup.update(elapsedTime);
//		enemyShot.update(elapsedTime);
//		bonusGroup.update(elapsedTime);
		keyboardControl.update();

		playerCollidesWithWall.checkCollision();
		playerCollidesWithEnemy.checkCollision();
		playerCollidesWithShot.checkCollision();
		playerCollidesWithBonus.checkCollision();
		enemyCollidesWithShot.checkCollision();
	}
	
	public void render(Graphics2D g){
//		 bg.render(g);
//		 star.render(g);
//		 playerGroup.render(g);
//		 playerShot.render(g);
//		 enemyGroup.render(g);
//		 enemyShot.render(g);
//		 bonusGroup.render(g);
	}

	// initializes collision managers
	private void setCollisionDetection() {

		// enemyCollidesWithShot = new EnemyCollidesWithShot(bsSound);
		// enemyCollidesWithShot.setCollisionGroup(playerShot, enemyGroup);
		playerCollidesWithEnemy = new PlayerCollidesEnemy();
		playerCollidesWithEnemy.setCollisionGroup(playerGroup, enemyGroup);
		playerCollidesWithWall = new PlayerCollidesWall(bg);
		playerCollidesWithWall.setCollisionGroup(playerGroup, playerGroup);
		playerCollidesWithShot = new PlayerCollidesWithShot();
		playerCollidesWithShot.setCollisionGroup(playerGroup, enemyShot);
		playerCollidesWithBonus = new PlayerCollidesWithBonus();
		playerCollidesWithBonus.setCollisionGroup(playerGroup, bonusGroup);
	}

	// intializes event manager
	private void startEventManager() {

		eventManager.addEventListener("PlayerMoveEvent", enemyShot);
		eventManager.addEventListener("PlayerFireEvent", playerShot);
		eventManager.addEventListener("EnemyFireEvent", enemyShot);
	}

	// calls other methods and initializes all remaining variables
	public void initResources() {
		// bsInput.setMouseVisible(false);
		// bsSound.setVolume(0.1f);
		eventManager = new EventManager();
		setSprites();
		BufferedImage gameBg = Resources.getImage("bg");
		bg = new ImageBackground(gameBg, 640, 480);

		setCollisionDetection();

		startEventManager();

	}

}
