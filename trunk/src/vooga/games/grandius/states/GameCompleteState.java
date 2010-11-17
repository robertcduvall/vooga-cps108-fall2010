package vooga.games.grandius.states;

import vooga.engine.resource.Resources;
import vooga.engine.state.BasicTextGameState;

public class GameCompleteState extends BasicTextGameState {

	private static String myGameCompleteMessage = Resources.getString("GameCompleteMessage");
	public GameCompleteState() {
		super(myGameCompleteMessage);
	}

}
