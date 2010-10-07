package vooga.games.galaxyinvaders;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.*;
import vooga.engine.player.control.*;

public class TorpedoPlayerCollider extends BasicCollisionGroup {

	private Game g;
	
	public TorpedoPlayerCollider(Game game) {
		g = game;
	}
	@Override
	public void collided(Sprite s1, Sprite s2) {
		s1.setActive(false);
		((PlayerSprite) s2).updateLives(-1);
		s2.setX(g.getWidth()/2);
	}

}
