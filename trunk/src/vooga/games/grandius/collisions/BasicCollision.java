package vooga.games.grandius.collisions;

import java.awt.image.BufferedImage;

import vooga.engine.resource.Resources;
import vooga.games.grandius.Grandius;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import com.golden.gamedev.object.sprite.VolatileSprite;

/**
 * This class takes care of collisions between a player-side
 * sprite (player itself or the fired bullet)and a common enemy 
 * which could be a Boomer or a Zipster. 
 * @author bhawana
 */
public class BasicCollision extends BasicCollisionGroup {

	private Grandius grandius;
	
	public BasicCollision(Grandius grandius) {
		super();
		this.grandius = grandius;
	}
	
	@Override
	public void collided(Sprite playerSideSprite, Sprite commonEnemy) {
		//TODO - add an explosion sound
		// make the explosion image stay longer
		BufferedImage[] images = Resources.getAnimation("Explosion");
		AnimatedSprite explosion = new VolatileSprite(images, commonEnemy.getX(), commonEnemy.getY());
		grandius.getPlayfield().add(explosion);
	}

	
}
