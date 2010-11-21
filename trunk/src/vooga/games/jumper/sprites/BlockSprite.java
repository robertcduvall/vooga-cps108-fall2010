package vooga.games.jumper.sprites;

import java.awt.Point;
import java.awt.image.BufferedImage;

import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;
import vooga.games.jumper.DropThis;


/**
 * This class represents the Blocks which push the Doodle
 * @author Brian
 *
 */
public abstract class BlockSprite extends BetterSprite {
	
	private String myBlockType;


	

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
	
	public void setBlockType(String blockType) {
		myBlockType = blockType;
	}
	
	public String getBlockType() {
		return myBlockType;
	}

	/**
	 * Overriding Sprite update method to include bouncing off wall
	 * @param elapsedTime long time elapsed from last update
	 */
	public void update(long elapsedTime){
		move(getHorizontalSpeed(), getVerticalSpeed());
//		bounceOnWall();
	}

	/**
	 * Bounce BlockSprite off wall if it touches the side
	 */
	public void bounceOnWall(){
		if (this.getX() + this.getWidth() > Resources.getInt("gameWidth") || this.getX() < 0){
			this.setHorizontalSpeed(this.getHorizontalSpeed() * -1);
		}
	}	
	
	public abstract void handleCollision(DoodleSprite doodle);
}