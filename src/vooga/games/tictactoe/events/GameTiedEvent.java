package vooga.games.tictactoe.events;

import com.golden.gamedev.object.Sprite;

import vooga.engine.core.PlayField;
import vooga.engine.event.IEventHandler;
import vooga.games.tictactoe.states.PlayState;

/**
 * Detects and informs the PlayState when the game ends tied.  No network API code.
 * 
 * @author Cue, Kolodziejzyk, Townsend
 * @version 1.0
 */
public class GameTiedEvent implements IEventHandler {
	private PlayField field;
	private PlayState playState;

	/**
	 * Give GameTiedEvent access to the main PlayField and the PlayState.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public GameTiedEvent(PlayField field, PlayState playState) {
		this.field = field;
		this.playState = playState;
	}

	/**
	 * If all 9 spots on the board are filled then tell the PlayState there's a tie.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	@Override
	public void actionPerformed() {
		playState.setMessage("tied");
	}

	/**
	 * Check to see if all 9 spots on the board are filled. Doesn't need to worry about
	 * if there's a win or loss in those 9 spots since the GameLostEvent and GameWonEvent
	 * are checked before.
	 * 
	 * @return if all 9 spots on the board are filled
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
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
