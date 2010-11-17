package vooga.engine.state;

import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * GameStateManager manages the behavior of GameState for overarching classes like Game. At its heart,
 * GameStateManager is a collection of GameStates with a number of useful methods for toggling, activating, and 
 * deactivating specific states. Otherwise, its main purpose is to sort and iterate over GameStates, rendering and
 * updating the active ones.
 * 
 * @author VitorOlivier & BrianSimel & BrentSodman
 * 
 * Special thanks to UTA Ben Getson for helping with refactoring and Scott Brothers for helping develop this concept
 */
public class GameStateManager {

	/**
	 * A list of all GameStates in the GameStateManager
	 */
	private ArrayList<GameState> currentGameStates;

	/**
	 * Constructs a new empty GameStateManager.
	 */
	public GameStateManager() {
		currentGameStates = new ArrayList<GameState>();
	}

	/**
	 * Adds a GameState to the top layer of a GameStateManager's collection of GameStates.
	 * 
	 * @param gamestate
	 */
	public void addGameState(GameState gamestate) {
		addGameState(gamestate, 0);
	}

	/**
	 * Adds a GameState to the layer above the specified GameState
	 * @param gamestate represents the GameState to be added
	 * @param nextStateDown represents the GameState one layer below the new GameState
	 */
	public void addGameState(GameState gamestate, int index){
		gamestate.initialize();
		currentGameStates.add( index, gamestate );
	}
	
	/**
	 * Same as addGameState(GameState, int), but takes a target GameState instead of a target index.
	 * 
	 * @param gamestate
	 * @param nextstate
	 */
	public void addGameState(GameState gamestate, GameState nextstate){
		addGameState(gamestate, currentGameStates.indexOf(nextstate));
	}

	/**
	* Add game state(s) to the game state manager. Assumes the first game state
	* in the list to be the default game state and activates it.
	* 
	* @param states a list of game states
	*/

	public void addGameState(GameState... states) {
		for (GameState gamestate : states) {
			addGameState(gamestate);
		}
		currentGameStates.get(0).activate();
	}
	
	/**
	 * Removes a GameState from the GameStateManager's collection of GameStates.
	 * 
	 * @param gamestate
	 */
	public void removeGameState(GameState gamestate) {
		currentGameStates.remove(gamestate);
	}

	/**
	 * Iterates over the GameStates, in order of their layer values, and updates the active ones in order.
	 * 
	 * @param t
	 */
	public void update(long t) {
		for (GameState state: currentGameStates){
			if (state.isActive()){
				state.update(t);
			}
		}
	}
	
	/**
	 * Iterates over the GameStates, in order of their layer values, and renders the active ones in order.
	 * 
	 * @param g represents the Graphics2D instance
	 */
	public void render(Graphics2D g) {
		for (GameState state: currentGameStates){
			if (state.isActive()){
				state.render(g);
			}
		}
	}

	/**
	 * Activates the specified GameState and deactivates all others.
	 * 
	 * @param gamestate represents the GameState to be activated; all others will be deactivated
	 */
	public void activateOnly(GameState gamestate) {
		for (GameState state: currentGameStates){
			if (state.equals(gamestate)){
				state.activate();
			}
			else{
				state.deactivate();
			}
		}
	}

	/**
	 * Deactivates the specified GameState.
	 * 
	 * @param gamestate
	 */
	public void deactivateOnly(GameState gamestate) {
		for (GameState state: currentGameStates){
			if (state.equals(gamestate)){
				state.deactivate();
			}
		}
	}

	/**
	 * Activates all GameStates.
	 */
	public void activateAll() {
		for (GameState state: currentGameStates){
			state.activate();
		}
	}

	
	/**
	 * If the specified GameState is in the GameStateManager, toggles it between active and inactive.
	 * 
	 * @param gamestate
	 */
	public void toggle(GameState gamestate) {
		for (GameState state: currentGameStates){
			if (state.equals(gamestate)) {
				if (state.isActive()){
					state.deactivate();
				} else {
					state.activate();
				}
			}
		}
	}

	/**
	 * Deactivates all GameStates in the GameStateManager.
	 */
	public void deactivateAll() {
		for (GameState state: currentGameStates){
			state.deactivate();
		}
	}

	/**
	 * Deactivates all GameStates except for the parameter GameState, which is activated.
	 * 
	 * @param gamestate
	 */
	public void switchTo(GameState activatedGameState) {
		for (GameState state: currentGameStates){
			state.deactivate();
		}
		activatedGameState.activate();

	}
	
}
