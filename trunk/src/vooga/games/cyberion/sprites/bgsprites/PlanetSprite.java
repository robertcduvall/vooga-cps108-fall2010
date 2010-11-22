package vooga.games.cyberion.sprites.bgsprites;

import java.awt.image.BufferedImage;

import vooga.engine.resource.Resources;

import vooga.engine.core.BetterSprite;

//creates an extension of sprite whose position is based on elapsed time
//change in position gives the illusion the ship is moving upwards
public class PlanetSprite extends BetterSprite {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	public PlanetSprite() {
		super(Resources.getImage("planetImage"));
	}

	public PlanetSprite(BufferedImage image, double x, double y) {
		super(image, x, y);
	}

	public void update(long elapsedTime) {
		setY(getY() + getVerticalSpeed() * elapsedTime);
	}
}
