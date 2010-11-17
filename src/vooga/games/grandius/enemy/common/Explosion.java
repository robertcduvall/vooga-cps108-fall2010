package vooga.games.grandius.enemy.common;

import java.awt.image.BufferedImage;

import vooga.engine.core.Sprite;


@SuppressWarnings("serial")
public class Explosion extends Sprite {
	
	public Explosion(BufferedImage[] images, double x, double y) {
		super(images);
		this.setLocation(x, y);
	}
	
}
