package vooga.games.zombies.collisions;

import vooga.engine.core.Game;
import vooga.games.zombies.items.Item;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

/**
 * This is the Human Item Collision sytem
 * @author Aaron Choi, Jimmy Mu, Yang Su
 *
 */

public class HICollisionManager extends BasicCollisionGroup{
	
	Game currentGame;
	
	public HICollisionManager(Game game)
	{
		currentGame = game;
	}
	
	/**
	 * processes the collision
	 */
	public void collided(Sprite human, Sprite item) {

		Item currentItem = (Item) item;

		currentItem.act();
		currentItem.setActive(false);
	}

}
