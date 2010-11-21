package vooga.games.cyberion.states;

import vooga.engine.resource.Resources;
import vooga.engine.state.BasicTextGameState;

/**
 * Game complete state for cyberion
 * 
 * @author Harris.He
 * 
 */

public class GameCompleteState extends BasicTextGameState {
	private static String myGameCompleteMessage = Resources
			.getString("gameCompleteMessage");

	public GameCompleteState() {
		super(myGameCompleteMessage);
	}
}
