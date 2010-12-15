package vooga.games.zombies.collisions;

import vooga.games.zombies.Shooter;

import vooga.games.zombies.Constants;
import com.golden.gamedev.object.Sprite;

import vooga.engine.core.Game;
import vooga.engine.resource.Resources;

import com.golden.gamedev.object.collision.PreciseCollisionGroup;

/**
 * Collision Manager for shooter to shooter collision. Resets health to max health if shooter
 * is revivable
 * 
 * @author Cue, Kolodziejzyk, Townsend
 */
public class SSCollisionManager extends PreciseCollisionGroup implements
		Constants {

	Game currentGame;

	public SSCollisionManager(Game game) {
		currentGame = game;
	}

	/**
	 * Initiates post-collision behavior for the two shooters
	 */
	public void collided(Sprite player, Sprite otherPlayer) {
		actOnCollision(player, otherPlayer);
	}

	/**
	 * Processes the collision. Calculates the health of the primary player
	 * and if it'd zero, and the times he's been revived is under 2, then reset
	 * his health. Then add one to the times he's been revived
	 * 
	 * @param Shooter
	 *            player
	 * @param Shooter
	 *            otherPlayer
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
