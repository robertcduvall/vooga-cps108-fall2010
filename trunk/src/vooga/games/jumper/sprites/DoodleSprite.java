package vooga.games.jumper.sprites;

import java.awt.Point;
import java.awt.image.BufferedImage;

import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;
import vooga.games.jumper.DropThis;

/**
 * This class represents the Doodle which is the main character of the game
 * @author Brian
 */

public class DoodleSprite extends BetterSprite {

	private double MAX_SPEED = Resources.getDouble("MAX_SPEED");

	/**
	 * Create new DoodleSprite
	 * @param image Sprite Image
	 * @param location Point representing starting sprite location
	 */
	public DoodleSprite(BufferedImage image, Point location) {
		super(image, location.x, location.y);  
	}

	public DoodleSprite(){}
	
	public void moveLeft(){	
		setHorizontalSpeed(-1* MAX_SPEED);
	}
	
	public void moveRight(){
		setHorizontalSpeed(MAX_SPEED);
	}
}