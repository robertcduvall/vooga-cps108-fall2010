package vooga.games.zombieland;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.collision.PreciseCollisionGroup;

/**
 * Collision Manager for bullet/zombie collision. Calculates damage if a zombie is hit
 * @author Jimmy Mu, Aaron Choi, Yang Su
 */
public class BZCollisionManager extends PreciseCollisionGroup{

	public void collided(Sprite bullet, Sprite zombie) {
		
		actOnCollision(bullet, zombie);	
	}
	
	/**
	 * 
	 * @param bullet Bullet and 
	 * @param zombie
	 */
	private void actOnCollision(Sprite bullet, Sprite zombie) {
		Zombie currentZombie = (Zombie) zombie;
		Bullet currentBullet = (Bullet) bullet;
		
		int damage = - (int) currentBullet.getDamage(); 
		
		currentZombie.updateHealth(damage);
		
		if(currentZombie.isHealthZero())
		{
			currentZombie.setHealth(0);
			currentZombie.setToCurrentSprite("ZombieDeath");
			((AnimatedSprite) currentZombie.getCurrentSprite()).setAnimate(true);
			
		}
		
		currentBullet.setActive(false);
	}
}
