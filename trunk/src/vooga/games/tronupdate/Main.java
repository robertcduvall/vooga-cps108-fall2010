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
import vooga.games.tronupdate.state.MenuState;
import vooga.games.tronupdate.state.TronGamePauseState;
import vooga.games.tronupdate.state.TronGamePlayState;
import vooga.games.tronupdate.collisions.*;

public class Main extends vooga.engine.core.Game{
	
	//private TronGamePlayState playState;
	private EventPool eventPool;
	
	@Override
	public void initResources() {
		super.initResources();
		setFPS(Resources.getInt("framerate")); 
		initStates();
	}
	
	private void initStates(){
		TronGamePauseState pauseState =new TronGamePauseState(this,stateManager);
		TronGamePlayState playState = new TronGamePlayState(this,stateManager);
		MenuState menuState = new MenuState(this,stateManager);
		//playState.activate();
		menuState.activate();
		stateManager.addGameState(playState,0);
		stateManager.addGameState(pauseState,1);
		stateManager.addGameState(menuState,2);
		
		
	}

	public static void main(String[] args) {
		launch(new Main());
	}
}