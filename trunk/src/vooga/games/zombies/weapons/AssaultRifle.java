package vooga.games.zombies.weapons;

import vooga.engine.resource.Resources;
import vooga.games.zombies.Shooter;

/**
 * Same as a Pistol, with a lower firing delay, higher damage, and faster
 * bullets
 * 
 * @author Jimmy Mu, Aaron Choi, Yang Su
 * 
 */
public class AssaultRifle extends Weapon {

	public AssaultRifle(Shooter shooter, int ammo) {
		super(shooter, ammo);
		setDamage(Resources.getInt("assaultRifleDamage"));
		setBulletSpeed(Resources.getInt("assaultRifleSpeed"));
		setFiringDelay(Resources.getInt("assaultRifleDelay"));
	}

	protected void fireBullets() {
		makeBullet(getOrientation());
	}

}
