package vooga.games.zombieland;

import com.golden.gamedev.object.*;

import vooga.engine.overlay.Stat;
import vooga.engine.player.control.PlayerSprite;

public class Shooter extends PlayerSprite {

	private int speed;
	private double angle;
	private Zombieland game;
	private int weapon;
	
	public Shooter(String name, String stateName, AnimatedSprite s,
			int playerHealth, int playerRank, Zombieland zombieland) {
		super(name, stateName, s, playerHealth, playerRank);
		game=zombieland;
		speed = -1;
		angle=90;
		weapon=1;
		
		setHealth(100);
	}

	
	public void goLeft() {
		
		if(healthIsZero()) return;
		
		angle=180;
		setToCurrentSprite("Left");
		moveX(speed);
		((AnimatedSprite) getCurrentSprite()).setAnimate(true);
	}

	public void goRight() {
		
		if(healthIsZero()) return;
		
		angle=0;
		setToCurrentSprite("Right");
		moveX(Math.abs(speed));
		((AnimatedSprite) getCurrentSprite()).setAnimate(true);
	}

	public void goUp() {
		
		if(healthIsZero()) return;
		
		angle=270;
		setToCurrentSprite("Up");
		moveY(speed);
		((AnimatedSprite) getCurrentSprite()).setAnimate(true);
	}

	public void goDown() {
		
		if(healthIsZero()) return;
		
		angle=90;
		setToCurrentSprite("Down");
		moveY(Math.abs(speed));
		((AnimatedSprite) getCurrentSprite()).setAnimate(true);
	}

	public void shoot() {
		
		if(healthIsZero()) return;
		
		setToCurrentSprite("Shoot");
		fire();
		((AnimatedSprite) getCurrentSprite()).setAnimate(true);
	}
	
	private void fire() {
		switch(weapon) {
		case 1:
			spawnBullets(weapon);
			break;
		default:
			spawnBullets(weapon);
			break;
		}
	}

	private void spawnBullets(int weaponChoice) {
		switch(weaponChoice) {
		case 1:
			makeBullet(angle);
			makeBullet(angle+10);
			makeBullet(angle-10);
			break;
		default:
			makeBullet(angle);
			break;
		}
	}
	
	private void makeBullet(double theta) {
		Bullet b=new Bullet(this, theta);
		game.addBullet(b,theta);
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
