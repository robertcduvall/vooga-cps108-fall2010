package vooga.games.doodlejump.platforms;

import java.awt.image.BufferedImage;
import com.golden.gamedev.object.Sprite;

/**
 * The GrayPlatform class extends Sprite and defines a GrayPlatform
 * 
 * @author Adam Cue, Marcus Molchany, Nick Straub
 * 
 */
public class GrayPlatform extends Sprite {
	private int changeDirection;

	public GrayPlatform(BufferedImage image, double x, double y) {
		super(image, x, y);
		changeDirection = 300;
		setVerticalSpeed(-0.1);
	}

	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		changeDirection--;
		if (changeDirection <= 0) {
			setVerticalSpeed(getVerticalSpeed() * -1);
			changeDirection = 300;
		}
	}
}
