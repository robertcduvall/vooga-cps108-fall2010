package vooga.games.marioclone;

import vooga.engine.player.control.PlayerSprite;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.collision.PreciseCollisionGroup;

public class MarioToEnemyCollision extends BetterCollisionGroup {
	long lastcollision;

	@Override
	public void collided(Sprite mario, Sprite enemy) {
		long thiscollision = System.currentTimeMillis();
		if(thiscollision-lastcollision > 100) {
			int side = getCollisionSide(mario,enemy);
			MarioSprite thisMario = (MarioSprite) mario;
			Enemy thisEnemy = (Enemy) enemy; 
			if(side == BOTTOM_TOP_COLLISION){
				thisEnemy.setHealth(thisEnemy.getHealth()-100);
				thisMario.jump(true);
			}
			else {
				thisMario.setHealth(thisMario.getHealth()-1);
				thisEnemy.bounce();
			}
//			mario.setHorizontalSpeed(0);
			lastcollision = thiscollision;
//			revertPosition1(mario, enemy);
		}
	}
}
