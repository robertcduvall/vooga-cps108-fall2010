package vooga.games.cyberion.collisions;

import vooga.engine.core.Game;
import vooga.engine.overlay.Stat;
import vooga.engine.resource.Resources;
import vooga.games.cyberion.sprites.playership.PlayerShip;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import com.golden.gamedev.object.collision.CollisionBounds;
import com.golden.gamedev.object.collision.CollisionShape;

public class PlayerCollidesWall extends BasicCollisionGroup {

	private Game game;

	public PlayerCollidesWall(Game game) {
		super();
		this.game = game;
	}

	@Override
	public boolean isCollide(Sprite s1, Sprite s2, CollisionShape shape1,
			CollisionShape shape2) {
		System.out.println("collide");
		return s1.getX() < 0 || s1.getY() < 0 || s1.getX() > game.getWidth()
				|| s1.getY() > game.getHeight();
	}

	public void collided(Sprite player1, Sprite player2) {
		collided((PlayerShip) player1, (PlayerShip) player2);
	}

	// grants an increase in weapon power when player collides with a bonus
	// sprite
	public void collided(PlayerShip player1, PlayerShip player2) {
		if (player1.getX() < 0) {
			player1.setX(0);
		}
		if (player1.getX() > game.getWidth()) {
			player1.setX(game.getWidth());
		}
		if (player1.getY() < 0) {
			player1.setY(0);
		}
		if (player1.getY() > game.getHeight()) {
			player1.setY(game.getHeight());
		}
	}

}
