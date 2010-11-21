package vooga.games.grandius;

import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;

@SuppressWarnings("serial")
public class Background extends BetterSprite {

	public Background() {
		this.setImage(Resources.getImage("backgroundImage"));
	}
	
	public Background(double x, double y) {
		super(Resources.getImage("backgroundImage"),x,y);
	}
	
}
