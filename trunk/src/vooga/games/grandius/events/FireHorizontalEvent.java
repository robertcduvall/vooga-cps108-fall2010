package vooga.games.grandius.events;

import java.awt.event.KeyEvent;

import vooga.games.grandius.DropThis;
import vooga.games.grandius.sprites.Player;
import vooga.games.grandius.sprites.weapons.Laser;
import vooga.games.grandius.states.PlayState;

/**
 * @author bhawana
 *
 */
public class FireHorizontalEvent  extends FiringEvent {
	
	public FireHorizontalEvent(DropThis grandius, Player player, PlayState playState){
		super(grandius, player, playState);
	}
	
	@Override
	public boolean isTriggered() {
		return grandius.keyPressed(KeyEvent.VK_ALT);
	}

	@Override
	public void actionPerformed() {
		Laser projectile = new Laser(getXLocation(), getYLocation());
		//projectile.setHorizontalSpeed(Resources.getDouble("laserSpeed"));
		getGroup("projectileGroup").add(projectile);
		playExplosionSound("laserSound");
	}

}
