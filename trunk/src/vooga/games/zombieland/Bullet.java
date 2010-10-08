package vooga.games.zombieland;

import vooga.engine.player.control.GameEntitySprite;
import com.golden.gamedev.object.Sprite;

/**
 * A bullet is the base unit for the attack algorithm in this game. Each weapons
 * spawns a bullet or bullets with different attributes. All
 * 
 * @author Jimmy Mu, Aaron Choi, Yang Su
 * 
 */
public class Bullet extends GameEntitySprite {

	private double myDamage;
	private double myVelocity;
	private double myAngle;
	private double myUserX;
	private double myUserY;

	public Bullet(double x, double y, double angle, double bulletDamage,
			double bulletSpeed) {
		super("Bullet", "Moving", new Sprite());
		
		setAngle(angle);
		setUserLocation(x,y);
		setDamage(bulletDamage);
		setVelocity(bulletSpeed);
		correctPositionOffset();
	}

	private void setAngle(double angle) {
		myAngle = angle;
	}

	private void setVelocity(double bulletSpeed) {
		myVelocity = bulletSpeed;
	}

	private void setDamage(double bulletDamage) {
		myDamage = bulletDamage;
	}
	
	private void setUserLocation(double x, double y)
	{	myUserX =x;
		myUserY =y;
	}

	/**
	 * Correct the position offset of the bullet in relation to the position of
	 * the player so the bullet is spawned in front of the player and next to
	 * the gun.
	 */
	
	private void correctPositionOffset() {
		if (angleFacingLeft())
		{	correctBulletPlacement(30, 25);
			return;
		}
	
		if (angleFacingUp())
		{	correctBulletPlacement(-15, 50);
			return;
		}
		
		if (angleFacingRight())
		{	correctBulletPlacement(-25, 10);
			return;
		}
		
		correctBulletPlacement(5, 0);
	}

	private boolean angleFacingRight() {
		return myAngle > 135 && myAngle < 225;
	}

	private boolean angleFacingUp() {
		return myAngle > 45 && myAngle < 135;
	}

	private boolean angleFacingLeft() {
		return myAngle > -45 && myAngle < 45;
	}
	
	

	/**
	 * Offset the position of the bullet relative to the position of the player
	 * 
	 * @param x
	 *            x offset
	 * @param y
	 *            y offset
	 */
	private void correctBulletPlacement(double x, double y) {
		setX(myUserX + x);
		setY(myUserY + y);
	}

	/**
	 * 
	 * @return damage of the current bullet
	 */
	public double getDamage() {
		return myDamage;
	}

	/**
	 * Update the location of this bullet. Used for animation
	 */
	public void update(long elapsedTime) {
		double newXAmountFromAngle = Math.cos(myAngle / 360.0 * Math.PI * 2) * myVelocity;
		moveX(newXAmountFromAngle);
		
		double newYAmountFromAngle = Math.sin(myAngle / 360.0 * Math.PI * 2) * myVelocity;
		moveY(newYAmountFromAngle);

	}
}
