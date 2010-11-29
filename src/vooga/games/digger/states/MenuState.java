package vooga.games.digger.states;

import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.widget.levelparse.ModularLevelParser;

public class MenuState extends GameState{
	
	public MenuState(String xmlPathKey){
		super();
		initialize(xmlPathKey);
	}
	
	public void initialize(String xmlPathKey) {
			ModularLevelParser layoutReader = new ModularLevelParser("moduleMapProperties");
			addPlayField(layoutReader.getPlayfield(Resources.getString(xmlPathKey), Resources.getGame()));
	}

	@Override
	public void initialize() {
		// TODO I shouldn't have to make this why do they insist on
		// making initialize an abstract method
		
	}
	
	

}
