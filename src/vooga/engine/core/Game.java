package vooga.engine.core;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import arcade.core.components.HighScorePanel;
import arcade.mod.controller.GameConsole;

import com.golden.gamedev.GameLoader;

import vooga.engine.factory.LevelParser;
import vooga.engine.networking.client.ChatConnection;
import vooga.engine.networking.server.VoogaServer;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;
import vooga.engine.util.SoundPlayer;
import vooga.games.digger.EntityMap;

/**
 * Extension of the Golden T game to automate some Vooga API functionality. <br />
 * initResources() automatically loads all resources associated with the
 * resources.xml file in your game's resources package. <br />
 * It also now has a built in GameStateManager which is automatically
 * initialized in initResources. The initialization behavior can be changed by
 * overriding initGameStates. The update and render methods call update and
 * render for this GameStateManager. <br />
 * The game also automatically loads the initial level using the Level XML
 * parser <br />
 * All subclasses should override initResources() and implement a main method
 * that calls launch(). The initResources() method should first call
 * super.initResources() and then initialize all the game states
 * 
 * @author rcd, Daniel Koverman, John Kline, Yang Su, Kate Yang, Jimmy Mu
 */
public class Game extends com.golden.gamedev.Game {

	protected GameStateManager stateManager;
	protected LevelParser levelParser;
	protected PlayField myCurrentLevel;
	protected GameConsole myGameConsole;
	protected GameState myPlayState;
	protected double finalScore;
	// private static final int INITIAL_LEVEL = 1;

	private static final int DEFAULT_WIDTH = 600;
	private static final int DEFAULT_HEIGHT = 400;
	private static final boolean DEFAULT_FULLSCREEN = false;
	private static final String DEFAULT_MOD_NAME = "default";

	private String modName;
	protected String userName;

	@Override
	public void initResources() {

		// Game teams should now convert their old imagelist.txt, soundlist.txt,
		// etc. files into the
		// new and improved resources.xml file format by following the example
		// code in
		// vooga.examples.resource.resourcesxmlexample
		stateManager = new GameStateManager();
		Resources.initialize(this, getResourceXMLPath());
		try {
			if (modName != null)
				Resources.loadResourcesXMLFile(modName + "resources.xml");
			else
				Resources.loadResourcesXMLFile("resources.xml");
		} catch (IOException e) {
			try {
				Resources.loadResourcesXMLFile("resources.xml");
			} catch (IOException e1) {
				System.out.println("Failed to load default resources package.");
				System.exit(0);
			}
		}
		initializeEntityMap();
		initGameStates();
		initSoundPlayer();
		initGameConsole();

		// setCurrentLevel(INITIAL_LEVEL);
	}

	public void initGameConsole() {
		myGameConsole = new GameConsole();

	}

	@Override
	public void update(long elapsedTime) {
		stateManager.update(elapsedTime);
		myGameConsole.update(elapsedTime);
		if (isFinish()) {
			notifyExit();
		}

	}

	@Override
	protected void notifyExit() {

	}

	@Override
	public void render(Graphics2D g) {
		stateManager.render(g);
	}

	public void initSoundPlayer() {
		SoundPlayer.setGame(this);
	}

	/**
	 * Returns the VoogaPlayField that is associated with the current level.
	 */
	public PlayField getCurrentLevel() {
		return myCurrentLevel;
	}

	/**
	 * this method gets the play game state
	 * 
	 * @return
	 */
	public GameState getPlayGameState() {
		return myPlayState;
	}

	/**
	 * This method sets the play game state
	 * 
	 * @param state
	 */
	public void setAsPlayGameState(GameState state) {
		myPlayState = state;
	}

	// No longer needed. This should be done inside the actual game using the
	// LevelManger
	// /**
	// * Sets the VoogaPlayField to the desired level.
	// */
	// public void setCurrentLevel(int level) {
	// myCurrentLevel = initLevel(level);
	// }

	/**
	 * Initializes the GameStateManager. This should be overridden by subclasses
	 * which should call super() and then setup/add all necessary GameStates.
	 */
	public void initGameStates() {
		stateManager = new GameStateManager();
	}

	// No longer needed. This should be done inside the actual game using the
	// LevelManger
	// See initLevelManager() in BlahThis of asteroids.
	// /**
	// * Initializes the level specified by the given int index. The XML file
	// * associated with the level is located at a filepath specified by
	// * Resources.getString("Level" + index) For example, if the level's XML
	// file
	// * is called blueLevel.xml, the stringMap in Resources contains the String
	// * -> String entry: "Level" + index -> "blueLevel.xml"
	// */
	// public PlayField initLevel(int index) {
	// levelParser = new LevelParser();
	// PlayField vpf;
	// try {
	// vpf = levelParser
	// .getPlayfield(
	// getResourceXMLPath()
	// + Resources.getString("Level" + index),
	// this);
	// return vpf;
	// } catch (Exception e) {
	// // TODO display error messages like: throw new
	// // Resources.FileNotFoundException;
	// e.printStackTrace();
	// return null;
	// }
	//
	// }

