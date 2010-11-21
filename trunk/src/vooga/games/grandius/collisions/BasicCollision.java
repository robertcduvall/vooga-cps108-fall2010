package vooga.games.grandius.collisions;

import vooga.engine.core.Game;
import vooga.engine.resource.Resources;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;


/**
 * This class takes care of collisions between a player-side
 * sprite (player itself or the fired bullet)and a common enemy 
 * which could be a Boomer or a Zipster. 
 * @author bhawana
 */
public class BasicCollision extends BasicCollisionGroup {

	//TODO the collision classes can definitely be cleaned up - lots of repeated code right now.
	private Game game;
	
	public BasicCollision(Game grandius) {
		super();
		this.game = grandius;
	}
	
	@Override
	public void collided(Sprite playerSideSprite, Sprite commonEnemy) {
		//TODO - add an explosion sound (done)
		game.playSound(Resources.getSound("explosionSound"));
		// make the explosion image stay longer
	}
	
}
