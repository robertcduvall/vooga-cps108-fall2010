package vooga.games.zombieland;

import com.golden.gamedev.object.*;

import vooga.engine.overlay.Stat;
import vooga.engine.player.control.PlayerSprite;

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
		weapons = new Weapon[4];

		// DEFAULT values
		speed = -1;
		orientation = 90;
		weaponChoice = 0;
		weapons[0] = new Pistol(this, 99999);
		weapons[1] = new AssaultRifle(this, 100);
		weapons[2] = new ShotGun(this, 40);

		health = new Stat<Integer>(playerHealth);
		score = new Stat<Integer>(0);
		ammo = new Stat<Integer>(getAmmo());
	}

	public void goLeft() {
		orientation = 180;

		if (healthIsZero())
			return;
		setToCurrentSprite("Left");
		moveX(speed);
		((AnimatedSprite) getCurrentSprite()).setAnimate(true);
	}

	public void goRight() {
		orientation = 0;
		if (healthIsZero())
			return;
		setToCurrentSprite("Right");
		moveX(Math.abs(speed));
		((AnimatedSprite) getCurrentSprite()).setAnimate(true);
	}

	public void goUp() {
		orientation = 270;
		if (healthIsZero())
			return;
		setToCurrentSprite("Up");
		moveY(speed);
		((AnimatedSprite) getCurrentSprite()).setAnimate(true);
	}

	public void goDown() {
		if (healthIsZero())
			return;
		orientation = 90;
		setToCurrentSprite("Down");
		moveY(Math.abs(speed));
		((AnimatedSprite) getCurrentSprite()).setAnimate(true);
	}

	public void switchWeapons(int choice){
		if(choice<=3)
			weaponChoice=choice;
		ammo.setStat(weapons[weaponChoice].getAmmo());
	}
	/**
	 * Initiate the shooting animation and fire bullets according to weapon
	 * choice
	 */
	public void shoot() {

		if (healthIsZero())
			return;

		setToCurrentSprite("Shoot");
		((AnimatedSprite) getCurrentSprite()).setAnimate(true);

		fireBullets();
	}
	
	private void fireBullets() {
		weapons[weaponChoice].fire();
		ammo.setStat(weapons[weaponChoice].getAmmo());
	}

	public void addBulletToGame(Bullet bullet, double angle) {
		game.addBullet(bullet, angle);
	}
	
	public Stat<Integer> getStatHealth(){
		return health;
	}

	public Stat<Integer> getStatScore(){
		return score;
	}
	
	public void updateScore(int number){
		super.updateScore(number);
		score.setStat(getScore());
	}
	
	public void setHealth(int number) {
		super.setHealth(number);
		health.setStat(getHealth());
	}

	public boolean healthIsZero() {
		return (getHealth() <= 0);
	}
	public void updateStatHealth(int number) {
        updateHealth(number);
        health.setStat(getHealth());
	}

	public double getOrientation() {
		return orientation;
	}

	public int getAmmo() {
		return weapons[weaponChoice].getAmmo();
	}

	public Stat<Integer> getStatAmmo(){
		return ammo;
	}
	
	public void addAmmo(int weapon, int amount){
		weapons[weapon].addAmmo(amount);
	}
	
	public void update(long elapsedTime) {
		AnimatedSprite sprite = (AnimatedSprite) getCurrentSprite();
		super.update(elapsedTime);

		sprite.setAnimate(false);
		
		if (healthIsZero()){
			setActive(false);
		}
		// sprite.setFrame(0);
	}

}
