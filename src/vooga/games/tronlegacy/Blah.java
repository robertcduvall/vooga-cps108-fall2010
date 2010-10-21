package vooga.games.tronlegacy;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.io.IOException;

import com.golden.gamedev.GameLoader;

import vooga.engine.core.Game;
import vooga.engine.overlay.OverlayCreator;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;

/**
 * @author BrentSodman
 *
 * Like Tron++
 */

public class Blah extends Game {
	
	private static final int GAME_WIDTH = 480;
	private static final int GAME_HEIGHT = 480;
	private static final String DEFAULT_FILEPATH = "src/vooga/games/tronlegacy/resources/";

	MainGameState playState = new MainGameState();
	GameState pauseState = new GameState();
	
	GameStateManager stateManager = new GameStateManager();
	
	public void initResources(){
		
		Resources.initialize(this);
		Resources.setDefaultPath(DEFAULT_FILEPATH);
		
		OverlayCreator.setGame(this);
		
		try {
			Resources.loadImageFile("game.properties");
		} catch (IOException e) {
			System.out.println("Error loading images from filepath: " + DEFAULT_FILEPATH);
			this.stop();
		}
		
		playState.initialize(this);
		stateManager.addGameState(playState);
		stateManager.addGameState(pauseState);
		stateManager.toggle(playState);
		
	}
	
	public void render(Graphics2D g){
		stateManager.render(g);
	}
	
	public void update(long elapsedTime){
		stateManager.update(elapsedTime);
	}
	

	
	//placeholder main function
	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new Blah(), new Dimension(GAME_WIDTH, GAME_HEIGHT), false);
		game.start();
	}
	
	
}
