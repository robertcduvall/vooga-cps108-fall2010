package vooga.games.grandius.collisions;

import java.awt.image.BufferedImage;

import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.resource.Resources;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.sprite.VolatileSprite;

public class PlayerEnemyProjectileCollision extends BasicCollision {

	
	public PlayerEnemyProjectileCollision(Game grandius) {
		super(grandius);
	}
	
	@Override
	public void collided(Sprite player, Sprite laser) {
		super.collided(player, laser);
		laser.setActive(false);
		BufferedImage[] images = Resources.getAnimation("explosionAnimation");
		AnimatedSprite explosion = new VolatileSprite(images, player.getX(), player.getY());
		PlayField newField = new PlayField();
		newField.add(explosion);
		getPlayer().updatePlayerLives();
	}

	
}
