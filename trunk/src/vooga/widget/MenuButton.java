package vooga.widget;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import vooga.engine.control.KeyboardControl;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.engine.state.MenuGameState;

public class MenuButton extends Button {

	private String stateForAction;
	private KeyboardControl keyboardControl;
	private GameState gameStateForButton;
	private MenuGameState menuState;
	private String gamePath = "vooga.examples.networking.tictactoe.TicTacToe";
	private static int keyKey = 49;
	
	
	public MenuButton(String label, MenuGameState mgs) {
		this(label, null, mgs);
	}
	
	public MenuButton(String label, GameState gamestate, MenuGameState mgs) {
		this(label, gamestate, 0, 0, mgs);
	}
	
	public MenuButton(String label, GameState gamestate, double x, double y, MenuGameState mgs) {
		super(label, x, y);
		gameStateForButton = gamestate;
		mgs.getMouseControl().addInput(MouseEvent.MOUSE_CLICKED, "switchToState", gamePath);
		mgs.getKeyboardControl().addInput(keyKey, "switchToState", gamePath);
		System.out.println("menuButton "+gamePath);

	}


	@Override
	public void actionPerformed() {
		if(isTriggered())
			System.out.println("menubutton: action performed!");
		Resources.getGame().switchState(gameStateForButton);
	}
}
