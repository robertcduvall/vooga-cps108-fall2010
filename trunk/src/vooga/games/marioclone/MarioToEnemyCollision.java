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
		if(thiscollision-lastcollision > 10000) {
			int side = getCollisionSide();
			if(side == BOTTOM_TOP_COLLISION){
				System.out.println("Mario wins");
			}
			else if(side == RIGHT_LEFT_COLLISION){
				System.out.println("Mario hit from left");
			}
			else{
				System.out.println("Mario loses");
			}
			System.out.println(getCollisionSide());
			MarioSprite thisMario = (MarioSprite) mario;
			Enemy thisEnemy = (Enemy) enemy; 
			if(getCollisionSide() == BOTTOM_TOP_COLLISION){
				thisEnemy.setHealth(thisEnemy.getHealth()-100);
				thisMario.jump(true);
			}
			else {
				thisMario.setHealth(thisMario.getHealth()-1);
				((Enemy) enemy).bounce();
			}
			mario.setHorizontalSpeed(0);
			lastcollision = thiscollision;
			//revertPosition1(mario, enemy);
			//revertPosition1(enemy, mario);
		}
	}
}
