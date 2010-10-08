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
		setDamage(10);
		setFiringDelay(8);
		setBulletSpeed(5);
	}

	protected void fireBullets() {
		makeBullet(getOrientation());
	}

}
