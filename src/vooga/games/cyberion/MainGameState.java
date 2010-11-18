//package vooga.games.cyberion;
//
//import java.awt.Graphics2D;
//import java.awt.image.BufferedImage;
//import java.util.Random;
//
//import com.golden.gamedev.object.CollisionManager;
//import com.golden.gamedev.object.PlayField;
//import com.golden.gamedev.object.SpriteGroup;
//import com.golden.gamedev.object.background.ImageBackground;
//
//import vooga.engine.core.Sprite;
//import vooga.engine.core.Game;
//import vooga.engine.event.EventPool;
//import vooga.engine.overlay.OverlayCreator;
//import vooga.engine.overlay.OverlayTracker;
//import vooga.engine.overlay.Stat;
//import vooga.engine.control.KeyboardControl;
//import vooga.engine.resource.Resources;
//
//import vooga.engine.state.GameState;
//import vooga.games.cyberion.collisions.EnemyCollidesWithShot;
//import vooga.games.cyberion.collisions.PlayerCollidesEnemy;
//import vooga.games.cyberion.collisions.PlayerCollidesWall;
//import vooga.games.cyberion.collisions.PlayerCollidesWithBonus;
//import vooga.games.cyberion.collisions.PlayerCollidesWithShot;
//import vooga.games.cyberion.sprites.Bonus;
//import vooga.games.cyberion.sprites.EnemyShip;
//import vooga.games.cyberion.sprites.EnemyShot;
//import vooga.games.cyberion.sprites.PlayerShip;
//import vooga.games.cyberion.sprites.PlayerShot;
//import vooga.games.cyberion.sprites.StarGroup;
//
//public class MainGameState extends GameState {
//
////	// creates image background
////	ImageBackground bg;
////	// creates image variables
////	private SpriteGroup star;
////	private PlayerShip playerShip;
////	private PlayerShot playerShot;
////	private SpriteGroup playerGroup;
////	private SpriteGroup enemyGroup;
////	private EnemyShot enemyShot;
////	private SpriteGroup bonusGroup;
////	// creates necessary collision managers
////	private CollisionManager playerCollidesWithEnemy;
////	private CollisionManager playerCollidesWithWall;
////	private CollisionManager playerCollidesWithBonus;
////	private CollisionManager playerCollidesWithShot;
////	private CollisionManager enemyCollidesWithShot;
////	// initializes constants
////	private static final int NUM_SHIPS = 1000;
////	private static final int NUM_BONUS = 10;
////	private static double SHIP_SPEED = 0.1;
////	// creates event manager
////	private EventPool eventManager;
////	// creates keyboard control
////	private vooga.engine.control.KeyboardControl keyboardControl;
////	private Resources resources;
//	private Game Game;
//	
////	public static Stat<Integer> myLives;
////	public static Stat<Integer> myScore;
//
//	//
//	@Override
//	public void initialize() {
//
////		initResources();
//	}
//
//	// initializes sprites from images
////	private void setSprites() {
////		BufferedImage playerShotImage = Resources.getImage("playerShot");
////		BufferedImage enemyShotImage = Resources.getImage("enemyShot");
////		BufferedImage playerImage = Resources.getImage("playerShip");
////		BufferedImage enemyImage = Resources.getImage("enemyShip");
////		BufferedImage bonusImage = Resources.getImage("bonus");
////		BufferedImage starImage = Resources.getImage("starImage");
////
////		star = new StarGroup("StarGroup", starImage);
////		playerGroup = new SpriteGroup("PlayerGroup");
////		playerShip = new PlayerShip("playerShip", new Sprite(playerImage));
////
////		playerShip.setEventManager(eventManager);
////		keyboardControl = new KeyboardControl(playerShip, myGame);
////		keyboardControl = playerShip.setKeyboardControl(keyboardControl);
////		playerGroup.add(playerShip);
////
////		enemyGroup = new SpriteGroup("EnemyGroup");
////		// randomly generates enemy ships
////		for (int i = 0; i < NUM_SHIPS; i++) {
////			Random random = new Random();
////			Sprite newShip = new EnemyShip(enemyImage, random.nextInt(640),
////					-random.nextInt(100000), 1, eventManager);
////			newShip.setVerticalSpeed(SHIP_SPEED);
////			enemyGroup.add(newShip);
////		}
////
////		bonusGroup = new SpriteGroup("BonusGroup");
////		// randomly generates bonuses
////		for (int i = 0; i < NUM_BONUS; i++) {
////			Random random = new Random();
////			Bonus newBonus = new Bonus(bonusImage, random.nextInt(640),
////					-random.nextInt(10000));
////			newBonus.setVerticalSpeed(SHIP_SPEED);
////			bonusGroup.add(newBonus);
////		}
////
////		playerShot = new PlayerShot("PlayerShot", 0, 0, 480, 640,
////				playerShotImage);
////		enemyShot = new EnemyShot("EnemyShot", enemyShotImage);
////
////		this.addGroup(star);
////		this.addGroup(playerGroup);
////		this.addGroup(enemyGroup);
////		this.addGroup(bonusGroup);
////		this.addGroup(enemyShot);
////		this.addGroup(playerShot);
////
////	}
////
////	public void update(long elapsedTime) {
////		super.update(elapsedTime);
////		keyboardControl.update();
////		playerCollidesWithWall.checkCollision();
////		playerCollidesWithEnemy.checkCollision();
////		playerCollidesWithShot.checkCollision();
////		playerCollidesWithBonus.checkCollision();
////		enemyCollidesWithShot.checkCollision();
////	}
////
////	private void setCollisionDetection() {
////
////		enemyCollidesWithShot = new EnemyCollidesWithShot();
////		enemyCollidesWithShot.setCollisionGroup(playerShot, enemyGroup);
////		playerCollidesWithEnemy = new PlayerCollidesEnemy();
////		playerCollidesWithEnemy.setCollisionGroup(playerGroup, enemyGroup);
////		playerCollidesWithWall = new PlayerCollidesWall(bg);
////		playerCollidesWithWall.setCollisionGroup(playerGroup, playerGroup);
////		playerCollidesWithShot = new PlayerCollidesWithShot();
////		playerCollidesWithShot.setCollisionGroup(playerGroup, enemyShot);
////		playerCollidesWithBonus = new PlayerCollidesWithBonus();
////		playerCollidesWithBonus.setCollisionGroup(playerGroup, bonusGroup);
////	}
////
////	// intializes event manager
////	private void startEventManager() {
////
////		eventManager.addEventListener("PlayerMoveEvent", enemyShot);
////		eventManager.addEventListener("PlayerFireEvent", playerShot);
////		eventManager.addEventListener("EnemyFireEvent", enemyShot);
////	}
////
////	// calls other methods and initializes all remaining variables
////	public void initResources() {
////		eventManager = new EventPool();
////		
////		OverlayTracker track = OverlayCreator.createOverlays("src/vooga/games/cyberion/resources/myOverlays.xml");
////		myLives = track.getStats().get(2);
////		myScore = track.getStats().get(3);
////		myPlayfield.addGroup(track.getOverlayGroups().get(0));
////		
////		setSprites();
////		BufferedImage gameBg = Resources.getImage("bg");
////		bg = new ImageBackground(gameBg, 640, 480);
////		myPlayfield.setBackground(bg);
////		
////		setCollisionDetection();
////
////		startEventManager();
////
////	}
//
//	public void setGame(Game cyberionGame) {
//		Game = cyberionGame;
//	}
//
//}


