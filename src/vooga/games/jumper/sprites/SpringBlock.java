package vooga.games.jumper.sprites;

import java.awt.Point;
import java.awt.image.BufferedImage;


public class SpringBlock extends BlockSprite{

	private final double SPRING_VELOCITY_MULTIPLIER = 5.0;
	private final static double SPRING_BLOCK_XVELOCITY = 0;
	private final static double SPRING_BLOCK_YVELOCITY = -2.0;
	
	public SpringBlock(BufferedImage image, Point location) {
		super(image, location, SPRING_BLOCK_XVELOCITY, SPRING_BLOCK_YVELOCITY);
	}

	public void handleCollision(DoodleSprite doodle) {
		doodle.setVerticalSpeed(this.getVerticalSpeed()*SPRING_VELOCITY_MULTIPLIER); //bounce
	}	
}
