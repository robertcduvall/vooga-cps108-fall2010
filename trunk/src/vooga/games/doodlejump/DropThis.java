package vooga.games.doodlejump;

import java.awt.*;

import vooga.engine.player.control.KeyboardControl;
import vooga.engine.player.control.PlayerSprite;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;
import vooga.engine.overlay.*;
import vooga.engine.core.*;
import vooga.engine.core.Game;
//import vooga.engine.core.Sprite;

import vooga.games.doodlejump.collisions.*;

import com.golden.gamedev.*;
import com.golden.gamedev.object.*;
import com.golden.gamedev.object.Sprite;
//import com.golden.gamedev.object.Sprite;

import com.golden.gamedev.object.background.*;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * The DoodleGame class creates a game based on the popular iPhone app Doodle
 * Jump
 * 
 * @author Adam Cue, Marcus Molchany, Nick Straub
 * 
 */
public class DropThis extends Game {

	// GameStateManager
	GameStateManager doodleStateManager;

	// GameStates
	GameState startMenuState, playState, pauseMenuState, gameOverState,
			winState;

	// Background
	private Background background;

	// Playfield
	protected PlayField playField;

	private DoodleLevelManager levelManager;
	private DoodleLevel level;

	private OverlayString scoreString, startString, winString;
	private Stat<Integer> score = new Stat<Integer>(0);
	private int currentLevel;
	private int passScore;
	private int nextLevel;

	// Doodle (main player)
	private DoodleSprite doodle;
	private KeyboardControl doodleKeyboardControl;

	protected SpriteGroup doodleGroup, ballGroup;

	// Collision Manager
	protected CollisionManager doodleToGreenPlatform, doodleToMonster,
			ballToMonster, doodleToBrownPlatform, doodleToWhitePlatform,
			doodleToSpring, doodleToTrampoline, doodleToJetpack;

	private boolean showStartMenu = true;

	{
		distribute = true;
	}

	// public DropThis() {
	// super();
	// currentLevel = 1;
	// showStartMenu = true;
	// score = new Stat<Integer>(0);
	// makeOverlayStrings();
	// levelManager = new DoodleLevelManager();
	// levelManager.addLevels("src/vooga/games/doodlejump/resources/levels",
	// new File("levelNames.txt"));
	// ResourceHandler.setGame(this);
	// try {
	// ResourceHandler
	// .loadFile("vooga/games/doodlejump/resources/resourcelist.txt");
	// } catch (IOException e) {
	// System.out.println(e);
	// }
	// }

	@Override
	public void initResources() {
		
		super.initResources();

		if (showStartMenu) {
			currentLevel = 1;
		}

		initLevelManager();

		initPlayField();

		initStates();

		background = level.getBackground();

		passScore += level.getScore();
		nextLevel = level.getNextLevel();

		initSpriteGroups();

		initDoodle();

		initCollisions(level);

		makeOverlayStrings();

		setFPS(100);
	}

	/**
	 * This method initializes the LevelManager
	 */
	public void initLevelManager() {
		levelManager = new DoodleLevelManager();
		levelManager.addLevels("src/vooga/games/doodlejump/resources/levels",
				new File("levelNames.txt"));
//		Resources.initialize(this,"");
//		try {
//			Resources
//					.loadPropertiesFile("src/vooga/games/doodlejump/resources/resourcelist.txt");
//		} catch (IOException e) {
//			System.out.println(e);
//		}
	}

	/**
	 * This method initializes the PlayField
	 */
	public void initPlayField() {
		if (playField != null)
			playField.clearPlayField();
		playField = levelManager.loadNextLevel();
		level = (DoodleLevel) levelManager.getCurrentDoodleLevel();

		// playfield
		playField = level.getPlayfield(new File(
				"src/vooga/games/doodlejump/resources/levels/level"
						+ Integer.toString(currentLevel) + ".txt"));
	}

	/**
	 * This method initializes all of the GameStates
	 */
	public void initStates() {
		doodleStateManager = new GameStateManager();

		startMenuState = new GameState();
		playState = new GameState();
		pauseMenuState = new GameState();
		gameOverState = new GameState();
		winState = new GameState();

		doodleStateManager.addGameState(startMenuState);
		doodleStateManager.addGameState(playState);
		doodleStateManager.addGameState(pauseMenuState);
		doodleStateManager.addGameState(gameOverState);
		doodleStateManager.addGameState(winState);

		if (showStartMenu)
			startMenu();
		else
			playGame();
	}

	/**
	 * This method initializes all of the SpriteGroups
	 */
	public void initSpriteGroups() {
		// spritegroups
		ballGroup = playField.addGroup(new SpriteGroup("Ball Group"));
		doodleGroup = playField.addGroup(new SpriteGroup("Doodle Group"));
	}

	/**
	 * This method initializes Doodle
	 */
	public void initDoodle() {
		// doodle (main player)
		doodle = new DoodleSprite("doodle", "normal", new Sprite(
				getImage("resources/images/doodle_right.png"), 325, 550), this);
		doodle.setVerticalSpeed(0.5);
		doodleGroup.add(doodle);
		doodleKeyboardControl = new KeyboardControl(doodle, this);
		doodleKeyboardControl.addInput(KeyEvent.VK_LEFT, "moveLeft",
				"vooga.games.doodlejump.DoodleSprite", null);
		doodleKeyboardControl.addInput(KeyEvent.VK_RIGHT, "moveRight",
				"vooga.games.doodlejump.DoodleSprite", null);
		doodleKeyboardControl.addInput(KeyEvent.VK_SPACE, "shoot",
				"vooga.games.doodlejump.DoodleSprite", null);
	}

