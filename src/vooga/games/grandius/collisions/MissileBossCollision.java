package vooga.games.grandius.collisions;

import java.awt.image.BufferedImage;

import vooga.engine.resource.Resources;
import vooga.games.grandius.Grandius;
import vooga.games.grandius.Missile;
import vooga.games.grandius.enemy.boss.BossPart;
import vooga.games.grandius.enemy.common.Enemy;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.sprite.VolatileSprite;

public class MissileBossCollision extends BasicCollision {
	private Grandius grandius;

	public MissileBossCollision(Grandius grandius) {
		super(grandius);
		this.grandius = grandius;
	}

	@Override
	public void collided(Sprite playersMissile, Sprite enemy) {
		super.collided(playersMissile, enemy);
		Missile missile = (Missile) playersMissile;
		missile.incrementHits();
		if(missile.hitsRemaining()==0){
			missile.setActive(false);
		}
		if (((BossPart) enemy).deplete(10)) {
			enemy.setActive(false);
			BufferedImage[] images = Resources.getAnimation("EyeExplosion");
			AnimatedSprite explosion = new VolatileSprite(images, enemy.getX(), enemy.getY());
			grandius.getPlayfield().add(explosion);
		}
		BufferedImage[] images = Resources.getAnimation("Explosion");
		AnimatedSprite explosion = new VolatileSprite(images, enemy.getX(), enemy.getY());
		grandius.getPlayfield().add(explosion);
		grandius.playSound(Resources.getMapping("LaserSound"));
		grandius.updateScoreOnCollision(((Enemy)enemy).getScore());
		grandius.updateCashOnCollision(((Enemy)enemy).getCashValue());
	}
}