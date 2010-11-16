package vooga.examples.state;

import java.awt.Dimension;

import com.golden.gamedev.GameLoader;

import vooga.engine.core.Game;
import vooga.engine.state.GameState;
import vooga.engine.state.PauseGameState;
import vooga.examples.resource.resourcesxmlexample.DropThis;

public class StateGame extends Game {
	
	GameState myPlayState;
	GameState myPauseState;
	
	@Override
	public void initResources() {
		
		super.initResources();
		
		myPlayState = new PlayState();
		stateManager.addGameState(myPlayState);
		stateManager.activateOnly(myPlayState);
		
	}
	
	public void pauseGame() {
		
		myPauseState = new PauseGameState(myPlayState);
		
		stateManager.addGameState(myPauseState);
		
		stateManager.toggle(myPlayState);
		stateManager.toggle(myPauseState);
		
	}
	
	public void unpauseGame() {
		
		stateManager.toggle(myPauseState);
		stateManager.toggle(myPlayState);
		
		stateManager.removeGameState(myPauseState);
		
	}

	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new DropThis(), new Dimension(300, 300), false);
		game.start();
	}
	
}
