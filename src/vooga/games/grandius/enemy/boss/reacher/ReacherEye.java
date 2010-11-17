package vooga.games.grandius.enemy.boss.reacher;

import java.awt.image.BufferedImage;

import vooga.engine.core.Sprite;
import vooga.engine.resource.Resources;
import vooga.games.grandius.enemy.boss.BossPart;

import com.golden.gamedev.object.Timer;

/**
 * The ReacherEye is a shielded station that must be destroyed to lower 
 * Reacher's shields.
 * @author jtk11
 */
@SuppressWarnings("serial")
public class ReacherEye extends BossPart{

	private Timer beamTimer;
	private boolean beamReloaded;
	
	private static final double REACHER_EYE_BEAM_SPEED = 0.10;
	private static final double REACHER_EYE_SPEED = 0.01;
	
	private static final int SCORE_VALUE = 200;
	private static final int CASH_VALUE = 4;
	private static final int REACHER_EYE_BEAM_RELOAD_TIME = 3000;
	
	public ReacherEye(BufferedImage[] images, int[] breakpoints, double x,
			double y, int health, int shields) {
		super(images, breakpoints, x, y, health, shields);
		this.setScore(SCORE_VALUE);
		this.setCash(CASH_VALUE);
		beamTimer = new Timer(REACHER_EYE_BEAM_RELOAD_TIME);
	}
	
	@Override
	/**
	 * Updates the ReacherEye by reloading it if enough time has passed, and
	 * updating its movement.
	 */
	public void update(long elapsedTime) {
		if (beamTimer.action(elapsedTime)) {
			beamReloaded = true;
		}
		this.updateMovement(elapsedTime);
	}
	
	/**
	 * Determines whether or not this ReacherEye will fire, depending on the position of
	 * itself and the player sprite that is passed into the method.
	 * @param playerSprite The player sprite to check the location of, relative to the ReacherEye.
	 * @return Whether or not the ReacherEye will fire.
	 */
	public boolean willFire(Sprite playerSprite) {
		return (playerSprite.getY() > this.getY() && playerSprite.getY() < this.getY()+this.getHeight()
				&& playerSprite.getX() < this.getX() && beamReloaded && this.isActive());
	}
	
	/**
	 * Fires the ReacherEye's beam weapon.
	 * @return The Beam sprite to add to the play field.
	 */
	public Sprite fireBeam() {
		Sprite beam = new Sprite(Resources.getImage("ReacherEyeBeam"),this.getX()-this.getWidth(),this.getY()+this.getHeight()/3);
		beam.setHorizontalSpeed(-REACHER_EYE_BEAM_SPEED);
		beamTimer = new Timer(REACHER_EYE_BEAM_RELOAD_TIME);
		beamReloaded = false;
		return beam;
	}
	
	/**
	 * Depletes the health or shields of the ReacherEye. Health will only be depleted if the shields have been depleted.
	 * @param depleteAmount The amount to deplete.
	 * @return If the ReacherEye is at health=0 (it is dead).
	 */
	public boolean deplete(int depleteAmount) {
		if (getShields() <= 0) {
			this.setHealth(getHealth()- depleteAmount);
			//this.health -= depleteAmount;
			if (getHealth() <= 0) {
				return true;
				
			} else if (getHealth() >= breakpoints[0]) {
//				this.setImages(new BufferedImage[]{images[1]});
				this.setImage(images[1]);
			} else if (getHealth() >= breakpoints[1]) {
//				this.setImages(new BufferedImage[]{images[2]});
				this.setImage(images[2]);
			} else {
//				this.setImages(new BufferedImage[]{images[3]});
				this.setImage(images[3]);
			} 
		} else {
//			this.shields -= depleteAmount;
			this.setShields(getShields()- depleteAmount);
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
	
	/**
	 * Returns the speed of the ReacherEye.
	 * @return The speed.
	 */
	public double getSpeed() {
		return REACHER_EYE_SPEED;
	}
}
