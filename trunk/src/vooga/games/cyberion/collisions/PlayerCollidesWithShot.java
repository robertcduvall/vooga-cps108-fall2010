package vooga.games.cyberion.collisions;
import vooga.engine.core.Game;
import vooga.engine.overlay.Stat;
import vooga.games.cyberion.sprites.playership.PlayerShip;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class PlayerCollidesWithShot extends BasicCollisionGroup {
	
	public PlayerCollidesWithShot(Game game){
		super();
	}
	
	@Override
	public void collided(Sprite playerShip, Sprite enemyShot) {
		collided((PlayerShip) playerShip, enemyShot);
	}
//decreases the player' life and weapon power, sets enemy shot to inactive.
	public void collided(PlayerShip playerShip, Sprite enemyShot) {
		playerShip.setLife(playerShip.getLife() - 1);
		Stat<Integer> intStat = new Stat(Integer.parseInt(playerShip.getStat("livesStat").getStat().toString())-1);
		playerShip.setStat("livesStat",intStat); 
		if (playerShip.getWeaponPower() > 1) {
			playerShip.setWeaponPower(playerShip.getWeaponPower() - 1);
		} else {
			playerShip.setWeaponPower(1);
		}
		enemyShot.setActive(false);
		if (Integer.parseInt(playerShip.getStat("livesStat").getStat().toString())>0){
			playerShip.forceX(320);
			playerShip.forceY(440);
		}

	}
}
