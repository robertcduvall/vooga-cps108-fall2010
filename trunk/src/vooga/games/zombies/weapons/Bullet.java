package vooga.games.zombies.weapons;

import java.awt.image.BufferedImage;

import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.util.ImageUtil;

/**
 * A bullet is the base unit for the attack algorithm in this game. Each weapons
 * spawns a bullet or bullets with different attributes. All
 * 
 * @author Jimmy Mu, Aaron Choi, Yang Su
 * 
 */
@SuppressWarnings("serial")
public class Bullet extends BetterSprite{

	private int damage;
	private double velocity;
	private double angle;
	private double userX;
	private double userY;

	public Bullet(double x, double y, double bulletAngle, int bulletDamage,
			double bulletSpeed) {
		super("Bullet", new Sprite());


		userX = x;
		userY = y;
		damage = bulletDamage;
		angle = bulletAngle;
		velocity = bulletSpeed;

		BufferedImage bulletImage = Resources.getImage("bullet");
		getCurrentSprite().setImage(
				ImageUtil.rotate(bulletImage, (int) angle));
		
		correctPositionOffset();
		setActive(true);
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

		int bulletsCorrectRightX = Resources.getInt("bulletsCorrectRightX");
		int bulletsCorrectRightY = Resources.getInt("bulletsCorrectRightY");

		if (angle > 135 && angle < 225)
			correctBulletPlacement(bulletsCorrectRightX, bulletsCorrectRightY);
	}

	/**
	 * correct bullets facing up
	 */
	private void correctUpFacingBullet() {

		int bulletsCorrectUpX = Resources.getInt("bulletsCorrectUpX");
		int bulletsCorrectUpY = Resources.getInt("bulletsCorrectUpY");

		if (angle > 45 && angle < 135)
			correctBulletPlacement(bulletsCorrectUpX, bulletsCorrectUpY);
	}

	/**
	 * correct bullets facing left
	 */
	private void correctLeftFacingBullet() {

		int bulletsCorrectLeftX = Resources.getInt("bulletsCorrectLeftX");
		int bulletsCorrectLeftY = Resources.getInt("bulletsCorrectLeftY");

		if (angle > -45 && angle < 45)
			correctBulletPlacement(bulletsCorrectLeftX, bulletsCorrectLeftY);
	}

	/**
	 * correct bullets facing down
	 */
	private void correctDownFacingBullet() {

		int bulletsCorrectDownX = Resources.getInt("bulletsCorrectDownX");
		int bulletsCorrectDownY = Resources.getInt("bulletsCorrectDownY");

		if (angle > 225 && angle < 315)
			correctBulletPlacement(bulletsCorrectDownX, bulletsCorrectDownY);
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
		double newXAmountFromAngle = Math.cos(Math.toRadians(angle))* velocity;
		moveX(newXAmountFromAngle);

		double newYAmountFromAngle = Math.sin(Math.toRadians(angle))* velocity;
		moveY(newYAmountFromAngle);

	}
}

