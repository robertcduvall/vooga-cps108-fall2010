package vooga.games.random;

import java.awt.image.*;

// GTGE
import com.golden.gamedev.object.*;
import com.golden.gamedev.object.sprite.*;
import com.golden.gamedev.object.collision.*;

class BombBallCollision extends BasicCollisionGroup {

	ReachTheTop owner;

	public BombBallCollision(ReachTheTop owner) {
		this.owner = owner; // set the game owner
		// this is used for getting image and adding explosion to playfield
	}

	// actions to take when bomb (player,group a) collides with a ball
	// (enemy,group b)
	public void collided(Sprite s1, Sprite s2) {

		// show explosion on the center of the exploded enemy
		// use VolatileSprite -> sprite that animates once and vanishes afterward
		BufferedImage[] images = owner.getImages("resources/explosion.png", 7,
				1);
		VolatileSprite explosion = new VolatileSprite(images, s2.getX(), s2
				.getY());

		owner.deductLife();
		if (owner.livesLeft()) {
			s1.setActive(true);
			s2.setActive(false);
			s1.setX(owner.myStartX);
			s1.setY(owner.myStartY);
		} else {
			s1.setActive(false);
			s2.setActive(false);
		}

		// directly add to playfield without using SpriteGroup
		// the sprite is added into a reserved extra sprite group in playfield
		// extra sprite group is used specially for animation effects in game
		owner.myPlayfield.add(explosion);
	}

}
