package vooga.games.grandius.enemy.common;
import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Timer;

import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;


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
	private static final int SCORE_VALUE = 25;
	private static final int CASH_VALUE = 1;
	
	public Zipster() {
		this(0,0);
	}
	
	public Zipster(double x, double y) {
		super(Resources.getAnimation("spinningZipsterAnimation"), x, y);
		this.setScore(SCORE_VALUE);
		this.setCash(CASH_VALUE);
		fireTimer = new Timer(ZIPSTER_LASER_RELOAD_TIME);
		blackHoleProximate = false;
		spin = 0;
	}
	
	@Override
	public void update(long elapsedTime) {
		if (fireTimer.action(elapsedTime)) {
			reloaded = true;
		}
		this.updateMovement(elapsedTime);
	}
	
	public boolean willFire(BetterSprite playersprite) {
		return (playersprite.getY() > this.getY() && playersprite.getY() < this.getY()+this.getHeight()
				&& playersprite.getX() < this.getX() && reloaded && this.isActive() && spin==0);
	}
	
	/**
	 * Fires the Zipster's laser weapon.
	 * @return The laser sprite to add to the game field.
	 */
	public BetterSprite fireLaser() {
		BetterSprite laser = new BetterSprite(Resources.getImage("zipsterLaserImage"),this.getX()-this.getWidth(),this.getY());
		laser.setHorizontalSpeed(-ZIPSTER_LASER_SPEED);
		fireTimer = new Timer(ZIPSTER_LASER_RELOAD_TIME);
		reloaded = false;
		return laser;
	}
	
	//TODO - This should no longer be needed. Use getScore()from superclass.
//	/**
//	 * Returns the point value of this enemy.
//	 * @return
//	 */
//	public int getScoreValue()
//	{
//		return SCORE_VALUE;
//	}
	
	public double getSpeed() {
		return ZIPSTER_SPEED;
	}
	
	public void spinZipster() {
		//this.setImages(new BufferedImage[]{Resources.getAnimation("spinningZipsterAnimation")[spin]});
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