package vooga.games.towerdefense.collisions;

import vooga.engine.resource.Resources;
import vooga.games.towerdefense.actors.Player;
import vooga.games.towerdefense.actors.enemies.Enemy;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;



public class ShotToEnemyCollision extends CollisionGroup {

	private static final int SCORE_ADD = 10;
	private static final int MONEY_ADD = 1;
	private Player myPlayer;
		
	@Override
	public void collided(Sprite shot, Sprite enemy) {
		shot.setActive(false);
		((Enemy)enemy).kill();
	}

}
