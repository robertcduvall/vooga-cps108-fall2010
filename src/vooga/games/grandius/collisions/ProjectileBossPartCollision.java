package vooga.games.grandius.collisions;

import java.awt.image.BufferedImage;

import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.resource.Resources;
import vooga.games.grandius.DropThis;
import vooga.games.grandius.enemy.boss.reacher.ReacherEye;
import vooga.games.grandius.enemy.common.Enemy;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.sprite.VolatileSprite;

public class ProjectileBossPartCollision extends BasicCollision {

	private Game grandius;
	
	public ProjectileBossPartCollision(Game grandius) {
		super(grandius);
		this.grandius = grandius;
	}
	
	
	public void collided(Sprite projectile, Sprite bosspart) {
		super.collided(projectile, bosspart);
		projectile.setActive(false);
		if (bosspart instanceof ReacherEye && ((ReacherEye) bosspart).deplete(10)) {
			bosspart.setActive(false);
			((DropThis)grandius).playSound(Resources.getSound("largeExplosionSound"));
			BufferedImage[] images = Resources.getAnimation("eyeExplosionAnimation");
			AnimatedSprite explosion = new VolatileSprite(images, bosspart.getX(), bosspart.getY());
			PlayField newField = new PlayField();
			newField.add(explosion);
			((DropThis)grandius).getPlayState().getRenderField().add(newField);
			((DropThis)grandius).getPlayState().getUpdateField().add(newField);
		}
		((DropThis)grandius).getPlayer().updateScore(((Enemy)bosspart).getScore());
		((DropThis)grandius).getPlayer().updateCash(((Enemy)bosspart).getCash());
	}
}
