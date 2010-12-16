package vooga.games.tictactoe;

import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.factory.LevelManager;
import vooga.engine.factory.LevelParser;
import vooga.engine.networking.client.ClientConnection;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.games.tictactoe.states.PlayState;
import vooga.games.tictactoe.states.TicTacNetworkMenuState;
import vooga.engine.state.WaitingState;

/**
 * The Vooga Game subclass for TicTacToe, a simple example game for the Networking API.
 * 
 * @author Cue, Kolodziejzyk, Townsend
 * @version 1.0
 */
public class Blah extends Game {
	private PlayState playState;
	private LevelManager levelManager;
	private WaitingState waitState;

	/**
	 * Initializes the levels and the states.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public void initResources() {
		super.initResources();
		levelManager = new LevelManager(this);
		levelManager.makeLevels(Resources.getString("levelFilesDirectory"), Resources.getString("levelNamesFile"));
		initStates();
	}

	/**
	 * Creates the PlayState with the ClientConnection, a WaitingState with the ClientConnection, and a TicTacNetworkMenuState.  Adds them all to the
	 * GameStateManager and switches to the TicTacNetworkMenuState.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	private void initStates() {
		ClientConnection connection = null;
		try{
			connection = new ClientConnection("TicTacToe", userName);
		}
		catch(Exception e){
			System.out.println("Error connecting to Prestige Worldwide Server: "+ e.getMessage());
			System.exit(1);
		}
		playState = new PlayState(this, levelManager, connection);
		LevelParser levelParser = new LevelParser();
		PlayField waitField = levelParser.getPlayfield(Resources.getString("waitXml"), this);
		waitState = new WaitingState(this, connection, waitField, playState);
		TicTacNetworkMenuState networkMenuState = new TicTacNetworkMenuState();
		networkMenuState.makeButton("WaitForOpponent", waitState);
		networkMenuState.makeButton("Play", playState);
		stateManager.addGameState(networkMenuState);
		stateManager.addGameState(waitState);
		stateManager.addGameState(playState);
		stateManager.switchTo(networkMenuState);
		//stateManager.switchTo(waitState);
	}
	
	public void switchToState (GameState gs) {
		stateManager.switchTo(gs);
	}


	/**
	 * Switches to waitState when called by the network menu state.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public void startWaitState() {
		stateManager.switchTo(waitState);
	}
	
	/**
	 * Launch the TicTacToe game.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public static void main(String[] args){
		launch(new Blah(), "guest");
	}
}