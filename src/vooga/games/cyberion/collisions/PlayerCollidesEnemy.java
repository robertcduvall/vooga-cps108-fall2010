package vooga.games.cyberion.collisions;


import vooga.engine.core.Game;
import vooga.engine.overlay.Stat;
import vooga.games.cyberion.MainGameState;
import vooga.games.cyberion.sprites.enemyship.EnemyShip;
import vooga.games.cyberion.sprites.playership.PlayerShip;
import vooga.games.cyberion.states.PlayState;

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
//when player collides with enemy, player' life and weapon power decrease by 1. 
//player is forced back to its initial position
	public void collided(PlayerShip player, EnemyShip enemy) {

		//player.setLife(player.getLife() - 1);
		//System.out.println(Integer.parseInt(player.getStat("livesStat").getStat().toString()));
		Stat<Integer> intStat = new Stat(Integer.parseInt(player.getStat("livesStat").getStat().toString())-1);
		player.setStat("livesStat",intStat); 
		if (player.getWeaponPower() > 1) {
			player.setWeaponPower(player.getWeaponPower() - 1);
		} else {
			player.setWeaponPower(1);
		}
		if (Integer.parseInt(player.getStat("livesStat").getStat().toString())>0){
			player.forceX(320);
			player.forceY(440);
		}
		else{
			
		}
	}

}
