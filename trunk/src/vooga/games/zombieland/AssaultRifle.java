package vooga.games.zombieland;

public class AssaultRifle extends Weapon {

	public AssaultRifle(Shooter s, int ammo) {
		super(s, ammo, 10);
	}

	protected void fireBullets() {
		makeBullet(getOrientation());
	}

}
