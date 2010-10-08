package vooga.games.zombieland;

public class Pistol extends Weapon {

	public Pistol(Shooter shooter, int ammo) {
		super(shooter, ammo);
	}

	public void fireBullets() {
		makeBullet(getOrientation());
	}

}
