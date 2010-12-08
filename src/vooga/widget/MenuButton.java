package vooga.widget;

import java.awt.event.KeyEvent;

import vooga.engine.control.KeyboardControl;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;

public class MenuButton extends Button {

	private String stateForAction;
	private KeyboardControl keyboardControl;
	private GameState gameStateForButton;
	
	
	public MenuButton(String str) {
		super(str);
	}
	
	public MenuButton(String label, GameState gamestate) {
		super(label);
		gameStateForButton = gamestate;
	}
	public MenuButton(String label, GameState gamestate, double x, double y) {
		super(label, x, y);
		gameStateForButton = gamestate;
	}

	
	public MenuButton(String label, String gamestate) {
		super(label);
	}

	@Override
	public void actionPerformed() {
		System.out.println("menubutton: action performed!");
		Resources.getGame().switchState(gameStateForButton);
	}
	

}
