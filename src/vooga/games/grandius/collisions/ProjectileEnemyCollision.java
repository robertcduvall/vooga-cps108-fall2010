package vooga.games.grandius.collisions;

import vooga.games.grandius.Grandius;
import com.golden.gamedev.object.Sprite;

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
	public void collided(Sprite playerSprite, Sprite enemy) {
		super.collided(playerSprite, enemy);
		enemy.setActive(false);
		grandius.updateScoreOnCollision();
	}

	
}