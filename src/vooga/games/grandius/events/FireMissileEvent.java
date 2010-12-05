package vooga.games.grandius.events;

import java.awt.event.KeyEvent;

import vooga.games.grandius.Blah;
import vooga.games.grandius.sprites.Player;
import vooga.games.grandius.sprites.weapons.Missile;
import vooga.games.grandius.states.PlayState;

/**
 * @author bhawana
 *
 */
public class FireMissileEvent extends FiringEvent {
	private boolean missileActive;
	
	public FireMissileEvent(Blah grandius, Player player, PlayState playState){
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
		getGroup("missileGroup").add(missile);
		playExplosionSound("missileSound");
	}

}
