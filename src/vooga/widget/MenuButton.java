package vooga.widget;

import java.awt.image.BufferedImage;


import vooga.engine.state.GameState;
import vooga.engine.state.MenuGameState;

@SuppressWarnings("serial")
public class MenuButton extends Button {

	private static String gamePath;
	private static int keyboardKey = 49; //keyCode 49 = 1; 50 = 2; etc.
	private MenuGameState menuState;
	

	/**
	 * 
	 * @param img
	 * @param label
	 * @param gamestate
	 * @param mgs
	 */
	public MenuButton(	BufferedImage img, 
						String label, 
						GameState gamestate, 
						MenuGameState mgs) {
		super(img, label, 0, 0);
	}
	
	/**
	 * 
	 * @param img
	 * @param label
	 * @param x
	 * @param y
	 * @param method
	 * @param mgs
	 * @param paramType
	 * @param objects
	 */
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
		keyboardKey++;	
		}

	/**
	 * 
	 * @param img
	 * @param label
	 * @param x
	 * @param y
	 * @param method
	 * @param mgs
	 * @param gamestateForButton
	 */
	public MenuButton(	BufferedImage img, 
						String label, 
						double x, 
						double y, 
						String method, 
						MenuGameState mgs,
						GameState gamestateForButton) {
		this(img, label, x, y, method, mgs, GameState.class, gamestateForButton);
	}


	@Override
	/**
	 * 
	 */
	public void actionPerformed() {
	}
	
	/**
	 * Sets the path for the game.
	 * @param path
	 */
	public static void setGamePath (String path) {
		gamePath = path; 
	}
}
