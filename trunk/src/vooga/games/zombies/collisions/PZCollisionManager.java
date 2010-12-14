package vooga.games.zombies.collisions;

import vooga.engine.core.Game;
import vooga.games.zombies.Constants;
import vooga.games.zombies.Shooter;
import vooga.games.zombies.Zombie;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.PreciseCollisionGroup;

/**
 * This is the basic collision class for player and zombie
 * 
 * @Author Jimmy Mu, Aaron Choi, Yang Su
 */

public class PZCollisionManager extends PreciseCollisionGroup implements
Constants {
	
	Game currentGame;
	
	public PZCollisionManager(Game game)
	{
		currentGame = game;
	}
	
	/**
	 * Initiates post-collision behavior for the zombie
	 */
	public void collided(Sprite player, Sprite zombie) {
		actOnCollision(player, zombie);
	}

	/**
	 * Calculates the damage a zombie does on the shooter and initiates
	 * animation for an attack
	 * 
	 * @param player
	 *            player
	 * @param zombie
	 *            a zombie
	 */
	private void actOnCollision(Sprite player, Sprite zombie) {
		Zombie currentZombie = (Zombie) zombie;
		Shooter currentPlayer = (Shooter) player;

		processDamage(currentZombie, currentPlayer);

		String attackDirection = currentZombie.getDirection();
		initiateAttackAnimation(attackDirection, currentZombie);
	}

	/**
	 * Checks to see if a zombie is ready to make an attack, if so, subtracts
	 * from the shooter's health
	 * 
	 * @param currentZombie
	 * @param currentPlayer
	 */
	private void processDamage(Zombie currentZombie, Shooter currentPlayer) {
		if (currentZombie.isAbleToAttack()) {
			currentZombie.resetAttackDelayStep();
			currentPlayer.updateHealth((int) -currentZombie.getDamage());
		} else
			currentZombie.updateAttackStep();
	}

	/**
	 * Initiate the attack animation on the zombie according to its attack
	 * direction
	 * 
	 * @param attackDirection
	 *            zombie attack direction
	 * @param currentZombie
	 *            a zombie
	 */
	private void initiateAttackAnimation(String attackDirection,
			Zombie currentZombie) {
		String direction = "";
		if (attackDirection.equals(ZOMBIE_RIGHT)) {
			direction = ATTACKRIGHT;
		}
		else if (attackDirection.equals(ZOMBIE_UP)) {
			direction = ATTACKUP;
		}
		else if (attackDirection.equals(ZOMBIE_LEFT)) {
			direction = ATTACKLEFT;
		}
		else {
			direction = ATTACKDOWN;
		}

		currentZombie.setAttackAnimation(direction);
	}
}