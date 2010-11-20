package vooga.games.grandius.weapons;

import vooga.engine.resource.Resources;

import com.golden.gamedev.object.Sprite;

@SuppressWarnings("serial")
public class Missile extends Sprite{
	
	private static final int MAX_HITS = 3;
	private int hits;
	
	public Missile(double x, double y) {
		super(Resources.getImage("missileImage"),x,y);
		this.setHorizontalSpeed(Resources.getDouble("projectileSpeed"));
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