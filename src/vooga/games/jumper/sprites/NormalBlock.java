package vooga.games.jumper.sprites;

import java.awt.Point;
import java.awt.image.BufferedImage;

import vooga.engine.resource.Resources;


public class NormalBlock extends BlockSprite{

	private static final double DOODLE_HEIGHT = Resources.getDouble("DOODLE_HEIGHT");

	public NormalBlock(){
		
	}
	
	public NormalBlock(BufferedImage image, Point location, double velocityX,
			double velocityY) {
		super(image, location, velocityX, velocityY);
	}

	@Override
	public void handleCollision(DoodleSprite doodle) {
		doodle.setY(getY() - DOODLE_HEIGHT);
//		doodle.setVerticalSpeed(this.getVerticalSpeed()); //stand on block
	}

}
