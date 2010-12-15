package vooga.engine.state;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Method;

import javax.imageio.ImageIO;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.util.ImageUtil;

import vooga.engine.control.KeyboardControl;
import vooga.engine.control.MouseControl;
import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.resource.Resources;
import vooga.widget.Button;
import vooga.widget.MenuButton;

/**
 * MenuGameState is a reusable component extension of GameState which 
 * contains a series of Buttons.
 * @author Brent
 * @author with a splash of Brian (comments)
 *
 */
public abstract class MenuGameState extends GameState {

	/**
	 * menuPlayfield is the desired new instance of PlayField
	 */	

	private int buttonHeight = 54; //TODO DO NOT HARDCORE --Devon
	private String buttonGroup = "buttonGroup";
	private PlayField menuPlayfield = new PlayField();
	private SpriteGroup buttons = new SpriteGroup(buttonGroup);
	private SpriteGroup backgroundGroup = new SpriteGroup("backgroundGroup");
	private MouseControl mouseControl;
	private KeyboardControl keyboardControl;
	private Game thisGame = Resources.getGame();
	private BufferedImage DEFAULT_BUTTON_IMAGE;
	private BufferedImage DEFAULT_BACKGROUND_IMAGE;
	private File buttonImageFile = new File("src/vooga/engine/state/resources/images/defaultMenuButton.png");
	private File backgroundImageFile = new File("src/vooga/engine/state/resources/images/defaultMenuBackground.png");



	/**
	 * Creates a new instance of MenuGameState
	 */
	public MenuGameState(){
		super();
		try{
			DEFAULT_BUTTON_IMAGE = ImageIO.read(buttonImageFile);
			DEFAULT_BACKGROUND_IMAGE = ImageUtil.resize(ImageIO.read(backgroundImageFile), thisGame.getWidth(), thisGame.getHeight());
		}
		catch(Exception e) {
			System.out.println("MenuGameState.java: "+e.getMessage());	
		}	
		Sprite menuBackGroundSprite = new Sprite(DEFAULT_BACKGROUND_IMAGE);
		backgroundGroup.add(menuBackGroundSprite);
		menuPlayfield.addGroup(backgroundGroup);
		menuPlayfield.addGroup(buttons);
		mouseControl = new MouseControl(thisGame, thisGame);
		keyboardControl = new KeyboardControl(thisGame, thisGame);
		menuPlayfield.addControl("keyboard", keyboardControl);
		menuPlayfield.addControl("mouse", mouseControl);

		initialize();
	}
	
	


	/**
	 * Creates a new instance of MenuGameState with a specified collection of buttons
	 * @param buttons iterable collection of buttons
	 */
	public MenuGameState(Iterable<Button> buttons) {
		this();
		for (Button button : buttons) {
			addButton(button);
		}
	}
	

	
	/**
	 * Initializes MenuGameState
	 */
	@Override
	public abstract void initialize();

	/**
	 * Adds a specified Button to the MenuGameState
	 * @param button
	 */
	public void addButton(Button button) {
		menuPlayfield.getGroup(buttonGroup).add(button);
		addPlayField(menuPlayfield);

	}
	
	public void makeButton(String label) {
		addButton(new MenuButton(DEFAULT_BUTTON_IMAGE, label, this));
	}
	
	public void setButtonHeight(int height) {
		buttonHeight = height;
	}
	

	
	public void makeButton(String label, GameState gamestate) {
		addButton(new MenuButton(DEFAULT_BUTTON_IMAGE, label, gamestate, this));
	}
	
	public void makeButton(String label, GameState gamestate, double x, double y) {
		addButton(new MenuButton(DEFAULT_BUTTON_IMAGE, label, gamestate, x, y, this));
	}
	
	public void makeNextButton(String label, GameState gamestate) {
		makeButton(	label, 
					gamestate, 
					5.0,
					(double)(menuPlayfield.getGroup(buttonGroup).getSize() * buttonHeight)+5.0); //TODO FIX BAD CODE --DEVON
	}

	
	public PlayField getMenuPlayfield(){
		return menuPlayfield;
	}
	
	public MouseControl getMouseControl() {
		return mouseControl;
	}
	public KeyboardControl getKeyboardControl() {
		return keyboardControl;
	}

}
