package vooga.games.cyberion.collisions;

import vooga.engine.core.Game;
import vooga.games.cyberion.sprites.playership.PlayerShip;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionBounds;

public class PlayerCollidesWall extends CollisionBounds {

	private Game game;

	public PlayerCollidesWall(Game game) {
		super(0, 0, game.getWidth(), game.getHeight());
		this.game = game;
	}

	public void collided(Sprite player) {
		collided((PlayerShip) player);
	}

	public void collided(PlayerShip player) {
		if (isCollisionSide(LEFT_COLLISION)) {
			player.setX(0);
		}
		if (isCollisionSide(RIGHT_COLLISION)) {
			player.setX(game.getWidth() - player.getWidth());
		}
		if (isCollisionSide(TOP_COLLISION)) {
			player.setY(0);
		}
		if (isCollisionSide(BOTTOM_COLLISION)) {
			player.setY(game.getHeight() - player.getHeight());
		}
	}

}
