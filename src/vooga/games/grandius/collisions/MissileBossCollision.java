package vooga.games.grandius.collisions;

import java.awt.image.BufferedImage;

import vooga.engine.core.PlayField;
import vooga.engine.resource.Resources;
import vooga.games.grandius.DropThis;
import vooga.games.grandius.enemy.boss.reacher.Reacher;
import vooga.games.grandius.enemy.common.Enemy;
import vooga.games.grandius.weapons.Missile;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.sprite.VolatileSprite;

public class MissileBossCollision extends BasicCollision {
	private DropThis grandius;

	public MissileBossCollision(DropThis grandius) {
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
			BufferedImage[] images = Resources.getAnimation("reacherExplosionAnimation");
			AnimatedSprite explosion = new VolatileSprite(images, boss.getX(), boss.getY());
			PlayField newField = new PlayField();
			newField.add(explosion);
			grandius.getPlayState().getRenderField().add(newField);
			grandius.getPlayState().getUpdateField().add(newField);
		}
		BufferedImage[] images = Resources.getAnimation("explosionAnimation");
		AnimatedSprite explosion = new VolatileSprite(images, boss.getX(), boss.getY());
		PlayField newField = new PlayField();
		newField.add(explosion);
		grandius.getPlayState().getRenderField().add(newField);
		grandius.getPlayState().getUpdateField().add(newField);
		//grandius.playSound(Resources.getMapping("LaserSound"));
		grandius.getPlayer().updateScore(((Enemy)boss).getScore());
		grandius.getPlayer().updateCash(((Enemy)boss).getCash());
	}
}