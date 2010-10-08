package vooga.games.zombieland;

public abstract class Weapon {

	private Shooter player;
	private int ammoCount;
	private double damage;

	public Weapon(Shooter s, int ammo) {
		this(s, ammo, 0);
	}

	public Weapon(Shooter s, int ammo, double damage) {
		player = s;
		ammoCount = ammo;
		this.damage = damage;
	}

	/**
	 * Add ammo. Used for bonuses
	 * 
	 * @param ammo
	 *            additional ammo
	 */
	public void addAmmo(int ammo) {
		ammoCount += ammo;
	}

	/**
	 * Get the remaining ammo. Used for display
	 * 
	 * @return number of remaining ammo
	 */
	public int getAmmo() {
		return ammoCount;
	}

	/**
	 * Get the orientation of the player/weapon
	 * 
	 * @return orientation of the player/weapon
	 */
	public double getOrientation() {
		return player.getOrientation();
	}

	/**
	 * Get the weapon damage. Used for damage calculations in the game. Returns
	 * a random damage within 10% of the weapon damage
	 * 
	 * @return weapon damage
	 */
	public double getDamage() {
		return (1 + (Math.random() * 2 - 1) * 0.1) * damage;
	}

	/**
	 * Checks if there's ammo left. If so, fire weapon and subtract from
	 * ammoCount
	 */
	public void fire() {
		if (ammoCount > 0)
			fireBullets();
		ammoCount--;
	}

	/**
	 * Specifies how and how many bullets are created every time the weapon
	 * fires. To be overwritten in subclasses
	 */
	protected abstract void fireBullets();

	/**
	 * Creates a bullet whose orientation is specified by angle
	 * 
	 * @param angle
	 *            orientation of the bullet to be created
	 */
	protected void makeBullet(double angle) {
		player.addBulletToGame(new Bullet(player.getX(), player.getY(), angle),
				angle);
	}
}
