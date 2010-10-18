package vooga.games.grandius.collisions;

import java.awt.image.BufferedImage;

import vooga.engine.resource.ResourcesBeta;
import vooga.games.grandius.Grandius;
import vooga.games.grandius.enemy.common.Enemy;
import vooga.games.grandius.weapons.Missile;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.sprite.VolatileSprite;

public class MissileEnemyCollision extends BasicCollision {
	private Grandius grandius;

	public MissileEnemyCollision(Grandius grandius) {
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
		BufferedImage[] images = ResourcesBeta.getAnimation("Explosion");
		AnimatedSprite explosion = new VolatileSprite(images, enemy.getX(), enemy.getY());
		grandius.getPlayfield().add(explosion);
		grandius.playSound(ResourcesBeta.getSound("LaserSound"));
		grandius.updateScoreOnCollision(((Enemy)enemy).getScore());
		grandius.updateCashOnCollision(((Enemy)enemy).getCashValue());
	}
}