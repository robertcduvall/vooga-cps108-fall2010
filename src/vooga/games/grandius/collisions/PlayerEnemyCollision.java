package vooga.games.grandius.collisions;

import vooga.engine.core.Game;
import vooga.games.grandius.Blah;

import com.golden.gamedev.object.Sprite;

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

	private Game myGame;
	
	public PlayerEnemyCollision(Game game) {
		super(game);
		this.myGame = game;
	}
	
	@Override
	public void collided(Sprite playerSprite, Sprite enemy) {
		super.collided(playerSprite, enemy);
		//myGame.updatePlayerLives();
	}

	
}
