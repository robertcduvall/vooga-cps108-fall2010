package vooga.games.cyberion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

//import vooga.engine.event.EventManager;
import vooga.engine.overlay.OverlayCreator;
import vooga.engine.overlay.OverlayTracker;
import vooga.engine.overlay.Stat;
//import vooga.engine.player.control.KeyboardControl;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameStateManager;
import vooga.engine.state.PauseGameState;

import vooga.games.asteroids.states.PlayState;
import vooga.games.cyberion.collisions.EnemyCollidesWithShot;
import vooga.games.cyberion.collisions.PlayerCollidesEnemy;
import vooga.games.cyberion.collisions.PlayerCollidesWall;
import vooga.games.cyberion.collisions.PlayerCollidesWithBonus;
import vooga.games.cyberion.collisions.PlayerCollidesWithShot;
import vooga.games.cyberion.sprites.Bonus;
import vooga.games.cyberion.sprites.processEnemyShip;
import vooga.games.cyberion.sprites.EnemyShot;
import vooga.games.cyberion.sprites.processPlayerShip;
import vooga.games.cyberion.sprites.PlayerShot;
import vooga.games.cyberion.sprites.StarGroup;

import vooga.engine.core.Game;
import vooga.engine.core.Sprite;
import vooga.engine.factory.LevelManager;
import vooga.engine.core.PlayField;

import com.golden.gamedev.GameLoader;

import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;

/**
 * Cyberion shooting game
 * 
 * @author Hao He, Scott Winkleman, Vitor Olivier
 * @version 1.0
 * @see http://www.istisoft.net/online/cyberion/cyberion.html
 */

public class DropThis extends Game {

	MainGameState playGameState;
	GameStateManager gameStateManager = new GameStateManager();
	public PlayField myPlayfield = new PlayField();
	LevelManager levelManager;

	
	// calls other methods and initializes all remaining variables

	public void initResources() {
		super.initResources();
		initLevelManager();
		PlayField levelPlayField = levelManager.loadFirstLevel();
		playGameState = new MainGameState(this, levelPlayField);
		stateManager.addGameState(playGameState);
	}
	
	private void initLevelManager() {
		//The reason this is not being moved to core.Game class is because you might want to 
		//create your own LevelParser in certain cases. In that case, use the following constructor:
		//levelManager = new LevelManager(this, yourCustomizedParser);
		levelManager = new LevelManager(this);
		String levelFilesDirectory = Resources.getString("levelFilesDirectory");
		String levelNamesFile = Resources.getString("levelNamesFile");
		levelManager.makeLevels(levelFilesDirectory,levelNamesFile);		
	}

//	public void initResources() {
//		
//		//Resources.initialize(this, "src/vooga/games/cyberion/resources/");
//		super.initResources();
//		
//		LevelManager levelManager = new LevelManager(this);
//		String levelFilesDirectory = Resources.getString("directory");
//		String levelNamesFile = Resources.getString("file");
//		levelManager.makeLevels(levelFilesDirectory,levelNamesFile);
//		
//		myPlayfield = levelManager.loadFirstLevel();
//
//		
//		//myPlayfield = getCurrentLevel();
//			
////		Resources.setGame(this);
////		loadImages();
//		
//		playGameState = new MainGameState(this, myPlayfield);
////		playGameState.initialize();
////		playGameState.activate();
//		gameStateManager.addGameState(playGameState);
//
//		OverlayCreator.setGame(this);
//		
//
//	}

//	private void loadImages() {
//		try {
//			Resources
//					.loadImageFile("src/vooga/games/cyberion/resources/game.properties");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	// updates sprite groups and checks for collisions
	public void update(long elapsedTime) {
		gameStateManager.update(elapsedTime);
		myPlayfield.update(elapsedTime);

	}

	// renders active sprites
	public void render(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		gameStateManager.render(g);
		myPlayfield.render(g);
		
		
	}

	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new DropThis(), new Dimension(640, 480), false);
		game.start();
	}

}
