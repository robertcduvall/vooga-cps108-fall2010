package vooga.games.zombies;

import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.factory.LevelParser;
import vooga.engine.networking.client.ClientConnection;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.games.zombies.gamestates.CreditMenu;
import vooga.games.zombies.gamestates.EndGameState;
import vooga.games.zombies.gamestates.HelpMenu1;
import vooga.games.zombies.gamestates.HelpMenu2;
import vooga.games.zombies.gamestates.MainMenu;
import vooga.games.zombies.gamestates.MultiplayerPlayState;
import vooga.games.zombies.gamestates.PauseState;
import vooga.games.zombies.gamestates.PlayState;
import vooga.games.zombies.gamestates.SingleplayerPlayState;
//import vooga.games.zombies.gamestates.WaitingState;
import vooga.engine.state.WaitingState;
import vooga.games.zombies.gamestates.ZombiesNetworkMenuState;

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
	private SingleplayerPlayState singleplayerState;
	private MultiplayerPlayState multiplayerState;
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
			connection = new ClientConnection("Zombies", userName);
		} 
		catch (Exception e){
			System.out.println("Error connecting to Prestige Worldwide Server: "+ e.getMessage());
			System.exit(1);
		}
		pauseState = new PauseState(this);
		multiplayerState = new MultiplayerPlayState(this);
		multiplayerState.setConnection(connection);
		LevelParser levelParser = new LevelParser();
		PlayField waitField = levelParser.getPlayfield(Resources.getString("waitXml"), this);
		waitingState = new WaitingState(this, connection, waitField, multiplayerState);
		singleplayerState = new SingleplayerPlayState(this);
		mainMenu =          new MainMenu(this);
		helpMenu1 =         new HelpMenu1(this);
		helpMenu2 =         new HelpMenu2(this);
		creditMenu =        new CreditMenu(this);
		endGameState =      new EndGameState(this);
		ZombiesNetworkMenuState networkMenuState = new ZombiesNetworkMenuState();
		networkMenuState.makeButton("Singleplayer", singleplayerState);
		networkMenuState.makeButton("Multiplayer", waitingState);
		networkMenuState.makeButton("Main Menu", mainMenu);
		networkMenuState.makeButton("End Game", endGameState);

		getGameStateManager().addGameState(waitingState, singleplayerState, multiplayerState, pauseState, endGameState, networkMenuState);
		getGameStateManager().switchTo(networkMenuState);
		//getGameStateManager().switchTo(networkMenuState);
	}
	
	public void switchToState (GameState gs) {
		getGameStateManager().switchTo(gs);

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
		launch(new Blah(), "guest");
	}

	public PlayState getPlayGameState() {
		return playState;
	}

}