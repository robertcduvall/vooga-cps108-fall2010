package vooga.games.zombieland;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.PreciseCollisionGroup;

/*
 * This is the Bullet Zombie Collision class
 * Authors: Jimmy Mu, Aaron Choi, Yang Su
 */


public class BZCollisionManager extends PreciseCollisionGroup{

	
	public void collided(Sprite bullet, Sprite zombie) {
		
		actOnCollision(bullet, zombie);	
	}

	private void actOnCollision(Sprite bullet, Sprite zombie) {
		
		int damage = (int) ((Bullet) bullet).getDamage(); 
		damage = -damage; 
		
		Zombie currentZombie = (Zombie) zombie;
		Bullet currentBullet = (Bullet) bullet;
		
		currentZombie.updateHealth(damage);
		
		
		if(currentZombie.healthIsZero())
		{
			currentZombie.setHealth(0);
			getZombieDeathFromBullet(currentZombie, currentBullet);
		}
	}
	
	/*
	 * Yang Su: How does the direction of your bullet class work?
	 * 
	 */
	
	private void getZombieDeathFromBullet(Zombie currentZombie, Bullet currentBullet)
	{
		
		
		
	}
	
	

	
	
	
}
