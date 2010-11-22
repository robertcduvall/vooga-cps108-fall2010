package vooga.games.zombieland;

import java.awt.event.KeyEvent;

import vooga.engine.control.KeyboardControl;
import vooga.engine.core.Game;
import vooga.engine.state.GameState;
import vooga.games.zombieland.gamestates.*;
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

	private static PlayState playState;
	private static PauseState pauseState;
	private static MainMenu mainMenu;
	private static HelpMenu1 helpMenu1;
	private static HelpMenu2 helpMenu2;
	private static CreditMenu creditMenu;
	
	private KeyboardControl control;
	
	/**
	 * We overrode this method because we have specific a subclass
	 * ResourceHandler that we implemented for our purpose
	 */
	public void initResources() {

		super.initResources();
		pauseState = new PauseState(this);
		playState = new PlayState(this);
		mainMenu = new MainMenu(this);
		helpMenu1 = new HelpMenu1(this);
		helpMenu2 = new HelpMenu2(this);
		creditMenu = new CreditMenu(this);

		getGameStateManager().addGameState(mainMenu,helpMenu1,helpMenu2,creditMenu,pauseState, playState);
	}

	public void pause() {
		getGameStateManager().activateOnly(pauseState);
	}
	public void main() {
		getGameStateManager().activateOnly(mainMenu);
	}
	public void help1() {
		getGameStateManager().activateOnly(helpMenu1);
	}
	public void help2() {
		getGameStateManager().activateOnly(helpMenu2);
	}
	public void credit() {
		getGameStateManager().activateOnly(creditMenu);
	}
	public void play() {
		getGameStateManager().activateOnly(playState);
	}
	
	/**
	 * Runs the game
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(new Blah());
	}

	public PlayState getPlayGameState() {
		return playState;
	}

}