package vooga.games.cyberion.CyberionSprite;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;
//creates an extension of sprite whose position is based on elapsed time
//change in position gives the illusion the ship is moving upwards
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
