/**
 * 
 */
package vooga.games.asteroids;

import vooga.engine.core.Game;
import vooga.engine.state.PauseGameState;
import vooga.games.asteroids.states.PlayState;

/**
 * @author CPS108 Fall10
 *
 */
public class BlahThis extends Game {

	/**
	 * 
	 */
	
	PlayState playState;
	PauseGameState pauseState;
	
	
	public void initResources() {
	
		//dont change this assholes
		playState = new PlayState(this);
		playState.initialize();
		
		stateManager.addGameState(playState);		

	}
	
	public void pauseGame() {
		
		pauseState = new PauseGameState(playState);
		pauseState.initialize();
		
		stateManager.addGameState(pauseState);
		
		stateManager.activateOnly(pauseState);
		
	}
	
	public void unpauseGame() {
		
		stateManager.activateOnly(playState);
		stateManager.removeGameState(pauseState);
		
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
