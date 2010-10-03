package vooga.engine.player.action;

import java.util.*;
import com.golden.gamedev.Game;
import vooga.engine.player.control.*;

/**
 * Control class which can be used to create control schemes. Can also be extended to create alternate schemes (for AI, control pads, etc)
 * 
 * Control takes one or more Players and registers actions that those Players will perform if an event occurs from some predetermined input device.
 * If the programmer is just trying to use the keyboard and mouse listening capabilties, they can do:
 * 
 * 	Control shipControl = new Control(this); //this is inside of a Player class.
 * 	shipControl.setParams(new Class[]{int.class}); //Tells Control to expect a single int parameter.
 * 	shipControl.addInput("KEYBOARD", "left", "rotateLeft", "Ship", 10); //Registers the left key to trigger rotateLeft with a parameter of 10.
 *  shipControl.act(); //Within Player's act method.  Tells Control to check the registered events and see if any have occurred.
 * 
 * @author Choi, Cue, Hawthorne
 * @version 1.0
 */
public class Control implements Controller{
    protected List<PlayerSprite> players;
    protected Class[] paramTypes;
    protected Game myGame;
    
    /**
     * Default Control Constructor
     */
    public Control() {
        players = new ArrayList<PlayerSprite>();
    }
    
    /**
     * Control Constructor with only a Game declared
     * 
     * @param game The game which this Control object is a part of
     */
    public Control(Game game) {
        this();
        myGame = game;
    }

    /**
     * Constructor which can add an intial player to the scheme
     * 
     * @param initialPlayer First player to add to use this control scheme
     * @param game The game which this Control object is a part of
     */
    public Control(PlayerSprite initialPlayer, Game game) {
        this(game);
        players.add(initialPlayer);
    }

    /**
     * Constructor that can add multiple players initially
     * 
     * @param players Initial players to use this scheme
     * @param game The game which this Control object is a part of
     */
    public Control(ArrayList<PlayerSprite> players, Game game) {
        this(game);
        this.players = players;
    }

    /**
     * Sets the parameter types that need to be used for the next method
     * 
     * @param Class<?> parameterTypes The types of parameters that need to be used. Need to be implemented in the form setParams(new Class[]{Class cls})
     */
    public void setParams(Class<?>... parameterTypes) {
        paramTypes = parameterTypes;
    }

    // Calls this each time through run loop.
    public void update() {
    	
    }
    
    /**
     * Listen for, and perform, actions that have been mapped to the mouse
     */
    public void mouseAct(){
        
    }
    
    public void addInput(String listen, String method, String classname, Object... paramVals) {
    //should be overridden in subclasses
    }

	
	public void initializeMappings() {
		// TODO Auto-generated method stub
		
	}
}
