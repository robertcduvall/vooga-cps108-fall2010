package vooga.games.grandius.collisions;

import java.awt.image.BufferedImage;

import vooga.engine.resource.Resources;
import vooga.games.grandius.Blah;
import vooga.games.grandius.enemy.boss.reacher.Reacher;
import vooga.games.grandius.enemy.common.Enemy;
import vooga.games.grandius.weapons.Missile;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.sprite.VolatileSprite;

public class MissileBossCollision extends BasicCollision {
	private Blah grandius;

	public MissileBossCollision(Blah grandius) {
		super(grandius);
		this.grandius = grandius;
	}

	@Override
	public void collided(Sprite playersMissile, Sprite boss) {
		super.collided(playersMissile, boss);
		Missile missile = (Missile) playersMissile;
		missile.incrementHits();
		if(missile.hitsRemaining()==0){
			missile.setActive(false);
		}
		if (boss instanceof Reacher && ((Reacher) boss).deplete(10)) {
			boss.setActive(false);
			BufferedImage[] images = Resources.getAnimation("ReacherExplosion");
			AnimatedSprite explosion = new VolatileSprite(images, boss.getX(), boss.getY());
			grandius.getPlayfield().add(explosion);
		}
		BufferedImage[] images = Resources.getAnimation("Explosion");
		AnimatedSprite explosion = new VolatileSprite(images, boss.getX(), boss.getY());
		grandius.getPlayfield().add(explosion);
		//grandius.playSound(Resources.getMapping("LaserSound"));
		grandius.updateScoreOnCollision(((Enemy)boss).getScore());
		grandius.updateCashOnCollision(((Enemy)boss).getCashValue());
	}
}