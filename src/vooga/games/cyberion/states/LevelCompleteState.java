package vooga.games.cyberion.states;

import vooga.engine.resource.Resources;
import vooga.engine.state.BasicTextGameState;

/**
 * Level complete state for cyberion
 * 
 * @author Harris.He
 *
 */


public class LevelCompleteState extends BasicTextGameState{
	private static String myLevelCompleteMessage = Resources
			.getString("levelCompleteMessage");

	public LevelCompleteState() {
		super(myLevelCompleteMessage);
	}
	
	//ToDo: load new level file, and start the game again
//	public void startNewLevel(){
//		
//	}
}
