package vooga.engine.player.control;

import java.lang.reflect.Method;
import java.util.*;

import com.golden.gamedev.Game;

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
public class Control{
    protected List<GameEntitySprite> entities;
    protected Class<?>[] paramTypes;
    protected Game myGame;
	protected Map<Integer, Method> methodMap;
	protected Map<Integer, Object[]> paramMap;
	protected int key;
    
    /**
     * Default Control Constructor
     */
    public Control() {
    	initializeMappings();
        entities = new ArrayList<GameEntitySprite>();
        key = 0;
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
    public Control(GameEntitySprite initialPlayer, Game game) {
        this(game);
        entities.add(initialPlayer);
    }

    /**
     * Constructor that can add multiple players initially
     * 
     * @param players Initial players to use this scheme
     * @param game The game which this Control object is a part of
     */
    public Control(ArrayList<GameEntitySprite> players, Game game) {
        this(game);
        this.entities = players;
    }

    /**
     * Sets the parameter types that need to be used for the next method
     * 
     * @param parameterTypes The types of parameters that need to be used. 
     * Need to be implemented in the form setParams(new Class[]{Class cls})
     * 
     * eg. player.setParams(new Class[]{int.class, double.class})
     */
    public void setParams(Class<?>... parameterTypes) {
        paramTypes = parameterTypes;
    }

    /**
     * Invoke methods here. Call method each time through game loop
     */
    public void update() {
    	if (methodMap.containsKey(key))
        {
            try{
                for (int i = 0; i < entities.size(); i++)
                {
                     Method perform = methodMap.get(key);
                     Object[] paramVals = paramMap.get(key);
                     perform.invoke(entities.get(i), paramVals);
                }
            }
            catch (Throwable e){
                System.err.println(e);
            }
        }
    }

	/**
     * Create keyset to map input to method. Can be overwritten to create new control scheme  
     * 
     * @param listen Use an Integer version of what to listen to (eg. Java.awt.event.KeyEvent constants
     * for "KEYBOARD" or Java.awt.event.MouseEvent constants for "MOUSE")
     * 
     * @param method Name of method to map to (do not include brackets)
     * 
     * @param classname Name of class that wants to use this (eg.
     * "Player" or "Game") NOTE: You must put the class's fully qualified name (including its package)
     * For example: if a class is called Test and is in the package cps108.games.example then the
     * String here must be "cps108.games.example.Test"
     * 
     * @param paramVals Value of the parameters that the method has NOTE: If you want to use this
     * parameter with something other than 'null', you must use setParams first.
     */
    public void addInput(int listen, String method, String classname, Object... paramVals) {
    	try {
            Class<?> myClass = Class.forName(classname);
            Method perform = myClass.getMethod(method, paramTypes);
            methodMap.put(listen, perform);
            paramMap.put(listen, paramVals);
            paramTypes = null;
        } catch (Throwable e) {
            System.err.println(e);
        }
    }


	public void initializeMappings() {
		methodMap = new HashMap<Integer, Method>();
		paramMap = new HashMap<Integer, Object[]>();
	}
	
	public void changeKey(int previousKey, int newKey){
		if (methodMap.containsKey(previousKey))
		{
			Method method = methodMap.get(previousKey);
			methodMap.put(newKey, method);
			methodMap.remove(previousKey);
		}
		else {
			System.out.println("This key didn't exist in the map yet");
		}
	}
}
