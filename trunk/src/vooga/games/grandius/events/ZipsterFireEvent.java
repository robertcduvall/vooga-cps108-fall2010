package vooga.games.grandius.events;

import vooga.engine.event.IEventHandler;
import vooga.engine.resource.Resources;
import vooga.games.grandius.Blah;
import vooga.games.grandius.sprites.Player;
import vooga.games.grandius.sprites.enemy.common.Zipster;
import vooga.games.grandius.states.PlayState;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public class ZipsterFireEvent implements IEventHandler {

	protected Blah grandius; 
	private Player player;
	private PlayState playState;
	private SpriteGroup enemyGroup;
	private Zipster currentZipster;

	public ZipsterFireEvent(Blah grandius, Player player, SpriteGroup enemyGroup, PlayState playState){
		this.grandius = grandius;
		this.player = player;
		this.enemyGroup = enemyGroup;
		this.playState = playState;
	}

	@Override
	public boolean isTriggered() {
		return isPlayerProximate();
	}

	@Override
	public void actionPerformed() {
		grandius.playSound(Resources.getSound("zipsterLaserSound"));
		getGroup("enemyProjectileGroup").add(currentZipster.fireLaser());
	}

	private boolean isPlayerProximate() {
		for (Sprite s: enemyGroup.getSprites()) {
			if (s!=null && ((Zipster)s).willFire(player)) {
				currentZipster = ((Zipster)s);
				return true;
			}
		}
		return false;
	}

	public SpriteGroup getGroup(String groupName){
		return playState.getGroup(groupName);
	}

}
