package vooga.games.cyberion.states;

import vooga.engine.resource.Resources;
import vooga.engine.state.BasicTextGameState;

public class GameOverState extends BasicTextGameState {
	private static String myGameOverMessage = Resources
			.getString("gameOverMessage");

	public GameOverState() {
		super(myGameOverMessage);
	}
}
