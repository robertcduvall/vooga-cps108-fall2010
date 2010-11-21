package vooga.games.grandius;

import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;

@SuppressWarnings("serial")
public class Neptune extends BetterSprite {

	public Neptune() {
		this(0,0);
	}
	
	public Neptune(double x, double y) {
		super(Resources.getImage("neptuneImage"), x, y);
	}
}