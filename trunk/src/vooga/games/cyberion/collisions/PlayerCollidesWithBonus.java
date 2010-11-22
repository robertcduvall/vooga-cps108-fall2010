package vooga.games.cyberion.collisions;


import vooga.engine.core.Game;
import vooga.engine.overlay.Stat;
import vooga.games.cyberion.sprites.playership.PlayerShip;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class PlayerCollidesWithBonus extends BasicCollisionGroup {
	
	public PlayerCollidesWithBonus(Game game)
	{
		super();
	}
	
	public void collided(Sprite player, Sprite bonus) {
		collided((PlayerShip) player, bonus);
	}
//grants an increase in weapon power when player collides with a bonus sprite
	public void collided(PlayerShip player, Sprite bonus) {
		Stat<Integer> score = (Stat<Integer>) player.getStat("scoreStat");
		score.setStat(score.getStat()+50);
		
		int power = player.getWeaponPower();
		if (power < 3) {
			power = power + 1;
		}
		player.setWeaponPower(power);
		bonus.setActive(false);

	}

}
