package vooga.games.grandius.collisions;

import vooga.engine.core.Game;
import vooga.games.grandius.sprites.weapons.Missile;

import com.golden.gamedev.object.Sprite;

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
//		BufferedImage[] images = Resources.getAnimation("explosionAnimation");
//		AnimatedSprite explosion = new VolatileSprite(images, enemy.getX(), enemy.getY());
//		PlayField newField = new PlayField();
//		newField.add(explosion);
//		((DropThis)grandius).getPlayState().getRenderField().add(newField);
//		((DropThis)grandius).getPlayState().getUpdateField().add(newField);
//		((DropThis)grandius).playSound(Resources.getSound("laserSound"));
//		getPlayer().updateScore(((Enemy)enemy).getScore());
//		getPlayer().updateCash(((Enemy)enemy).getCash());
	}
}