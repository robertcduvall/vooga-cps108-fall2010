package vooga.games.grandius.enemy.common;
import java.awt.image.BufferedImage;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.sprite.VolatileSprite;

/**
 * A Boomer is a common Gradius enemy that may only move west, but can explode.
 * @author jtk11
 *
 */
@SuppressWarnings("serial")
public class Boomer extends VolatileSprite {

	public static final int BOOMER_BLUE = 0;
	public static final int BOOMER_YELLOW = 1;
	public static final int BOOMER_RED = 2;
	public static final int SCORE_VALUE = 50;
	
	private int myColor;
	
	public Boomer(BufferedImage[] images, double x, double y) {
		super(images, x, y);
		this.myColor = BOOMER_BLUE;
		this.setHorizontalSpeed(-4);
	}
	
	public int getColor() {
		return this.myColor;
	}
	
	public void trigger() {
		if (myColor == BOOMER_RED) {
			this.setActive(false);
		} else {
			this.myColor++;
		}
	}
	
	/**
	 * Returns the point value of this enemy.
	 * @return
	 */
	public int getScoreValue()
	{
		return SCORE_VALUE;
	}
	
	
}
