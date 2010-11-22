package vooga.games.towerdefense.states;

import java.awt.event.KeyEvent;

import vooga.engine.control.Control;
import vooga.engine.control.KeyboardControl;
import vooga.engine.overlay.OverlayTracker;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.engine.state.PauseGameState;


public class PauseState extends PauseGameState {

	OverlayTracker myTracker;
	
	

	public PauseState(GameState previousGameState, OverlayTracker tracker) {
		super(previousGameState, "");
		myTracker = tracker;
	}

	@Override
	public void initialize() {
		super.initialize();
		getPlayField().addControl("unPause", addControls());
		getPlayField().addGroup(myTracker.getOverlayGroup("pause"));
	}

	private Control addControls() {
		KeyboardControl control = new KeyboardControl(this, Resources.getGame());
		control.addInput(KeyEvent.VK_U, "unPause", "vooga.games.towerdefense.Pauser");
		return control;
	}
	
}
