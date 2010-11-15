package vooga.engine.core;

import java.awt.Graphics2D;
import java.io.IOException;

import vooga.engine.factory.LevelParser;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameStateManager;

/**
 * Extension of the Golden T game to automate some Vooga API functionality.
 * 
 * Currently differs from Golden T game because initResources() automatically
 * loads all resources associated with the resources.xml file (or the game.properties file)
 * in your game's resources package.
 * 
 * It also now has a built in GameStateManager which is automatically initialized
 * in initResources. The initialization behavior can be changed by overriding 
 * initGameStates. The update and render methods call update and render for this 
 * GameStateManager.
 * 
 * @author rcd, Daniel Koverman, John Kline
 */
public class Game extends com.golden.gamedev.Game {

	private GameStateManager stateManager;

	@Override
	public void initResources() {
		// Game teams should now convert their old imagelist.txt, soundlist.txt, etc. files into the
		// new and improved resources.xml file format by following the example code in 
		// vooga.examples.resource.resourcesxmlexample
		Resources.initialize(this, getResourcePath());
		try {
			Resources.loadResourcesXMLFile("resources.xml");
		} catch (IOException e) {
			System.out.println("Failed to load resources.xml");
		}
		initGameStates();
		initLevel(1);
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
	 * Initializes the GameStateManager. This should be 
	 * overridden by subclasses which should call super() and 
	 * then setup/add all necessary GameStates.
	 */
	public void initGameStates() {
		stateManager = new GameStateManager();
	}
	
	/**
	 * Initializes the level specified by the given int index. The XML file associated with the level
	 * is located at a filepath specified by Resources.getString("Level" + index)
	 * For example, if the level's XML file is called blueLevel.xml, the stringMap in
	 * Resources contains the String -> String entry: "Level" + index -> "blueLevel.xml" 
	 */
	public VoogaPlayField initLevel(int index) {
		LevelParser levelParser = new LevelParser();
		System.out.println(getResourcePath());
		VoogaPlayField vpf = levelParser.getPlayfield(getResourcePath() + Resources.getString("Level" + index), this);
		return vpf;
	}
	
	public GameStateManager getGameStateManager(){
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
		String gamePath = getClass().getPackage().toString();
		String defaultPath = "src/" + gamePath.substring(8, gamePath.length())
				+ "/resources/";
		return defaultPath.replace('.', '/');
	}
	
	
}
