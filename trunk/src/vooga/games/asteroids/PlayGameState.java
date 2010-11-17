package vooga.games.asteroids;

import vooga.engine.state.GameState;

public class PlayGameState extends GameState {

	@Override
	public void initialize() {
		
//		we should get level' playfield here!
		add(playfield);
		
	}

}
