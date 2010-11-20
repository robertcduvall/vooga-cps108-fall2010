package vooga.games.grandius;

import java.awt.image.BufferedImage;

import vooga.engine.core.BetterSprite;

public class Explosion extends BetterSprite {

	public Explosion() {
		
	}
	
	public Explosion(BufferedImage[] anim, double x, double y) {
		super(anim);
		this.setX(x);
		this.setY(y);
	}
	
}
