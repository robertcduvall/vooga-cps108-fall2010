package vooga.engine.state;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ResourceBundle;

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
	private int buttonHeight; 
	private int buttonWidth;
	private String buttonGroup = "buttonGroup";
	private PlayField menuPlayfield = new PlayField();
	private SpriteGroup buttons = new SpriteGroup(buttonGroup);
	private SpriteGroup backgroundGroup = new SpriteGroup("backgroundGroup");
	private MouseControl mouseControl;
	private KeyboardControl keyboardControl;
	private Game thisGame = Resources.getGame();
	private BufferedImage buttonImage;
	private BufferedImage backgroundImage;
	private double imagePlacementBuffer = 5.0;
	private ResourceBundle resources;


	/**
	 * Creates a new instance of MenuGameState.  This state runs at game initialization and
	 * easily allows the user to switch to a given GameState or call other method.
	 */
	public MenuGameState(){
		super();
		initResources("vooga/engine/state/resources/resource");
		setDefaultImages();
		Sprite menuBackGroundSprite = new Sprite(backgroundImage);
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
	 * Creates a ResourceBundle from the resource file.
	 * @param path
	 */
	private void initResources(String path) {
		resources = ResourceBundle.getBundle(path);
	}




	/**
	 * Uses resources to set the default images and sizes for the button and
	 * menu background.
	 */
	private void setDefaultImages() {
		
		buttonWidth = Integer.parseInt(resources.getString("buttonWidth"));
		buttonHeight = Integer.parseInt(resources.getString("buttonHeight"));
		try{
			setButtonImage(		ImageUtil.resize(	ImageIO.read(new File(resources.getString("buttonPath"))),
													buttonWidth,
													buttonHeight));
			setBackgroundImage( ImageUtil.resize(	ImageIO.read(new File(resources.getString("backgroundPath"))), 
													thisGame.getWidth(), 
													thisGame.getHeight()));
		}
		catch(Exception e) {
			System.out.println("MenuGameState.java Error: "+e.getMessage());	
		}	
	}

	/**
	 * Creates a new instance of MenuGameState with a specified collection of buttons
	 * @param buttons iterable collection of buttons
	 */
	public MenuGameState(Iterable<Button> buttons) {
		this();
		addButtons(buttons);
	}

	/**
	 * Adds a new button to the menu.
	 * @param label
	 * @param gamestate
	 */
	public void makeButton(	String label, 
							GameState gamestate) {
		makeButton(	label, 
					gamestate, 
					imagePlacementBuffer,
					(double)(menuPlayfield.getGroup(buttonGroup).getSize() * buttonHeight)+imagePlacementBuffer); //TODO FIX BAD CODE --DEVON
	}
	
	/**
	 * Adds a new button to the menu.
	 * @param label
	 * @param gamestate
	 * @param method
	 */
	public void makeButton(	String label, 
							GameState gamestate, 
							String method) {
		makeButton(	label, 
					gamestate,
					method,
					imagePlacementBuffer,
					(double)(menuPlayfield.getGroup(buttonGroup).getSize() * buttonHeight)+imagePlacementBuffer); //TODO FIX BAD CODE --DEVON
	}

	/**
	 * Adds a new button to the menu.
	 * @param label
	 * @param gamestate
	 * @param x
	 * @param y
	 */
	public void makeButton(	String label, 
							GameState gamestate, 
							double x, 
							double y) {
		
		makeButton(label, gamestate, switchStateMethod, x, y);
	}
	
	/**
	 * Adds a new button to the menu.
	 * @param label
	 * @param gamestate
	 * @param gameMethod
	 * @param x
	 * @param y
	 */
	public void makeButton(	String label, 
							GameState gamestate, 
							String gameMethod, 
							double x, 
							double y) {
		
		makeButton(	label, 
					gamestate,
					gameMethod,
					x,
					y,
					buttonImage); 
	}
	
	/**
	 * Adds a new button to the menu.
	 * @param label
	 * @param gamestate
	 * @param method
	 * @param x
	 * @param y
	 * @param img
	 */
	public void makeButton(	String label, 
							GameState gamestate, 
							String method,
							double x,
							double y,
							BufferedImage img) {
		addButton(new MenuButton(buttonImage, label, x, y, method, this, gamestate));
	}
	
	


	/**
	 * Appends button to menu.
	 * @param button
	 */
	public void addButton(Button button) {
		menuPlayfield.getGroup(buttonGroup).add(button);
		addPlayField(menuPlayfield);

	}
	
	/**
	 * Appends buttons to menu.
	 * @param buttons
	 */
	public void addButtons(Iterable<Button> buttons) {
		for (Button button : buttons) {
			addButton(button);
		}
	}

	
	/**
	 * 
	 * @return the Playfield that holds all the menu sprites.
	 */
	public PlayField getMenuPlayfield(){
		return menuPlayfield;
	}
	
	/**
	 * 
	 * @return the MouseControl for the Menu.
	 */
	public MouseControl getMouseControl() {
		return mouseControl;
	}
	
	/**
	 * 
	 * @return the KeyboardControl for the Menu.
	 */
	public KeyboardControl getKeyboardControl() {
		return keyboardControl;
	}
	

	/**
	 * 
	 * @param Sets the button image used when making new buttons.
	 */
	public void setButtonImage (BufferedImage img) {
		buttonImage = img;
	}
	
	/**
	 * 
	 * @param Sets the background image used when making new buttons.
	 */
	public void setBackgroundImage (BufferedImage img) {
		backgroundImage = img;
	}

	
	/**
	 * Sets the width of buttons.  Only affects buttons 
	 * created AFTER this is set.
	 * @param new button width
	 */
	public void setButtonWidth(int w) {
		buttonWidth = w;
	}
	
	/**
	 * Sets the height of buttons.  Only affects buttons 
	 * created AFTER this is set.
	 * @param button height
	 */
	public void setButtonHeight(int h) {
		buttonHeight = h;
	}
}
