package vooga.games.zombieland;

import java.util.ResourceBundle;

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

	private static final String MAIN_RESOURCES_PATH = "vooga.games.zombieland.MainResources";
	
	private double damage;
	private double velocity;
	private double angle;
	private double userX;
	private double userY;
	private double adjustment;
	private ResourceBundle bundle;

	public Bullet(double x, double y, double bulletAngle, double bulletDamage,
			double bulletSpeed) {
		super("Bullet", "Moving", new Sprite());
		
		bundle = ResourceBundle.getBundle(MAIN_RESOURCES_PATH);
		
		userX = x;
		userY = y;
		damage = bulletDamage;
		angle = bulletAngle;
		velocity = bulletSpeed;
		
		correctPositionOffset();
		convertToRadian();
	}

	/**
	 * This method sets the bullet angle to radians
	 */
	private void convertToRadian() {
		angle = angle / 360.0 * Math.PI * 2;
	}

	/**
	 * Correct the position offset of the bullet in relation to the position of
	 * the player so the bullet is spawned in front of the player and next to
	 * the gun.
	 */

	private void correctPositionOffset() {
		correctRightFacingBullet();
		correctUpFacingBullet();
		correctLeftFacingBullet();
		correctDownFacingBullet();
	}

	/**
	 * Correct bullets facing right
	 */
	private void correctRightFacingBullet() {
		if (angle > 135 && angle < 225)
			correctBulletPlacement(-25, 10);
	}

	/**
	 * correct bullets facing up
	 */
	private void correctUpFacingBullet() {
		if (angle > 45 && angle < 135)
			correctBulletPlacement(-15, 50);
	}

	/**
	 * correct bullets facing left
	 */
	private void correctLeftFacingBullet() {
		if (angle > -45 && angle < 45)
			correctBulletPlacement(30, 25);
	}

	/**
	 * correct bullets facing down
	 */
	private void correctDownFacingBullet() {
		if (angle > 225 && angle < 315)
			correctBulletPlacement(5, 0);
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
		setX(userX + x);
		setY(userY + y);
	}

	/**
	 * Get the damage of this bullet. Returns a random damage within 10% of the
	 * weapon damage
	 * 
	 * @return damage of the current bullet
	 */
	public double getDamage() {
		return (1 + (Math.random() * 2 - 1) * 0.1) * damage;
	}

	/**
	 * Update the location of this bullet. Used for animation
	 */
	public void update(long elapsedTime) {
		double newXAmountFromAngle = Math.cos(adjustment)* velocity;
		moveX(newXAmountFromAngle);

		double newYAmountFromAngle = Math.sin(adjustment)* velocity;
		moveY(newYAmountFromAngle);

	}
}
