package vooga.games.zombies.weapons;

import vooga.games.zombies.Shooter;
import vooga.engine.resource.Resources;

/**
 * Shogun. Generates multiple bullets per round. High damage, high firing delay,
 * and high bullet speed
 * 
 * @author Jimmy Mu, Aaron Choi, Yang Su
 * 
 */
public class ShotGun extends Weapon {

	// The angular spread of the shotgun bullets
	private static double angleRange;
	// The number of shots fired in each round
	private static int numberOfShots;

	public ShotGun(Shooter shooter, int ammo) {
		super(shooter, ammo);
		setDamage(Resources.getInt("shotgunDamage"));
		setBulletSpeed(Resources.getInt("shotgunSpeed"));
		setFiringDelay(Resources.getInt("shotgunDelay"));
		angleRange=Resources.getInt("shotgunAngleRange");
		numberOfShots=Resources.getInt("shotgunNumberOfShots");
	}

	/**
	 * Uses the angleRange and numberOfShots variables to generate bullets that
	 * are evenly distributed
	 */
	protected void fireBullets() {
		for (int i = -numberOfShots / 2; i <= numberOfShots / 2; i++) {
			makeBullet(getOrientation() + angleRange / (numberOfShots) * i);
		}
	}

}
