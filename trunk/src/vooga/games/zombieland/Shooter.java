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
	
	
	public Shooter(String name, String stateName, AnimatedSprite s,
			int playerHealth, int playerRank, Zombieland zombieland) {
		super(name, stateName, s, playerHealth, playerRank);
		game=zombieland;
		weapons=new Weapon[5];
		
		//DEFAULT values
		speed = -1;
		orientation=90;
		weaponChoice=0;
		weapons[weaponChoice]=new Pistol(this,99999);

		health = new Stat<Integer>(playerHealth);
	}

	
	public void goLeft() {
		orientation=180;
		
		if(healthIsZero()) return;
		setToCurrentSprite("Left");
		moveX(speed);
		((AnimatedSprite) getCurrentSprite()).setAnimate(true);
	}

	public void goRight() {
		orientation=0;
		if(healthIsZero()) return;
		setToCurrentSprite("Right");
		moveX(Math.abs(speed));
		((AnimatedSprite) getCurrentSprite()).setAnimate(true);
	}

	public void goUp() {
		orientation=270;
		if(healthIsZero()) return;
		setToCurrentSprite("Up");
		moveY(speed);
		((AnimatedSprite) getCurrentSprite()).setAnimate(true);
	}

	public void goDown() {
		if(healthIsZero()) return;
		orientation=90;
		setToCurrentSprite("Down");
		moveY(Math.abs(speed));
		((AnimatedSprite) getCurrentSprite()).setAnimate(true);
	}
	
	/**
	 * Initiate the shooting animation and fire bullets according to weapon choice
	 */
	public void shoot() {
		
		if(healthIsZero()) return;
		
		setToCurrentSprite("Shoot");
		((AnimatedSprite) getCurrentSprite()).setAnimate(true);
		fireBullets();
	}
	
	public Stat<Integer> getHealth(int placeholder){
		return health;
	}
	
	private void fireBullets() {
		weapons[weaponChoice].fire();
	}
	
	public void addBulletToGame(Bullet bullet, double angle){
		game.addBullet(bullet,angle);
	}
	
	public double getOrientation(){
		return orientation;
	}
	
	public void setHealth(int i) {
        super.setHealth(i);
        health.setStat(getHealth());
	}
	
	public void update(long elapsedTime) {
		AnimatedSprite sprite=(AnimatedSprite) getCurrentSprite();
		super.update(elapsedTime);

		sprite.setAnimate(false);
		//		sprite.setFrame(0);
	}
	
	public boolean healthIsZero()
	{
		return (getHealth() <= 0);
	}

	
}
