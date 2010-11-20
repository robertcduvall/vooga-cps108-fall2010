package vooga.games.grandius.events;

import java.awt.event.KeyEvent;

import vooga.engine.event.IEventHandler;
import vooga.engine.resource.Resources;
import vooga.games.grandius.DropThis;
import vooga.games.grandius.Player;
import vooga.games.grandius.states.PlayState;
import vooga.games.grandius.weapons.Missile;

public class FireMissileEvent implements IEventHandler {
	private DropThis grandius; 
	private Player player;
	private PlayState playState;
	private boolean missileActive;
	
	public FireMissileEvent(DropThis grandius, Player player, PlayState playState){
		this.grandius = grandius;
		this.player = player;
		this.playState = playState;
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
		Missile missile = new Missile(player.getX()+player.getWidth(),player.getY());
		playState.getGroup("Missile").add(missile);
		grandius.playSound(Resources.getSound("missileSound"));
	}

}
