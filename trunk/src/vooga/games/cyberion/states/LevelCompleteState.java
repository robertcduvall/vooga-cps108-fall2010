package vooga.games.cyberion.states;

import vooga.engine.resource.Resources;
import vooga.engine.state.BasicTextGameState;

public class LevelCompleteState extends BasicTextGameState{
	private static String myLevelCompleteMessage = Resources
			.getString("levelCompleteMessage");

	public LevelCompleteState() {
		super(myLevelCompleteMessage);
	}
}
