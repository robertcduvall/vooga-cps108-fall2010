package vooga.games.digger.mod;

import vooga.engine.core.BetterSprite;
import vooga.games.digger.movers.Mover;

public class SuperMover implements Mover{
	
	private final long wavelength = 1000;

	private BetterSprite sprite;
	private long phase;


	@Override
	public void move(long elapsedTime) {
		phase += elapsedTime;
		sprite.forceY(sprite.getY()+Math.sin(phase));
		
	}

	@Override
	public void setSprite(BetterSprite sprite) {
		this.sprite = sprite;
	}
}
