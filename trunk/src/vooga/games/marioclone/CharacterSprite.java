package vooga.games.marioclone;

import java.awt.image.BufferedImage;

import vooga.engine.overlay.Stat;
import vooga.engine.player.control.PlayerSprite;

import com.golden.gamedev.object.AnimatedSprite;

/*
 * @author Andrew Brown
 */

@SuppressWarnings("serial")
public abstract class CharacterSprite extends PlayerSprite {

	private static final double GRAVITY = .0025;
	private double myGravityCoef = 1;
//	private boolean active;

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
		
//		active = true;
	}

	public abstract Integer getMaxHealth();

	public void setHealth(Integer health) {
		((Stat<Integer>) getStat("Health")).setStat(health);
		
	}

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

		System.out.println(isActive());

		
		if (getHealth() <= 0) {
			System.out.println("set false");
			setActive(false);
		}
		
		System.out.println(isActive());
		/*
		 * double yVelocity = getVerticalSpeed(); double newYVelocity =
		 * yVelocity + gravity*elapsedTime; setVerticalSpeed(newYVelocity);
		 */

	}

	protected void setGravityCoef(double gravityCoef) {
		this.myGravityCoef = gravityCoef;
	}

	protected double getGravityCoef() {
		return myGravityCoef;
	}
	
//	@Override
//	public boolean isActive() {
//		return active;
//	}
//	
//	@Override
//	public void setActive(boolean b) {
//		active = b;
//	}

}
