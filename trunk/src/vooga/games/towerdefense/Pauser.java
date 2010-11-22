package vooga.games.towerdefense;

import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;

public class Pauser {
	
	private static GameStateManager myGameStateManager = Resources.getGame().getGameStateManager();

	public static void pause(){
		GameState pause = myGameStateManager.getGameState(1);
		myGameStateManager.switchTo(pause);
	}
	
	public static void unPause(){
		GameState play = myGameStateManager.getGameState(2);
		myGameStateManager.switchTo(play);
	}
	
	
}
