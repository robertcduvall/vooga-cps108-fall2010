package vooga.games.cyberion.collisions;


import vooga.engine.core.Game;
import vooga.games.cyberion.MainGameState;
import vooga.games.cyberion.sprites.EnemyShip;
import vooga.games.cyberion.sprites.PlayerShip;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class PlayerCollidesEnemy extends BasicCollisionGroup {

	@Override
	public void collided(Sprite player, Sprite enemy) {
		collided((PlayerShip) player, (EnemyShip) enemy);

	}
//when player collides with enemy, player' life and weapon power decrease by 1. 
//player is forced back to its initial position
	public void collided(PlayerShip player, EnemyShip enemy) {

		player.setLife(player.getLife() - 1);
		MainGameState.myLives.setStat(MainGameState.myLives.getStat()-1); 
		if (player.getWeaponPower() > 1) {
			player.setWeaponPower(player.getWeaponPower() - 1);
		} else {
			player.setWeaponPower(1);
		}
		if (MainGameState.myLives.getStat()>0){
			player.forceX(320);
			player.forceY(440);
		}
		else{
			
		}
	}

}
