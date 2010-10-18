package vooga.games.zombieland;

/**
 * Shogun. Generates multiple bullets per round. High damage, high firing delay,
 * and high bullet speed
 * 
 * @author Jimmy Mu, Aaron Choi, Yang Su
 * 
 */
public class ShotGun extends Weapon {

	// The angular spread of the shotgun bullets
	private static double angleRange = 20;
	// The number of shots fired in each round
	private static int numberOfShots = 3;

	public ShotGun(Shooter shooter, int ammo) {
		super(shooter, ammo, 25, 7, 20);
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
