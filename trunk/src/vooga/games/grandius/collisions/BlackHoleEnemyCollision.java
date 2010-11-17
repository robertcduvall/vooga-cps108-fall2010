package vooga.games.grandius.collisions;

import java.awt.image.BufferedImage;

import vooga.engine.resource.Resources;
import vooga.games.grandius.DropThis;
import vooga.games.grandius.enemy.common.Enemy;
import vooga.games.grandius.weapons.BlackHole;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.sprite.VolatileSprite;

/**
 * This class takes care of collisions between a black hole
 * fired by the player and a common enemy - a Boomer or a Zipster. 
 * An instance of this class needs to be added to the playfield using 
 * the playfield's addCollisionGroup method. Once this is 
 * done,the playfield checks for a collision between a bullet
 * and an enemy every time the playfield is updated.
 * @author Bhawana, John, Se-Gil
 */
public class BlackHoleEnemyCollision extends BasicCollision{

	private DropThis grandius;
	
	public BlackHoleEnemyCollision(DropThis grandius) {
		super(grandius);
		this.grandius = grandius;
	}
	
	@Override
	public void collided(Sprite blackHole, Sprite enemy) {
		super.collided(blackHole, enemy);
		((BlackHole)(blackHole)).swallowEnemy();
		//TODO:make spinning animation for swallowing zipster into blackhole
		if (((BlackHole)(blackHole)).hitsRemaining()<=0) {
			blackHole.setActive(false);
		}
		enemy.setActive(false);
		BufferedImage[] images = Resources.getAnimation("Vortex");
		AnimatedSprite vortex = new VolatileSprite(images, enemy.getX(), enemy.getY());
		grandius.getPlayfield().add(vortex);
		grandius.updateScoreOnCollision(((Enemy)enemy).getScore());
		grandius.updateCashOnCollision(((Enemy)enemy).getCash());
	}

	
}