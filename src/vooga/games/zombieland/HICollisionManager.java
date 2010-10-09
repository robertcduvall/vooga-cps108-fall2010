package vooga.games.zombieland;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

/**
 * This is the Human Item Collision sytem
 * @author Aaron Choi, Jimmy Mu, Yang Su
 *
 */

public class HICollisionManager extends BasicCollisionGroup{

	public void collided(Sprite human, Sprite item) {

		Item currentItem = (Item) item;
		
		currentItem.act();
		currentItem.setActive(false);
	}

}
