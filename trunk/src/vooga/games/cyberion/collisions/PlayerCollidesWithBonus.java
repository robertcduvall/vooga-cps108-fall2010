package vooga.games.cyberion.collisions;


import vooga.games.cyberion.sprites.PlayerShip;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class PlayerCollidesWithBonus extends BasicCollisionGroup {
	public void collided(Sprite player, Sprite bonus) {
		collided((PlayerShip) player, bonus);
	}
//grants an increase in weapon power when player collides with a bonus sprite
	public void collided(PlayerShip player, Sprite bonus) {

		int power = player.getWeaponPower();
		if (power < 3) {
			power = power + 1;
		}
		player.setWeaponPower(power);
		bonus.setActive(false);

	}

}
