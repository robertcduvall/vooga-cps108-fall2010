package vooga.games.cyberion.collisions;

import vooga.engine.core.Game;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class ShotsCollide extends BasicCollisionGroup {
	
	public ShotsCollide(Game game)
	{
		super();
	}
	
	public void collided(Sprite playerShot, Sprite enemyShot) {

		playerShot.setActive(false);
		enemyShot.setActive(false);
	}

}
