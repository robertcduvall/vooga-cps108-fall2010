package vooga.games.towerdefense;

import java.awt.Graphics2D;
import java.util.*;

import vooga.engine.state.GameState;

/**
 * GameStateManager manages the behavior of GameState for overarching classes like Game. At its heart,
 * GameStateManager is a collection of GameStates with a number of useful methods for toggling, activating, and 
 * deactivating specific states. Otherwise, its main purpose is to sort and iterate over GameStates, rendering and
 * updating the active ones.
 * 
 * @author VitorOlivier & BrianSimel & sort of BrentSodman
 * 
 * Special thanks to UTA Ben Getson for helping with refactoring
 */
public class NonSetGameStateManager {

	private Collection<GameState> currentGameStates;

	/**
	 * Constructs a new GameStateManager.
	 */
	public NonSetGameStateManager() {
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
		//sort();
		for (GameState state: currentGameStates){
			if (state.isActive()){
				state.update(t);
			}
		}
	}
	
	/**
	 * Iterates over the GameStates, in order of their layer values, and renders the active ones in order.
	 * 
	 * @param g
	 */
	public void render(Graphics2D g) {
		//sort();
		for (GameState state: currentGameStates){
			if (state.isActive()){
				state.render(g);
			}
		}
	}

	/**
	 * Activates the specified GameState and deactivates all others.
	 * 
	 * @param gamestate
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

//	/**
//	 * Deactivates a GameState, transfers all sprite groups to another GameState
//	 * which is then activated.
//	 * 
//	 * @param gamestate
//	 */
//	public void switchToAndTransferAll(GameState gamestate) {
//		
//		gamestate.addState(this);
//		this.switchTo(gamestate);
//	}
//
//	/**
//	 * Deactivates this GameState, transfers update sprite group to gamestate
//	 * which is then activated
//	 * 
//	 * @param gamestate
//	 */
//	public void switchToAndTransferUpdate(GameState gamestate) {
//		gamestate.addUpdateState(this);
//		switchTo(gamestate);
//	}
//
//	/**
//	 * Deactivates this GameState, transfers update sprite group to gamestate
//	 * which is then activated
//	 * 
//	 * @param gamestate
//	 */
//	public void switchToAndTransferRender(GameState gamestate) {
//		gamestate.addRenderState(this);
//		switchTo(gamestate);
//	}

	
}
