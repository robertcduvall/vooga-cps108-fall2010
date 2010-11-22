package vooga.games.doodlejump;

import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.factory.LevelManager;
import vooga.engine.resource.Resources;
import vooga.engine.state.PauseGameState;
import vooga.games.doodlejump.BlahThis;
import vooga.games.doodlejump.states.PlayState;
import vooga.games.doodlejump.states.StartMenuState;

public class BlahThis extends Game {

	private PlayState playState;
	private PauseGameState pauseState;
	private StartMenuState startMenuState;
	private LevelManager levelManager;
	
	public void initResources() {
		super.initResources();
		initLevelManager();
		playState = new PlayState(this, levelManager);
		pauseState = new PauseGameState(playState);
		startMenuState = new StartMenuState(this);
		stateManager.addGameState(playState, pauseState, startMenuState);
		stateManager.switchTo(startMenuState);
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

	public void pauseResumeGame() {
		if(pauseState.isActive()){
			stateManager.switchTo(playState);
		}
		else{
			stateManager.switchTo(pauseState);	
		}
	}
	
	public void resumeGame() {		
		stateManager.switchTo(playState);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(new BlahThis());
	}

}
