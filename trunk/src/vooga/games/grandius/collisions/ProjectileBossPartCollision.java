package vooga.games.grandius.collisions;

import java.awt.image.BufferedImage;

import vooga.engine.resource.Resources;
import vooga.games.grandius.Grandius;
import vooga.games.grandius.enemy.boss.BossPart;
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
	
	
	public void collided(Sprite bullet, Sprite enemy) {
		super.collided(bullet, enemy);
		bullet.setActive(false);
		if (((BossPart) enemy).deplete(10)) {
			enemy.setActive(false);
			BufferedImage[] images = Resources.getAnimation("EyeExplosion");
			AnimatedSprite explosion = new VolatileSprite(images, enemy.getX(), enemy.getY());
			grandius.getPlayfield().add(explosion);
		}
		grandius.updateScoreOnCollision(((Enemy)enemy).getScore());
		grandius.updateCashOnCollision(((Enemy)enemy).getCashValue());
	}
}
