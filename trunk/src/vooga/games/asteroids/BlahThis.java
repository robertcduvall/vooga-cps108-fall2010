/**
 * 
 */
package vooga.games.asteroids;

import vooga.engine.core.Game;

/**
 * @author student
 *
 */
public class BlahThis extends Game {

	/**
	 * 
	 */
	public BlahThis() {
		PlayGameState playGameState = new PlayGameState();
		playGameState.initialize();
		stateManager.addGameState(playGameState);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
