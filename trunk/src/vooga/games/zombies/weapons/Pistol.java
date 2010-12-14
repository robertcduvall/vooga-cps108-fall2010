package vooga.games.zombies.weapons;

import vooga.games.zombies.Shooter;
import vooga.engine.resource.Resources;

/**
 * The default weapon. Low damage, medium firing delay, and low bullet speed.
 * 
 * @author Jimmy Mu, Aaron Choi, Yang Su
 */
public class Pistol extends Weapon {
	public Pistol(Shooter shooter, int ammo) {
		super(shooter, ammo);
		setDamage(Resources.getInt("pistolDamage"));
		setBulletSpeed(Resources.getInt("pistolSpeed"));
		setFiringDelay(Resources.getInt("pistolDelay"));
	}

	public void fireBullets() {
		makeBullet(getOrientation());
	}
}
