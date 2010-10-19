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
public class Bullet extends GameEntitySprite implements IZombielandConstants {
	
	private double damage;
	private double velocity;
	private double angle;
	private double userX;
	private double userY;
	private double adjustment;

	public Bullet(double x, double y, double bulletAngle, double bulletDamage,
			double bulletSpeed) {
		super("Bullet", "Moving", new Sprite());
				
		userX = x;
		userY = y;
		damage = bulletDamage;
		angle = bulletAngle;
		velocity = bulletSpeed;
		
		correctPositionOffset();
		adjustment = getRadianAdjustment();
	}

	private double getRadianAdjustment() {
		return angle / 360.0 * Math.PI * 2;
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
		
		int bulletsCorrectRightX = parseInt("bulletsCorrectRightX");
		int bulletsCorrectRightY = parseInt("bulletsCorrectRightY");
		
		if (angle > 135 && angle < 225)
			correctBulletPlacement(bulletsCorrectRightX, bulletsCorrectRightY);
	}

	/**
	 * correct bullets facing up
	 */
	private void correctUpFacingBullet() {
		
		int bulletsCorrectUpX = parseInt("bulletsCorrectUpX");
		int bulletsCorrectUpY = parseInt("bulletsCorrectUpY");
		
		if (angle > 45 && angle < 135)
			correctBulletPlacement(bulletsCorrectUpX, bulletsCorrectUpY);
	}

	/**
	 * correct bullets facing left
	 */
	private void correctLeftFacingBullet() {
		
		int bulletsCorrectLeftX = parseInt("bulletsCorrectLeftX");
		int bulletsCorrectLeftY = parseInt("bulletsCorrectLeftY");
		
		if (angle > -45 && angle < 45)
			correctBulletPlacement(bulletsCorrectLeftX, bulletsCorrectLeftY);
	}

	/**
	 * correct bullets facing down
	 */
	private void correctDownFacingBullet() {
		
		int bulletsCorrectDownX = parseInt("bulletsCorrectDownX");
		int bulletsCorrectDownY = parseInt("bulletsCorrectDownY");
		
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
		double newXAmountFromAngle = Math.cos(adjustment)* velocity;
		moveX(newXAmountFromAngle);

		double newYAmountFromAngle = Math.sin(adjustment)* velocity;
		moveY(newYAmountFromAngle);

	}
	/**
	 * (This method does not really belong here but the Resource group does not yet
	 * have any  classes to deal with literals).
	 * This method allows the user to parse the value associated with the keyName to
	 * a double. Specifically, this method does this by drawing the value associated
	 * with the keyName from the global ResourceBundle bundle. 
	 * @param keyName
	 * @return
	 */
	public double parseDouble(String keyName) {
		String string = bundle.getString(keyName);
		return Double.parseDouble(string);
	}

	/**
	 * (This method does not really belong here but the Resource group does not yet 
	 * have any classes to deal with literals).
	 * This method allows the user to parse the value associated with the keyName to
	 * an integer. Specifically, this method does this by drawing the value associated
	 * with the keyName from the global ResourceBundle bundle. 
	 * @param keyName
	 * @return
	 */
	public int parseInt(String keyName) {
		String string = bundle.getString(keyName);
		return Integer.parseInt(string);
	}
}

