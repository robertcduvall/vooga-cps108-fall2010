package vooga.games.grandius.collisions;

import vooga.engine.resource.ResourcesBeta;
import vooga.games.grandius.GrandiusMain;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;


/**
 * This class takes care of collisions between a player-side
 * sprite (player itself or the fired bullet)and a common enemy 
 * which could be a Boomer or a Zipster. 
 * @author bhawana
 */
public class BasicCollision extends BasicCollisionGroup {

	private GrandiusMain grandius;
	
	public BasicCollision(GrandiusMain grandius) {
		super();
		this.grandius = grandius;
	}
	
	@Override
	public void collided(Sprite playerSideSprite, Sprite commonEnemy) {
		//TODO - add an explosion sound (done)
		grandius.playSound(ResourcesBeta.getSound("ExplosionSound"));
		// make the explosion image stay longer
	}
	
}
