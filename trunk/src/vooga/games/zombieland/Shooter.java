package vooga.games.zombieland;

import java.util.ResourceBundle;

import com.golden.gamedev.object.*;

import vooga.engine.overlay.Stat;
import vooga.engine.resource.Resources;
import vooga.engine.util.AnimationUtil;
import vooga.engine.core.BetterSprite;
import vooga.engine.event.IEventHandler;
import vooga.games.zombieland.events.AddBulletsEvent;
import vooga.games.zombieland.gamestates.PlayState;
import vooga.games.zombieland.weapons.AssaultRifle;
import vooga.games.zombieland.weapons.Bullet;
import vooga.games.zombieland.weapons.Pistol;
import vooga.games.zombieland.weapons.ShotGun;
import vooga.games.zombieland.weapons.Weapon;

/**
 * Player class. Contains all properties and abilities of the player
 * 
 * @author Jimmy Mu, Aaron Choi, Yang Su
 * 
 */
public class Shooter extends BetterSprite implements Constants{

	private static int shotgunAmmo;
	private static int rifleAmmo;
	private static int pistolAmmo;
	private int maxHealth;
	private PlayState state;
	private int speed;
	private double orientation;
	private int weaponChoice;
	private Weapon[] weapons;
	private Stat<Integer> health;
	private Stat<Integer> score;
	private Stat<Integer> ammo;
	private int levelScore;
	private AddBulletsEvent addbullets;

	@SuppressWarnings("unchecked")
	public Shooter() {
		super();
		weapons = new Weapon[3];
		// DEFAULT attributes
		speed = Resources.getInt("speed");
		maxHealth = Resources.getInt("maxHealth");
		orientation = Resources.getInt("orientation");
		weaponChoice = Resources.getInt("weaponChoice");
		levelScore = Resources.getInt("levelScore");
		shotgunAmmo = Resources.getInt("shotgunAmmo");
		rifleAmmo = Resources.getInt("rifleAmmo");
		pistolAmmo = Resources.getInt("pistolAmmo");
		
		setupWeapons();
		
		int playerDefaultX = Resources.getInt("playerDefaultX");
		int playerDefaultY = Resources.getInt("playerDefaultY");
		this.setX(playerDefaultX);
		this.setY(playerDefaultY);
		
//		AnimatedSprite down = AnimationUtil.getInitializedAnimatedSprite(Resources.getAnimation(PLAYER_DOWN));
//		AnimatedSprite up = AnimationUtil.getInitializedAnimatedSprite(Resources.getAnimation(PLAYER_UP));
//		AnimatedSprite left = AnimationUtil.getInitializedAnimatedSprite(Resources.getAnimation(PLAYER_LEFT));
//		AnimatedSprite right = AnimationUtil.getInitializedAnimatedSprite(Resources.getAnimation(PLAYER_RIGHT));
		
//		this.addSprite(PLAYER_DOWN, down);
//		this.addSprite(PLAYER_UP, up);
//		this.addSprite(PLAYER_LEFT, left);
//		this.addSprite(PLAYER_RIGHT, right);
		
		// Setup displays
//		health = (Stat<Integer>) getStat("initHealth");
//		setStat("health", health);
//		score = (Stat<Integer>) getStat("initScore");
//		setStat("score", score);
//		ammo = (Stat<Integer>) getStat("initAmmo");
//		setStat("ammo", ammo);
	}

	/**
	 * Creates weapon objects with default ammo
	 */
	private void setupWeapons() {
		weapons[0] = new Pistol(this, pistolAmmo);
		weapons[1] = new AssaultRifle(this, rifleAmmo);
		weapons[2] = new ShotGun(this, shotgunAmmo);
	}

	/**
	 * This method sets the bullet listener for the player
	 * @param bulletlistener
	 */
	public void setBulletListener(IEventHandler bulletlistener)
	{
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

	public void setState(PlayState state)
	{
		this.state = state;
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
		((Stat<Integer>)getStat("initAmmo")).setStat(weapons[weaponChoice].getAmmo());
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
		return ((Integer)getHealth().getStat() <= 0);
	}

	/**
	 * Set health of the shooter
	 */
	public void setHealth(int number) {
		setStat("initHealth", new Stat<Integer>(number));
	}
	
	/**
	 * update the health of the shooter
	 * 
	 * @param d
	 */
	public void updateHealth(double d) {
		int health = (Integer)getHealth().getStat();
		getHealth().setStat(new Integer(health + (int)d));
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
		return (Stat<Integer>)getStat("initHealth");
	}

	/**
	 * Get the score stat object
	 * 
	 * @return score stat object
	 */
	@SuppressWarnings("unchecked")
	public Stat<Integer> getScore() {
		return (Stat<Integer>)getStat("initScore");
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
