import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import Collision.EnemyCollidesWithShot;
import Collision.PlayerCollidesEnemy;
import Collision.PlayerCollidesWall;
import Collision.PlayerCollidesWithShot;
import CyberionSprite.EnemyShip;
import CyberionSprite.EnemyShot;
import CyberionSprite.PlayerShip;
import CyberionSprite.PlayerShot;
import CyberionSprite.StarGroup;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;

import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.background.ImageBackground;
import com.golden.gamedev.util.ImageUtil;

import engine.event.EventManager;

public class CyberionGame extends Game {

	// sets bg bounds
	ImageBackground bg;

	private PlayerShot playerShot;
	// creates sprites
	private SpriteGroup star;
	private PlayerShip playerShip;
	private SpriteGroup playerGroup;
	private EnemyShip enemy;
	private SpriteGroup enemyGroup;
	private EnemyShot enemyShot;

	private CollisionManager playerCollidesWithEnemy;
	private CollisionManager playerCollidesWithWall;
	private CollisionManager enemyCollidesWithShot;

	private CollisionManager playerCollidesWithShot;

	private BufferedImage starImage;
	private BufferedImage playerShotImage;
	private BufferedImage enemyShotImage;
	private BufferedImage playerImage;
	private BufferedImage enemyImage;

	private static int NUM_SHIPS = 1000;
	private static double SHIP_SPEED = 0.1;

	private EventManager eventManager;

	private void setImages() {
		starImage = getImage("Resources/star.png");
		playerShotImage = getImage("Resources/playerShot.png");
		enemyShotImage = getImage("Resources/enemyShot.png");
		playerImage = getImage("Resources/playerShip.png");
		enemyImage = getImage("Resources/enemyShip.png");
	}

	private void setSprites() {
		playerGroup = new SpriteGroup("PlayerGroup");

		bsInput.setMouseVisible(false);
		// Set images

		BufferedImage starImage = getImage("Resources/star.png");
		BufferedImage playerShotImage = getImage("Resources/playerShot.png");
		BufferedImage enemyShotImage = getImage("Resources/enemyShot.png");
		BufferedImage playerImage = getImage("Resources/playerShip.png");
		BufferedImage enemyImage = getImage("Resources/enemyShip.png");

		// resize images

		// sets sprite position to be the same of ball || block object
		star = new StarGroup("StarGroup", starImage);

		playerShip = new PlayerShip(playerImage, 440, 320, 5, 1, eventManager,
				bsInput);
		playerGroup.add(playerShip);

		enemyGroup = new SpriteGroup("EnemyGroup");

		for (int i = 0; i < NUM_SHIPS; i++) {
			Random random = new Random();
			Sprite newShip = new EnemyShip(enemyImage, random.nextInt(640),
					-random.nextInt(100000), 1, eventManager);
			newShip.setVerticalSpeed(SHIP_SPEED);
			enemyGroup.add(newShip);
		}

		playerShot = new PlayerShot("PlayerShot", 0, 0, 480, 640,
				playerShotImage);
		enemyShot = new EnemyShot("EnemyShot", enemyShotImage);
	}

	private void setCollisionDetection() {
		enemyCollidesWithShot = new EnemyCollidesWithShot(bsSound);
		enemyCollidesWithShot.setCollisionGroup(playerShot, enemyGroup);
		playerCollidesWithEnemy = new PlayerCollidesEnemy();
		playerCollidesWithEnemy.setCollisionGroup(playerGroup, enemyGroup);
		playerCollidesWithWall = new PlayerCollidesWall(bg);
		playerCollidesWithWall.setCollisionGroup(playerGroup, playerGroup);
		playerCollidesWithShot = new PlayerCollidesWithShot();
		playerCollidesWithShot.setCollisionGroup(playerGroup, enemyShot);
	}

	private void startEventManager() {
		eventManager.addEventListener("PlayerMoveEvent", enemyShot);
		eventManager.addEventListener("PlayerFireEvent", playerShot);
		eventManager.addEventListener("EnemyFireEvent", enemyShot);
	}

	public void initResources() {

		bsSound.setVolume(0.1f);

		System.out.println(bsSound.getVolume());
		eventManager = new EventManager();

		BufferedImage gameBg = getImage("Resources/bg.png");
		bg = new ImageBackground(gameBg, 640, 480);

		bsInput.setMouseVisible(false);

		setImages();

		setSprites();

		setCollisionDetection();

		startEventManager();

		playMusic("Resources/missionimpossible.mid");
	}

	public void update(long elapsedTime) {

		star.update(elapsedTime);
		playerGroup.update(elapsedTime);
		playerShot.update(elapsedTime);
		playerCollidesWithWall.checkCollision();
		playerCollidesWithEnemy.checkCollision();
		playerCollidesWithShot.checkCollision();
		enemyCollidesWithShot.checkCollision();
		enemyGroup.update(elapsedTime);
		enemyShot.update(elapsedTime);
	}

	public void render(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());

		bg.render(g);
		playerGroup.render(g);
		star.render(g);
		playerShot.render(g);
		enemyGroup.render(g);
		enemyShot.render(g);
	}

	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new CyberionGame(), new Dimension(640, 480), false);
		game.start();

	}

}
