package vooga.games.grandius.collisions;

import vooga.engine.core.Game;

import com.golden.gamedev.object.Sprite;

/**
 * This class takes care of collisions between the player
 * and a BossPart. An instance 
 * of this class needs to be added to the playfield using 
 * the playfield's addCollisionGroup method. Once this is 
 * done,the playfield checks for a collision between the player
 * and an enemy every time the playfield is updated.
 * @author Bhawana, John, Se-Gil
 */
public class PlayerBossPartCollision extends BasicCollision{

	public PlayerBossPartCollision(Game grandius) {
		super(grandius);
	}
	
	@Override
	public void collided(Sprite playerSprite, Sprite enemy) {
		super.collided(playerSprite, enemy);
		getPlayer().updatePlayerLives();
	}

	
}
