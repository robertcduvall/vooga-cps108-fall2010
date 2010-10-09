package vooga.games.zombieland;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.PreciseCollisionGroup;

/**
 * This is the basic collision class for player and zombie 
 * @Author Jimmy Mu, Aaron Choi, Yang Su
 */

public class HZCollisionManager extends PreciseCollisionGroup{


	public void collided(Sprite human, Sprite zombie) {
		actOnCollision(human, zombie);

	}

	private void actOnCollision(Sprite human, Sprite zombie){
		Zombie currentZombie = (Zombie) zombie;
		Shooter currentPlayer = (Shooter) human;

		if(currentZombie.isAbleToAttack())
		{
			currentZombie.resetAttackDelayStep();
			currentPlayer.updateStatHealth(-currentZombie.getDamage());
		}
		else
			currentZombie.updateAttactStep();
		

		int collisionSide= (int) (((Zombie)zombie).getAttackDirection());

		collisionSideEffect(collisionSide, currentZombie, currentPlayer);

	}


	private void collisionSideEffect(int collisionSide, Zombie currentZombie,
			Shooter currentPlayer) {
		int zombiedamage = - currentZombie.getDamage();

		switch (collisionSide) {
		case 0:
			currentZombie.attackFrom("AttackRight");break;
		case 1:
			currentZombie.attackFrom("AttackUp");break;
		case 2:
			currentZombie.attackFrom("AttackLeft");break;
		case 3:
			currentZombie.attackFrom("AttackDown");break;
		}
	}










}
