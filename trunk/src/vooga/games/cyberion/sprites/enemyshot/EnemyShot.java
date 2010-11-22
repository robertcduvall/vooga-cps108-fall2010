package vooga.games.cyberion.sprites.enemyshot;

import java.awt.image.BufferedImage;

import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;

/**
 * Super enemy shot class for various enemy shot to inherit
 * 
 */

public class EnemyShot extends BetterSprite {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EnemyShot() {
		super(Resources.getImage("enemyShot"));

	}

	public EnemyShot(BufferedImage image) {
		super(image);
	}

}
