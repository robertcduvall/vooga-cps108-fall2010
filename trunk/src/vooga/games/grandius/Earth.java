package vooga.games.grandius;

import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;

public class Earth extends BetterSprite {

	public Earth() {
		this(0,0);
	}
	
	public Earth(double x, double y) {
		super(Resources.getImage("earthImage"), x, y);
	}
}