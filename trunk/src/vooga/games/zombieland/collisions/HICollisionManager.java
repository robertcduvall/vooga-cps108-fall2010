package vooga.games.zombieland.collisions;

import vooga.games.zombieland.Blah;
import vooga.games.zombieland.items.Item;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

/**
 * This is the Human Item Collision sytem
 * @author Aaron Choi, Jimmy Mu, Yang Su
 *
 */

public class HICollisionManager extends BasicCollisionGroup{
	
	Blah currentGame;
	
	public HICollisionManager(Blah game)
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