	/**
	 * get the GameStateManager for this game
	 * 
	 * @return GameStateManager object
	 */
	public GameStateManager getGameStateManager() {
		return stateManager;
	}

	/**
	 * add a GameState to the stateManager to prevent repetitive calls to
	 * retrieve the stateManager to add to it in Game extensions
	 * 
	 * @param gameState
	 *            GameState to add to the Game stateManager
	 * @return gameState added to reduce number of lines of code when adding
	 *         GameStates
	 */
	public GameState addGameState(GameState gameState) {
		stateManager.addGameState(gameState);
		return gameState;
	}

	// No longer needed.
	// /**
	// * get the LevelParser for this game
	// *
	// * @return LevelParser object
	// */
	// public LevelParser getLevelParser() {
	// return levelParser;
	// }

	// TODO change ambiguity of "." and "/"
	/**
	 * Get the path for the resources package directory where the resources.xml
	 * file is
	 * 
	 * @return the path for the resources directory
	 */
	protected String getResourceXMLPath() {
		String defaultPath = "src/" + getResourcePackagePath();
		return defaultPath.replace('.', '/');
	}

	/**
	 * Get the path for the game resources package
	 * 
	 * @return game package resources path
	 */
	private String getResourcePackagePath() {
		return getClass().getPackage().getName() + ".resources.";
	}

	public void updateHighScore(double score) {
		finalScore = score;
		// TODO Move following statement to a on close listener of some sort
		HighScorePanel.updateHighScore(finalScore);
	}

	public double getHighScore() {
		return finalScore;

	}

	/**
	 * Launches the game using default or given settings. The game width,
	 * height, and fullscreen option are stored in a file called
	 * config.properties under the resources package. These values are extracted
	 * before the game is launched. Gets the game name from the game.properties and determines whether
	 * it's an online game or not.  If it is it initializes the chat and sends the userName to the game.
	 * If it isn't then just run the game normally.
	 * 
	 * @param g the Game to run
	 * @param userName the name of the user
	 */
	public static void launch(Game g, String userName) {

		// Default settings
		int width = DEFAULT_WIDTH;
		int height = DEFAULT_HEIGHT;
		boolean fullScreen = DEFAULT_FULLSCREEN;
		String gameName = "";
		try {
			String configPath = g.getResourcePackagePath() + "config";
			ResourceBundle rb = Resources.loadPreLaunchData(configPath);
			width = Integer.parseInt(rb.getString("GAME_WIDTH"));
			height = Integer.parseInt(rb.getString("GAME_HEIGHT"));
			fullScreen = (rb.getString("FULLSCREEN").equals("true")) ? true
					: false;
			String gameInfoPath = g.getResourcePackagePath() + "game";
			ResourceBundle gameRb = Resources.loadPreLaunchData(gameInfoPath);
			gameName = gameRb.getString("name");
		} catch (Exception e) {
			// TODO Resource Exception
			e.printStackTrace();
		}

		if (VoogaServer.getChatPort(gameName) != -1) {
			ChatConnection connection = null;
			try {
				connection = new ChatConnection(gameName, userName);
			} catch (IOException e) {
			}
			VoogaFrame frame = new VoogaFrame(connection);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			// frame.setUndecorated(true);
			frame.setVisible(true);
			g.setUsername(userName);
		}
		GameLoader game = new GameLoader();
		game.setup(g, new Dimension(width, height), fullScreen);
		game.start();
	}

	/**
	 * For networking team.
	 * 
	 * @param gameStateForButton
	 */
	public void switchState(GameState gameStateForButton) {
		stateManager.switchTo(gameStateForButton);
	}

	public void setMod(String modName) {
		this.modName = modName;
	}

	public void initializeEntityMap() {
		System.out.println("INITIALIZING ENTITY MAP");
		try {
			EntityMap.inititalize();
		} catch (Throwable e) {

		}
	}

	public String getMod() {
		return modName;
	}

	public void setUsername(String userName) {
		this.userName = userName;
	}

	public String getUsername() {
		return userName;
	}

	public LevelParser getLevelParser() {
		return levelParser;
	}

	public GameConsole getGameConsole() {
		return myGameConsole;
	}

	public void toggleConsole() {
		myGameConsole.toggleVisible();
	}

}
