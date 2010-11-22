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
import vooga.games.tronupdate.state.GameOverState;
import vooga.engine.state.PauseGameState;
import vooga.games.tronupdate.state.LoadState;
import vooga.games.tronupdate.state.TronGamePauseState;
import vooga.games.tronupdate.state.TronGamePlayState;
import vooga.games.tronupdate.collisions.*;
import vooga.games.tronupdate.state.SetNumMatchesState;

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
		LoadState menuState = new LoadState(this,stateManager);
		SetNumMatchesState setNumMatchesState= new SetNumMatchesState(this,stateManager);
		menuState.activate(); playState.deactivate(); pauseState.deactivate();gameOverState.deactivate();
		
		stateManager.addGameState(playState,0);
		stateManager.addGameState(pauseState,1);
		stateManager.addGameState(gameOverState,2);
		stateManager.addGameState(menuState,3);
		stateManager.addGameState(setNumMatchesState,4);
	}

	public static void main(String[] args) {
		launch(new Main());
	}
}