package vooga.games.cyberion.sprites.playershot;

import java.awt.image.BufferedImage;

import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;

/**
 * Super PlayerShot class for various player shots to inherit
 * 
 * 
 * 
 */

public class PlayerShot extends BetterSprite {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PlayerShot() {
		super(Resources.getImage("playerShot"));

	}

	public PlayerShot(BufferedImage image) {
		super(image);
	}

}
