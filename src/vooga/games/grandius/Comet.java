package vooga.games.grandius;

import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;

public class Comet extends BetterSprite {

	public Comet() {
		this(0,0);
	}
	
	public Comet(double x, double y) {
		super(Resources.getImage("cometImage"), x, y);
	}
}