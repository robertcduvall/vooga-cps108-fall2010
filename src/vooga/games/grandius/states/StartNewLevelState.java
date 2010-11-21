package vooga.games.grandius.states;

import vooga.engine.resource.Resources;
import vooga.engine.state.BasicTextGameState;

public class StartNewLevelState extends BasicTextGameState {

	private static String myStartLevelMessage = Resources.getString("StartLevelMessage");
	public StartNewLevelState() {
		super(myStartLevelMessage);
	}

}
