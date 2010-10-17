package vooga.games.galaxyinvaders;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.*;
import vooga.engine.player.control.*;

/**
 * This class is an extension of BasicCollisionGroup, it handles collisions 
 * between torpedoes and players.
 * 
 * @author Drew Sternesky, Kate Yang, Nick Hawthorne
 *
 */
public class TorpedoPlayerCollider extends BasicCollisionGroup {

	private Game g;
	
	public TorpedoPlayerCollider(Game game) {
		g = game;
		pixelPerfectCollision = true;
	}
	@Override
	public void collided(Sprite s1, Sprite s2) {
		s1.setActive(false);
		((PlayerSprite) s2).getStat("lives").addTo(-1);
		s2.setX(g.getWidth()/2);
	}

}
