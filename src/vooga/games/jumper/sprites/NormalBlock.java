package vooga.games.jumper.sprites;

import java.awt.Point;
import java.awt.image.BufferedImage;


public class NormalBlock extends BlockSprite{

	public NormalBlock(){
		
	}
	
	public NormalBlock(BufferedImage image, Point location, double velocityX,
			double velocityY) {
		super(image, location, velocityX, velocityY);
	}

	@Override
	public void handleCollision(DoodleSprite doodle) {
		System.out.println("shouldgoup");
		doodle.setVerticalSpeed(this.getVerticalSpeed()); //stand on block
	}

}
