package vooga.widget;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import vooga.engine.control.KeyboardControl;
import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.engine.state.MenuGameState;

public class MenuButton extends Button {

	private String gamePath = "vooga.examples.networking.tictactoe.TicTacToe";
	private static int keyKey = 49;
	

	
	
	public MenuButton(String label, MenuGameState mgs) {
		this(label, null, mgs);
	}
	
	public MenuButton(String label, GameState gamestate, MenuGameState mgs) {
		this(label, gamestate, 0, 0, mgs);
	}
	
	public MenuButton(String label, GameState gamestateForButton, double x, double y, MenuGameState mgs) {
		super(label, x, y);
		mgs.getKeyboardControl().addInput(keyKey, "switchToState", gamePath, GameState.class);
		mgs.getKeyboardControl().setParams(keyKey, gamestateForButton);

	}


	@Override
	public void actionPerformed() {
	}
}
