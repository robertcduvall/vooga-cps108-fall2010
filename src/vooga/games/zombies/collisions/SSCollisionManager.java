package vooga.games.zombies.collisions;

import vooga.games.zombies.Shooter;
import vooga.games.zombies.Zombie;
import vooga.games.zombies.weapons.Bullet;
import vooga.games.zombies.Constants;
import vooga.games.zombies.gamestates.*;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;

import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;

import vooga.games.zombies.Blah;

import com.golden.gamedev.object.collision.PreciseCollisionGroup;

/**
 * Collision Manager for shooter to shooter collision. Resets health if shooter
 * is revived
 * 
 * @author Cue, Kolodziejzyk, Townsend
 */
public class SSCollisionManager extends PreciseCollisionGroup implements
		Constants {

	Game currentGame;

	// GameState endGameState;

	public SSCollisionManager(Game game) {
		currentGame = game;
		// endGameState = endGame;
	}

	/**
	 * Initiates post-collision behavior for the zombie and the bullet
	 */
	public void collided(Sprite player, Sprite otherPlayer) {
		actOnCollision(player, otherPlayer);
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
	private void actOnCollision(Sprite player, Sprite otherPlayer) {
		Shooter shooter = (Shooter) player;

		if (!player.equals(otherPlayer)) {

			// Process bullet damage
			if (shooter.getHealth().getStat() <= 0
					&& shooter.getTimesRevived() < 2) {
				shooter.setHealth(Resources.getInt("maxHealth"));
				shooter.setTimesRevived(shooter.getTimesRevived() + 1);
			}
		}

	}

}
