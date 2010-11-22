package vooga.games.grandius.sprites.weapons;

import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;

@SuppressWarnings("serial")
public class Missile extends BetterSprite{
	
	private static final int MAX_HITS = 3;
	private int hits;
	
	public Missile(double x, double y) {
		super(Resources.getImage("missileImage"),x,y);
		this.setHorizontalSpeed(Resources.getDouble("bulletSpeed"));
		hits=0;
	}

	public void incrementHits() {
		hits++;
	}

	public int getHits() {
		return hits;
	}
	
	public int hitsRemaining(){
		return (MAX_HITS - hits);
	}
	
}