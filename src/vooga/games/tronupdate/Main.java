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


public class Main extends vooga.engine.core.Game{
	@Override
	public void initResources() {
		super.initResources();
		setFPS(Resources.getInt("framerate")); 
		initStates();
	}
	
	private void initStates(){
		TronGamePauseState pauseState =new TronGamePauseState(this,stateManager);
		GameOverState gameOverState =new GameOverState(this,stateManager);
		TronGamePlayState playState = new TronGamePlayState(this,stateManager);
		LoadState loadState = new LoadState(this,stateManager);
		SetLevelState setLevelState= new SetLevelState(this,stateManager);
		HelpState helpState = new HelpState(this,stateManager);
		
		PlayState trialState = new PlayState(this,stateManager);
		
		loadState.activate(); 
		playState.deactivate(); 
		pauseState.deactivate();
		gameOverState.deactivate();
		
		trialState.deactivate();
		
		stateManager.addGameState(playState,Resources.getInt("PlayState"));
		stateManager.addGameState(pauseState,Resources.getInt("PauseState"));
		stateManager.addGameState(gameOverState,Resources.getInt("GameOverState"));
		stateManager.addGameState(loadState,Resources.getInt("LoadState"));
		stateManager.addGameState(setLevelState,Resources.getInt("SetLevelState"));
		stateManager.addGameState(helpState,Resources.getInt("HelpState"));
		
		stateManager.addGameState(trialState,6);
	}

	public static void main(String[] args) {
		launch(new Main());
	}
}