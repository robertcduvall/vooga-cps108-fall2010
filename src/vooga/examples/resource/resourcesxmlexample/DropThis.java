package vooga.examples.resource.resourcesxmlexample;

import java.awt.Dimension;

import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.AnimatedSprite;

import vooga.engine.core.Game;
import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;

public class DropThis extends Game{

	@Override
	/**
	 * Utilizes resources.xml XML file to initialize Resources.
	 * Note: running this class will not produce a running, functional Game, but the
	 * new infrastructure for loading resources is outlined here.
	 */
	public void initResources() {
		super.initResources(); //It really is as simple as that! core.Game now loads your resources for you, as long as you
							   //create a resources.xml file in a resources package within your game package.
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
		BetterSprite oneImage = new BetterSprite(Resources.getImage("duvall"), 0, 0);
		AnimatedSprite fourImage = new AnimatedSprite(Resources.getAnimation("downAnim"),0,0);
	}	
}
