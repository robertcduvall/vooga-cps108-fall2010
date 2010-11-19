package vooga.games.galaxyinvaders.collisions;

import vooga.engine.core.Game;
import vooga.games.galaxyinvaders.DropThis;
import vooga.games.galaxyinvaders.states.GalaxyGameState;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
/**
 * This class is an extension of BasicCollisionGroup, it handles collisions 
 * between powerups and players.
 * 
 * @author Drew Sternesky, Kate Yang, Nick Hawthorne
 *
 */
public class ItemPlayerCollider extends BasicCollisionGroup {

	private static int POWER_UP_AMOUNT = 1;
	
	private DropThis game;
	private GalaxyGameState playGameState;
	
	public ItemPlayerCollider(Game g) {
		super();
		game = (DropThis) g;
		playGameState = game.getPlayGameState();
		pixelPerfectCollision = true;
	}
	
	@Override
	public void collided(Sprite s1, Sprite s2) {
		s1.setActive(false);
		playGameState.changePlayerLives(POWER_UP_AMOUNT);
		
	}
	
	

}
