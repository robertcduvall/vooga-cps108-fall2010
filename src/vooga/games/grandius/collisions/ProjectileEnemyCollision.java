package vooga.games.grandius.collisions;

import java.awt.image.BufferedImage;

import vooga.engine.resource.Resources;
import vooga.games.grandius.Grandius;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.sprite.VolatileSprite;

/**
 * This class takes care of collisions between a bullet
 * fired by the player and a common enemy - a Boomer or a Zipster. 
 * An instance of this class needs to be added to the playfield using 
 * the playfield's addCollisionGroup method. Once this is 
 * done,the playfield checks for a collision between a bullet
 * and an enemy every time the playfield is updated.
 * @author bhawana
 */
public class ProjectileEnemyCollision extends BasicCollision{

	private Grandius grandius;
	
	public ProjectileEnemyCollision(Grandius grandius) {
		super(grandius);
		this.grandius = grandius;
	}
	
	@Override
	public void collided(Sprite bullet, Sprite enemy) {
		super.collided(bullet, enemy);
		bullet.setActive(false);
		enemy.setActive(false);
		BufferedImage[] images = Resources.getAnimation("Explosion");
		AnimatedSprite explosion = new VolatileSprite(images, enemy.getX(), enemy.getY());
		grandius.getPlayfield().add(explosion);
		grandius.updateScoreOnCollision();
	}

	
}