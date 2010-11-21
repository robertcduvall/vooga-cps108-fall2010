package vooga.games.grandius.events;

import java.awt.event.KeyEvent;

import vooga.games.grandius.DropThis;
import vooga.games.grandius.Player;
import vooga.games.grandius.states.PlayState;
import vooga.games.grandius.weapons.Missile;

/**
 * @author bhawana
 *
 */
public class FireMissileEvent extends FiringEvent {
	private boolean missileActive;
	
	public FireMissileEvent(DropThis grandius, Player player, PlayState playState){
		super(grandius, player, playState);
		missileActive = false;
	}
	
	@Override
	public boolean isTriggered() {
		return grandius.keyPressed(KeyEvent.VK_M) && isMissileActive();
	}

	public boolean isMissileActive() {
		return missileActive;
	}
	
	public void setMissileActive(boolean activated){
		missileActive = activated;
	}

	@Override
	public void actionPerformed() {
		Missile missile = new Missile(getXLocation(), getYLocation());
		getGroup("Missile").add(missile);
		playExplosionSound("missileSound");
	}

}
