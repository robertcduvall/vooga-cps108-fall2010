package vooga.games.grandius.collisions;

import vooga.engine.resource.Resources;
import vooga.games.grandius.Blah;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;


/**
 * This class takes care of collisions between a player-side
 * sprite (player itself or the fired bullet)and a common enemy 
 * which could be a Boomer or a Zipster. 
 * @author bhawana
 */
public class BasicCollision extends BasicCollisionGroup {

	private Blah grandius;
	
	public BasicCollision(Blah grandius) {
		super();
		this.grandius = grandius;
	}
	
	@Override
	public void collided(Sprite playerSideSprite, Sprite commonEnemy) {
		//TODO - add an explosion sound (done)
		grandius.playSound(Resources.getSound("ExplosionSound"));
		// make the explosion image stay longer
	}
	
}
