package vooga.examples.resource.worstgameever;

import java.awt.Dimension;
import java.io.IOException;

import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.AnimatedSprite;

import vooga.engine.core.Game;
import vooga.engine.core.Sprite;
import vooga.engine.resource.ResourcesBeta;

public class DropThis extends Game{

	@Override
	/**
	 * Loads the game.properties file in the resources file using 
	 * ResourcesBeta. That file lists commas seperated value files 
	 * which can contain images, sounds, strings, integers, doubles, or 
	 * another file that lists other files. The images, sounds, strings, 
	 * etc are automatically loaded and can be accessed anywhere using
	 * the ResourcesBeta class.
	 */
	public void initResources() {
		ResourcesBeta.initialize(this, getResourcePath());
		try {
			ResourcesBeta.loadPropertiesFile("game.properties");
		} catch (IOException e) {
			System.out.println("Failed to load resources/game.properties");
			e.printStackTrace();
			System.exit(1);
		}
		
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
		System.out.println(ResourcesBeta.getString("helloMessage"));
		Sprite oneImage = new Sprite(ResourcesBeta.getImage("duvall"), 0, 0);
		AnimatedSprite fourImage = new AnimatedSprite(ResourcesBeta.getAnimation("downAnim"),0,0);
		
	}
	
	/**
	 * This seems like a ridiculous way to do this, but it works on at least Linux. 
	 * If someone has a better way of handling this, it's all yours.
	 * @return 
	 */
	private String getResourcePath(){
		String gamePath = getClass().getPackage().toString();
		String defaultPath = "src/" + gamePath.substring(8, gamePath.length())+"/resources/";
		return defaultPath.replace('.', '/');
	}
	
	
}
