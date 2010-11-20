package vooga.games.grandius.collisions;

import java.awt.image.BufferedImage;

import vooga.engine.core.PlayField;
import vooga.engine.resource.Resources;
import vooga.games.grandius.DropThis;
import vooga.games.grandius.enemy.common.Enemy;

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

	private DropThis grandius;
	
	public ProjectileEnemyCollision(DropThis grandius) {
		super(grandius);
		this.grandius = grandius;
	}
	
	@Override
	public void collided(Sprite bullet, Sprite enemy) {
		super.collided(bullet, enemy);
		System.out.println("got here");
		bullet.setActive(false);
		enemy.setActive(false);
		//TODO trying to add explosions, but ConcurrentModificationsException results.
		//This is probably due to the PlayState's PlayField being updated
		//as well as being added to?
		BufferedImage[] images = Resources.getAnimation("explosionAnimation");
		AnimatedSprite explosion = new VolatileSprite(images, enemy.getX(), enemy.getY());
		PlayField newField = new PlayField();
		newField.add(explosion);
		//grandius.getPlayState().getRenderField().add(newField);
		//grandius.getPlayState().getUpdateField().add(newField);
		grandius.getPlayer().updateScore(((Enemy)enemy).getScore());
		grandius.getPlayer().updateCash(((Enemy)enemy).getCash());
	}

	
}