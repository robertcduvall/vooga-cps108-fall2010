package vooga.engine.state;

import vooga.engine.event.IEventHandler;

public abstract class GameStateChangeEvent implements IEventHandler {

	GameState myTargetState;
	GameStateManager myManager;
	
	public GameStateChangeEvent (GameStateManager gsManager, GameState targetState) {
		
		myTargetState = targetState;
		myManager = gsManager;
		
	}
	
	
	/**
	 * This method should check if the rule for changing to a state is satisfied.
	 */
	@Override
	public abstract boolean isTriggerred();

	@Override
	public void actionPerformed() {

		myManager.activateOnly(myTargetState);
		
	}

}
