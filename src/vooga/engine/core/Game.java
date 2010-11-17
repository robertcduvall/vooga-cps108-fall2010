package vooga.engine.core;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ResourceBundle;

import com.golden.gamedev.GameLoader;

import vooga.engine.factory.LevelParser;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameStateManager;

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
 * @author rcd, Daniel Koverman, John Kline, Yang Su, Kate Yang
 */
public class Game extends com.golden.gamedev.Game {

	protected GameStateManager stateManager;
	protected LevelParser levelParser;
	protected PlayField myCurrentLevel;
	private static final int INITIAL_LEVEL = 1;

	private static final int DEFAULT_WIDTH = 600;
	private static final int DEFAULT_HEIGHT = 400;
	private static final boolean DEFAULT_FULLSCREEN = false;

	@Override
	public void initResources() {
		// Game teams should now convert their old imagelist.txt, soundlist.txt,
		// etc. files into the
		// new and improved resources.xml file format by following the example
		// code in
		// vooga.examples.resource.resourcesxmlexample
		Resources.initialize(this, getResourcePath());
		try {
			Resources.loadResourcesXMLFile("resources.xml");
		} catch (IOException e) {
			System.out.println("Failed to load resources.xml");
		}
		initGameStates();
		setCurrentLevel(INITIAL_LEVEL);
	}

	@Override
	public void update(long elapsedTime) {
		stateManager.update(elapsedTime);
	}

	@Override
	public void render(Graphics2D g) {
		stateManager.render(g);
	}

	/**
	 * Returns the VoogaPlayField that is associated with the current level.
	 */
	public PlayField getCurrentLevel() {
		return myCurrentLevel;
	}

	/**
	 * Sets the VoogaPlayField to the desired level.
	 */
	public void setCurrentLevel(int level) {
		myCurrentLevel = initLevel(level);
	}

	/**
	 * Initializes the GameStateManager. This should be overridden by subclasses
	 * which should call super() and then setup/add all necessary GameStates.
	 */
	public void initGameStates() {
		stateManager = new GameStateManager();
	}

	/**
	 * Initializes the level specified by the given int index. The XML file
	 * associated with the level is located at a filepath specified by
	 * Resources.getString("Level" + index) For example, if the level's XML file
	 * is called blueLevel.xml, the stringMap in Resources contains the String
	 * -> String entry: "Level" + index -> "blueLevel.xml"
	 */
	public PlayField initLevel(int index) {
		levelParser = new LevelParser();
		PlayField vpf;
		try {
			vpf = levelParser.getPlayfield(
					getResourcePath() + Resources.getString("Level" + index),
					this);
			return vpf;
		} catch (Exception e) {
			// TODO display error messages like: throw new
			// Resources.FileNotFoundException;
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * get the GameStateManager for this game
	 * 
	 * @return GameStateManager object
	 */
	public GameStateManager getGameStateManager() {
		return stateManager;
	}

	/**
	 * get the LevelParser for this game
	 * 
	 * @return LevelParser object
	 */
	public LevelParser getLevelParser() {
		return levelParser;
	}

	/**
	 * This seems like a ridiculous way to do this, but it works on at least
	 * Linux. If someone has a better way of handling this, it's all yours.
	 * 
	 * @return the packages forming the gap between the VOOGA directory and the
	 *         current game resources package
	 */
	private String getResourcePath() {
		String gamePath = getClass().getPackage().getName();
		String defaultPath = "src/" + gamePath + "/resources/";
		return defaultPath.replace('.', '/');
	}

	/**
	 * Get the path for the config.properties to set up prelaunch
	 * configurations for the game
	 * 
	 * @return path for config.properties
	 */
	private String getConfigPath() {
		return getClass().getPackage().getName() + ".resources.config";
	}

	/**
	 * Launches the game using default or given settings. The game width,
	 * height, and fullscreen option are stored in a file called
	 * config.properties under the resources package. These values are extracted
	 * before the game is launched. Any subclass should call launch() in the
	 * main method
	 * 
	 * @param g
	 */
	public static void launch(Game g) {

		// Default settings
		int width = DEFAULT_WIDTH;
		int height = DEFAULT_HEIGHT;
		boolean fullScreen = DEFAULT_FULLSCREEN;

		try {
			ResourceBundle rb = Resources.loadPreLaunchData(g.getConfigPath());
			width = Integer.parseInt(rb.getString("GAME_WIDTH"));
			height = Integer.parseInt(rb.getString("GAME_HEIGHT"));
			fullScreen = (rb.getString("FULLSCREEN").equals("true")) ? true
					: false;
		} catch (Exception e) {
			// TODO Resource Exception
			e.printStackTrace();
		}

		GameLoader game = new GameLoader();
		game.setup(g, new Dimension(width, height), fullScreen);
		game.start();
	}
}
