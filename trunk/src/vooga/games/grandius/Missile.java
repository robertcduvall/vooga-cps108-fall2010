package vooga.games.grandius;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

public class Missile extends Sprite{
	
	private static final int MAX_HITS = 3;
	private int hits;
	
	public Missile(BufferedImage image, double x, double y) {
		super(image,x,y);
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