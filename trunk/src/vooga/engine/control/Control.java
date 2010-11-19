package vooga.engine.control;

import java.lang.reflect.Method;
import java.util.*;

import vooga.engine.core.BetterSprite;

import vooga.engine.core.Game;

/**
 * Control class which can be extended to create control schemes.  Keyboard and mouse control are already packaged subclasses, but this class can also be
 * extended to control GameEntitySprites with an AI, joystick, network, etc.
 * <br /><br />
 * Control takes one or more GameEntitySprites and registers actions that those sprites will perform if an event occurs from some predetermined input device.
 * If the programmer is just trying to use the built in keyboard or mouse listening capabilties, they can do the following:

 * <xmp>
 * Control shipControl = new Control(this); //this is inside of a GameEntitySprite subclass.
 * shipControl.addInput(KeyEvent.VK_LEFT, "left", "rotateLeft", "Ship", new Class[]{int.class}); //Registers the left key to trigger rotateLeft with an int parameter.
 * shipControl.setParams(KeyEvent.VK_LEFT, 10); //Sets parameter of rotateLeft to 10.
 * shipControl.act(); //Within Player's act method.  Tells Control to check the registered events and see if any have occurred.
 * </xmp>
 * 
 * @author Choi, Cue, Hawthorne
 * @version 1.0
 */
public class Control{
	protected List<BetterSprite> entities;
	protected Class<?>[] paramTypes;
	protected Game myGame;
	protected Map<Integer, ArrayList<Method>> methodMap;
	protected Map<Integer, ArrayList<Object[]>> paramMap;
	protected Map<Integer, ArrayList<Method[]>> methodParamMap;
	protected ArrayList<Integer> key;

	/**
	 * Default Control Constructor
	 */
	public Control() {
		initializeMappings();
		entities = new ArrayList<BetterSprite>();
		key = new ArrayList<Integer>();
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
	 * Constructor which can add an initial player to the scheme
	 * 
	 * @param initialPlayer First player to add to use this control scheme
	 * @param game The game which this Control object is a part of
	 */
	public Control(BetterSprite initialPlayer, Game game) {
		this(game);
		entities.add(initialPlayer);
	}

	/**
	 * Constructor that can add multiple players initially
	 * 
	 * @param players Initial players to use this scheme
	 * @param game The game which this Control object is a part of
	 */
	public Control(List<BetterSprite> players, Game game) {
		this(game);
		this.entities = players;
	}

	/**
	 * Sets the parameter values that need to be used for a method
	 * 
	 * @param listen The key which the method listens for
	 * 
	 * @param paramValues The value of the parameters (eg. "Shoot", 10, etc.)
	 * 
	 */
	public void setParams(int listen, Object... paramValues) {
		ArrayList<Object[]> prevParams = paramMap.get(listen) == null ? new ArrayList<Object[]>() : paramMap.get(listen);
		prevParams.add(paramValues);
		paramMap.put(listen, prevParams);
	}

	/**
	 * Sets the parameter values that need to be used for a method
	 * 
	 * @param listen The key which the method listens for
	 * 
	 * @param paramClass The class which the method belongs to
	 * 
	 * @param paramValues The method which can be used as the value of the parameter. The method MUST
	 * return an object needed for the method's parameters. (eg. getMouseX(), getCurrentLevel(), etc.)
	 */
	
	public void setParams(int listen, Class<?> paramClass, Method... paramValues) {
		ArrayList<Method[]> prevParams = methodParamMap.get(listen) == null ? new ArrayList<Method[]>() : methodParamMap.get(listen);
		prevParams.add(paramValues);
		methodParamMap.put(listen, prevParams);
	}
	
	/**
	 * Invoke methods here. Call method each time through game loop
	 */
	public void update() {
		while (key.size() > 0)
		{
			int thisKey = key.remove(0);
			if (methodMap.containsKey(thisKey))
			{
				try{
					for (int i = 0; i < entities.size(); i++)
					{
						for(int e = 0; e < methodMap.get(thisKey).size(); e++){
							Method perform = methodMap.get(thisKey).get(e);
							Object[] paramVals = paramMap.containsKey(thisKey) ? paramMap.get(thisKey).get(e) : new Object[0];
							Method[] newParams = methodParamMap.containsKey(thisKey) ? methodParamMap.get(thisKey).get(e) : new Method[0];
							List<Object> objectParameters = new ArrayList<Object>();
							for (Object parameter : paramVals)
							{
								objectParameters.add(parameter);
							}
							for (Method method : newParams)
							{
								objectParameters.add(method.invoke(method.getDeclaringClass()));
							}
							perform.invoke(entities.get(i), objectParameters.toArray());
								
						}
					}
				}
				catch (Throwable e){
					System.err.println(e);
					e.printStackTrace();
				}
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
	 * @param paramTypes Class type that the parameters must be for the method (previously set with setParams() method.
	 * For example: String.class, int.class, etc.
	 */
	public void addInput(int listen, String method, String classname, Class<?>... paramTypes) {
		try {
			Class<?> myClass = Class.forName(classname);
			Method perform = myClass.getMethod(method, paramTypes);
			ArrayList<Method> prevMethods = methodMap.get(listen) == null ? new ArrayList<Method>() : methodMap.get(listen);
			prevMethods.add(perform);
			methodMap.put(listen, prevMethods);
//			ArrayList<Stat<?>[]> prevParams = paramMap.get(listen) == null ? new ArrayList<Stat<?>[]>() : paramMap.get(listen);
//			prevParams.add(paramVals);
//			paramMap.put(listen, prevParams);
		} catch (Throwable e) {
			System.err.println(e);
		}
	}

	/**
	 * Initialize the mappings of inputs to methods and inputs to the methods parameters.
	 */

	public void initializeMappings() {
		methodMap = new HashMap<Integer, ArrayList<Method>>();
		paramMap = new HashMap<Integer, ArrayList<Object[]>>();
		methodParamMap = new HashMap<Integer, ArrayList<Method[]>>();
	}

	/**
	 * Change the input corresponding to a set of actions to a different input.  Use this method to change
	 * moving left from the left arrow key to the 'A' key for instance.
	 * 
	 * @param previousKey the previous input 
	 * @param newKey the new input
	 */
	
	public void changeKey(int previousKey, int newKey){
		if (methodMap.containsKey(previousKey))
		{
			ArrayList<Method> methods = methodMap.get(previousKey);
			methodMap.put(newKey, methods);
			methodMap.remove(previousKey);
			if (paramMap.containsKey(previousKey))
			{
				ArrayList<Object[]> parameters = paramMap.get(previousKey);
				paramMap.put(newKey, parameters);
				paramMap.remove(previousKey);
			}
		}
		else {
			System.out.println("This key didn't exist in the map yet");
		}
	}
}
