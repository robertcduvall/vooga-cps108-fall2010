package vooga.engine.networking.client.events;

import com.golden.gamedev.object.Sprite;

import vooga.engine.core.PlayField;
import vooga.engine.event.IEventHandler;
import vooga.engine.networking.client.states.PlayState;

/**
 * The DoodleDiedEvent implement IEventHandler and activates myGameOverState
 * when Doodle Dies
 * 
 * @author Adam Cue, Marcus Molchany, Nick Straub
 * 
 */
public class GameTiedEvent implements IEventHandler {
	private PlayField field;
	private PlayState playState;

	public GameTiedEvent(PlayField field, PlayState playState) {
		this.field = field;
		this.playState = playState;
	}

	@Override
	public void actionPerformed() {
		playState.setTied(true);
	}

	@Override
	public boolean isTriggered() {
		int pieces = 0;
		for(Sprite piece : field.getGroup("xGroup").getSprites()){
			if(piece != null)
				pieces++;
		}
		for(Sprite piece : field.getGroup("oGroup").getSprites()){
			if(piece != null)
				pieces++;
		}
		return pieces >= 9;
	}
}
