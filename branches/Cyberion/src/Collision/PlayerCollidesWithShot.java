package Collision;

import CyberionSprite.EnemyShot;
import CyberionSprite.PlayerShip;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class PlayerCollidesWithShot extends BasicCollisionGroup {
	@Override
	public void collided(Sprite playerShip, Sprite enemyShot) {
		collided((PlayerShip) playerShip, enemyShot);
	}

	public void collided(PlayerShip playerShip, Sprite enemyShot) {
		playerShip.setLife(playerShip.getLife() - 1);
		playerShip.forceX(320);
		playerShip.forceY(440);
		enemyShot.setActive(false);
	}
}
