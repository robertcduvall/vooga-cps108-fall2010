/**
 * 
 */
package vooga.games.grandius.events;

import java.awt.event.KeyEvent;

import com.golden.gamedev.object.Sprite;

import vooga.engine.event.IEventHandler;
import vooga.engine.resource.Resources;
import vooga.games.grandius.DropThis;
import vooga.games.grandius.Player;
import vooga.games.grandius.states.PlayState;

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
		Sprite projectile = new Sprite(Resources.getImage("Projectile"),player.getX()+player.getWidth(),player.getY());
		projectile.setHorizontalSpeed(Resources.getDouble("ProjectileSpeed"));
		playState.getGroup("Projectile").add(projectile);
		grandius.playSound(Resources.getSound("LaserSound"));	
	}

}
