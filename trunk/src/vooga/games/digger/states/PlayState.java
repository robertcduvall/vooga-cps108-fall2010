package vooga.games.digger.states;

import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.widget.levelparse.ModularLevelParser;


public class PlayState extends GameState{
	
	public PlayState(String xmlPathKey){
		super();
		initialize(xmlPathKey);
	}
	
	public void initialize(String xmlPathKey) {
			ModularLevelParser layoutReader = new ModularLevelParser("moduleMapProperties");
			addPlayField(layoutReader.getPlayfield(Resources.getString(xmlPathKey), Resources.getGame()));
	}

	@Override
	public void initialize() {
		
	}
}