package vooga.games.cyberion;

import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.overlay.Stat;
import vooga.engine.state.GameState;
import vooga.engine.core.BetterSprite;

public class MainGameState extends GameState{
	
private Game game;
public static Stat<Integer> myLives;
public static Stat<Integer> myScore;
	
	public MainGameState(Game game, PlayField field){
		super(field);
		this.game = game;
	}

	@Override
	public void initialize() {
		initLevel();
		
		
	}
	
	private void initLevel(){

//		PlayField playField = factory.getPlayfield("INSERTFILEPATHERE", game);
		
//		playField.addCollisionGroup(bulletGroup, asteroidGroup, new BulletToAsteroidCollision());
//		playField.addCollisionGroup(shipGroup, asteroidGroup, new ShipToAsteroidCollision());
//		
//		addPlayField(playField);
		
//		Sprite playerShip = (Sprite)(getGroup("playerShip").getSprites()[0]);
//		initControls(playerShip);

	}
	
//	private void initControls(Sprite player){
//		Control playerControl = new KeyboardControl(player, game);
//		playerControl.addInput(KeyEvent.VK_LEFT, "rotateLeft", "vooga.games.asteroids.sprites.Ship", null);
//		playerControl.addInput(KeyEvent.VK_RIGHT, "rotateRight", "vooga.games.asteroids.sprites.Ship", null);
//		playerControl.addInput(KeyEvent.VK_UP, "thrust", "vooga.games.asteroids.sprites.Ship", null);
//		playerControl.addInput(KeyEvent.VK_SPACE, "fire", "vooga.games.asteroids.sprites.Ship", null);
//		
//	}

}

