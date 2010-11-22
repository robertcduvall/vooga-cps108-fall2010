package vooga.games.jumper.sprites;

import java.awt.Point;
import java.awt.image.BufferedImage;

import vooga.engine.core.BetterSprite;


/**
 * This class represents the Blocks which push the Doodle
 * @author Brian
 *
 */
public abstract class BlockSprite extends BetterSprite {
	
	/**
	 * Create new BlockSprite
	 * @param image Sprite Image
	 * @param location Point representing starting sprite location
	 * @param velocityX double representing Sprite horizontal velocity
	 * @param velocityY double representing Sprite vertical velocity
	 */
	public BlockSprite(BufferedImage image, Point location, double velocityX, double velocityY) {
		super(image, location.x, location.y);
		setSpeed(velocityX, velocityY);
		
	}

	public BlockSprite(){
		
	}
	
	
	public abstract void handleCollision(DoodleSprite doodle);
}