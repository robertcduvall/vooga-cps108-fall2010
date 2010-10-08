package vooga.games.grandius.enemy.common;
//import vooga.engine.core.*;
import java.awt.image.BufferedImage;

import com.golden.gamedev.object.*;

/**
 * A Zipster is a common Gradius enemy that moves either west, southwest, or
 * northwest, depending on the y position of the player.
 * @author jtk11
 *
 */
@SuppressWarnings("serial")
public class Zipster extends Enemy {
	
	public static final int MOVING_W = 0;
	public static final int MOVING_NW = 1;
	public static final int MOVING_SW = 2;
	private static final int SCORE_VALUE = 100;
	private static final int CASH_VALUE = 10;
	
	private int myDirection;
	
	public Zipster(BufferedImage[] images, double x, double y) {
		super(images, x, y);
		myDirection = MOVING_W;
		myScore = SCORE_VALUE;
		myCash = CASH_VALUE;
	}
	
	public int getDirection() {
		return this.myDirection;
	}
	
	public void setDirection(int newDirection) {
		this.myDirection = newDirection;
	}
	
	@Override
	public void update(long elapsedTime) {
		if (this.myDirection == MOVING_W) {
			this.setHorizontalSpeed(-0.05);
			this.setVerticalSpeed(0);
		} else if (this.myDirection == MOVING_NW) {
			this.setHorizontalSpeed(-0.03);
			this.setVerticalSpeed(-0.04);
		} else if (this.myDirection == MOVING_SW) {
			this.setHorizontalSpeed(-0.03);
			this.setVerticalSpeed(0.04);
		}
		this.updateMovement(elapsedTime);
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
