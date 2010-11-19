package vooga.games.galaxyinvaders.collisions;


import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.*;

import vooga.engine.core.Game;
import vooga.games.galaxyinvaders.DropThis;
import vooga.games.galaxyinvaders.states.GalaxyGameState;

/**
 * This class is an extension of BasicCollisionGroup, it handles collisions 
 * between torpedoes and players.
 * 
 * @author Drew Sternesky, Kate Yang, Nick Hawthorne
 *
 */
public class TorpedoPlayerCollider extends BasicCollisionGroup {

	private DropThis game;
	private GalaxyGameState playGameState;
	
	private static final int LIVES_LOST = 1;
	
	public TorpedoPlayerCollider(Game g) {
		game = (DropThis) g;
		pixelPerfectCollision = true;
	}
	@Override
	public void collided(Sprite s1, Sprite s2) {
		s1.setActive(false);
		playGameState.changePlayerLives(-LIVES_LOST);
		s2.setX(game.getWidth()/2);
	}

}
