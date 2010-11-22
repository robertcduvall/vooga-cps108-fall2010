package vooga.games.towerdefense.states;

import vooga.engine.core.PlayField;
import vooga.engine.event.EventPool;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.widget.levelparse.ModularLevelParser;

public class GameOverState extends GameState{
	
	public GameOverState(){
		super();
		initialize();
	}
	
	/**
	 * Read inn the layout  xml file representing the main menu.
	 */
	@Override
	public void initialize() {
		ModularLevelParser layoutReader = new ModularLevelParser("moduleMapProperties");
		addPlayField(layoutReader.getPlayfield(Resources.getString("gameOverLayoutPath"), Resources.getGame()));
	}

}
