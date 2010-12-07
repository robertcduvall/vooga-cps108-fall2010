package vooga.examples.networking.tictactoe.states;

import vooga.engine.core.Game;
import vooga.engine.state.NetworkMenuState;



public class TicTacNetworkMenuState extends NetworkMenuState{
	
	public TicTacNetworkMenuState(Game g) {
		super(g);
		config = "vooga/examples/networking/tictactoe/resources/config.properties";

	}
	
	public void initialize() {
		setEnterAddress("vooga.examples.networking.tictactoe.TicTacToe");
		super.initialize(342, 333);
	}

}
