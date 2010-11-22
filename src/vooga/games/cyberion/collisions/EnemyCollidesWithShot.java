package vooga.games.cyberion.collisions;

import vooga.engine.core.Game;
import vooga.engine.overlay.Stat;
import vooga.engine.resource.Resources;
import vooga.games.cyberion.sprites.enemyship.EnemyShip;
import vooga.games.cyberion.sprites.playershot.PlayerShot;

import com.golden.gamedev.engine.BaseAudio;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;


public class EnemyCollidesWithShot extends BasicCollisionGroup {

	public EnemyCollidesWithShot(Game game) {
		super();
	}

	@Override
	public void collided(Sprite playershot, Sprite enemy) {
		collided((PlayerShot)playershot, (EnemyShip) enemy);

	}
	
//when a playershot collides with an enemy, enemy's life decreases, shot disappears, and a sound is played
	public void collided(Sprite playershot, EnemyShip enemy) {
		if (enemy.isOnScreen()){
				Stat<Integer> score = (Stat<Integer>) enemy.getStat("scoreStat");
				score.setStat(score.getStat()+10);
		}
		enemy.setLife(enemy.getLife() - 1);
		playershot.setActive(false);
	}

}
