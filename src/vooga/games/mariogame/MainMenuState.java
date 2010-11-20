package vooga.games.mariogame;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import vooga.engine.control.Control;
import vooga.engine.control.KeyboardControl;
import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.overlay.OverlayCreator;
import vooga.engine.overlay.OverlayTracker;
import vooga.engine.state.MenuGameState;

import com.golden.gamedev.object.GameFontManager;
import com.golden.gamedev.object.background.ImageBackground;
import com.golden.gamedev.object.SpriteGroup;

/**
 * 
 * @author David Herzka, Cameron McCallie, Andrew Brown
 * 
 *         This extension of GameState represents the opening main menu screen
 *         of the game.
 * 
 */

public class MainMenuState extends MenuGameState {

	private Game myGame;
	private ImageBackground myMainMenuBG;
	private SpriteGroup myOverlays;

	/**
	 * This constructs a MainMenuState with dimensions for the screen, as well
	 * as a font manager used to render fonts to the screen
	 * 
	 * @param width
	 * @param height
	 * @param fontManager
	 */

	public MainMenuState(Game game, BufferedImage backgroundImage,
			GameFontManager fontManager) {
		super();
		myGame = game;
		myMainMenuBG = new ImageBackground(backgroundImage);
		OverlayCreator overlayCreator = new OverlayCreator();
		OverlayTracker overlayTracker = overlayCreator.createOverlays("src/vooga/games/mariogame/resources/overlays/MainMenuOverlays.xml");
		myOverlays = overlayTracker.getOverlayGroup("MainMenuGroup");
	}

	/**
	 * Method called to render fonts to the screen, as well as the background.
	 */
	public void render(Graphics2D g) {
		super.render(g);
		myMainMenuBG.render(g);
		myOverlays.render(g);
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		initControls(myGame);
	}
	
	private void initControls(Game game){
		Control gameControl = new KeyboardControl(game,game);
		gameControl.addInput(KeyEvent.VK_SPACE, "resumeGame", "vooga.games.mariogame.DropThis");
		getMenuPlayfield().addControl(gameControl);
	}

}
