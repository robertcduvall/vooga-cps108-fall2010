package vooga.games.grandius.enemy.common;
import java.awt.image.BufferedImage;

import vooga.engine.resource.ResourcesBeta;

import com.golden.gamedev.object.*;

/**
 * A Zipster is a common Gradius enemy that can fire a simple laser every second if the player 
 * moves within range.
 * @author jtk11
 */
@SuppressWarnings("serial")
public class Zipster extends Enemy {
	
	private Timer fireTimer;
	private boolean reloaded;
	private boolean blackHoleProximate;
	private int spin;
	private static final double ZIPSTER_LASER_SPEED = 0.15;
	private static final double ZIPSTER_SPEED = 0.015;
	private static final int ZIPSTER_LASER_RELOAD_TIME = 1000;
	public static final int SCORE_VALUE = 100;
	private static final int CASH_VALUE = 10;
	
	public Zipster(BufferedImage[] images, double x, double y) {
		super(images, x, y);
		fireTimer = new Timer(ZIPSTER_LASER_RELOAD_TIME);
		myScore = SCORE_VALUE;
		myCash = CASH_VALUE;
		spin = 0;
		blackHoleProximate = false;
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
				&& playersprite.getX() < this.getX() && reloaded && this.isActive() && spin==0);
	}
	
	/**
	 * Fires the Zipster's laser weapon.
	 * @return The laser sprite to add to the game field.
	 */
	public Sprite fireLaser() {
		Sprite laser = new Sprite(ResourcesBeta.getImage("ZipsterLaser"),this.getX()-this.getWidth(),this.getY());
		laser.setHorizontalSpeed(-ZIPSTER_LASER_SPEED);
		fireTimer = new Timer(ZIPSTER_LASER_RELOAD_TIME);
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
	
	public double getSpeed() {
		return ZIPSTER_SPEED;
	}
	
	public void spinZipster() {
		//this.setImages(new BufferedImage[]{Resources.getAnimation("SpinningZipster")[spin]});
		setProximateToBlackHole(true);
		if (spin == 7)
			spin = 0; 
		else 
			spin++;
	}
	
	public void setSpin(int newSpin) {
		spin = newSpin;
	}

	public int getSpin() {
		return spin;
	}
	
	public boolean isProximateToBlackHole() {
		return blackHoleProximate;
	}

	public void setProximateToBlackHole(boolean newProximity) {
		blackHoleProximate = newProximity;
	}
}
