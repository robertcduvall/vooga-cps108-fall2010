/**
 * 
 */
package vooga.games.grandius.events;

import java.awt.event.KeyEvent;

import vooga.engine.event.IEventHandler;
import vooga.engine.resource.Resources;
import vooga.games.grandius.DropThis;
import vooga.games.grandius.Player;
import vooga.games.grandius.states.PlayState;
import vooga.games.grandius.weapons.Laser;

/**
 * @author bhawana
 *
 */
public class FireHorizontalEvent  implements IEventHandler{
	private DropThis grandius; 
	private Player player;
	private PlayState playState;
	
	public FireHorizontalEvent(DropThis grandius, Player player, PlayState playState){
		this.grandius = grandius;
		this.player = player;
		this.playState = playState;
	}
	
	@Override
	public boolean isTriggered() {
		return grandius.keyPressed(KeyEvent.VK_ALT);
	}

	@Override
	public void actionPerformed() {
		Laser projectile = new Laser(player.getX()+player.getWidth(),player.getY());
		projectile.setHorizontalSpeed(Resources.getDouble("laserSpeed"));
		playState.getGroup("projectileGroup").add(projectile);
		grandius.playSound(Resources.getSound("laserSound"));	
	}

}
