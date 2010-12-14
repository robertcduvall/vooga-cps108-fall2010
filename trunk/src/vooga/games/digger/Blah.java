package vooga.games.digger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

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
public class Blah extends Game {
	public Map<String, GameState> stateMap;
	
	public static void main(String[] args){
		try {
			EntityMap.inititalize();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		launch(new Blah(), "Guest");
	}
	
	public void switchGameState(String stateName){
		getGameStateManager().switchTo(stateMap.get(stateName));
	}
	
	@Override
	public void initGameStates(){
		super.initGameStates();
		setMod("mod1");
		stateMap = new HashMap<String, GameState>();
		GameState mainMenu = addGameState(new MenuState("mainMenuLayout"));
		stateMap.put("mainMenu", mainMenu);
		GameState playState = addGameState(new PlayState("level1XML"));
		stateMap.put("play", playState);
		getGameStateManager().switchTo(mainMenu);		
	}

}
