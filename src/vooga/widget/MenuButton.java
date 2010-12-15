package vooga.widget;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import vooga.engine.control.KeyboardControl;
import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.engine.state.MenuGameState;

public class MenuButton extends Button {

	private static String gamePath = "vooga.games.tictactoe.Blah"; //TODO DONT HARDCODE and allow it to extend to other games -- Devon
	private static int keyboardKey = 49; //WTF is this?  --Devon
	

	
	
	public MenuButton(BufferedImage img, String label, MenuGameState mgs) {
		this(img, label, null, mgs);
	}
	
	public MenuButton(BufferedImage img, String label, GameState gamestate, MenuGameState mgs) {
		this(img, label, gamestate, 0, 0, mgs);
	}
	
	public MenuButton(BufferedImage img, String label, double x, double y, MenuGameState mgs, String method, String className) {
		super(img, label, x, y);
		mgs.getKeyboardControl().addInput(keyboardKey, method, className);
		keyboardKey++;	
		}
	
	public MenuButton(BufferedImage img, String label, GameState gamestateForButton, double x, double y, MenuGameState mgs) {
		super(img, label, x, y);
		mgs.getKeyboardControl().addInput(keyboardKey, "switchToState", gamePath, GameState.class);
		mgs.getKeyboardControl().setParams(keyboardKey, gamestateForButton);
		keyboardKey++;

	}


	@Override
	public void actionPerformed() {
	}
	
	public static void setGamePath (String path) {
		gamePath = path; 
	}
}
