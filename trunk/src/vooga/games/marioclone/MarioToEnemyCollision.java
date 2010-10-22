package vooga.games.marioclone;

import com.golden.gamedev.object.Sprite;

public class MarioToEnemyCollision extends BetterCollisionGroup {
	long lastcollision;

	@Override
	public void collided(Sprite mario, Sprite enemy) {
		long thiscollision = System.currentTimeMillis();
		if (thiscollision - lastcollision > 100) {
			int side = getCollisionSide(mario, enemy);
			MarioSprite thisMario = (MarioSprite) mario;
			Enemy thisEnemy = (Enemy) enemy;
			if (side == BOTTOM_TOP_COLLISION) {
				thisEnemy.setActive(false);
				thisMario.jump(true);
				thisMario.incScore(10);
			} else {
				thisMario.setHealth(thisMario.getHealth() - 1);
				thisEnemy.bounce();
			}
			lastcollision = thiscollision;
		}
	}
}
