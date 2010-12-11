package vooga.games.tronupdate;

import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

import vooga.engine.core.PlayField;
import vooga.engine.core.BetterSprite;
import vooga.engine.event.EventPool;
import vooga.engine.factory.LevelManager;
import vooga.engine.resource.Resources;
import vooga.engine.state.BasicTextGameState;
import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;
import vooga.engine.state.PauseGameState;
import vooga.games.tronupdate.collisions.*;
import vooga.games.tronupdate.state.*;


public class Blah extends vooga.engine.core.Game{
	@Override
	public void initResources() {
		super.initResources();
		setFPS(Resources.getInt("framerate")); 
		initStates();
	}
	
	private void initStates(){
		TronGamePauseState pauseState =new TronGamePauseState(this,stateManager);
		GameOverState gameOverState =new GameOverState(this,stateManager);
		PlayState playState = new PlayState(this,stateManager);
		LoadState loadState = new LoadState(this,stateManager);
		SetLevelState setLevelState= new SetLevelState(this,stateManager);
		HelpState helpState = new HelpState(this,stateManager);
		SetNumMatchesState setNumMatchesState = new SetNumMatchesState(this,stateManager);
		SetEnvironmentState setEnvironmentState = new SetEnvironmentState(this,stateManager);
		
		loadState.activate(); 
		pauseState.deactivate();
		gameOverState.deactivate();
		playState.deactivate();
		
		stateManager.addGameState(playState,Resources.getInt("PlayState"));
		stateManager.addGameState(pauseState,Resources.getInt("PauseState"));
		stateManager.addGameState(gameOverState,Resources.getInt("GameOverState"));
		stateManager.addGameState(loadState,Resources.getInt("LoadState"));
		stateManager.addGameState(setLevelState,Resources.getInt("SetLevelState"));
		stateManager.addGameState(helpState,Resources.getInt("HelpState"));
		stateManager.addGameState(setNumMatchesState,Resources.getInt("SetNumMatchesState"));
		stateManager.addGameState(setEnvironmentState,Resources.getInt("SetEnvironmentState"));
	}

	public static void main(String[] args) {
		launch(new Blah());
	}
}