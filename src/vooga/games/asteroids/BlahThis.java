/**
 * 
 */
package vooga.games.asteroids;

import vooga.engine.core.Game;

/**
 * @author CPS108 Fall10
 *
 */
public class BlahThis extends Game {

	/**
	 * 
	 */
	public void initResources() {
		PlayGameState playGameState = new PlayGameState();
		playGameState.initialize();
		stateManager.addGameState(playGameState);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(new BlahThis());
	}

}
