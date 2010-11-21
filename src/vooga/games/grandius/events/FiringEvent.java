package vooga.games.grandius.events;

import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.event.IEventHandler;
import vooga.engine.resource.Resources;
import vooga.games.grandius.DropThis;
import vooga.games.grandius.sprites.Player;
import vooga.games.grandius.states.PlayState;

/**
 * @author bhawana
 *
 */
public class FiringEvent implements IEventHandler{
	protected DropThis grandius; 
	private Player player;
	private PlayState playState;
	
	public FiringEvent(DropThis grandius, Player player, PlayState playState){
		this.grandius = grandius;
		this.player = player;
		this.playState = playState;
	}
	

	@Override
	public boolean isTriggered() {
		return false;
	}

	@Override
	public void actionPerformed() {		
	}
	
	public double getXLocation(){
		return player.getX()+player.getWidth();
	}
	
	public double getYLocation(){
		return player.getY();
	}
	
	public void playExplosionSound(String soundName){
		grandius.playSound(Resources.getSound(soundName));
	}
	
	public SpriteGroup getGroup(String groupName){
		return playState.getGroup(groupName);
	}
}
