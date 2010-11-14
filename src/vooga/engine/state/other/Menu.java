/**
 * @author: Yijia Mu
 * @date: 10-17-10
 * @description: The menu class is used to display the images associated with any game menu 
 * and to respond to mouse and keyboard inputs from user. It is divided into three core 
 * functionalities: handling images for the menu, getting inputs from the user, and interacting
 * with other menus. 
 * 
 * For handling images, the menu class can load images in three ways: GTGE sprite, bufferedImage,
 * relative URL. GTGE sprite comes in forms of simple sprite and animated sprite. For dealing with
 * animatedsprite, one can pass in either the animated sprite to the designated method or one can
 * pass in the associated array of bufferedImages relating that animated sprite. (The relative URL
 * is not provided for animated sprite because it is inconvenient to pass in a text file or 
 * several paramenters of relative URL's.) Similarly, one can do the same with simple sprites. In
 * the menu class, there are two type of sprites: static and dynamic sprites. Static sprites do not  
 * move once they are updated and are set with initial positions. Dynamic sprites do move and are 
 * set with initial positions and velocity. Basically, all of the sprites are stored inside 
 * menuDisplay, which is a SpriteGroup. The render method simply calls on menuDisplay.render(g) and
 * the sprites update are kept track of inside the update method, which calls on menuDisplay.update().
 * 
 * For extracting inputs from the user, the menu class provides overridable methods, such as 
 * actOnMouseClick() and actOnKeyPress(int key). Additionally, if one seeks to create a more
 * complex menu system with specific effects, one can check out the MouseMenu and the keyMenu interfaces.
 * The MouseMenu, for example, provides special effects methods such as actOnMouseHover(Sprite). The
 * MOUSE_EVENT is an integer representation of the mouse event to mimic the keyEvent in the java
 * keyEvent class. Keeping a static copy of the MOUSE_EVENT allows the user to map the MOUSE_EVENT to
 * another menu, which the user can switch to as he or she desires. The actOnMouseClick() and 
 * actOnKeyPress(int key) are checked in the update() method. The tagKeyToMenu() and the tagMouseToMenu()
 * allows the user to associate a particular keyboard or mouse event with a particular menu. For example,
 * the user might want to associate the spacebar keyevent with an another menu he wishes to switch to. 
 * 
 * For interacting with other menus objects, the method switchToMenu(String name) allows the user to 
 * switch to a different menu. The user can use this method with actOnMouseClick() or actOnKeyPress(), 
 * etc. The setActive(boolean) method allows the user to set the current menu to be active or inactive.
 * This method is especially crucial if the user is switching beween menus. Initially, menuDisplay is 
 * set to false and is not displayed until activated. 
 * 
 * The constructors in the Menu class have three parameters. The first takes in the current Game
 * to which the menu belongs to. The second takes in the current game and the spriteGroup associated 
 * with the menu. The third takes in the current Game and any data structure part java's Collection 
 * library.
 */

package vooga.engine.state.other;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import vooga.engine.state.GameState;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.sun.org.apache.bcel.internal.classfile.Method;


public class Menu extends GameState{

	//The Mouse Event is java keyEvent Equivalent for the mouse. Making this variable allows us to put MOUSE_EVENT in keyToMenu
	private final static int MOUSE_EVENT = 1000;
	
	private Game currentGame;
	private SpriteGroup menuDisplay;
	private Map<Integer, Menu> keyToMenu;
	private Map<Integer, Method> keyToMethod;

	public Menu(Game game)
	{
		currentGame = game;
		keyToMenu = new TreeMap<Integer, Menu>();
		keyToMethod = new TreeMap<Integer, Method>();
		
		//Initially, the menu is not active, until called 
		setActive(false);
	}

	public Menu(Game game, SpriteGroup displayedgroup)
	{
		this(game);
		menuDisplay = displayedgroup;
	}
	
	/**************************************************************************
	 * METHODS FOR MOUSECLICK AND KEYPRESS EVENT, CHANGING MENU, SETTING CURRENT MENU ACTIVE 
	 **************************************************************************/
	/**
	 * This method specifies which action to take upon mouse click.	
	 */
	public void actOnMouseClicked()
	{
		
	}
	
	/**
	 * This method specifies which action to take upon a particular key press. 
	 * @param key
	 */
	public void actOnKeyPressed(int key)
	{
		
	}
	
	/**
	 * This method associates a specific key to another menu. Doing so allows the user 
	 * to switch from menu to menu with key press.
	 * @param key (Java key events are integers)
	 * @param menu (Another menu the user wants the key to associate with)
	 */
	public void tagKeyToMenu(int key, Menu menu)
	{
		keyToMenu.put(key, menu);
	}
	
	/**
	 * This method allows the user to associate the mouse click to a particular menu. 
	 * Doing so allows the user to switch from menu to menu with the mouse click.
	 * @param menu (Another menu the user wnats the mouse click to associate with)
	 */
	public void tagMouseToMenu(Menu menu)
	{
		keyToMenu.put(MOUSE_EVENT, menu);	
	}
	
	/**
	 * This method allows the user to set another menu to be active. The design decision
	 * here is that the user is only allowed to have one Menu active at one time. Thus,
	 * switching to a different menu necessarily sets the current menu to be inactive.
	 * @param menu 
	 */
	public void switchToMenu(Menu menu)
	{
		setActive(false);
		menu.setActive(true);
	}
	
	/**
	 * This method sets the the current menu to be active or inactive. Initially,
	 * inside the constructor, any menu is set to inactive. 
	 * @param state
	 */
	public void setActive(boolean state)
	{
		menuDisplay.setActive(state);
	}
	
	/**
	 * This method returns the current game that the menu belows to
	 */
	public Game getCurrentGame()
	{
		return currentGame;
	}
	
	
	
	
	/****************************************************************************
	 * METHODS FOR UPDATING, RENDERING, AND LISTENING
	 ****************************************************************************/
	/**
	 * This method updates the dynamic sprites and respond to user mouse and keyboard
	 * inputs.
	 */
	public void update(long elapsedTime)
	{
		menuDisplay.update(elapsedTime);
		listenforKeyPresses();
		listenforMouseClick();
	}

	/**
	 * This method checks whether a particular key is pressed for all the keys the user
	 * associated with the keytoMenu map. If so, this method calls on actOnKeyPressed(key).
	 */
	private void listenforKeyPresses() 
	{
		for(Integer key: keyToMenu.keySet())
		{
			if(currentGame.keyPressed(key))
				actOnKeyPressed(key);
		}
	}
	
	/**
	 * This method checks whether the mouse is clicked. If so, this method calls on 
	 * actOnMouseClicked().
	 */
	private void listenforMouseClick()
	{
		if(currentGame.click())
			actOnMouseClicked();
	}
	
	/**
	 * This method displays the menuDisplay.
	 * @param g
	 */
	@Override
	public void render(Graphics2D g)
	{
		menuDisplay.render(g);
	}
	
	



}
