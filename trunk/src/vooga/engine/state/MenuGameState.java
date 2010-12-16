package vooga.engine.state;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import org.w3c.dom.Document;

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
	private BufferedImage buttonImage;
	private BufferedImage backgroundImage;
	private double imagePlacementBuffer = 5.0;
	//private File defaultButtonFile = new File("src/vooga/engine/state/resources/images/defaultMenuButton.png");
	//private File defaultBackgroundFile = new File("src/vooga/engine/state/resources/images/defaultMenuBackground.png");
	private ResourceBundle resources;


	/**
	 * Creates a new instance of MenuGameState
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
	
	


	private void initResources(String path) {
		resources = ResourceBundle.getBundle(path);
	}




	private void setDefaultImages() {
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
	 * Initializes MenuGameState
	 */
	
	public void initialize() {
		
	}

	public void makeButton(	String label, 
							GameState gamestate) {
		makeButton(	label, 
					gamestate, 
					imagePlacementBuffer,
					(double)(menuPlayfield.getGroup(buttonGroup).getSize() * buttonHeight)+imagePlacementBuffer); //TODO FIX BAD CODE --DEVON
	}
	
	public void makeButton(	String label, 
							GameState gamestate, 
							String method) {
		makeButton(	label, 
					gamestate,
					method,
					imagePlacementBuffer,
					(double)(menuPlayfield.getGroup(buttonGroup).getSize() * buttonHeight)+imagePlacementBuffer); //TODO FIX BAD CODE --DEVON
	}

	
	public void makeButton(	String label, 
							GameState gamestate, 
							double x, 
							double y) {
		
		makeButton(label, gamestate, switchStateMethod, x, y);
	}
	
	public void makeButton(	String label, 
							GameState gamestate, 
							String gameMethod, 
							double x, 
							double y) {
		
		makeButton(	label, 
					gamestate,
					gameMethod,
					imagePlacementBuffer,
					(double)(menuPlayfield.getGroup(buttonGroup).getSize() * buttonHeight)+imagePlacementBuffer,
					buttonImage); 
	}
	
	public void makeButton(	String label, 
							GameState gamestate, 
							String method,
							double x,
							double y,
							BufferedImage img) {
		addButton(new MenuButton(buttonImage, label, x, y, method, this, gamestate));
	}
	
	
	public void makeButton(	String label, 
							Class<?> cls, 
							String method,
							double x,
							double y,
							BufferedImage img) {
		
	}

	public void addButton(Button button) {
		menuPlayfield.getGroup(buttonGroup).add(button);
		addPlayField(menuPlayfield);

	}
	
	public void addButtons(Iterable<Button> buttons) {
		for (Button button : buttons) {
			addButton(button);
		}
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
	
	public void setButtonImage (BufferedImage img) {
		buttonImage = img;
	}
	
	public void setBackgroundImage (BufferedImage img) {
		backgroundImage = img;
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
