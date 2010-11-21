package vooga.games.grandius.enemy.common;

import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Timer;


/**
 * A Zipster is a common Grandius enemy that can fire a simple laser every second if the player 
 * moves within range.
 * @author jtk11
 */
@SuppressWarnings("serial")
public class Zipster extends Enemy {
	
	private Timer fireTimer;
	private boolean reloaded;
	private boolean blackHoleProximate;
	private int spin;
	private static final double ZIPSTER_LASER_SPEED = Resources.getDouble("zipsterLaserSpeed");
	private static final int ZIPSTER_LASER_RELOAD_TIME = 1000;
	private static final int SCORE_VALUE = 25;
	private static final int CASH_VALUE = 1;
	
	public Zipster() {
		this(0,0);
	}
	
	public Zipster(double x, double y) {
		super(Resources.getAnimation("zipsterAnimation"), x, y);
		((AnimatedSprite)this.getCurrentSprite()).setAnimate(true);
		((AnimatedSprite)this.getCurrentSprite()).setLoopAnim(true);
		this.setScore(SCORE_VALUE);
		this.setCash(CASH_VALUE);
		fireTimer = new Timer(ZIPSTER_LASER_RELOAD_TIME);
		//blackHoleProximate = false;
		//spin = 0;
	}
	
	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		if (fireTimer.action(elapsedTime)) {
			reloaded = true;
		}
	}
	
	/**
	 * Checks to see if this Zipster can fire on the Player, depending on its location 
	 * and whether or not the Zipster has reloaded.
	 */
	public boolean willFire(BetterSprite playersprite) {
		return (playersprite.getY() > this.getY() && playersprite.getY() < this.getY()+this.getHeight()
				&& playersprite.getX() < this.getX() && reloaded && this.isActive() && spin==0);
	}
	
	/**
	 * Fires the Zipster's laser weapon.
	 * @return The laser sprite to add to the enemyProjectile SpriteGroup.
	 */
	public BetterSprite fireLaser() {
		BetterSprite laser = new BetterSprite(Resources.getImage("zipsterLaserImage"),this.getX()-this.getWidth(),this.getY());
		laser.setHorizontalSpeed(ZIPSTER_LASER_SPEED);
		fireTimer = new Timer(ZIPSTER_LASER_RELOAD_TIME);
		reloaded = false;
		return laser;
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
