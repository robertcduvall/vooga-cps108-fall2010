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

	private double damage;
	private double velocity;
	private double orientation;
	private double playerX;
	private double playerY;

	public Bullet(double x, double y, double angle, double bulletDamage,
			double bulletSpeed) {
		super("Bullet", "Moving", new Sprite());
		orientation = angle;
		playerX = x;
		playerY = y;
		damage = bulletDamage;
		velocity = bulletSpeed;

		correctPositionOffset();
	}

	/**
	 * Correct the position offset of the bullet in relation to the position of
	 * the player so the bullet is spawned in front of the player and next to
	 * the gun.
	 */
	private void correctPositionOffset() {
		if (orientation > -45 && orientation < 45)
			setOffset(30, 25);
		else if (orientation > 45 && orientation < 135)
			setOffset(-15, 50);
		else if (orientation > 135 && orientation < 225)
			setOffset(-25, 10);
		else
			setOffset(5, 0);
	}

	/**
	 * Offset the position of the bullet relative to the position of the player
	 * 
	 * @param x
	 *            x offset
	 * @param y
	 *            y offset
	 */
	private void setOffset(double x, double y) {
		setX(playerX + x);
		setY(playerY + y);
	}

	/**
	 * 
	 * @return damage of the current bullet
	 */
	public double getDamage() {
		return damage;
	}

	/**
	 * Update the location of this bullet. Used for animation
	 */
	public void update(long elapsedTime) {

		moveX(Math.cos(orientation / 360.0 * Math.PI * 2) * velocity);
		moveY(Math.sin(orientation / 360.0 * Math.PI * 2) * velocity);

	}
}
