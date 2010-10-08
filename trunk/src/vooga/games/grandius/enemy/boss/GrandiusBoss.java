package vooga.games.grandius.enemy.boss;

import java.awt.image.BufferedImage;
import java.util.List;

import com.golden.gamedev.object.AnimatedSprite;

/**
 * A GradiusBoss is the final enemy in a Gradius Level. It is composed of many
 * BossParts, which must be individually destroyed before the boss can be 
 * killed.
 * @author jtk11
 *
 */
@SuppressWarnings("serial")
public abstract class GrandiusBoss extends AnimatedSprite {
	
	public static final int NOT_MOVING = 0;
	public static final int MOVING_N = 1;
	public static final int MOVING_S = 2;
	
	private int myDirection;
	private List<BossPart> myParts;
	
	public GrandiusBoss(BufferedImage[] images, double x, double y, 
				       int direction, List<BossPart> parts) {
		this.myDirection = direction;
		this.myParts = parts;
	}
	
	public void setDirection(int newDirection) {
		this.myDirection = newDirection;
	}
	
	public boolean isDead() {
		return myParts.isEmpty();
	}
	
	public void setParts(List<BossPart> parts) {
		this.myParts = parts;
	}
	
	public void addPart(BossPart part) {
		this.myParts.add(part);
	}
	
	public void removePart(BossPart part) {
		this.myParts.remove(part);
	}
	
	
	@Override
	public void update(long elapsedTime) {
		if (this.myDirection == NOT_MOVING) {
			this.setHorizontalSpeed(0);
			this.setVerticalSpeed(0);
		} else if (this.myDirection == MOVING_N) {
			this.setHorizontalSpeed(0);
			this.setVerticalSpeed(-2);
		} else if (this.myDirection == MOVING_S) {
			this.setHorizontalSpeed(0);
			this.setVerticalSpeed(2);
		}
		this.updateMovement(elapsedTime);
	}
	
	
}
