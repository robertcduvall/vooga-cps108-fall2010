package vooga.games.grandius.collisions;

import vooga.engine.player.control.PlayerSprite;
import vooga.games.grandius.Grandius;
import vooga.games.grandius.enemy.common.Boomer;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

/**
 * This class takes care of collisions between the player
 * and a Boomer. An instance of this class needs to be added to
 * the playfield using the playfield's addCollisionGroup method. 
 * Once this is done,the playfield checks for a collision between 
 * the player and a Boomer every time the playfield is updated.
 * @author bhawana
 */
public class PlayerBoomerCollision extends BasicCollisionGroup {

	private Grandius grandius;
	
	public PlayerBoomerCollision(Grandius grandius) {
		super();
		this.grandius = grandius;
	}
	
	@Override
	public void collided(Sprite playerSprite, Sprite boomerSprite) {		
		Boomer boomer = (Boomer) boomerSprite;
		PlayerSprite player = (PlayerSprite) playerSprite;
		//TODO - add an explosion image
		boomer.setActive(false);
		grandius.updatePlayerLives();
	}
	
}
