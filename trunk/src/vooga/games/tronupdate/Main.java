package vooga.games.tronupdate;

import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

import vooga.engine.core.PlayField;
import vooga.engine.core.Sprite;
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
	
	private LevelManager levelManager;
	private PlayState playState;
	private PauseGameState textState;
	private EventPool ep;
	//private BasicCollisionGroup playerAndBoundariesCollision;
	
	@Override
	public void initResources() {
		super.initResources();
		initLevelManager();
		// Setup game states
		//BasicTextGameState start_state = new BasicTextGameState("push enter to start the wonderful game!");
		//start_state.initialize();
		
		PlayField levelPlayField = levelManager.loadFirstLevel();  
		
		playState = new PlayState(this, levelPlayField);
		textState = new PauseGameState(playState,"can you work ??");  //can't display the image
		
		// Add game states to game state manager
		//iniEvents();
		//levelPlayField.addEventPool(ep);
		levelPlayField.addCollisionGroup((SpriteGroup)(playState.getGroup("playerSpriteGroup")),null,  new PlayerAndBoundariesCollision(0,0,800,600,textState,stateManager));
		
		stateManager.addGameState(playState);
		stateManager.addGameState(textState);
		stateManager.activateOnly(playState);
		//stateManager.switchTo(pauseState);
		
	}
	
	private void iniEvents() {
		ep = new EventPool();
		GameOverEvent gp = new GameOverEvent((Sprite)(playState.getGroup("playerSpriteGroup").getSprites()[1]),textState,stateManager);
		ep.addEvent(gp);
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
	
	

	public static void main(String[] args) {
		launch(new Main());
	}
}
