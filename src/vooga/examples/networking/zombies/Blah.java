package vooga.examples.networking.zombies;


import java.io.IOException;
import java.net.UnknownHostException;

import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.factory.LevelParser;
import vooga.engine.networking.client.ClientConnection;
import vooga.engine.resource.Resources;
import vooga.examples.networking.zombies.gamestates.CreditMenu;
import vooga.examples.networking.zombies.gamestates.EndGameState;
import vooga.examples.networking.zombies.gamestates.HelpMenu1;
import vooga.examples.networking.zombies.gamestates.HelpMenu2;
import vooga.examples.networking.zombies.gamestates.MainMenu;
import vooga.examples.networking.zombies.gamestates.PauseState;
import vooga.examples.networking.zombies.gamestates.PlayState;
import vooga.examples.networking.zombies.gamestates.WaitingState;

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

	private PlayState playState;
	private PauseState pauseState;
	private EndGameState endGameState;
	private MainMenu mainMenu;
	private HelpMenu1 helpMenu1;
	private HelpMenu2 helpMenu2;
	private static CreditMenu creditMenu;
	private static WaitingState waitingState;
	private ClientConnection connection;

	/**
	 * We overrode this method because we have specific a subclass
	 * ResourceHandler that we implemented for our purpose
	 */
	public void initResources() {

		super.initResources();
		try {
			connection = new ClientConnection("Zombies");
		} 
		catch (Exception e){
			System.out.println("Error connecting to Prestige Worldwide Server: "+ e.getMessage());
			System.exit(1);
		}
		pauseState = new PauseState(this);
		playState = new PlayState(this);
		playState.setConnection(connection);
		LevelParser levelParser = new LevelParser();
		PlayField waitField = levelParser.getPlayfield(Resources.getString("waitXml"), this);
		waitingState = new WaitingState(this, connection, waitField, playState);
		mainMenu = new MainMenu(this);
		helpMenu1 = new HelpMenu1(this);
		helpMenu2 = new HelpMenu2(this);
		creditMenu = new CreditMenu(this);
		endGameState = new EndGameState(this);
//		getGameStateManager().addGameState(mainMenu, helpMenu1, helpMenu2,
//				creditMenu, pauseState, playState, endGameState);
		getGameStateManager().addGameState(waitingState, playState, pauseState, endGameState);
		getGameStateManager().switchTo(waitingState);
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
	public static void run() {
		launch(new Blah());
	}

	public PlayState getPlayGameState() {
		return playState;
	}

}