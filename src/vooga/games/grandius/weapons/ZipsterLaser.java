package vooga.games.grandius.weapons;

import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;

@SuppressWarnings("serial")
public class ZipsterLaser extends BetterSprite {

	public ZipsterLaser(double x, double y) {
		super(Resources.getImage("zipsterLaserImage"),x,y);
		this.setHorizontalSpeed(Resources.getDouble("zipsterLaserSpeed"));
	}
	
}
