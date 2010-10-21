package vooga.engine.core;

import java.awt.*;
import java.io.IOException;

import vooga.engine.resource.*;

/**
 * Extension of the Golden T game to automate some Vooga API functionality.
 * 
 * Currently differs from Golden T game because initResources() automatically 
 * loads all resources associated with the game.properties file in your games 
 * resources folder.
 *
 * @author rcd, Daniel Koverman
 */
public class Game extends com.golden.gamedev.Game {

   @Override
public void initResources() {
      // initialize game packages
	   Resources.initialize(this, getResourcePath());
		try {
			Resources.loadPropertiesFile("game.properties");
		} catch (IOException e) {
			System.out.println("Failed to load resources/game.properties");
			e.printStackTrace();
			System.exit(1);
		}
   }

   @Override
public void update(long elapsedTime) {
      // call game packages before user gets to update
   }

   @Override
public void render(Graphics2D g) {
      // render the game to the screen
   }
   
   /**
	 * This seems like a ridiculous way to do this, but it works on at least Linux. 
	 * If someone has a better way of handling this, it's all yours.
	 * @return the packages forming the gap between the VOOGA directory and 
	 * the current game resources package
	 */
	private String getResourcePath(){
		String gamePath = getClass().getPackage().toString();
		String defaultPath = "src/" + gamePath.substring(8, gamePath.length())+"/resources/";
		return defaultPath.replace('.', '/');
	}
}
