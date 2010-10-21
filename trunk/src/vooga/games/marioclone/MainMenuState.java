package vooga.games.marioclone;

import java.awt.Graphics2D;

import vooga.engine.resource.ResourcesBeta;
import vooga.engine.state.GameState;

import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.GameFontManager;
import com.golden.gamedev.object.background.ImageBackground;

/**
 * 
 * @author David Herzka, Cameron McCallie, Andrew Brown
 *
 *	This extension of GameState represents the opening main menu screen of the game.
 *  
 */

public class MainMenuState extends GameState {

	private ImageBackground myMainMenu;
	private int WIDTH;
	private int HEIGHT;
	private GameFont myMenuFont;

	/**
	 * This constructs a MainMenuState with dimensions for the screen, as well as a font manager
	 * used to render fonts to the screen
	 * 
	 * @param width
	 * @param height
	 * @param fontManager
	 */
	
	public MainMenuState(int width, int height, GameFontManager fontManager) {
		super();
		myMenuFont = fontManager.getFont("MENU");
		myMainMenu = new ImageBackground(ResourcesBeta.getImage("MenuBG"));
		WIDTH = width;
		HEIGHT = height;
	}
	
	/**
	 * Method called to render fonts to the screen, as well as the background.
	 */
	public void render(Graphics2D g) {
		super.render(g);
		
		myMainMenu.render(g);
		myMenuFont.drawString(g, "WELCOME TO MARIOCLONE!", WIDTH / 4, HEIGHT / 2 + 50);
		myMenuFont.drawString(g, "PRESS A AND D TO MOVE LEFT AND RIGHT.",(WIDTH / 4), (HEIGHT / 2) + 125);
		myMenuFont.drawString(g, "PRESS W TO JUMP AND S TO CROUCH.", (WIDTH / 4), (HEIGHT / 2) + 200);
		myMenuFont.drawString(g, "PRESS SPACE TO PLAY!", (WIDTH / 4), (HEIGHT / 2) + 250);
	}
	

}
