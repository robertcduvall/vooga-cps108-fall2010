package vooga.games.grandius.enemy.boss.reacher;

import java.awt.image.BufferedImage;
import java.util.List;

import vooga.engine.core.Sprite;
import vooga.engine.resource.Resources;
import vooga.games.grandius.enemy.boss.GrandiusBoss;

import com.golden.gamedev.object.Timer;

/**
 * Reacher is the boss of the first Grandius level. Its two arms can fire large beams,
 * and its central turret fires a red ray.
 * @author jtk11
 *
 */
@SuppressWarnings("serial")
public class Reacher extends GrandiusBoss {

	private Timer fireTopBeamTimer;
	private Timer fireBottomBeamTimer;
	private Timer fireRedRayTimer;
	private boolean topBeamReloaded;
	private boolean bottomBeamReloaded;
	private boolean redRayReloaded;
	
	private static final double REACHER_BEAM_SPEED = 0.05;
	private static final double REACHER_REDRAY_SPEED = 0.10;
	private static final double REACHER_SPEED = 0.005;
	private static final int REACHER_BEAM_RELOAD_TIME = 4000;
	private static final int REACHER_REDRAY_RELOAD_TIME = 6000;
	private static final int SCORE_VALUE = 1000;
	private static final int CASH_VALUE = 100;

	public Reacher(BufferedImage[] images, int[] breakpoints, double x, double y, int health, List<Sprite> parts) {
		super(images, breakpoints, x, y, health, parts);
		fireTopBeamTimer = new Timer(4000);
		fireBottomBeamTimer = new Timer(4000);
		fireRedRayTimer = new Timer(6000);
		this.setScore(SCORE_VALUE);
		this.setCash(CASH_VALUE);
	}

	@Override
	public void update(long elapsedTime) {
		if (fireTopBeamTimer.action(elapsedTime)) {
			topBeamReloaded = true;
		}
		if (fireBottomBeamTimer.action(elapsedTime)) {
			bottomBeamReloaded = true;
		}
		if (fireRedRayTimer.action(elapsedTime)) {
			redRayReloaded = true;
		}
		this.updateMovement(elapsedTime);
	}

	public boolean topBeamWillFire(Sprite playersprite) {
		return (playersprite.getY() > this.getY() && playersprite.getY() < this.getY()+this.getHeight()/4
				&& playersprite.getX() < this.getX() && topBeamReloaded && this.isActive());
	}
	
	public boolean bottomBeamWillFire(Sprite playersprite) {
		return (playersprite.getY() > this.getY()+3*this.getHeight()/4 && playersprite.getY() < this.getY()+this.getHeight()
				&& playersprite.getX() < this.getX() && bottomBeamReloaded && this.isActive());
	}
	
	public boolean redRayWillFire(Sprite playersprite) {
		return (playersprite.getY() > this.getY()+this.getHeight()/4 && playersprite.getY() < this.getY()+3*this.getHeight()/4
				&& playersprite.getX() < this.getX() && redRayReloaded && this.isActive());
	}

	public Sprite fireTopBeam() {
		Sprite beam = new Sprite(Resources.getImage("ReacherBeam"),this.getX(),this.getY()+this.getHeight()/7);
		beam.setHorizontalSpeed(-REACHER_BEAM_SPEED);
		fireTopBeamTimer = new Timer(REACHER_BEAM_RELOAD_TIME);
		topBeamReloaded = false;
		return beam;
	}

	public Sprite fireBottomBeam() {
		Sprite beam = new Sprite(Resources.getImage("ReacherBeam"),this.getX(),this.getY()+5*this.getHeight()/7);
		beam.setHorizontalSpeed(-REACHER_BEAM_SPEED);
		fireBottomBeamTimer = new Timer(REACHER_BEAM_RELOAD_TIME);
		bottomBeamReloaded = false;
		return beam;
	}

	public Sprite fireRedRay() {
		Sprite redray = new Sprite(Resources.getImage("ReacherRedRay"),this.getX()+this.getWidth()/4,this.getY()+3*this.getHeight()/7);
		redray.setHorizontalSpeed(-REACHER_REDRAY_SPEED);
		fireRedRayTimer = new Timer(REACHER_REDRAY_RELOAD_TIME);
		redRayReloaded = false;
		return redray;
	}

	/**
	 * Depletes the health of Reacher. Reacher's shield level is controlled by the number of 
	 * ReacherEyes still active.
	 * @param depleteAmount
	 * @return Has Reacher been destroyed?
	 */
	public boolean deplete(int depleteAmount) {
		if (!this.isVulnerable()) {
			return false;
		}
		//this.health -= depleteAmount;
		this.setHealth(getHealth()- depleteAmount);
		if (getHealth() <= 0) {
			return true;
		} else if (getHealth() >= breakpoints[0]) {
			//this.setImages(new BufferedImage[]{Resources.getAnimation("Reacher")[3]});
			this.setImage(Resources.getAnimation("Reacher")[3]);
		} else if (getHealth() >= breakpoints[1]) {
			//this.setImages(new BufferedImage[]{Resources.getAnimation("Reacher")[4]});
			this.setImage(Resources.getAnimation("Reacher")[4]);
		} else {
			//this.setImages(new BufferedImage[]{Resources.getAnimation("Reacher")[5]});
			this.setImage(Resources.getAnimation("Reacher")[5]);
		} 
		return false;
	}
	
	//TODO - these methods no longer needed. Use getScore()& getCash()from superclass.
//	/**
//	 * Returns the point value of this enemy.
//	 * @return
//	 */
//	public int getScoreValue()
//	{
//		return SCORE_VALUE;
//	}
//
//	/**
//	 * Returns the cash value of this enemy.
//	 * @return
//	 */
//	public int getCashValue()
//	{
//		return CASH_VALUE;
//	}
	
	public double getSpeed() {
		return REACHER_SPEED;
	}
}
