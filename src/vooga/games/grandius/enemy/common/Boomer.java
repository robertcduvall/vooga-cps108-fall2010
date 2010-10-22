package vooga.games.grandius.enemy.common;

import java.awt.image.BufferedImage;

/**
 * A Boomer is a common Gradius enemy that may only move west, but can explode.
 * @author jtk11
 *
 */
@SuppressWarnings("serial")
public class Boomer extends Enemy {

	public static final int BOOMER_BLUE = 0;
	public static final int BOOMER_YELLOW = 1;
	public static final int BOOMER_RED = 2;
	private static final int SCORE_VALUE = 100;
	private static final int CASH_VALUE = 2;
	
	private int color;
	
	public Boomer(BufferedImage[] images, double x, double y) {
		super(images, x, y);
		this.color = BOOMER_BLUE;
		this.setHorizontalSpeed(-0.04);
		scoreValue = SCORE_VALUE;
		cashValue = CASH_VALUE;
	}
	
	public int getColor() {
		return this.color;
	}
	
	public void trigger() {
		if (color == BOOMER_RED) {
			this.setActive(false);
		} else {
			this.color++;
		}
	}
	
	
}
