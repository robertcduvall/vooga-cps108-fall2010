package vooga.games.zombieland;

import com.golden.gamedev.object.*;

import vooga.engine.overlay.Stat;
import vooga.engine.player.control.PlayerSprite;

/**
 * Player class. Contains all properties and abilities of the player
 * 
 * @author Jimmy Mu, Aaron Choi, Yang Su
 * 
 */
public class Shooter extends PlayerSprite {

	private Zombieland game;
	private int speed;
	private double orientation;
	private int weaponChoice;
	private Weapon[] weapons;
	private Stat<Integer> health;
	private Stat<Integer> score;
	private Stat<Integer> ammo;
	private int levelScore;

	public Shooter(String name, String stateName, AnimatedSprite s,
			int playerHealth, int playerRank, Zombieland zombieland) {
		super(name, stateName, s, playerHealth, playerRank);
		game = zombieland;
		weapons = new Weapon[3];

		setupWeapons();

		// Setup displays
		health = new Stat<Integer>(playerHealth);
		score = new Stat<Integer>(0);
		ammo = new Stat<Integer>(getAmmo());

		// DEFAULT attributes
		speed = -1;
		orientation = 90;
		weaponChoice = 0;
		levelScore = 0;

	}

	/**
	 * Creates weapon objects with default ammo
	 */
	private void setupWeapons() {
		weapons[0] = new Pistol(this, 99999);
		weapons[1] = new AssaultRifle(this, 100);
		weapons[2] = new ShotGun(this, 40);
	}

	/**
	 * Add a bullet sprite to the game world. Used by weapons to create bullets
	 * 
	 * @param bullet
	 *            a bullet
	 * @param angle
	 *            the angle of the bullet's trajectory
	 */
	public void addBulletToGame(Bullet bullet, double angle) {
		game.addBullet(bullet, angle);
	}

	private void showAnimation(String direction) {
		setToCurrentSprite(direction);
		((AnimatedSprite) getCurrentSprite()).setAnimate(true);
	}

	/**
	 * Move the shooter left and play the corresponding animation.
	 */
	public void goLeft() {
		orientation = 180;
		showAnimation("Left");
		moveX(speed);
	}

	/**
	 * Move the shooter right and play the corresponding animation.
	 */
	public void goRight() {
		orientation = 0;
		showAnimation("Right");
		moveX(Math.abs(speed));
	}

	/**
	 * Move the shooter up and play the corresponding animation.
	 */
	public void goUp() {
		orientation = 270;
		showAnimation("Up");
		moveY(speed);
	}

	/**
	 * Move the shooter down and play the corresponding animation.
	 */
	public void goDown() {
		orientation = 90;
		showAnimation("Down");
		moveY(Math.abs(speed));
	}

	public void switchWeapons(int choice) {
		if (choice <= 3)
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

	public void setAmmo() {
		ammo.setStat(weapons[weaponChoice].getAmmo());
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
		return (getHealth() <= 0);
	}

	/**
	 * Set health of the shooter
	 */
	public void setHealth(int number) {
		super.setHealth(number);
		health.setStat(getHealth());
	}

	/**
	 * update the health of the shooter
	 * 
	 * @param number
	 */
	public void updateStatHealth(int number) {
		updateHealth(number);
		if (getHealth()>200)
			setHealth(200);
		health.setStat(getHealth());
	}

	/**
	 * Get the health stat object
	 * 
	 * @return health stat objec
	 */
	public Stat<Integer> getStatHealth() {
		return health;
	}

	/**
	 * Get the score stat object
	 * 
	 * @return score stat object
	 */
	public Stat<Integer> getStatScore() {
		return score;
	}
	
	/**
	 * Resets the score so that it can start counting new level's score from 0.
	 */
	public void resetLevelScore(){
		levelScore = 0;
	}
	
	/**
	 * Get the player's score for the current level.
	 * @return levelScore The current level's score
	 */
	public int getLevelScore(){
		return levelScore;
	}
	
	/**
	 * update score
	 */
	public void updateScore(int number) {
		super.updateScore(number);
		levelScore+=number;
		score.setStat(getScore());
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
