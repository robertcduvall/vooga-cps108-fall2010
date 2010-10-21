package vooga.games.zombieland.weapons;

import vooga.games.zombieland.Shooter;
import vooga.games.zombieland.ZombielandResources;

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
		setDamage(ZombielandResources.getInt("assaultRifleDamage"));
		setBulletSpeed(ZombielandResources.getInt("assaultRifleSpeed"));
		setFiringDelay(ZombielandResources.getInt("assaultRifleDelay"));
	}

	protected void fireBullets() {
		makeBullet(getOrientation());
	}

}
