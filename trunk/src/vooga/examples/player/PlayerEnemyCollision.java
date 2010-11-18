package vooga.examples.player;

import com.golden.gamedev.object.Sprite;

import vooga.engine.core.Game;
import vooga.games.grandius.Blah;
import vooga.games.grandius.collisions.BasicCollision;


/**
 * This class takes care of collisions between the player
 * and a common enemy - a Boomer or a Zipster. An instance 
 * of this class needs to be added to the playfield using 
 * the playfield's addCollisionGroup method. Once this is 
 * done,the playfield checks for a collision between the player
 * and an enemy every time the playfield is updated.
 * @author bhawana
 */
public class PlayerEnemyCollision extends BasicCollision{
	
	public PlayerEnemyCollision(Game game) {
		super(game);
	}
	
	@Override
	public void collided(Sprite playerSprite, Sprite enemy) {
		super.collided(playerSprite, enemy);
		//as an example, on collision, the player is set to switch to a different animation
		((vooga.engine.core.BetterSprite)playerSprite).setAsRenderedSprite("rightFacingDoodleAnimation");
		//myGame.updatePlayerLives();
	}

	
}
