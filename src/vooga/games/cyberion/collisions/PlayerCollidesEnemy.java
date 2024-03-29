package vooga.games.cyberion.collisions;

import vooga.engine.core.Game;
import vooga.engine.overlay.Stat;
import vooga.games.cyberion.sprites.enemyship.EnemyShip;
import vooga.games.cyberion.sprites.playership.PlayerShip;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class PlayerCollidesEnemy extends BasicCollisionGroup {

	public PlayerCollidesEnemy(Game game) {
		super();
	}

	@Override
	public void collided(Sprite player, Sprite enemy) {
		collided((PlayerShip) player, (EnemyShip) enemy);

	}

	// when player collides with enemy, player' life and weapon power decrease
	// by 1.
	// player is forced back to its initial position
	@SuppressWarnings("unchecked")
	public void collided(PlayerShip player, EnemyShip enemy) {

		player.setLife(player.getLife() - 1);

		Stat<Integer> stat = (Stat<Integer>) player.getStat("livesStat");
		if (player.getWeaponPower() > 1) {
			player.setWeaponPower(player.getWeaponPower() - 1);
		} else {
			player.setWeaponPower(1);
		}
		if (stat.getStat() > 0) {
			stat.setStat(stat.getStat() - 1);
			player.forceX(320);
			player.forceY(440);
		}

	}

}
