package vooga.games.grandius.events;

import java.awt.event.KeyEvent;

import com.golden.gamedev.object.Sprite;

import vooga.engine.resource.Resources;
import vooga.games.grandius.Blah;
import vooga.games.grandius.sprites.Player;
import vooga.games.grandius.states.PlayState;

/**
 * @author bhawana
 *
 */
public class FireVerticalEvent extends FiringEvent {
	
	public FireVerticalEvent(Blah grandius, Player player, PlayState playState){
		super(grandius, player, playState);
	}
	
	@Override
	public boolean isTriggered() {
		return grandius.keyPressed(KeyEvent.VK_SPACE);
	}

	@Override
	public void actionPerformed() {
		Sprite projectile = new Sprite(Resources.getImage("verticalProjectileImage"),getXLocation(), getYLocation());
		projectile.setVerticalSpeed(Resources.getDouble("bulletSpeed"));
		getGroup("projectileGroup").add(projectile);
		playExplosionSound("laserSound");	
	}

}
