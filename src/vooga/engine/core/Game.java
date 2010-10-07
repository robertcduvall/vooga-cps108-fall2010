package vooga.engine.core;

import java.awt.*;
import java.io.IOException;

import vooga.engine.resource.ResourceHandler;

/**
 * Placeholder until we decide whose Sprite to use.
 *
 * @author rcd
 */
public class Game extends com.golden.gamedev.Game {

   @Override
public void initResources() {
      // initialize game packages
	   try {
		   ResourceHandler.loadFile("resourcelist.txt");
	   } catch (IOException e) {
		   System.out.println("Images not loaded successfully");
		   e.printStackTrace();
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
}
