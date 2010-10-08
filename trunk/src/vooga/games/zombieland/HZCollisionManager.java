package vooga.games.zombieland;

import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.PreciseCollisionGroup;

/*
 * This is the basic collision class for player and zombie 
 * Author: Jimmy Mu, Aaron Choi, Yang Su
 */

public class HZCollisionManager extends PreciseCollisionGroup{


	public void collided(Sprite human, Sprite zombie) {
		
		actOnCollision(human, zombie);
		
	}
	
	private void actOnCollision(Sprite human, Sprite zombie)
	{
		int collisionSide = getCollisionSide();
		
		if( collisionSide == LEFT_RIGHT_COLLISION)
			zombieAttackFromRight(human, zombie);
		
		if( collisionSide == RIGHT_LEFT_COLLISION)
			zombieAttackFromLeft(human, zombie);
		
		if( collisionSide == TOP_BOTTOM_COLLISION) 
			zombieAttackFromBelow(human, zombie);
		
		if( collisionSide == BOTTOM_TOP_COLLISION)
			zombieAttackFromAbove(human, zombie);
	}
	
	/**
	 * check the directions of zombie attacking relative to the player
	 * still need to add how the players are affected by this.
	 * @param collisionSide
	 * @param human
	 * @param zombie
	 */

	private void zombieAttackFromBelow(Sprite human, Sprite zombie) {
		
			( (Zombie) zombie).attackFrom("AttackFromBelow");
			((Shooter) human).updateStatHealth(-1);
	}

	private void zombieAttackFromAbove(Sprite human, Sprite zombie) {
		
			( (Zombie) zombie).attackFrom("AttackFromAbove");
			((Shooter) human).updateStatHealth(-1);
	}

	private void zombieAttackFromLeft(Sprite human, Sprite zombie) {
	
			( (Zombie) zombie).attackFrom("AttackFromLeft");
			((Shooter) human).updateStatHealth(-1);
	}

	private void zombieAttackFromRight(Sprite human, Sprite zombie) {
		
			( (Zombie) zombie).attackFrom("AttackFromRight");
			((Shooter) human).updateStatHealth(-1);
			
	}
	
	
	
}
