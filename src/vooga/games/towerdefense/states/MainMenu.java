package vooga.games.towerdefense.states;

import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.factory.LevelParser;
import vooga.engine.resource.Resources;
import vooga.engine.state.*;
import vooga.games.towerdefense.LayoutParser;
import vooga.widget.*;

import java.util.*;


public class MainMenu extends GameState{
	
	
	public MainMenu(){
		super();
		initialize();
	}
	
	@Override
	public void initialize() {
			LevelParser layoutReader = new LayoutParser();
			//TODO getting the playField currently generates an error
			//the main menu can't be displayed until this is resolved
			addPlayField(layoutReader.getPlayfield(Resources.getString("mainMenuLayoutPath"), Resources.getGame()));
	}
	
 
}
