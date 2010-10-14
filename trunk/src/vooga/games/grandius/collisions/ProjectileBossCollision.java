package vooga.games.grandius.collisions;

import java.awt.image.BufferedImage;

import vooga.engine.resource.Resources;
import vooga.games.grandius.Grandius;
import vooga.games.grandius.enemy.boss.reacher.Reacher;
import vooga.games.grandius.enemy.boss.reacher.ReacherEye;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.sprite.VolatileSprite;

public class ProjectileBossCollision extends BasicCollision {
	
private Grandius grandius;
	
	public ProjectileBossCollision(Grandius grandius) {
		super(grandius);
		this.grandius = grandius;
	}
	
	@Override
	public void collided(Sprite projectile, Sprite boss) {
		super.collided(projectile, boss);
		projectile.setActive(false);
		if (boss instanceof Reacher && ((Reacher) boss).deplete(10)) {
			boss.setActive(false);
			grandius.playSound(Resources.getMapping("LargeExplosionSound"));
			BufferedImage[] images = Resources.getAnimation("ReacherExplosion");
			AnimatedSprite explosion = new VolatileSprite(images, boss.getX(), boss.getY());
			grandius.getPlayfield().add(explosion);
		}
	}
}
