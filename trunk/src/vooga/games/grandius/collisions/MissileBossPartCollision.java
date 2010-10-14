package vooga.games.grandius.collisions;

import java.awt.image.BufferedImage;

import vooga.engine.resource.Resources;
import vooga.games.grandius.Grandius;
import vooga.games.grandius.enemy.boss.BossPart;
import vooga.games.grandius.enemy.boss.reacher.ReacherEye;
import vooga.games.grandius.enemy.common.Enemy;
import vooga.games.grandius.weapons.Missile;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.sprite.VolatileSprite;

public class MissileBossPartCollision extends BasicCollision {
	private Grandius grandius;

	public MissileBossPartCollision(Grandius grandius) {
		super(grandius);
		this.grandius = grandius;
	}

	@Override
	public void collided(Sprite playerMissile, Sprite bossPart) {
		super.collided(playerMissile, bossPart);
		Missile missile = (Missile) playerMissile;
		missile.incrementHits();
		if(missile.hitsRemaining()==0){
			missile.setActive(false);
		}
		if (bossPart instanceof ReacherEye && ((ReacherEye) bossPart).deplete(10)) {
			bossPart.setActive(false);
			BufferedImage[] images = Resources.getAnimation("EyeExplosion");
			AnimatedSprite explosion = new VolatileSprite(images, bossPart.getX(), bossPart.getY());
			grandius.getPlayfield().add(explosion);
		}
		BufferedImage[] images = Resources.getAnimation("Explosion");
		AnimatedSprite explosion = new VolatileSprite(images, bossPart.getX(), bossPart.getY());
		grandius.getPlayfield().add(explosion);
		//grandius.playSound(Resources.getMapping("LaserSound"));
		grandius.updateScoreOnCollision(((Enemy)bossPart).getScore());
		grandius.updateCashOnCollision(((Enemy)bossPart).getCashValue());
	}
}
