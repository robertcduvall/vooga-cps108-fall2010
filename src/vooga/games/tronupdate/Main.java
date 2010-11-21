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
import vooga.games.tronupdate.events.GameOverEvent;
import vooga.games.tronupdate.state.PlayState;
import vooga.games.tronupdate.collisions.*;

public class Main extends vooga.engine.core.Game{
	
	private PlayState playState;
	private EventPool eventPool;
	
	@Override
	public void initResources() {
		super.initResources();
		setFPS(Resources.getInt("framerate")); 
		initStates();
	}
	
	private void initStates(){
		playState = new PlayState(this);
		playState.activate();
	//	levelPlayField.addCollisionGroup((SpriteGroup)(playState.getGroup("playerSpriteGroup")),null,  new PlayerAndBoundariesCollision(0,0,800,600,textState,stateManager));
		//getGameStateManager().addGameState(zombielandPlayState);
		stateManager.addGameState(playState);
	}
	
	private void iniEvents() {
		eventPool = new EventPool();
	//	GameOverEvent gp = new GameOverEvent((BetterSprite)(playState.getGroup("playerSpriteGroup").getSprites()[1]),textState,stateManager);
	//	eventPool.addEvent(gp);
	}


	public static void main(String[] args) {
		launch(new Main());
	}
}
