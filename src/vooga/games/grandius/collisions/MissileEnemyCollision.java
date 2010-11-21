package vooga.games.grandius.collisions;

import java.awt.image.BufferedImage;

import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.resource.Resources;
import vooga.games.grandius.DropThis;
import vooga.games.grandius.weapons.Missile;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.sprite.VolatileSprite;

public class MissileEnemyCollision extends BasicCollision {
	private Game grandius;

	public MissileEnemyCollision(Game grandius) {
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
		enemy.setActive(false);
		BufferedImage[] images = Resources.getAnimation("explosionAnimation");
		AnimatedSprite explosion = new VolatileSprite(images, enemy.getX(), enemy.getY());
		PlayField newField = new PlayField();
		newField.add(explosion);
		((DropThis)grandius).getPlayState().getRenderField().add(newField);
		((DropThis)grandius).getPlayState().getUpdateField().add(newField);
		((DropThis)grandius).playSound(Resources.getSound("laserSound"));
//		getPlayer().updateScore(((Enemy)enemy).getScore());
//		getPlayer().updateCash(((Enemy)enemy).getCash());
	}
}