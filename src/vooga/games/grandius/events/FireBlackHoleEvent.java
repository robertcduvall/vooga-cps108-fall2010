package vooga.games.grandius.events;

import java.awt.event.KeyEvent;

import vooga.games.grandius.Blah;
import vooga.games.grandius.sprites.Player;
import vooga.games.grandius.sprites.weapons.BlackHole;
import vooga.games.grandius.states.PlayState;

/**
 * @author bhawana
 *
 */
public class FireBlackHoleEvent	  extends FiringEvent {
	
	public FireBlackHoleEvent(Blah grandius, Player player, PlayState playState){
		super(grandius, player, playState);

	}
	
	public void FireBackHoleEvent()
	{
		
	}
	
	@Override
	public boolean isTriggered() {
		return grandius.keyPressed(KeyEvent.VK_B);
	}

	@Override
	public void actionPerformed() {
		BlackHole blackhole = new BlackHole(getXLocation(), getYLocation());
		getGroup("BlackHole").add(blackhole);
		playExplosionSound("missileSound");		
	}

}
