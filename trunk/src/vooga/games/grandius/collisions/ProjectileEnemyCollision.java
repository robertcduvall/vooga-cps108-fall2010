package vooga.games.grandius.collisions;


import vooga.engine.core.Game;

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

	public ProjectileEnemyCollision(Game grandius) {
		super(grandius);
	}
	
	@Override
	public void collided(Sprite bullet, Sprite enemy) {
		super.collided(bullet, enemy);
		bullet.setActive(false);
		enemy.setActive(false);
//		getPlayer().updateScore(((Enemy)enemy).getScore());
//		getPlayer().updateCash(((Enemy)enemy).getCash());
	}

	
}