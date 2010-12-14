package vooga.games.zombieland;


import vooga.engine.core.Game;
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
	private static EndGameState endGameState;
	private static MainMenu mainMenu;
	private static HelpMenu1 helpMenu1;
	private static HelpMenu2 helpMenu2;
	private static CreditMenu creditMenu;

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
		endGameState = new EndGameState(this);
		getGameStateManager().addGameState(mainMenu, helpMenu1, helpMenu2,
				creditMenu, pauseState, playState, endGameState);
	}

	public void play() {
		getGameStateManager().switchTo(playState);
	}

	public void pause() {
		getGameStateManager().switchTo(pauseState);
	}

	public void main() {
		getGameStateManager().switchTo(mainMenu);
	}

	public void help1() {
		getGameStateManager().switchTo(helpMenu1);
	}

	public void help2() {
		getGameStateManager().switchTo(helpMenu2);
	}

	public void credit() {
		getGameStateManager().switchTo(creditMenu);
	}

	public void end() {
		getGameStateManager().switchTo(endGameState);
	}

	public void reset() {
		getPlayGameState().initialize();
		credit();
	}

	/**
	 * Runs the game
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(new Blah(), "player");
	}

	public PlayState getPlayGameState() {
		return playState;
	}

}