package vooga.games.zombieland;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.IOException;
import vooga.engine.core.Game;
import vooga.engine.state.*;
import vooga.games.zombieland.gamestates.ZombielandPauseState;
import vooga.games.zombieland.gamestates.ZombielandPlayState;

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

public class Blah extends Game implements Constants{

	private static ZombielandPlayState zombielandPlayState;
	private static ZombielandPauseState zombielandPauseState;
	
	/**
	 * We overrode this method because we have specific a subclass ResourceHandler 
	 * that we implemented for our purpose
	 */
	public void initResources() {
		
		ZombielandResources.initialize(this,DEFAULT_RESOURCE_DIRECTORY);
		try {
			ZombielandResources.loadPropertiesFile(RESOURCE_FILENAME);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		zombielandPauseState = new ZombielandPauseState(this);
		zombielandPlayState = new ZombielandPlayState(this);
	
		initGameStates();
		getGameStateManager().addGameState(zombielandPlayState);
		zombielandPlayState.activate();
		getGameStateManager().addGameState(zombielandPauseState);
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
	 * updating getGameStateManger() and zombielandPlayState makes the game 
	 * go twice the speed, which makes the game more fun.
	 */
	public void update(long elapsedTime) {
		
		if (bsInput.getKeyPressed() == KeyEvent.VK_P) {
			getGameStateManager().toggle(zombielandPlayState);
			getGameStateManager().toggle(zombielandPauseState);
		}
		
		getGameStateManager().update(elapsedTime);
	}

	/**
	 * render the graphics component in the game.
	 * rendering both at the same time makes the game run faster.
	 */
	public void render(Graphics2D g) {
		getGameStateManager().render(g);
		zombielandPlayState.render(g);
	}

	/**
	 * Runs the game
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new Blah(), new Dimension(GAME_WIDTH, GAME_HEIGHT),
				false);
		game.start();
	}

}