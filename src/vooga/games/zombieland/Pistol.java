package vooga.games.zombieland;

/**
 * The default weapon. Low damage, medium firing delay, and low bullet speed.
 * 
 * @author Jimmy Mu, Aaron Choi, Yang Su
 */
public class Pistol extends Weapon {
	public Pistol(Shooter shooter, int ammo) {
		super(shooter, ammo);
		setDamage(5);
		setFiringDelay(15);
		setBulletSpeed(3);
	}

	public void fireBullets() {
		makeBullet(getOrientation());
	}
}
