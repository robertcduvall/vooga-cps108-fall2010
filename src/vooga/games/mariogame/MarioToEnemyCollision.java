package vooga.games.mariogame;

import vooga.engine.core.Game;

import com.golden.gamedev.object.Sprite;

public class MarioToEnemyCollision extends BetterCollisionGroup {
	long myLastCollisionTime;
	
	public MarioToEnemyCollision(Game game) { 
		super();
	}
	
	@Override
	public void collided(Sprite mario, Sprite enemy) {
		long thisCollisionTime = System.currentTimeMillis();
		if (thisCollisionTime - myLastCollisionTime > 100) {
			int side = getCollisionSide(mario, enemy);
			MarioSprite thisMario = (MarioSprite) mario;
			Enemy thisEnemy = (Enemy) enemy;
			if (side == BOTTOM_TOP_COLLISION) {
				thisEnemy.setActive(false);
				thisMario.jump(true);
			} else {
				thisMario.setHealth(thisMario.getHealth() - 1);
				thisEnemy.bounce();
			}
			myLastCollisionTime = thisCollisionTime;
		}
	}
}
