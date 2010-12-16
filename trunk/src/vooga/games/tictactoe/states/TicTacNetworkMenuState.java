package vooga.games.tictactoe.states;

import java.awt.event.KeyEvent;

import vooga.engine.core.Game;
import vooga.engine.state.MenuGameState;
import vooga.engine.state.NetworkMenuState;
import vooga.games.tictactoe.Blah;
import vooga.widget.MenuButton;



public class TicTacNetworkMenuState extends MenuGameState{
	
	public TicTacNetworkMenuState() {
		super();
		initialize();
	}

	@Override
	public void initialize() {
		MenuButton.setGamePath("vooga.games.tictactoe.Blah"); //TODO DONT HARDCODE -- Devon
	}

	

}
