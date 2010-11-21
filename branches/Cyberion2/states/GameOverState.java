package vooga.games.cyberion.states;

import vooga.engine.resource.Resources;
import vooga.engine.state.BasicTextGameState;

/**
 * Game over state for cyberion
 * 
 * @author Harris.He
 * 
 */

public class GameOverState extends BasicTextGameState {
	private static String myGameOverMessage = Resources
			.getString("gameOverMessage");

	public GameOverState() {
		super(myGameOverMessage);
	}
}
