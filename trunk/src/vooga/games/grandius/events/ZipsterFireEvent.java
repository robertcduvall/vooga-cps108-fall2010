package vooga.games.grandius.events;

import com.golden.gamedev.object.Sprite;

import vooga.games.grandius.DropThis;
import vooga.games.grandius.Player;
import vooga.games.grandius.enemy.common.Zipster;
import vooga.games.grandius.states.PlayState;

public class ZipsterFireEvent extends FiringEvent {
	private Player player;
	private Zipster firingZipster;

	public ZipsterFireEvent(DropThis grandius, Player player, PlayState playState) {
		super(grandius, player, playState);
		this.player = player;
	}
	
	@Override
	public boolean isTriggered() {
		for(Sprite enemy: getGroup("enemyGroup").getSprites()){
			if (enemy instanceof Zipster && ((Zipster) enemy).willFire(player)){
					firingZipster = (Zipster) enemy;
					return true;
			}
		}
		return false;
	}

	@Override
	public void actionPerformed() {
        getGroup("enemyProjectileGroup").add(firingZipster.fireLaser());
        playExplosionSound("laserSound");
	}

}
