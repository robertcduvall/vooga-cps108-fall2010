package vooga.games.marioclone;

import vooga.engine.player.control.ItemSprite;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

public class MarioToItemCollision extends AdvanceCollisionGroup {

	@Override
	public void collided(Sprite mario, Sprite item) {
		((MarioSprite) mario).actOnItem((ItemSprite) item);
		item.setActive(false);
	}

}
