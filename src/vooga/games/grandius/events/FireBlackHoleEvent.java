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
import vooga.games.grandius.weapons.BlackHole;

/**
 * @author bhawana
 *
 */
public class FireBlackHoleEvent  implements IEventHandler{
	private DropThis grandius; 
	private Player player;
	private PlayState playState;
	
	public FireBlackHoleEvent(DropThis grandius, Player player, PlayState playState){
		this.grandius = grandius;
		this.player = player;
		this.playState = playState;
	}
	
	@Override
	public boolean isTriggered() {
		return grandius.keyPressed(KeyEvent.VK_B);
	}

	@Override
	public void actionPerformed() {
		BlackHole blackhole = new BlackHole(Resources.getImage("blackHoleImage"),player.getX()+player.getWidth(),player.getY());
		blackhole.setHorizontalSpeed(Resources.getDouble("projectileSpeed"));
		playState.getGroup("BlackHole").add(blackhole);
		grandius.playSound(Resources.getSound("missileSound"));	
	}

}
