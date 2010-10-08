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
		Zombie currentZombie = (Zombie) zombie;
		Shooter currentPlayer = (Shooter) human;

		if(currentZombie.healthIsZero()) return;

		if(!currentZombie.isAbleToAttack())
		{
			currentZombie.updateAttactStep();
			return;
		}

		currentZombie.resetAttackDelayStep();

		int collisionSide = getCollisionSide();

		collisionSideEffect(collisionSide, currentZombie, currentPlayer);

	}


	private void collisionSideEffect(int collisionSide, Zombie currentZombie,
			Shooter currentPlayer) {

		int zombiedamage = - currentZombie.getDamage();
		currentPlayer.updateStatHealth(zombiedamage);
		
		if( collisionSide == LEFT_RIGHT_COLLISION)
			currentZombie.attackFrom("AttackFromRight");
			
		if( collisionSide == RIGHT_LEFT_COLLISION)
			currentZombie.attackFrom("AttackFromLeft");
			

		if( collisionSide == TOP_BOTTOM_COLLISION) 
			currentZombie.attackFrom("AttackFromBelow");
		
		if( collisionSide == BOTTOM_TOP_COLLISION)
			currentZombie.attackFrom("AttackFromAbove");
		
	}










}
