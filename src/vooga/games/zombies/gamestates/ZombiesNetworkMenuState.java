package vooga.games.zombies.gamestates;

import vooga.engine.state.MenuGameState;
import vooga.widget.MenuButton;

public class ZombiesNetworkMenuState extends MenuGameState{
	
	public ZombiesNetworkMenuState() {
		super();
		initialize();
	}

	@Override
	public void initialize() {
		MenuButton.setGamePath("vooga.games.zombies.Blah"); //TODO DONT HARDCODE -- Devon
		/*makeNextButton("Quit","exit" ,"vooga.engine.state.MenuGameState");*/
	}

	

}
