package vooga.engine.state;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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

	private String switchStateMethod = "switchToState";
	private int buttonHeight = 54; //TODO DO NOT HARDCORE --Devon
	private int buttonWidth = 216;
	private String buttonGroup = "buttonGroup";
	private PlayField menuPlayfield = new PlayField();
	private SpriteGroup buttons = new SpriteGroup(buttonGroup);
	private SpriteGroup backgroundGroup = new SpriteGroup("backgroundGroup");
	private MouseControl mouseControl;
	private KeyboardControl keyboardControl;
	private Game thisGame = Resources.getGame();
	private BufferedImage DEFAULT_BUTTON_IMAGE;
	private BufferedImage DEFAULT_BACKGROUND_IMAGE;
	private BufferedImage buttonImage;
	private BufferedImage backgroundImage;
	private double imagePlacementBuffer = 5.0;
	private File defaultButtonFile = new File("src/vooga/engine/state/resources/images/defaultMenuButton.png");
	private File backgroundImageFile = new File("src/vooga/engine/state/resources/images/defaultMenuBackground.png");



	/**
	 * Creates a new instance of MenuGameState
	 */
	public MenuGameState(){
		super();
		setDefaultButtons();
		
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
	
	


	private void setDefaultButtons() {
		try{
			DEFAULT_BUTTON_IMAGE     = 	ImageUtil.resize(	ImageIO.read(defaultButtonFile),
															buttonWidth,
															buttonHeight);
			DEFAULT_BACKGROUND_IMAGE = 	ImageUtil.resize(	ImageIO.read(backgroundImageFile), 
															thisGame.getWidth(), 
															thisGame.getHeight());
		}
		catch(Exception e) {
			System.out.println("MenuGameState.java: "+e.getMessage());	
		}	
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
	
	public void initialize() {
		
	}

	/**
	 * Adds a specified Button to the MenuGameState
	 * @param button
	 */
	public void addButton(Button button) {
		menuPlayfield.getGroup(buttonGroup).add(button);
		addPlayField(menuPlayfield);

	}
	
	/*
	public void makeButton(String label) {
		addButton(new MenuButton(DEFAULT_BUTTON_IMAGE, label, this));
	}
	

	
	public void makeButton(String label, GameState gamestate) {
		addButton(new MenuButton(DEFAULT_BUTTON_IMAGE, label, gamestate, this));
	}*/
	
	public void makeButton(String label, GameState gamestate, double x, double y) {
		makeButton(label, gamestate, switchStateMethod, x, y);
	}
	
	public void makeButton(String label, GameState gamestate, String gameMethod, double x, double y) {
		addButton(new MenuButton(DEFAULT_BUTTON_IMAGE, label, gamestate, x, y, gameMethod,  this));
	}
	
	
	public void makeNextButton(String label, GameState gamestate) {
		makeButton(	label, 
					gamestate, 
					imagePlacementBuffer,
					(double)(menuPlayfield.getGroup(buttonGroup).getSize() * buttonHeight)+imagePlacementBuffer); //TODO FIX BAD CODE --DEVON
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
	
	public void setButtonWidth(int w) {
		buttonWidth = w;
		buttonImage = ImageUtil.resize(	buttonImage,
										w,
										buttonHeight);
	}
	public void setButtonHeight(int h) {
		buttonImage = ImageUtil.resize(	buttonImage,
										buttonWidth,
										h);
	}
	public void setButtonSize(int w, int h) {
		buttonImage = ImageUtil.resize(	buttonImage,
										w,
										h);
	}

}
