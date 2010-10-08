package vooga.games.zombieland;

/**
 * Weapon class. Sets up the basic functions of a weapon and allows for
 * customization in how a weapon produces bullets.
 * 
 * @author Jimmy Mu, Aaron Choi, Yang Su
 * 
 */
public abstract class Weapon {

	private Shooter player;
	private int ammoCount;
	private double damage;
	private double bulletSpeed;
	private int frameCount;
	private int firingDelay;

	public Weapon(Shooter shooter, int ammo) {
		player = shooter;
		ammoCount = ammo;
		frameCount = 0;
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
	 * Set weapon damage
	 * 
	 * @param weaponDamage
	 */
	public void setDamage(double weaponDamage) {
		damage = weaponDamage;
	}

	/**
	 * Set firing delay
	 * 
	 * @param delay
	 */
	public void setFiringDelay(int delay) {
		firingDelay = delay;
	}

	/**
	 * Set bullet speed
	 * 
	 * @param speed
	 */
	public void setBulletSpeed(double speed) {
		bulletSpeed = speed;
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
		if (ammoCount > 0) {
			if (frameCount < firingDelay) {
				frameCount++;
			} else {
				frameCount = 0;
				fireBullets();
				ammoCount--;
			}
		}
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
		player.addBulletToGame(new Bullet(player.getX(), player.getY(), angle,
				damage, bulletSpeed), angle);
	}
}
