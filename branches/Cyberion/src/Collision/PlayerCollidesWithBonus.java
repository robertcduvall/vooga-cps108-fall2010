package Collision;

import CyberionSprite.EnemyShip;
import CyberionSprite.PlayerShip;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class PlayerCollidesWithBonus extends BasicCollisionGroup {
	public void collided(Sprite player, Sprite bonus) {
		collided((PlayerShip) player, bonus);
	}

	public void collided(PlayerShip player, Sprite bonus) {

		int power = player.getWeaponPower();
		if (power < 3) {
			power = power + 1;
		}
		player.setWeaponPower(power);
		bonus.setActive(false);

	}

}
