package vooga.examples.resource.resourcesxmlexample;

import java.awt.Dimension;
import java.io.IOException;

import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.AnimatedSprite;

import vooga.engine.core.Game;
import vooga.engine.core.Sprite;
import vooga.engine.resource.Resources;

public class DropThis extends Game{

	@Override
	/**
	 * Utilizes resources.xml XML file to initialize Resources.
	 */
	public void initResources() {
		super.initResources();
		
		runTests();
	}
	
	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new DropThis(), new Dimension(300, 300), false);
		game.start();
	}
		
	/**
	 * Checks to see if it can retrieve the String and create a Sprite 
	 * and animated Sprite from the stored images and animations. It does 
	 * without crashing, though the Sprites have not yet been tested.
	 */
	private static void runTests(){
		System.out.println(Resources.getString("helloMessage"));
		Sprite oneImage = new Sprite(Resources.getImage("duvall"), 0, 0);
		AnimatedSprite fourImage = new AnimatedSprite(Resources.getAnimation("downAnim"),0,0);
		
	}
	
	
}
