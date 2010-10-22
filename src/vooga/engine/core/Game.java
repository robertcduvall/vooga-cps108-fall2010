package vooga.engine.core;

import java.awt.*;
import java.io.IOException;

import vooga.engine.resource.*;
import vooga.engine.state.GameStateManager;

/**
 * Extension of the Golden T game to automate some Vooga API functionality.
 * 
 * Currently differs from Golden T game because initResources() automatically
 * loads all resources associated with the game.properties file in your games
 * resources folder.
 * 
 * It also now has a built in GameStateManager which is automatically initialized
 * in initResources. The initialization behavior can be changed by overriding 
 * initGameStates. The update and render methods call update and render for this 
 * game state manager.
 * 
 * @author rcd, Daniel Koverman
 */
public class Game extends com.golden.gamedev.Game {

	private GameStateManager stateManager;

	@Override
	public void initResources() {
		// initialize all resources stemming from resources/game.properties
		Resources.initialize(this, getResourcePath());
		try {
			Resources.loadPropertiesFile("game.properties");
		} catch (IOException e) {
			System.out.println("Failed to load resources/game.properties");
			e.printStackTrace();
			System.exit(1);
		}

		initGameStates();
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
