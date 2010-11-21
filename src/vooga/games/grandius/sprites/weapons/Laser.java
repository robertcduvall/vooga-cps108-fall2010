package vooga.games.grandius.sprites.weapons;

import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;

@SuppressWarnings("serial")
public class Laser extends BetterSprite {

	public Laser(double x, double y) {
		super(Resources.getImage("laserImage"),x,y);
		this.setHorizontalSpeed(Resources.getDouble("laserSpeed"));
	}
	
}
