package vooga.games.tronupdate.collisions;
/**
 * This class handles the player and enemy collision
 * @author Meng Li,Brent Sodman,JiaQi Yan
 */
import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameStateManager;
import vooga.games.tronupdate.events.SwitchLevelEvent;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class  PlayerAndEnemyCollision extends BasicCollisionGroup {
	
	private Game game;
	private GameStateManager gameStateManager;
	
	public PlayerAndEnemyCollision(Game game,GameStateManager gameStateManager) {
		this.game=game;
		pixelPerfectCollision = true;
		this.gameStateManager=gameStateManager;
	}
	/**
	 * handles the collision
	 */
	public void collided(Sprite s1, Sprite s2) {	
		s1.setActive(false);   //  this doesn't work in our cases	
		s2.setActive(false);		
		game.playSound(Resources.getSound("explosionSound"));	
		gameStateManager.switchTo(gameStateManager.getGameState(2));
	}
}
