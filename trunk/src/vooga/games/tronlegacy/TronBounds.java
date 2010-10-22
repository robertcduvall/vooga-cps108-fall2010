package vooga.games.tronlegacy;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import com.golden.gamedev.object.collision.CollisionBounds;

public class TronBounds extends CollisionBounds {

	public TronBounds(Background backgr) {
		super(backgr);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void collided(Sprite sprite) {
		// TODO Auto-generated method stub
		sprite.setActive(false);
	}

}
