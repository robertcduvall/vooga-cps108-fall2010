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
		if(thiscollision-lastcollision > 200) {
			if(getCollisionSide() == BOTTOM_TOP_COLLISION || mario.getVerticalSpeed()>0){
				((CharacterSprite) enemy).setHealth(((CharacterSprite) enemy).getHealth()-50);
				((MarioSprite) mario).jump(true);
			}
			else {
				((CharacterSprite) mario).setHealth(((CharacterSprite) mario).getHealth()-10);
				((Enemy) enemy).bounce();
			}
			mario.setHorizontalSpeed(0);
			lastcollision = thiscollision;
			revertPosition1(mario, enemy);
			revertPosition1(enemy, mario);
		}
	}
}
