package vooga.games.digger;

import java.util.HashMap;
import java.util.Map;

import vooga.engine.core.Game;
import vooga.engine.state.GameState;
import vooga.games.digger.states.MenuState;
import vooga.games.digger.states.PlayState;

/**
 * The start of a game which will apparently 
 * feature digging somehow. 
 * @author Daniel Koverman
 *
 */
public class DropThis extends Game {
	public Map<String, GameState> stateMap;
	
	public static void main(String[] args){
		launch(new DropThis());
	}
	
	public void switchGameState(String stateName){
		getGameStateManager().switchTo(stateMap.get(stateName));
	}
	
	@Override
	public void initGameStates(){
		super.initGameStates();
		stateMap = new HashMap<String, GameState>();
		GameState mainMenu = addGameState(new MenuState("mainMenuLayout"));
		stateMap.put("mainMenu", mainMenu);
		GameState playState = addGameState(new PlayState("level1XML"));
		stateMap.put("play", playState);
		getGameStateManager().switchTo(mainMenu);		
	}

}
