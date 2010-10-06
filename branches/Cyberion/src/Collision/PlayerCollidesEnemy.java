package Collision;

import CyberionSprite.EnemyShip;
import CyberionSprite.PlayerShip;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class PlayerCollidesEnemy extends BasicCollisionGroup{


	@Override
	public void collided(Sprite player, Sprite enemy) {
		collided((PlayerShip) player, (EnemyShip)enemy);
		
	}
	
	public void collided(PlayerShip player, EnemyShip enemy) {
		
		player.setLife(player.getLife()-1);
		player.forceX(320);
		player.forceY(440);
		
		
	}

}
