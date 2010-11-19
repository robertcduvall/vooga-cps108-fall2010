package vooga.games.towerdefense.states;

import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.factory.LevelParser;
import vooga.engine.resource.Resources;
import vooga.engine.state.*;
import vooga.games.towerdefense.LayoutParser;
import vooga.widget.*;

import java.util.*;

/**
 *  Designed to be the first thing the player sees (with the 
 *  exception of the possible future implementation of a 
 *  splash screen). However, the LevelParsing is currently down
 *  so the Game does not launch the Main Menu. It reads a playfield 
 *  consisting of buttons for levels, a background, and a title 
 *  from a LayoutParser.
 * @author Daniel Koverman
 *
 */
public class MainMenu extends GameState{
	
	
	public MainMenu(){
		super();
		initialize();
	}
	
	/**
	 * Read inn the layout  xml file representing the main menu.
	 */
	@Override
	public void initialize() {
			LevelParser layoutReader = new LayoutParser();
			//TODO getting the playField currently generates an error
			//the main menu can't be displayed until this is resolved
			addPlayField(layoutReader.getPlayfield(Resources.getString("mainMenuLayoutPath"), Resources.getGame()));
	}
	
 
}
