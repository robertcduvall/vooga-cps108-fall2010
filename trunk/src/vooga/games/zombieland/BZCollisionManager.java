package vooga.games.zombieland;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.PreciseCollisionGroup;

/**
 * Collision Manager for bullet/zombie collision. Calculates damage if a zombie
 * is hit
 * 
 * @author Jimmy Mu, Aaron Choi, Yang Su
 */
public class BZCollisionManager extends PreciseCollisionGroup {
	/**
	 * Initiates post-collision behavior for the zombie and the bullet
	 */
	public void collided(Sprite bullet, Sprite zombie) {

		actOnCollision(bullet, zombie);
	}

	/**
	 * Processes the collision. Calculates the damage taken by a zombie from the
	 * bullet. If the zombie is killed by the bullet, it's set to display the
	 * death animation. The bullet is deleted after the collision processing is
	 * completed.
	 * 
	 * @param bullet
	 *            bullet
	 * @param zombie
	 *            zombie
	 */
	private void actOnCollision(Sprite bullet, Sprite zombie) {
		Zombie currentZombie = (Zombie) zombie;
		Bullet currentBullet = (Bullet) bullet;

		int damage = -(int) currentBullet.getDamage();

		currentZombie.updateHealth(damage);

		if (currentZombie.isHealthZero()) {
			currentZombie.setToCurrentSprite("ZombieDeath");
			((AnimatedSprite) currentZombie.getCurrentSprite())
					.setAnimate(true);

		}

		currentBullet.setActive(false);
	}
}
