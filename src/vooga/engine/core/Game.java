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
		// Game teams should convert their old imagelist.txt, soundlist.txt, etc. files into the
		// new and improved resources.xml file format by following the example code in 
		// vooga.examples.resources
		// Then, the second try statement here can be removed.
		Resources.initialize(this, getResourcePath());
		try {
			Resources.loadResourcesXMLFile("resources.xml");
		} catch (IOException e) {
			System.out.println("Failed to load resources.xml");
		}
		try {
			Resources.loadPropertiesFile("game.properties");
		} catch (IOException e) {
			System.out.println("Failed to load resources/game.properties");
		}
		initGameStates();
		initFirstLevel();
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
	 * Initializes the game state manager. This should be 
	 * overridden by subclasses which should call super and 
	 * then setup/add all game states.
	 */
	public void initGameStates() {
		stateManager = new GameStateManager();
	}
	
	public void initFirstLevel() {
		LevelParser levelParser = new LevelParser();
		System.out.println(getResourcePath());
		VoogaPlayField vpf = levelParser.getPlayfield(getResourcePath() + "level1.xml", this);
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
