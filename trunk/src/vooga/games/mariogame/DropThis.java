package vooga.games.mariogame;

import java.io.IOException;

import vooga.engine.core.Game;


import com.golden.gamedev.GameLoader;


/**
 * 
 * @author David Herzka, Cameron McCallie, Andrew Brown
 * 
 *         Our game takes after the popular Mario game series. This project was
 *         written and developed with the VOOGA game engine, in Duke
 *         University's Computer Science 108 class.
 * 
 */

public class DropThis extends Game {

	public static void main(String[] args) throws IOException {
		GameLoader gl = new GameLoader();
		DropThis game = new DropThis();
		
		//TODO: 		// Game teams should now convert their old imagelist.txt, soundlist.txt,
		// etc. files into the
		// new and improved resources.xml file format by following the example
		// code in
		// vooga.examples.resource.resourcesxmlexample
		
		
		//TODO: How do we format the config.properties file for width, height, etc?
		
		game.launch(game);
	}
	
	

	
	public void initResources() {
		super.initResources();

		//TODO: Figure out where to put this
//		GameFont menuFont = fontManager.getFont(getImages(
//				"resources/images/font.png", 20, 3),
//				" !            .,0123456789:   -? ABCDEFGHIJKLMNOPQRSTUVWXYZ ");


	}
	
	public void initGameStates(){
		super.initGameStates();
	}

	
}
