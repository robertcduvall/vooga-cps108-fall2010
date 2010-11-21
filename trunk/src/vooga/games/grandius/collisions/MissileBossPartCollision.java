package vooga.games.grandius.collisions;

import java.awt.image.BufferedImage;

import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.resource.Resources;
import vooga.games.grandius.DropThis;
import vooga.games.grandius.enemy.boss.reacher.ReacherEye;
import vooga.games.grandius.weapons.Missile;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.sprite.VolatileSprite;

public class MissileBossPartCollision extends BasicCollision {
	private Game grandius;

	public MissileBossPartCollision(Game grandius) {
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
			BufferedImage[] images = Resources.getAnimation("eyeExplosionAnimation");
			AnimatedSprite explosion = new VolatileSprite(images, bossPart.getX(), bossPart.getY());
			PlayField newField = new PlayField();
			newField.add(explosion);
			((DropThis)grandius).getPlayState().getRenderField().add(newField);
			((DropThis)grandius).getPlayState().getUpdateField().add(newField);
		}
		BufferedImage[] images = Resources.getAnimation("explosionAnimation");
		AnimatedSprite explosion = new VolatileSprite(images, bossPart.getX(), bossPart.getY());
		PlayField newField = new PlayField();
		newField.add(explosion);
		((DropThis)grandius).getPlayState().getRenderField().add(newField);
		((DropThis)grandius).getPlayState().getUpdateField().add(newField);
//		getPlayer().updateScore(((Enemy)bossPart).getScore());
//		getPlayer().updateCash(((Enemy)bossPart).getCash());
	}
}
