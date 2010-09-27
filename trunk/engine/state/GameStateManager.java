package engine.state;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author vitorolivier
 * 
 */
public class GameStateManager {

	private ArrayList<GameState> currentGameStates;

	/**
	 * 
	 */
	public GameStateManager() {
		currentGameStates = new ArrayList<GameState>();
	}

	/**
	 * @param gamestate
	 */
	public void addGameState(GameState gamestate) {
		currentGameStates.add(gamestate);
	}

	/**
	 * @param gamestate
	 */
	public void removeGameState(GameState gamestate) {
		currentGameStates.remove(gamestate);
	}

	/**
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

	public void render(Graphics2D g) {
		sort();
		for (int i = 0; i < currentGameStates.size(); i++) {
			if (currentGameStates.get(i).isActive()) {
				currentGameStates.get(i).render(g);
			}
		}
	}

	/**
	 * @param gamestate
	 */
	public void activateOnly(GameState gamestate) {
		for (int i = 0; i < currentGameStates.size(); i++) {
			if (currentGameStates.get(i) == gamestate) {
				currentGameStates.get(i).activate();
			} else
				currentGameStates.get(i).deactivate();
		}
	}

	/**
	 * @param gamestate
	 */
	public void deactivateOnly(GameState gamestate) {
		for (int i = 0; i < currentGameStates.size(); i++) {
			if (currentGameStates.get(i) == gamestate) {
				currentGameStates.get(i).deactivate();
			}
		}
	}

	/**
	 * 
	 */
	public void activateAll() {
		for (int i = 0; i < currentGameStates.size(); i++) {
			currentGameStates.get(i).activate();
		}
	}

	public void toggle(GameState gamestate) {
		if (gamestate.isActive())
			gamestate.deactivate();
		else
			gamestate.activate();
	}

	/**
	 * 
	 */
	public void deactivateAll() {
		for (int i = 0; i < currentGameStates.size(); i++) {
			currentGameStates.get(i).deactivate();
		}
	}

	/**
	 * 
	 */
	private void sort() {
		Collections.sort(currentGameStates);
	}

}
