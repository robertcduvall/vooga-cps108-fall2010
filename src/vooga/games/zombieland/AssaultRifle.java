package vooga.games.zombieland;

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
		setDamage(getInt("assaultRifleDamage"));
		setBulletSpeed(getInt("assaultRifleSpeed"));
		setFiringDelay(getInt("assaultRifleDelay"));
	}

	protected void fireBullets() {
		makeBullet(getOrientation());
	}

}
