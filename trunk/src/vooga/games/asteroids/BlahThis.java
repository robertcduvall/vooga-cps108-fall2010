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
		super.initResources();
		stateManager.addGameState(new PlayState(this));		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(new BlahThis());
	}

}
