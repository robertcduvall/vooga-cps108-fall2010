import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;

/**
 * GameStateManager manages the behavior of GameState for overarching classes like Game. At its heart,
 * GameStateManager is a collection of GameStates with a number of useful methods for toggling, activating, and 
 * deactivating specific states. Otherwise, its main purpose is to sort and iterate over GameStates, rendering and
 * updating the active ones.
 * 
 * @author vitorolivier & sort of brentsodman
 */
public class GameStateManager {

	private ArrayList<GameState> currentGameStates;

	/**
	 * Constructs a new GameStateManager.
	 */
	public GameStateManager() {
		currentGameStates = new ArrayList<GameState>();
	}

	/**
	 * Adds a GameState to the GameStateManager's collection of GameStates.
	 * 
	 * @param gamestate
	 */
	public void addGameState(GameState gamestate) {
		currentGameStates.add(gamestate);
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
		sort();
		for (int i = 0; i < currentGameStates.size(); i++) {
			if (currentGameStates.get(i).isActive()) {
				currentGameStates.get(i).update(t);
			}

		}
	}
	
	/**
	 * Iterates over the GameStates, in order of their layer values, and renders the active ones in order.
	 * 
	 * @param g
	 */
	public void render(Graphics2D g) {
		sort();
		for (int i = 0; i < currentGameStates.size(); i++) {
			if (currentGameStates.get(i).isActive()) {
				currentGameStates.get(i).render(g);
			}
		}
	}

	/**
	 * Activates the specified GameState and deactivates all others.
	 * 
	 * @param gamestate
	 */
	public void activateOnly(GameState gamestate) {
		for (int i = 0; i < currentGameStates.size(); i++) {
			if (currentGameStates.get(i).equals(gamestate)) {
				currentGameStates.get(i).activate();
			} else
				currentGameStates.get(i).deactivate();
		}
	}

	/**
	 * Deactivates the specified GameState.
	 * 
	 * @param gamestate
	 */
	public void deactivateOnly(GameState gamestate) {
		for (int i = 0; i < currentGameStates.size(); i++) {
			if (currentGameStates.get(i).equals(gamestate)) {
				currentGameStates.get(i).deactivate();
			}
		}
	}

	/**
	 * Activates all GameStates.
	 */
	public void activateAll() {
		for (int i = 0; i < currentGameStates.size(); i++) {
			currentGameStates.get(i).activate();
		}
	}

	
	/**
	 * If the specified GameState is in the GameStateManager, toggles it between active and inactive.
	 * 
	 * @param gamestate
	 */
	public void toggle(GameState gamestate) {
		for (int i = 0; i < currentGameStates.size(); i++) {
			GameState currentState = currentGameStates.get(i);
			
			if (currentState.equals(gamestate)) {
				if (currentState.isActive()){
					currentState.deactivate();
				} else {
					currentState.activate();
				}
			}
		}
	}

	/**
	 * Deactivates all GameStates in the GameStateManager.
	 */
	public void deactivateAll() {
		for (int i = 0; i < currentGameStates.size(); i++) {
			currentGameStates.get(i).deactivate();
		}
	}

	/**
	 * Sorts all of the GameStates by layer values.
	 */
	private void sort() {
		Collections.sort(currentGameStates);
	}

}
