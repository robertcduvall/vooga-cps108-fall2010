package vooga.games.grandius.enemy.common;
import java.awt.image.BufferedImage;

import vooga.engine.resource.GameClock;
import vooga.engine.resource.Resources;

import com.golden.gamedev.object.*;

/**
 * A Zipster is a common Gradius enemy that can fire a simple laser every second if the player 
 * moves within range.
 * @author jtk11
 *
 */
@SuppressWarnings("serial")
public class Zipster extends Enemy {
	
	private Timer fireTimer;
	private boolean reloaded;
	public static final int SCORE_VALUE = 100;
	private static final double laserSpeed = -0.15;
	private static final int CASH_VALUE = 10;
	
	
	public Zipster(BufferedImage[] images, double x, double y) {
		super(images, x, y);
		fireTimer = new Timer(1000);
		myScore = SCORE_VALUE;
		myCash = CASH_VALUE;
	}
	
	@Override
	public void update(long elapsedTime) {
		if (fireTimer.action(elapsedTime)) {
			reloaded = true;
		}
		this.updateMovement(elapsedTime);
	}
	
	public boolean willFire(Sprite playersprite) {
		return (playersprite.getY() > this.getY() && playersprite.getY() < this.getY()+this.getHeight()
				&& reloaded);
	}
	
	public Sprite fireLaser() {
		Sprite laser = new Sprite(Resources.getImage("ZipsterLaser"),this.getX()-this.getWidth(),this.getY());
		laser.setHorizontalSpeed(laserSpeed);
		fireTimer = new Timer(1000);
		reloaded = false;
		return laser;
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
