/**
 * 
 */
package vooga.games.asteroids;

import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.factory.LevelFactory;
import vooga.engine.factory.LevelManager;
import vooga.engine.factory.LevelParser;
import vooga.engine.resource.Resources;
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
		super.initResources();
		PlayField levelPlayField = new LevelManager(this).loadFirstLevel();

		playState = new PlayState(this, levelPlayField);
		pauseState = new PauseGameState(playState);
		stateManager.addGameState(playState, pauseState);
	}
	
	public void pauseGame() {
		stateManager.activateOnly(pauseState);		
	}
	
	public void unpauseGame() {		
		stateManager.activateOnly(playState);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(new BlahThis());
	}

}
