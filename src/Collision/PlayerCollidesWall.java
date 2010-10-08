package Collision;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionBounds;

public class PlayerCollidesWall extends CollisionBounds {

	private Background bg;

	public PlayerCollidesWall(Background bg) {
		super(bg);
		this.bg = bg;
	}
	//prevents player from going off the screen
	@Override
	public void collided(Sprite player) {
		if (isCollisionSide(BOTTOM_COLLISION)) {
			player.setY(bg.getHeight() - player.getHeight());
		}
		if (isCollisionSide(TOP_COLLISION)) {
			player.setY(0);
		}
		if (isCollisionSide(LEFT_COLLISION)) {
			player.setX(0);
		}
		if (isCollisionSide(RIGHT_COLLISION)) {
			player.setX(bg.getWidth() - player.getWidth());
		}
	}
}
