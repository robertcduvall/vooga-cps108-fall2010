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
 * Extension of the Golden T game to automate some Vooga API functionality.
 * 
 * Currently differs from Golden T game because initResources() automatically
 * loads all resources associated with the resources.xml file (or the
 * game.properties file) in your game's resources package.
 * 
 * It also now has a built in GameStateManager which is automatically
 * initialized in initResources. The initialization behavior can be changed by
 * overriding initGameStates. The update and render methods call update and
 * render for this GameStateManager.
 * 
 * @author rcd, Daniel Koverman, John Kline
 */
public class Game extends com.golden.gamedev.Game {

	private GameStateManager stateManager;
	private VoogaPlayField myCurrentLevel;
	private static final int INITIAL_LEVEL = 1;

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

	/**
	 * Returns the VoogaPlayField that is associated with the current level.
	 */
	public VoogaPlayField getCurrentLevel() {
		return myCurrentLevel;
	}

	/**
	 * Sets the VoogaPlayField to the desired level.
	 */
	public void setCurrentLevel(int level) {
		myCurrentLevel = initLevel(level);
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
	public VoogaPlayField initLevel(int index) {
		LevelParser levelParser = new LevelParser();
		System.out.println(getResourcePath());
		VoogaPlayField vpf;
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

	public GameStateManager getGameStateManager() {
		return stateManager;
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

	public static void main(String[] args) {
		Game g = new Game();
		/**
		 * The game width, height, and fullscreen option are stored in a file
		 * called config.properties under the resources package. These values
		 * are extracted before the game is launched.
		 */
		ResourceBundle rb = Resources.loadPreLaunchData(g.getResourcePath()
				+ "config.properties");
		int width = Integer.parseInt(rb.getString("GAME_WIDTH"));
		int height = Integer.parseInt(rb.getString("GAME_HEIGHT"));
		boolean fullScreen = (rb.getString("FULLSCREEN").equals("true")) ? true
				: false;
		//TODO this is a terrible solution
		String classname = rb.getString("CLASSNAME");
		try {
			Class<?> c=Class.forName(classname);
			g=(Game)c.newInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		GameLoader game = new GameLoader();
		game.setup(g, new Dimension(width, height), fullScreen);
		game.start();
	}
}