	/**
	 * This method takes in a DoodleLevel as a parameter and initializes all of
	 * the Collisions
	 * 
	 * @param level
	 */
	public void initCollisions(DoodleLevel level) {
		// Collision
		doodleToGreenPlatform = new DoodleToGreenPlatformCollision();
		doodleToMonster = new DoodleToMonsterCollision();
		ballToMonster = new BallToMonsterCollision();
		doodleToBrownPlatform = new DoodleToBrownPlatformCollision();
		doodleToWhitePlatform = new DoodleToWhitePlatformCollision();
		doodleToSpring = new DoodleToSpringCollision();
		doodleToTrampoline = new DoodleToTrampolineCollision();

		playField.addCollisionGroup(doodleGroup, level.getPlatformGroup(),
				doodleToGreenPlatform);
		playField.addCollisionGroup(doodleGroup, level.getMonsterGroup(),
				doodleToMonster);
		playField.addCollisionGroup(ballGroup, level.getMonsterGroup(),
				ballToMonster);
		playField.addCollisionGroup(doodleGroup, level.getBrownPlatformGroup(),
				doodleToBrownPlatform);
		playField.addCollisionGroup(doodleGroup, level.getWhitePlatformGroup(),
				doodleToWhitePlatform);
		playField.addCollisionGroup(doodleGroup, level.getSpringGroup(),
				doodleToSpring);
		playField.addCollisionGroup(doodleGroup, level.getTrampolineGroup(),
				doodleToTrampoline);
	}

	/**
	 * This method instantiates all of the OverlayStrings
	 */
	public void makeOverlayStrings() {
		scoreString = new OverlayString("Score: " + 0);
		startString = new OverlayString("Press Enter to Start!", new Font(
				"SansSerif", Font.BOLD, 40));
		startString.setX(532 / 2 - startString.getWidth() / 2);
		startString.setY(550 / 2 - startString.getHeight() / 2);
		winString = new OverlayString("You Win! Enter to Restart.", new Font(
				"SansSerif", Font.BOLD, 35));
		winString.setX(532 / 2 - startString.getWidth() / 2);
		winString.setY(200 - startString.getHeight() / 2);
		scoreString.setX(400);
		scoreString.setY(50);
	}

	@Override
	public void update(long elapsedTime) {
		doodleKeyboardControl.update();

		if (gameOverState.isActive()) {
			if (keyPressed(KeyEvent.VK_ENTER)) {
				resetGame();
			}
		} else if (startMenuState.isActive()) {
			if (keyPressed(KeyEvent.VK_ENTER)) {
				playGame();
			}
			playField.setBackground(new ImageBackground(
					getImage("resources/images/default-play.png")));
		} else if (playState.isActive()) {

			updateGame(elapsedTime);

			checkLevelCompleted();

			if (keyPressed(KeyEvent.VK_P)) {
				pauseGame();
			}
		} else {
			if (keyPressed(KeyEvent.VK_P)) {
				playGame();
			}
			playField.setBackground(new ImageBackground(
					getImage("resources/images/pause-cover-resume.png")));
		}
	}

	/**
	 * This method activates startMenuState and deactivates each other state
	 * besides win
	 */
	public void startMenu() {
		doodleStateManager.switchTo(startMenuState);
	}

	/**
	 * This method resets the game by setting the current level to 1, setting
	 * score and passScore to 0, and calling initResources()
	 */
	public void resetGame() {
		currentLevel = 1;
		score.setStat(0);
		passScore = 0;
		levelManager = new DoodleLevelManager();
		levelManager.addLevels("src/vooga/games/doodlejump/resources/levels",
				new File("levelNames.txt"));
		initResources();
	}

	/**
	 * This method activates playState and deactivates each other state besides
	 * win
	 */
	public void playGame() {
		doodleStateManager.switchTo(playState);
	}

	/**
	 * This method takes in a long and updates all of the sprites in each
	 * SpriteGroup on the PlayField
	 * 
	 * @param elapsedTime
	 */
	public void updateGame(long elapsedTime) {
		for (SpriteGroup group : playField.getGroups()) {
			for (Sprite sprite : group.getSprites()) {
				if (doodle.getY() < 400 && sprite != null) {
					if (group.getName().equals("Doodle Group"))
						score.setStat(score.getStat() + 5);
					sprite.moveY(400 - doodle.getY());
				}
			}
		}
		playField.update(elapsedTime);
		scoreString.setString("Score: " + Integer.toString(score.getStat()));
	}

	/**
	 * This method checks to see if the current level has been completed then
	 * either goes to the next level or Game Over screen
	 */
	public void checkLevelCompleted() {
		if (score.getStat() >= passScore) {
			if (nextLevel == 0) {
				winState.activate();
				if (keyPressed(KeyEvent.VK_ENTER)) {
					gameOver();
					winState.deactivate();
				}
			}
			if (nextLevel != 0) {
				currentLevel = nextLevel;
				showStartMenu = false;
				initResources();
			} else {
				gameOver();
			}
		}
	}

	/**
	 * This method activates gameOverState and deactivates each other state
	 * besides win
	 */
	public void gameOver() {
		doodleStateManager.switchTo(gameOverState);
	}

	/**
	 * This method activates pauseMenuState and deactivates each other state
	 * besides win
	 */
	public void pauseGame() {
		doodleStateManager.switchTo(pauseMenuState);
	}

	@Override
	public void render(Graphics2D g) {
		if (playState.isActive()) {
			playField.setBackground(background);
			playField.render(g);
			scoreString.render(g);
		} else if (startMenuState.isActive()) {
			playField.getBackground().render(g);
			startString.render(g);
		} else if (pauseMenuState.isActive()) {
			playField.getBackground().render(g);
		} else if (winState.isActive()) {
			winString.render(g);
		}
	}

	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new DropThis(), new Dimension(532, 850), false);
		game.start();
	}

}
