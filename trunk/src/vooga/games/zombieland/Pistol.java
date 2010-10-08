package vooga.games.zombieland;
/**
 * 
 * @author Yang
 * The default type of weapon.  
 */
public class Pistol extends Weapon {
	public Pistol(Shooter shooter, int ammo) {
		super(shooter, ammo, 5);
	}

	public void fireBullets() {
		makeBullet(getOrientation());
	}
}
