package vooga.games.towerdefense;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class TowerShotEnemyCollision extends BasicCollisionGroup{

	@Override
	public void collided(Sprite towerShot, Sprite enemy) {
				towerShot.setActive(false);
				enemy.setActive(false);
	}
	

}
