package CyberionGameMain;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import Collision.EnemyCollidesWithShot;
import Collision.PlayerCollidesEnemy;
import Collision.PlayerCollidesWall;
import Collision.PlayerCollidesWithBonus;
import Collision.PlayerCollidesWithShot;
import CyberionSprite.Bonus;
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
import com.golden.gamedev.object.background.ImageBackground;

import engine.event.EventManager;

public class CyberionGame extends Game {
	//creates image background
	ImageBackground bg;
	//creates image variables
	private SpriteGroup star;
	private PlayerShip playerShip;
	private PlayerShot playerShot;
	private SpriteGroup playerGroup;
	private SpriteGroup enemyGroup;
	private EnemyShot enemyShot;
	private SpriteGroup bonusGroup;
	//creates necessary collision managers
	private CollisionManager playerCollidesWithEnemy;
	private CollisionManager playerCollidesWithWall;
	private CollisionManager playerCollidesWithBonus;
	private CollisionManager playerCollidesWithShot;
	private CollisionManager enemyCollidesWithShot;
	//initializes constants
	private static final int NUM_SHIPS = 1000;
	private static final int NUM_BONUS = 10;
	private static double SHIP_SPEED = 0.1;
	//creates event manager
	private EventManager eventManager;
	
	//initializes sprites from images
	private void setSprites() {
		BufferedImage starImage = getImage("Resources/star.png");
		BufferedImage playerShotImage = getImage("Resources/playerShot.png");
		BufferedImage enemyShotImage = getImage("Resources/enemyShot.png");
		BufferedImage playerImage = getImage("Resources/playerShip.png");
		BufferedImage enemyImage = getImage("Resources/enemyShip.png");
		BufferedImage bonusImage = getImage("Resources/bonus.png");

		star = new StarGroup("StarGroup", starImage);
		playerGroup = new SpriteGroup("PlayerGroup");
		playerShip = new PlayerShip(playerImage, 440, 320, 5, 1, eventManager,
				bsInput);
		playerGroup.add(playerShip);

		enemyGroup = new SpriteGroup("EnemyGroup");
		//randomly generates enemy ships
		for (int i = 0; i < NUM_SHIPS; i++) {
			Random random = new Random();
			Sprite newShip = new EnemyShip(enemyImage, random.nextInt(640),
					-random.nextInt(100000), 1, eventManager);
			newShip.setVerticalSpeed(SHIP_SPEED);
			enemyGroup.add(newShip);
		}
	
		bonusGroup = new SpriteGroup("BonusGroup");
		//randomly generates bonuses 
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
	}
	//initializes collision managers
	private void setCollisionDetection() {
	
		enemyCollidesWithShot = new EnemyCollidesWithShot(bsSound);
		enemyCollidesWithShot.setCollisionGroup(playerShot, enemyGroup);
		playerCollidesWithEnemy = new PlayerCollidesEnemy();
		playerCollidesWithEnemy.setCollisionGroup(playerGroup, enemyGroup);
		playerCollidesWithWall = new PlayerCollidesWall(bg);
		playerCollidesWithWall.setCollisionGroup(playerGroup, playerGroup);
		playerCollidesWithShot = new PlayerCollidesWithShot();
		playerCollidesWithShot.setCollisionGroup(playerGroup, enemyShot);
		playerCollidesWithBonus = new PlayerCollidesWithBonus();
		playerCollidesWithBonus.setCollisionGroup(playerGroup, bonusGroup);
	}
	//intializes event manager
	private void startEventManager() {
		
		eventManager.addEventListener("PlayerMoveEvent", enemyShot);
		eventManager.addEventListener("PlayerFireEvent", playerShot);
		eventManager.addEventListener("EnemyFireEvent", enemyShot);
	}
	//calls other methods and initializes all remaining variables
	public void initResources() {

		bsInput.setMouseVisible(false);
		bsSound.setVolume(0.1f);

		eventManager = new EventManager();

		BufferedImage gameBg = getImage("Resources/bg.png");
		bg = new ImageBackground(gameBg, 640, 480);

		setSprites();

		setCollisionDetection();

		startEventManager();

		playMusic("Resources/missionimpossible.mid");
	}
	//updates sprite groups and checks for collisions
	public void update(long elapsedTime) {
		star.update(elapsedTime);
		playerGroup.update(elapsedTime);
		playerShot.update(elapsedTime);
		enemyGroup.update(elapsedTime);
		enemyShot.update(elapsedTime);
		bonusGroup.update(elapsedTime);

		playerCollidesWithWall.checkCollision();
		playerCollidesWithEnemy.checkCollision();
		playerCollidesWithShot.checkCollision();
		playerCollidesWithBonus.checkCollision();
		enemyCollidesWithShot.checkCollision();
	}
	//renders active sprites
	public void render(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		bg.render(g);
		star.render(g);
		playerGroup.render(g);
		playerShot.render(g);
		enemyGroup.render(g);
		enemyShot.render(g);
		bonusGroup.render(g);
	}

	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new CyberionGame(), new Dimension(640, 480), false);
		game.start();
	}

}
