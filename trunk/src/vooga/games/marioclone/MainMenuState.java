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
	private int myWidth;
	private int myHeight;
	private GameFont myMenuFont;

	/**
	 * This constructs a MainMenuState with dimensions for the screen, as well as a font manager
	 * used to render fonts to the screen
	 * 
	 * @param width
	 * @param height
	 * @param fontManager
	 */
	
	public MainMenuState(GameFontManager fontManager) {
		super();
		myMenuFont = fontManager.getFont("MENU");
		myMainMenu = new ImageBackground(ResourcesBeta.getImage("MenuBG"));
        myWidth = ResourcesBeta.getInt("Width");
        myHeight = ResourcesBeta.getInt("Height");
	}
	
	/**
	 * Method called to render fonts to the screen, as well as the background.
	 */
	public void render(Graphics2D g) {
		super.render(g);
		
		myMainMenu.render(g);
		myMenuFont.drawString(g, "WELCOME TO MARIOCLONE!", myWidth / 4, myHeight / 2 + 50);
		myMenuFont.drawString(g, "PRESS A AND D TO MOVE LEFT AND RIGHT.",(myWidth / 4), (myHeight / 2) + 125);
		myMenuFont.drawString(g, "PRESS W TO JUMP AND S TO CROUCH.", (myWidth / 4), (myHeight / 2) + 200);
		myMenuFont.drawString(g, "PRESS SPACE TO PLAY!", (myWidth / 4), (myHeight / 2) + 250);
	}
	

}
