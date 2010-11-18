package vooga.games.galaxyinvaders;


import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.*;

import vooga.engine.core.Game;

/**
 * This class is an extension of BasicCollisionGroup, it handles collisions 
 * between torpedoes and players.
 * 
 * @author Drew Sternesky, Kate Yang, Nick Hawthorne
 *
 */
public class TorpedoPlayerCollider extends BasicCollisionGroup {

	private DropThis g;
	
	private static final int LIVES_LOST = 1;
	
	public TorpedoPlayerCollider(Game game) {
		g = (DropThis) game;
		pixelPerfectCollision = true;
	}
	@Override
	public void collided(Sprite s1, Sprite s2) {
		s1.setActive(false);
		g.livesStat.setStat(g.livesStat.getStat()-LIVES_LOST);
		s2.setX(g.getWidth()/2);
	}

}
