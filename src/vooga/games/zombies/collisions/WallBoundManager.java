package vooga.games.zombies.collisions;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionBounds;

/**
 * This is the general manager to keep GameEntity inside the game
 * 
 * @author Jimmy Mu, Aaron Choi, Yang Su
 * 
 */
public class WallBoundManager extends CollisionBounds {

	public WallBoundManager(Background bg) {
		super(bg);
	}

	public void collided(Sprite gameObject) {
		revertPosition1();
	}
}
