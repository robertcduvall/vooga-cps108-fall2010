package vooga.games.grandius.collisions;

import vooga.engine.core.Game;
import vooga.games.grandius.DropThis;

import com.golden.gamedev.object.Sprite;

/**
 * This class takes care of collisions between the player
 * and a Boss. An instance 
 * of this class needs to be added to the playfield using 
 * the playfield's addCollisionGroup method. Once this is 
 * done,the playfield checks for a collision between the player
 * and an enemy every time the playfield is updated.
 * @author Bhawana, John, Se-Gil
 */
public class PlayerBossCollision extends BasicCollision{

	private Game grandius;
	
	
	public PlayerBossCollision(Game grandius) {
		super(grandius);
		this.grandius = grandius;
	}
	
	@Override
	public void collided(Sprite playerSprite, Sprite enemy) {
		super.collided(playerSprite, enemy);
		getPlayer().updatePlayerLives();
	}

	
}
