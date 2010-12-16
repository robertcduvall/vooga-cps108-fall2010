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

	private static String gamePath; //TODO DONT HARDCODE and allow it to extend to other games -- Devon
	private static int keyboardKey = 49; //WTF is this?  --Devon
	private MenuGameState menuState;
	

	

	public MenuButton(	BufferedImage img, 
						String label, 
						GameState gamestate, 
						MenuGameState mgs) {
		super(img, label, 0, 0);
	}
	
	public MenuButton(	BufferedImage img, 
						String label, 
						double x, 
						double y, 
						String method, 
						MenuGameState mgs, 
						Class<?> paramType,
						Object...objects
						) {
		super(img, label, x, y);
		menuState = mgs;
		menuState.getKeyboardControl().addInput(keyboardKey, method, gamePath, paramType);
		menuState.getKeyboardControl().setParams(keyboardKey, objects);

		System.out.println("yeah menubutton");
		keyboardKey++;	
		}
	
	
	/*public MenuButton(BufferedImage img, String label, GameState gamestateForButton, double x, double y, String method, MenuGameState mgs) {
		this(img, label, x, y, mgs, method, GameState.class);
		setGameStateToSwitchTo(gamestateForButton);
	}*/

	/*public MenuButton(	BufferedImage img, 
						String label, 
						double x, 
						double y, 
						String method, 
						MenuGameState mgs,
						GameState gamestateForButton) {
		super(img, label, x, y);
		menuState = mgs;
		System.out.println("menubutton: "+method);
		menuState.getKeyboardControl().addInput(keyboardKey, method, gamePath, GameState.class);
		menuState.getKeyboardControl().setParams(keyboardKey, gamestateForButton);
		keyboardKey++;

	}*/
	public MenuButton(	BufferedImage img, 
						String label, 
						double x, 
						double y, 
						String method, 
						MenuGameState mgs,
						GameState gamestateForButton) {
		this(img, label, x, y, method, mgs, GameState.class, gamestateForButton);
		/*menuState = mgs;
		System.out.println("menubutton: "+method+" "+gamestateForButton);
		menuState.getKeyboardControl().addInput(keyboardKey, method, gamePath, GameState.class);
		menuState.getKeyboardControl().setParams(keyboardKey, gamestateForButton);
		keyboardKey++;*/
	}


	@Override
	public void actionPerformed() {
	}
	
	public static void setGamePath (String path) {
		gamePath = path; 
	}
}
