/**
 * 
 */
package vooga.games.asteroids;

import java.awt.event.KeyEvent;

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
public class Blah extends Game {

	/**
	 * 
	 */
	
	PlayState playState;
	PauseGameState pauseState;
	private LevelManager levelManager;
	
	
	public void initResources() {
		super.initResources();
		initLevelManager();
		PlayField levelPlayField = levelManager.loadFirstLevel();
		playState = new PlayState(this, levelPlayField);
		pauseState = new PauseGameState(playState);
		stateManager.addGameState(playState, pauseState);
		resumeGame();
	}
	
	
	private void initLevelManager() {
		//The reason this is not being moved to core.Game class is because you might want to 
		//create your own LevelParser in certain cases. In that case, use the following constructor:
		//levelManager = new LevelManager(this, yourCustomizedParser);
		levelManager = new LevelManager(this);
		String levelFilesDirectory = Resources.getString("levelFilesDirectory");
		String levelNamesFile = Resources.getString("levelNamesFile");
		levelManager.makeLevels(levelFilesDirectory,levelNamesFile);		
	}

	public void pauseGame() {
		stateManager.activateOnly(pauseState);		
	}
	
	public void resumeGame() {		
		stateManager.activateOnly(playState);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(new Blah(), "Guest");
	}

}
