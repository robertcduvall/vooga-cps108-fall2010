package vooga.games.grandius.collisions;

import java.awt.image.BufferedImage;

import vooga.engine.core.PlayField;
import vooga.engine.resource.Resources;
import vooga.games.grandius.DropThis;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.sprite.VolatileSprite;

public class PlayerEnemyProjectileCollision extends BasicCollision {

	private DropThis grandius;
	
	public PlayerEnemyProjectileCollision(DropThis grandius) {
		super(grandius);
		this.grandius = grandius;
	}
	
	@Override
	public void collided(Sprite player, Sprite laser) {
		super.collided(player, laser);
		laser.setActive(false);
		BufferedImage[] images = Resources.getAnimation("Explosion");
		AnimatedSprite explosion = new VolatileSprite(images, player.getX(), player.getY());
		PlayField newField = new PlayField();
		newField.add(explosion);
		grandius.getPlayState().getRenderField().add(newField);
		grandius.getPlayState().getUpdateField().add(newField);
		grandius.getPlayer().updatePlayerLives();
	}

	
}
