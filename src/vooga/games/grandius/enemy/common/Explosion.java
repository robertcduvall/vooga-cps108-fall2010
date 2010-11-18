package vooga.games.grandius.enemy.common;

import java.awt.image.BufferedImage;

import vooga.engine.core.BetterSprite;


@SuppressWarnings("serial")
public class Explosion extends BetterSprite {
	
	public Explosion(BufferedImage[] images, double x, double y) {
		super(images);
		this.setLocation(x, y);
	}
	
}
