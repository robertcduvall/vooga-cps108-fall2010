package vooga.games.zombieland;

/**
 * The default weapon. Low damage, medium firing delay, and low bullet speed.
 * 
 * @author Jimmy Mu, Aaron Choi, Yang Su
 */
public class Pistol extends Weapon {
	public Pistol(Shooter shooter, int ammo) {
		super(shooter, ammo);
		setDamage(getInt("pistolDamage"));
		setBulletSpeed(getInt("pistolSpeed"));
		setFiringDelay(getInt("pistolDelay"));
	}

	public void fireBullets() {
		makeBullet(getOrientation());
	}
}
