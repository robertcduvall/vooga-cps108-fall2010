package vooga.games.grandius.planetoids;

import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;

@SuppressWarnings("serial")
public class Earth extends BetterSprite {

	public Earth() {
		this(0,0);
	}
	
	public Earth(double x, double y) {
		super(Resources.getImage("earthImage"), x, y);
	}
}