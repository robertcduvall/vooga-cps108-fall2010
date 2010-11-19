package vooga.games.grandius.collisions;

import vooga.games.grandius.DropThis;
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

	private DropThis grandius;
	
	public PlayerBossPartCollision(DropThis grandius) {
		super(grandius);
		this.grandius = grandius;
	}
	
	@Override
	public void collided(Sprite playerSprite, Sprite enemy) {
		super.collided(playerSprite, enemy);
		grandius.getPlayer().updatePlayerLives();
	}

	
}
