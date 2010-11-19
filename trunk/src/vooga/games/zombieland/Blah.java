package vooga.games.zombieland;

import java.awt.event.KeyEvent;
import vooga.engine.core.Game;
import vooga.games.zombieland.gamestates.ZombielandPauseState;
import vooga.games.zombieland.gamestates.ZombielandPlayState;

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

public class Blah extends Game implements Constants {

	private static ZombielandPlayState zombielandPlayState;
	private static ZombielandPauseState zombielandPauseState;

	/**
	 * We overrode this method because we have specific a subclass
	 * ResourceHandler that we implemented for our purpose
	 */
	public void initResources() {

		super.initResources();
		zombielandPauseState = new ZombielandPauseState(this);
		zombielandPlayState = new ZombielandPlayState(this);

		getGameStateManager().addGameState(zombielandPlayState,
				zombielandPauseState);
		// TODO add this in control
		// if (bsInput.getKeyPressed() == KeyEvent.VK_P) {
		// getGameStateManager().toggle(zombielandPlayState);
		// getGameStateManager().toggle(zombielandPauseState);
		// }
	}

	/**
	 * Runs the game
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(new Blah());
	}

	public ZombielandPlayState getPlayGameState() {
		return zombielandPlayState;
	}

}