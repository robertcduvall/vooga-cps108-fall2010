package vooga.games.zombies.collisions;


import vooga.games.zombies.Zombie;
import vooga.games.zombies.weapons.Bullet;
import vooga.games.zombies.Constants;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;

import vooga.engine.core.Game;

import com.golden.gamedev.object.collision.PreciseCollisionGroup;

/**
 * Collision Manager for bullet/zombie collision. Calculates damage if a zombie
 * is hit
 * 
 * @author Jimmy Mu, Aaron Choi, Yang Su
 */
public class BZCollisionManager extends PreciseCollisionGroup implements Constants{
	
	Game currentGame;
	
	public BZCollisionManager(Game game)
	{
		currentGame = game;
	}
	
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
		
		//Process bullet damage
		currentZombie.calculateDamage(currentBullet.getDamage());
		
		//If the zombie's killed, turn on the zombie death animation
		if (currentZombie.isDead()) {
			currentZombie.setAsRenderedSprite(DEATH);
			((AnimatedSprite) currentZombie.getCurrentSprite()).setAnimate(true);
		}
		
		//delete bullet from the game
		currentBullet.setActive(false);
	}

}
