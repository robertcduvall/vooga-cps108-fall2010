package vooga.games.zombieland;

import com.golden.gamedev.object.*;

import vooga.engine.overlay.Stat;
import vooga.engine.player.control.PlayerSprite;

/**
 * Player class. Contains all properties and abilities of the player
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

	public Shooter(String name, String stateName, AnimatedSprite s,
			int playerHealth, int playerRank, Zombieland zombieland) {
		super(name, stateName, s, playerHealth, playerRank);
		game = zombieland;
		weapons = new Weapon[3];
		
		setupWeapons();	
		
		//Setup displays
		health = new Stat<Integer>(playerHealth);
		score = new Stat<Integer>(0);
		ammo = new Stat<Integer>(getAmmo());
		
		// DEFAULT attributes
		speed = -1;
		orientation = 90;
		weaponChoice = 0;
		
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
	 * @param bullet a bullet
	 * @param angle the angle of the bullet's trajectory
	 */
	public void addBulletToGame(Bullet bullet, double angle) {
		game.addBullet(bullet, angle);
	}

	/**
	 * Move the shooter left and play the corresponding animation.
	 */
	public void goLeft() {
		if (healthIsZero())
			return;
		orientation = 180;
		setToCurrentSprite("Left");
		((AnimatedSprite) getCurrentSprite()).setAnimate(true);
		moveX(speed);
	}
	/**
	 * Move the shooter right and play the corresponding animation.
	 */
	public void goRight() {
		if (healthIsZero())
			return;
		orientation = 0;
		setToCurrentSprite("Right");
		((AnimatedSprite) getCurrentSprite()).setAnimate(true);
		moveX(Math.abs(speed));
	}
	/**
	 * Move the shooter up and play the corresponding animation.
	 */
	public void goUp() {
		if (healthIsZero())
			return;
		orientation = 270;
		setToCurrentSprite("Up");
		((AnimatedSprite) getCurrentSprite()).setAnimate(true);
		moveY(speed);
	}
	/**
	 * Move the shooter down and play the corresponding animation.
	 */
	public void goDown() {
		if (healthIsZero())
			return;
		orientation = 90;
		setToCurrentSprite("Down");
		((AnimatedSprite) getCurrentSprite()).setAnimate(true);
		moveY(Math.abs(speed));
	}

	public void switchWeapons(int choice) {
		if (choice <= 3)
			weaponChoice = choice;
		ammo.setStat(weapons[weaponChoice].getAmmo());
	}

	/**
	 * Initiate the shooting animation and fire bullets according to weapon
	 * choice
	 */
	public void shoot() {
		if (healthIsZero())		return;
		fireWeapon();
	}
	
	/**
	 * Fires the current weapon
	 */
	private void fireWeapon() {
		weapons[weaponChoice].fire();
		ammo.setStat(weapons[weaponChoice].getAmmo());
	}
	
	/**
	 * Gets the remaining ammo count on the current weapon
	 * @return remaining ammo count 
	 */
	public int getAmmo() {
		return weapons[weaponChoice].getAmmo();
	}

	/**
	 * Add ammo to a weapon
	 * @param weapon
	 * @param amount
	 */
	public void addAmmo(int weapon, int amount) {
		weapons[weapon].addAmmo(amount);
	}
	
	/**
	 * 
	 * @return
	 */
	public Stat<Integer> getStatAmmo() {
		return ammo;
	}
	
	/**
	 * get the direction the player is facing (in degrees)
	 * @return player orientation
	 */
	public double getOrientation() {
		return orientation;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean healthIsZero() {
		return (getHealth() <= 0);
	}

	public void setHealth(int number) {
		super.setHealth(number);
		health.setStat(getHealth());
	}

	public void updateStatHealth(int number) {
		updateHealth(number);
		health.setStat(getHealth());
	}

	public Stat<Integer> getStatHealth() {
		return health;
	}

	public Stat<Integer> getStatScore() {
		return score;
	}

	public void updateScore(int number) {
		super.updateScore(number);
		score.setStat(getScore());
	}

	public void update(long elapsedTime) {
		AnimatedSprite sprite = (AnimatedSprite) getCurrentSprite();
		super.update(elapsedTime);

		sprite.setAnimate(false);

		if (healthIsZero()) {
			setActive(false);
		}
		// sprite.setFrame(0);
	}

}
