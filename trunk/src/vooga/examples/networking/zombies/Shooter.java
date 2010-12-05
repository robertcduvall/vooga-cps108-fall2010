package vooga.examples.networking.zombies;

import com.golden.gamedev.object.*;

import vooga.engine.overlay.Stat;
import vooga.engine.resource.Resources;
import vooga.engine.core.BetterSprite;
import vooga.engine.event.IEventHandler;
import vooga.games.zombieland.events.AddBulletsEvent;
import vooga.games.zombieland.weapons.*;

/**
 * Player class. Contains all properties and abilities of the player
 * 
 * @author Jimmy Mu, Aaron Choi, Yang Su
 * 
 */
@SuppressWarnings("serial")
public class Shooter extends BetterSprite implements Constants {

	private int maxHealth;
	private int speed;
	private double orientation;
	private int weaponChoice;
	private Weapon[] weapons;
	private Stat<Integer> ammo;
	private int levelScore;
	private AddBulletsEvent addbullets;

	public Shooter() {
		super();
		// DEFAULT attributes
		speed = Resources.getInt("speed");
		maxHealth = Resources.getInt("maxHealth");
		orientation = Resources.getInt("orientation");
		weaponChoice = Resources.getInt("weaponChoice");
		levelScore = Resources.getInt("levelScore");

		setupWeapons();

		int playerDefaultX = Resources.getInt("playerDefaultX");
		int playerDefaultY = Resources.getInt("playerDefaultY");
		this.setX(playerDefaultX);
		this.setY(playerDefaultY);
	}

	/**
	 * Creates weapon objects with default ammo
	 */
	private void setupWeapons() {

		weapons = new Weapon[3];
		int shotgunAmmo = Resources.getInt("shotgunAmmo");
		int rifleAmmo = Resources.getInt("rifleAmmo");
		int pistolAmmo = Resources.getInt("pistolAmmo");
		weapons[0] = new Pistol(this, pistolAmmo);
		weapons[1] = new AssaultRifle(this, rifleAmmo);
		weapons[2] = new ShotGun(this, shotgunAmmo);
	}

	/**
	 * This method sets the bullet listener for the player
	 * 
	 * @param bulletlistener
	 */
	public void setBulletListener(IEventHandler bulletlistener) {
		addbullets = (AddBulletsEvent) bulletlistener;
	}

	/**
	 * Add a bullet sprite to the game world. Used by weapons to create bullets
	 * 
	 * @param bullet
	 *            a bullet
	 * @param angle
	 *            the angle of the bullet's trajectory
	 */
	public void addBulletToGame(Bullet bullet) {
		addbullets.addBullet(bullet);

	}

	private void showAnimation(String direction) {
		setAsRenderedSprite(direction);
		((AnimatedSprite) getCurrentSprite()).setAnimate(true);
	}

	/**
	 * Move the shooter left and play the corresponding animation.
	 */
	public void goLeft() {
		orientation = 180;
		showAnimation(PLAYER_LEFT);
		moveX(speed);
	}

	/**
	 * Move the shooter right and play the corresponding animation.
	 */
	public void goRight() {
		orientation = 0;
		showAnimation(PLAYER_RIGHT);
		moveX(Math.abs(speed));
	}

	/**
	 * Move the shooter up and play the corresponding animation.
	 */
	public void goUp() {
		orientation = 270;
		showAnimation(PLAYER_UP);
		moveY(speed);
	}

	/**
	 * Move the shooter down and play the corresponding animation.
	 */
	public void goDown() {
		orientation = 90;
		showAnimation(PLAYER_DOWN);
		moveY(Math.abs(speed));
	}

	public void switchWeapons(int choice) {
		if (choice <= weapons.length)
			weaponChoice = choice;
		setAmmo();
	}

	/**
	 * Initiate the shooting animation and fire bullets according to weapon
	 * choice
	 */
	public void shoot() {
		if (healthIsZero())
			return;
		fireWeapon();
	}

	/**
	 * Fires the current weapon
	 */
	private void fireWeapon() {
		weapons[weaponChoice].fire();
		setAmmo();
	}

	@SuppressWarnings("unchecked")
	public void setAmmo() {
		((Stat<Integer>) getStat("initAmmo")).setStat(weapons[weaponChoice]
				.getAmmo());
	}

	/**
	 * Gets the remaining ammo count on the current weapon
	 * 
	 * @return remaining ammo count
	 */
	public int getAmmo() {
		return weapons[weaponChoice].getAmmo();
	}

	/**
	 * Add ammo to a weapon
	 * 
	 * @param weapon
	 * @param amount
	 */
	public void addAmmo(int weapon, int amount) {
		weapons[weapon].addAmmo(amount);
	}

	/**
	 * Get the ammo as an object that's able to be displayed using overlays
	 * 
	 * @return ammo stat object
	 */
	public Stat<Integer> getStatAmmo() {
		return ammo;
	}

	/**
	 * Get the direction the player is facing (in degrees)
	 * 
	 * @return player orientation
	 */
	public double getOrientation() {
		return orientation;
	}

	/**
	 * Checks the player's health to see if he's still alive
	 * 
	 * @return true if the player's health is 0
	 */
	public boolean healthIsZero() {
		return ((Integer) getHealth().getStat() <= 0);
	}

	/**
	 * Set health of the shooter
	 */
	public void setHealth(int number) {
		getHealth().setStat(number);
	}

	/**
	 * update the health of the shooter
	 * 
	 * @param d
	 */
	public void updateHealth(double d) {
		int health = (Integer) getHealth().getStat();
		getHealth().setStat(new Integer(health + (int) d));
		if (getHealth().getStat() > maxHealth)
			setHealth(maxHealth);
	}

	/**
	 * Get the health stat object
	 * 
	 * @return health stat objec
	 */
	@SuppressWarnings("unchecked")
	public Stat<Integer> getHealth() {
		return (Stat<Integer>) getStat("initHealth");
	}

	/**
	 * Get the score stat object
	 * 
	 * @return score stat object
	 */
	@SuppressWarnings("unchecked")
	public Stat<Integer> getScore() {
		return (Stat<Integer>) getStat("initScore");
	}

	/**
	 * Resets the score so that it can start counting new level's score from 0.
	 */
	public void resetLevelScore() {
		levelScore = 0;
	}

	/**
	 * Get the player's score for the current level.
	 * 
	 * @return levelScore The current level's score
	 */
	public int getLevelScore() {
		return levelScore;
	}

	/**
	 * update score
	 */
	public void updateScore(int number) {
		getScore().setStat(getScore().getStat() + number);
		levelScore += number;
	}

	/**
	 * Update the shooter's image. Update movement and then stand still so the
	 * player is not walking in place. Also checks the player's health for the
	 * end game condition, which is when the player's health reaches 0
	 */
	public void update(long elapsedTime) {
		AnimatedSprite sprite = (AnimatedSprite) getCurrentSprite();
		super.update(elapsedTime);

		sprite.setAnimate(false);

		if (healthIsZero()) {
			setActive(false);
		}
	}
}