package vooga.games.grandius.collisions;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import vooga.engine.player.control.PlayerSprite;
import vooga.engine.resource.Resources;
import vooga.games.grandius.Grandius;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import com.golden.gamedev.object.sprite.VolatileSprite;

/**
 * This class takes care of collisions between the player
 * and a common enemy - a Boomer or a Zipster. An instance 
 * of this class needs to be added to the playfield using 
 * the playfield's addCollisionGroup method. Once this is 
 * done,the playfield checks for a collision between the player
 * and an enemy every time the playfield is updated.
 * @author bhawana
 */
public class PlayerEnemyCollision extends BasicCollisionGroup {

	private Grandius grandius;
	
	public PlayerEnemyCollision(Grandius grandius) {
		super();
		this.grandius = grandius;
	}
	
	@Override
	public void collided(Sprite playerSprite, Sprite commonEnemy) {
		PlayerSprite player = (PlayerSprite) playerSprite;
		grandius.updatePlayerLives();

		//TODO - add an explosion sound
		// make the explosion image stay longer
		BufferedImage[] images = Resources.getAnimation("Explosion");
		AnimatedSprite explosion = new VolatileSprite(images, player.getX(), player.getY());
		grandius.getPlayfield().add(explosion);
	}

	
}
