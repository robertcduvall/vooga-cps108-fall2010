package vooga.games.grandius.collisions;

import java.awt.image.BufferedImage;

import vooga.engine.resource.Resources;
import vooga.games.grandius.Grandius;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.sprite.VolatileSprite;

public class PlayerEnemyProjectileCollision extends BasicCollision {

	private Grandius grandius;
	
	public PlayerEnemyProjectileCollision(Grandius grandius) {
		super(grandius);
		this.grandius = grandius;
	}
	
	@Override
	public void collided(Sprite player, Sprite laser) {
		super.collided(player, laser);
		laser.setActive(false);
		BufferedImage[] images = Resources.getAnimation("Explosion");
		AnimatedSprite explosion = new VolatileSprite(images, player.getX(), player.getY());
		grandius.getPlayfield().add(explosion);
		grandius.updatePlayerLives();
	}

	
}
