//VERY CLOSE TO ACTUALLY MAKING THIS WORK ... ugh. we have to fix how images are loaded. it's about time we use a resourceBundle. For REAL!

//package vooga.games.cyberion.CyberionGameMain;
//
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.util.Random;
//
//import com.golden.gamedev.engine.BaseAudio;
//import com.golden.gamedev.engine.BaseInput;
//import com.golden.gamedev.engine.BaseLoader;
//import com.golden.gamedev.object.Background;
//import com.golden.gamedev.object.CollisionManager;
//import com.golden.gamedev.object.Sprite;
//import com.golden.gamedev.object.SpriteGroup;
//import com.golden.gamedev.object.background.ImageBackground;
//
//import vooga.engine.event.EventManager;
//import vooga.engine.player.control.KeyboardControl;
//import vooga.engine.resource.Resources;
//import vooga.engine.state.GameState;
//import vooga.games.cyberion.Collision.EnemyCollidesWithShot;
//import vooga.games.cyberion.Collision.PlayerCollidesEnemy;
//import vooga.games.cyberion.Collision.PlayerCollidesWall;
//import vooga.games.cyberion.Collision.PlayerCollidesWithBonus;
//import vooga.games.cyberion.Collision.PlayerCollidesWithShot;
//import vooga.games.cyberion.CyberionSprite.Bonus;
//import vooga.games.cyberion.CyberionSprite.EnemyShip;
//import vooga.games.cyberion.CyberionSprite.EnemyShot;
//import vooga.games.cyberion.CyberionSprite.PlayerShip;
//import vooga.games.cyberion.CyberionSprite.PlayerShot;
//import vooga.games.cyberion.CyberionSprite.StarGroup;
//
//public class MainGameState extends GameState {
//	BaseInput bsInput;
//	BaseAudio bsSound;
//	// creates image background
//	ImageBackground bg;
//	// creates image variables
//	private SpriteGroup star;
//	private PlayerShip playerShip;
//	private PlayerShot playerShot;
//	private SpriteGroup playerGroup;
//	private SpriteGroup enemyGroup;
//	private EnemyShot enemyShot;
//	private SpriteGroup bonusGroup;
//	// creates necessary collision managers
//	private CollisionManager playerCollidesWithEnemy;
//	private CollisionManager playerCollidesWithWall;
//	private CollisionManager playerCollidesWithBonus;
//	private CollisionManager playerCollidesWithShot;
//	private CollisionManager enemyCollidesWithShot;
//	// initializes constants
//	private static final int NUM_SHIPS = 1000;
//	private static final int NUM_BONUS = 10;
//	private static double SHIP_SPEED = 0.1;
//	// creates event manager
//	private EventManager eventManager;
//	// creates keyboard control
//	private KeyboardControl keyboardControl;
//	private Resources resources;
//	//
//	CyberionGame myGame = new CyberionGame();
//
//	@Override
//	public void initialize() {
//		initResources();
//	}
//
//	// initializes sprites from images
//	private void setSprites() {
////TODO: MAKE THIS WORK:
//		Resources.loadImage("starImage", 
//				"/vooga/src/vooga/games/cyberion/Resources/star.png");
//		Resources.loadImage("playerShot",
//				"/vooga/src/vooga/games/cyberion/Resources/playerShot.png");
//		Resources.loadImage("enemyShot",
//				"/vooga/src/vooga/games/cyberion/Resources/enemyShot.png");
//		Resources.loadImage("playerShip",
//				"/vooga/src/vooga/games/cyberion/Resources/playerShip.png");
//		Resources.loadImage("enemyShip",
//				"/vooga/src/vooga/games/cyberion/Resources/enemyShip.png");
//		Resources
//				.loadImage("bonus", "/vooga/src/vooga/games/cyberion/Resources/bonus.png");
//		Resources.loadImage("bg", "/vooga/src/vooga/games/cyberion/Resources/bg.png");
//
//		BufferedImage playerShotImage = Resources.getImage("playerShot");
//		BufferedImage enemyShotImage = Resources.getImage("enemyShot");
//		BufferedImage playerImage = Resources.getImage("playerShip");
//		BufferedImage enemyImage = Resources.getImage("enemyShip");
//		BufferedImage bonusImage = Resources.getImage("bonus");
//
//		BufferedImage starImage = Resources.getImage("starImage");
//
//		star = new StarGroup("StarGroup", starImage);
//		playerGroup = new SpriteGroup("PlayerGroup");
//		playerShip = new PlayerShip("playerShip", "normal", new Sprite(
//				playerImage));
//		playerShip.setEventManager(eventManager);
//		keyboardControl = new KeyboardControl(playerShip, myGame);
//		keyboardControl = playerShip.setKeyboardControl(keyboardControl);
//		playerGroup.add(playerShip);
//
//		enemyGroup = new SpriteGroup("EnemyGroup");
//		// randomly generates enemy ships
//		for (int i = 0; i < NUM_SHIPS; i++) {
//			Random random = new Random();
//			Sprite newShip = new EnemyShip(enemyImage, random.nextInt(640),
//					-random.nextInt(100000), 1, eventManager);
//			newShip.setVerticalSpeed(SHIP_SPEED);
//			enemyGroup.add(newShip);
//		}
//
//		bonusGroup = new SpriteGroup("BonusGroup");
//		// randomly generates bonuses
//		for (int i = 0; i < NUM_BONUS; i++) {
//			Random random = new Random();
//			Bonus newBonus = new Bonus(bonusImage, random.nextInt(640),
//					-random.nextInt(10000));
//			newBonus.setVerticalSpeed(SHIP_SPEED);
//			bonusGroup.add(newBonus);
//		}
//
//		playerShot = new PlayerShot("PlayerShot", 0, 0, 480, 640,
//				playerShotImage);
//		enemyShot = new EnemyShot("EnemyShot", enemyShotImage);
//
//	}
//
//	public void update() {
//
//	}
//
//	// initializes collision managers
//	private void setCollisionDetection() {
//
//		enemyCollidesWithShot = new EnemyCollidesWithShot(bsSound);
//		enemyCollidesWithShot.setCollisionGroup(playerShot, enemyGroup);
//		playerCollidesWithEnemy = new PlayerCollidesEnemy();
//		playerCollidesWithEnemy.setCollisionGroup(playerGroup, enemyGroup);
//		playerCollidesWithWall = new PlayerCollidesWall(bg);
//		playerCollidesWithWall.setCollisionGroup(playerGroup, playerGroup);
//		playerCollidesWithShot = new PlayerCollidesWithShot();
//		playerCollidesWithShot.setCollisionGroup(playerGroup, enemyShot);
//		playerCollidesWithBonus = new PlayerCollidesWithBonus();
//		playerCollidesWithBonus.setCollisionGroup(playerGroup, bonusGroup);
//	}
//
//	// intializes event manager
//	private void startEventManager() {
//
//		eventManager.addEventListener("PlayerMoveEvent", enemyShot);
//		eventManager.addEventListener("PlayerFireEvent", playerShot);
//		eventManager.addEventListener("EnemyFireEvent", enemyShot);
//	}
//
//	// calls other methods and initializes all remaining variables
//	public void initResources() {
//		// bsInput.setMouseVisible(false);
//		// bsSound.setVolume(0.1f);
//		eventManager = new EventManager();
//
//		BufferedImage gameBg = Resources.getImage("bg");
//		bg = new ImageBackground(gameBg, 640, 480);
//
//		setSprites();
//
//		setCollisionDetection();
//
//		startEventManager();
//
//	}
//
//	public void setBaseInput(BaseInput bi) {
//		bsInput = bi;
//	}
//
//	public void setBaseAudio(BaseAudio ba) {
//		bsSound = ba;
//	}
//
//	public void setResources(Resources r) {
//		resources = r;
//
//	}
//
// }
