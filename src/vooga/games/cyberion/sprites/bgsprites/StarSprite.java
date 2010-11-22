package vooga.games.cyberion.sprites.bgsprites;

import java.awt.image.BufferedImage;

import vooga.engine.resource.Resources;

import vooga.engine.core.BetterSprite;

//creates an extension of sprite whose position is based on elapsed time
//change in position gives the illusion the ship is moving upwards
public class StarSprite extends BetterSprite {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StarSprite() {
		super(Resources.getImage("starImage"));
	}

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
