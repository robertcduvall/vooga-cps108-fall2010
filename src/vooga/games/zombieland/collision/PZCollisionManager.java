package vooga.games.zombieland.collision;

import vooga.games.zombieland.Shooter;
import vooga.games.zombieland.Zombie;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.PreciseCollisionGroup;

/**
 * This is the basic collision class for player and zombie
 * 
 * @Author Jimmy Mu, Aaron Choi, Yang Su
 */

public class PZCollisionManager extends PreciseCollisionGroup {
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
		
		int attackDirection = (int) (currentZombie.getAttackDirection());
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
			currentPlayer.updateHealth(-currentZombie.getDamage());
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
	private void initiateAttackAnimation(int attackDirection,
			Zombie currentZombie) {
		String direction = "";
		switch (attackDirection) {
		case 0:
			direction = "AttackRight";
			break;
		case 1:
			direction = "AttackUp";
			break;
		case 2:
			direction = "AttackLeft";
			break;
		case 3:
			direction = "AttackDown";
			break;
		}
		currentZombie.setAttackAnimation(direction);
	}
}