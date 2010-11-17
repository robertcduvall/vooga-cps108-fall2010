/**
 * 
 */
package vooga.games.asteroids;

import vooga.engine.core.Game;
import vooga.games.asteroids.states.PlayState;

/**
 * @author CPS108 Fall10
 *
 */
public class BlahThis extends Game {

	/**
	 * 
	 */
	public void initResources() {
		PlayState playState = new PlayState(this);
		playState.initialize();
		stateManager.addGameState(playState);
		
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(new BlahThis());
	}

}
