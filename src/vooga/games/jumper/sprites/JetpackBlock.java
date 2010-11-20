package vooga.games.jumper.sprites;

import java.awt.Point;
import java.awt.image.BufferedImage;

import vooga.games.jumper.DropThis;

public class JetpackBlock extends BlockSprite{

	public JetpackBlock(BufferedImage image, Point location, double velocityX,
			double velocityY) {
		super(image, location, velocityX, velocityY);
	}

	@Override
	public void handleCollision(DoodleSprite doodle) {
		this.setActive(false);
		DropThis.setJetpackOn(true);

	}

}
