package vooga.games.grandius.collisions;

import vooga.engine.core.Game;

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
	
	public PlayerEnemyCollision(Game grandius) {
		super(grandius);
	}
	
	@Override
	public void collided(Sprite playerSprite, Sprite enemy) {
		super.collided(playerSprite, enemy);
		int livesLeft = getPlayer().updatePlayerLives();
		if (livesLeft <= 0)
			myGame.getGameStateManager().switchTo(myGame.getGameStateManager().getGameState(1));
			
	}

	
}
