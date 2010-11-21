package vooga.games.grandius.sprites;

import java.awt.image.BufferedImage;

import vooga.engine.core.BetterSprite;

@SuppressWarnings("serial")
public class Explosion extends BetterSprite {

	public Explosion() {
		//TODO blank constructor needed?
	}
	
	public Explosion(BufferedImage[] anim, double x, double y) {
		super(anim);
		this.setX(x);
		this.setY(y);
	}
	
}
