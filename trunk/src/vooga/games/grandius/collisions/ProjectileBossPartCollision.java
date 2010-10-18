package vooga.games.grandius.collisions;

import java.awt.image.BufferedImage;

import vooga.engine.resource.ResourcesBeta;
import vooga.games.grandius.Grandius;
import vooga.games.grandius.enemy.boss.reacher.ReacherEye;
import vooga.games.grandius.enemy.common.Enemy;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.sprite.VolatileSprite;

public class ProjectileBossPartCollision extends BasicCollision {

	private Grandius grandius;
	
	public ProjectileBossPartCollision(Grandius grandius) {
		super(grandius);
		this.grandius = grandius;
	}
	
	
	public void collided(Sprite projectile, Sprite bosspart) {
		super.collided(projectile, bosspart);
		projectile.setActive(false);
		if (bosspart instanceof ReacherEye && ((ReacherEye) bosspart).deplete(10)) {
			bosspart.setActive(false);
			grandius.playSound(ResourcesBeta.getSound("LargeExplosionSound"));
			BufferedImage[] images = ResourcesBeta.getAnimation("EyeExplosion");
			AnimatedSprite explosion = new VolatileSprite(images, bosspart.getX(), bosspart.getY());
			grandius.getPlayfield().add(explosion);
		}
		grandius.updateScoreOnCollision(((Enemy)bosspart).getScore());
		grandius.updateCashOnCollision(((Enemy)bosspart).getCashValue());
	}
}
