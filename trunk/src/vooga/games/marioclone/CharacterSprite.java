package vooga.games.marioclone;

import java.awt.image.BufferedImage;

import vooga.engine.overlay.Stat;
import vooga.engine.player.control.PlayerSprite;

import com.golden.gamedev.object.AnimatedSprite;

/*
 * @author Andrew Brown
 */

@SuppressWarnings("serial")
public class CharacterSprite extends PlayerSprite {

	private static final double GRAVITY = .0025;
	private double myGravityCoef = 1;
	public Integer myMaxHealth = 100;

	public CharacterSprite(String name, String stateName,
			BufferedImage[] right, BufferedImage[] left) {
		super(name, stateName, new AnimatedSprite(right));

		AnimatedSprite r = new AnimatedSprite(right);
		r.setAnimate(true);
		r.setLoopAnim(true);
		AnimatedSprite l = new AnimatedSprite(left);
		r.setAnimate(true);
		r.setLoopAnim(true);
		mapNameToSprite("Right", r);
		mapNameToSprite("Left", l);

		addStat("Health", new Stat<Integer>(getMaxHealth()));

		// active = true;
	}

	public Integer getMaxHealth() {
		return myMaxHealth;
	}
	
	protected void setMaxHealth(Integer health) {
		myMaxHealth = health;
	}

	@SuppressWarnings("unchecked")
	public void setHealth(Integer health) {
		((Stat<Integer>) getStat("Health")).setStat(health);

	}

	@SuppressWarnings("unchecked")
	public Integer getHealth() {
		return ((Stat<Integer>) getStat("Health")).getStat();
	}

	@Override
	public void update(long elapsedTime) {

		super.update(elapsedTime);
		setVerticalSpeed(getVerticalSpeed() + getGravityCoef() * GRAVITY
				* elapsedTime);

		((AnimatedSprite) getCurrentSprite())
				.setAnimate(!(getHorizontalSpeed() == 0));

		if (getHealth() <= 0) {
			setActive(false);
		}

	}

	protected void setGravityCoef(double gravityCoef) {
		this.myGravityCoef = gravityCoef;
	}

	protected double getGravityCoef() {
		return myGravityCoef;
	}

}
