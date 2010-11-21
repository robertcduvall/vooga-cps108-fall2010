package vooga.games.mariogame;

import vooga.engine.core.Game;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;


public class MarioToItemCollision extends AdvanceCollisionGroup {
	
	public MarioToItemCollision(Game game) {
		super();
	}

	@Override
	public void collided(Sprite mario, Sprite item) {
		((MarioSprite) mario).actOnItem(item);
		item.setActive(false);
	}

}
