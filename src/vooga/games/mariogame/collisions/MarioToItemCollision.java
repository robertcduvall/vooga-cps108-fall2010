package vooga.games.mariogame.collisions;

import vooga.engine.core.Game;
import vooga.games.mariogame.items.Item;
import vooga.games.mariogame.sprites.MarioSprite;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;


public class MarioToItemCollision extends AdvanceCollisionGroup {
	
	public MarioToItemCollision(Game game) {
		super();
	}

	@Override
	public void collided(Sprite mario, Sprite item) {
		((MarioSprite) mario).actOnItem((Item) item);
		item.setActive(false);
	}

}
