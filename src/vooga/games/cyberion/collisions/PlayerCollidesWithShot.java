package vooga.games.cyberion.collisions;
import vooga.games.cyberion.MainGameState;
import vooga.games.cyberion.sprites.playership.PlayerShip;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class PlayerCollidesWithShot extends BasicCollisionGroup {
	@Override
	public void collided(Sprite playerShip, Sprite enemyShot) {
		collided((PlayerShip) playerShip, enemyShot);
	}
//decreases the player' life and weapon power, sets enemy shot to inactive.
	public void collided(PlayerShip playerShip, Sprite enemyShot) {
		playerShip.setLife(playerShip.getLife() - 1);
		MainGameState.myLives.setStat(MainGameState.myLives.getStat()-1); 

		if (playerShip.getWeaponPower() > 1) {
			playerShip.setWeaponPower(playerShip.getWeaponPower() - 1);
		} else {
			playerShip.setWeaponPower(1);
		}
		enemyShot.setActive(false);
		if (MainGameState.myLives.getStat()>0){
			playerShip.forceX(320);
			playerShip.forceY(440);
		}

	}
}
