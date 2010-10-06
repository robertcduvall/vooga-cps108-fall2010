package CyberionSprite;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

public class StarSprite extends Sprite {

	public StarSprite(BufferedImage image, double x, double y) {
		super(image, x, y);
	}

	public void update(long elapsedTime) {
		setY(getY() + getVerticalSpeed() * elapsedTime);
		if (getY() > 480) {
			setY(0);
		}
	}
}
