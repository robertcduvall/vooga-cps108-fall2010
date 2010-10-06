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
public class Zipster extends AnimatedSprite {
	
	public static final int MOVING_W = 0;
	public static final int MOVING_NW = 1;
	public static final int MOVING_SW = 2;
	
	private int myDirection;
	
	public Zipster(BufferedImage[] images, double x, double y) {
		super(images, x, y);
		myDirection = MOVING_W;
	}
	
	public int getDirection() {
		return this.myDirection;
	}
	
	public void setDirection(int newDirection) {
		this.myDirection = newDirection;
	}
	
	public void update(long elapsedTime) {
		if (this.myDirection == MOVING_W) {
			this.setHorizontalSpeed(-5);
			this.setVerticalSpeed(0);
		} else if (this.myDirection == MOVING_NW) {
			this.setHorizontalSpeed(-3);
			this.setVerticalSpeed(-4);
		} else if (this.myDirection == MOVING_SW) {
			this.setHorizontalSpeed(-3);
			this.setVerticalSpeed(4);
		}
		this.updateMovement(elapsedTime);
	}
	
	
	
	
	
}
