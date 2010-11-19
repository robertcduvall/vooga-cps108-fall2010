package vooga.games.grandius.collisions;

import java.awt.image.BufferedImage;

import vooga.engine.core.PlayField;
import vooga.engine.resource.Resources;
import vooga.games.grandius.DropThis;
import vooga.games.grandius.enemy.boss.reacher.Reacher;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.sprite.VolatileSprite;

public class ProjectileBossCollision extends BasicCollision {
	
private DropThis grandius;
	
	public ProjectileBossCollision(DropThis grandius) {
		super(grandius);
		this.grandius = grandius;
	}
	
	@Override
	public void collided(Sprite projectile, Sprite boss) {
		super.collided(projectile, boss);
		projectile.setActive(false);
		if (boss instanceof Reacher && ((Reacher) boss).deplete(10)) {
			boss.setActive(false);
			grandius.playSound(Resources.getSound("LargeExplosionSound"));
			BufferedImage[] images = Resources.getAnimation("ReacherExplosion");
			AnimatedSprite explosion = new VolatileSprite(images, boss.getX(), boss.getY());
			PlayField newField = new PlayField();
			newField.add(explosion);
			grandius.getPlayState().getRenderField().add(newField);
			grandius.getPlayState().getUpdateField().add(newField);
		}
	}
}
