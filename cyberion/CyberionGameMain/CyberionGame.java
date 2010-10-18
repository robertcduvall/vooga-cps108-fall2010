package vooga.games.cyberion.CyberionGameMain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import vooga.engine.event.EventManager;
import vooga.engine.player.control.KeyboardControl;
import vooga.engine.resource.Resources;
import vooga.engine.resource.ResourcesBeta;
import vooga.engine.state.GameStateManager;

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

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;

import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;

/**
 * Cyberion shooting game
 * 
 * @author Hao He, Scott Winkleman, Vitor Olivier
 * @version 1.0
 * @see http://www.istisoft.net/online/cyberion/cyberion.html
 */

public class CyberionGame extends Game {
	// // creates image background
	// ImageBackground bg;
	// // creates image variables
	// private SpriteGroup star;
	// private PlayerShip playerShip;
	// private PlayerShot playerShot;
	// private SpriteGroup playerGroup;
	// private SpriteGroup enemyGroup;
	// private EnemyShot enemyShot;
	// private SpriteGroup bonusGroup;
	// // creates necessary collision managers
	// private CollisionManager playerCollidesWithEnemy;
	// private CollisionManager playerCollidesWithWall;
	// private CollisionManager playerCollidesWithBonus;
	// private CollisionManager playerCollidesWithShot;
	// private CollisionManager enemyCollidesWithShot;
	// // initializes constants
	// private static final int NUM_SHIPS = 1000;
	// private static final int NUM_BONUS = 10;
	// private static double SHIP_SPEED = 0.1;
	// // creates event manager
	// private EventManager eventManager;
	// //creates keyboard control
	// private KeyboardControl keyboardControl;
	MainGameState playGameState = new MainGameState();
	GameStateManager gameStateManager = new GameStateManager();

	// initializes sprites from images
	// private void setSprites() {
	// BufferedImage starImage =
	// getImage("vooga/games/cyberion/Resources/star.png");
	// BufferedImage playerShotImage =
	// getImage("vooga/games/cyberion/Resources/playerShot.png");
	// BufferedImage enemyShotImage =
	// getImage("vooga/games/cyberion/Resources/enemyShot.png");
	// BufferedImage playerImage =
	// getImage("vooga/games/cyberion/Resources/playerShip.png");
	// BufferedImage enemyImage =
	// getImage("vooga/games/cyberion/Resources/enemyShip.png");
	// BufferedImage bonusImage =
	// getImage("vooga/games/cyberion/Resources/bonus.png");
	//
	// star = new StarGroup("StarGroup", starImage);
	// playerGroup = new SpriteGroup("PlayerGroup");
	// playerShip = new PlayerShip("playerShip", "normal", new
	// Sprite(playerImage));
	// playerShip.setEventManager(eventManager);
	// keyboardControl = new KeyboardControl(playerShip, this);
	// keyboardControl = playerShip.setKeyboardControl(keyboardControl);
	// playerGroup.add(playerShip);
	//
	// enemyGroup = new SpriteGroup("EnemyGroup");
	// // randomly generates enemy ships
	// for (int i = 0; i < NUM_SHIPS; i++) {
	// Random random = new Random();
	// Sprite newShip = new EnemyShip(enemyImage, random.nextInt(640),
	// -random.nextInt(100000), 1, eventManager);
	// newShip.setVerticalSpeed(SHIP_SPEED);
	// enemyGroup.add(newShip);
	// }
	//
	// bonusGroup = new SpriteGroup("BonusGroup");
	// // randomly generates bonuses
	// for (int i = 0; i < NUM_BONUS; i++) {
	// Random random = new Random();
	// Bonus newBonus = new Bonus(bonusImage, random.nextInt(640),
	// -random.nextInt(10000));
	// newBonus.setVerticalSpeed(SHIP_SPEED);
	// bonusGroup.add(newBonus);
	// }
	//
	// playerShot = new PlayerShot("PlayerShot", 0, 0, 480, 640,
	// playerShotImage);
	// enemyShot = new EnemyShot("EnemyShot", enemyShotImage);
	//
	// }
	//
	// // initializes collision managers
	// private void setCollisionDetection() {
	//
	// enemyCollidesWithShot = new EnemyCollidesWithShot(bsSound);
	// enemyCollidesWithShot.setCollisionGroup(playerShot, enemyGroup);
	// playerCollidesWithEnemy = new PlayerCollidesEnemy();
	// playerCollidesWithEnemy.setCollisionGroup(playerGroup, enemyGroup);
	// playerCollidesWithWall = new PlayerCollidesWall(bg);
	// playerCollidesWithWall.setCollisionGroup(playerGroup, playerGroup);
	// playerCollidesWithShot = new PlayerCollidesWithShot();
	// playerCollidesWithShot.setCollisionGroup(playerGroup, enemyShot);
	// playerCollidesWithBonus = new PlayerCollidesWithBonus();
	// playerCollidesWithBonus.setCollisionGroup(playerGroup, bonusGroup);
	// }
	// //
	// // intializes event manager
	// private void startEventManager() {
	//
	// eventManager.addEventListener("PlayerMoveEvent", enemyShot);
	// eventManager.addEventListener("PlayerFireEvent", playerShot);
	// eventManager.addEventListener("EnemyFireEvent", enemyShot);
	// }

	// calls other methods and initializes all remaining variables
	public void initResources() {
		Resources.setGame(this);
		loadImages();
		playGameState.setBaseAudio(bsSound);
		playGameState.setBaseInput(bsInput);
		playGameState.initialize();
		gameStateManager.addGameState(playGameState);
		//
		// bsInput.setMouseVisible(false);
		// bsSound.setVolume(0.1f);
		//
		// eventManager = new EventManager();
		//
		// BufferedImage gameBg =
		// getImage("vooga/games/cyberion/Resources/bg.png");
		// bg = new ImageBackground(gameBg, 640, 480);
		//
		// setSprites();
		//
		// setCollisionDetection();
		//
		// startEventManager();
		//
		// playMusic("vooga/games/cyberion/Resources/missionimpossible.mid");

	}

	private void loadImages() {
		try {
			Resources
					.loadFile("src/vooga/games/cyberion/Resources/imageList.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// updates sprite groups and checks for collisions
	public void update(long elapsedTime) {
		gameStateManager.update(elapsedTime);
		// star.update(elapsedTime);
		// playerGroup.update(elapsedTime);
		// playerShot.update(elapsedTime);
		// enemyGroup.update(elapsedTime);
		// enemyShot.update(elapsedTime);
		// bonusGroup.update(elapsedTime);
		// keyboardControl.update();

		// playerCollidesWithWall.checkCollision();
		// playerCollidesWithEnemy.checkCollision();
		// playerCollidesWithShot.checkCollision();
		// playerCollidesWithBonus.checkCollision();
		// enemyCollidesWithShot.checkCollision();
	}

	// renders active sprites
	public void render(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		gameStateManager.render(g);
		// bg.render(g);
		// star.render(g);
		// playerGroup.render(g);
		// playerShot.render(g);
		// enemyGroup.render(g);
		// enemyShot.render(g);
		// bonusGroup.render(g);

	}

	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new CyberionGame(), new Dimension(640, 480), false);
		game.start();
	}

}
