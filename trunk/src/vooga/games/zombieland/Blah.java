package vooga.games.zombieland;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.IOException;
import vooga.engine.core.Game;
import vooga.engine.state.*;

import com.golden.gamedev.GameLoader;

/**
 * @date 10-8-10
 * @author Aaron Choi, Jimmy Mu, Yang Su
 * @description: The Zombieland game is a game in which the player controls a
 *               shooter that has access to a few different types of weapons
 *               with limited ammo. The objective is to stay alive and kill as
 *               many zombies as you can. Zombies are spawned regularly. When a
 *               zombie is killed, there's a chance that an bonus item will be
 *               dropped.
 */

public class Blah extends Game {
	
	private static final int SCREEN_WIDTH = 700;
	private static final int SCREEN_HEIGHT = 500;

	private static GameStateManager stateManager;
	private static ZombielandPlayState zombielandPlayState;
	private static ZombielandPauseState zombielandPauseState;
	
	public void initResources() {
		
		ZombielandResources.initialize(this,
				"src/vooga/games/zombieland/resources/");
		try {
			ZombielandResources.loadPropertiesFile("game.properties");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		stateManager = new GameStateManager();
		
		zombielandPauseState = new ZombielandPauseState(this);
		zombielandPlayState = new ZombielandPlayState(this, SCREEN_WIDTH,
				SCREEN_HEIGHT);
		zombielandPlayState.initialize();
	
		stateManager.addGameState(zombielandPlayState);
		stateManager.addGameState(zombielandPauseState);
		stateManager.activateAll();
	}

	/**
	 * Check for null pointer returns
	 * 
	 * @return
	 */
	public GameState getCurrentState() {
		return zombielandPlayState;
	}

	/**
	 * update all components of the ZombieLand game. This method checks to see
	 * if more zombies can be added or if the level has been completed.
	 */
	public void update(long elapsedTime) {
		
		if (bsInput.getKeyPressed() == KeyEvent.VK_P) {
			stateManager.toggle(zombielandPauseState);
			stateManager.toggle(zombielandPlayState);
			
			boolean active = zombielandPlayState.getOverlayPauseString().isActive();
			zombielandPlayState.getOverlayPauseString().setActive(!active);
		}
		
		stateManager.update(elapsedTime);
		zombielandPlayState.update(elapsedTime);
	}

	/**
	 * render the graphics component in the game
	 */
	public void render(Graphics2D g) {
		stateManager.render(g);
		zombielandPlayState.render(g);
	}

	/**
	 * Runs the game
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new Blah(), new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT),
				false);
		game.start();
	}

}