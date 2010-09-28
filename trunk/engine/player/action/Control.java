package engine.player.action;

import java.util.*;
import java.lang.reflect.*;
import greenfoot.*;

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
public class Control {
    protected List<Player> players;
    protected Class[] paramTypes;
    private Map<String, Method> keymethodMap;
    private Map<String, Object[]> keyparamMap;
    private Map<Integer, Method> mousemethodMap;
    private Map<Integer, Object[]> mouseparamMap;

    private Method activate;
    private Object[] parametervalues;
    
    /**
     * Default Control Constructor
     */
    public Control() {
        players = new ArrayList<Player>();
        keymethodMap = new HashMap<String, Method>();
        keyparamMap = new HashMap<String, Object[]>();
        mousemethodMap = new HashMap<Integer, Method>();
        mouseparamMap = new HashMap<Integer, Object[]>();
    }

    /**
     * Constructor which can add an intial player to the scheme
     * 
     * @param Player initialPlayer First player to add to use this control scheme
     */
    public Control(Player initialPlayer) {
        this();
        players.add(initialPlayer);
    }

    /**
     * Constructor that can add multiple players initially
     * 
     * @param ArrayList<Player> players Initial players to use this scheme
     */
    public Control(ArrayList<Player> players) {
        this();
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

    /**
     * Create keyset to map input to method. Can be overwritten to create new control scheme
     * 
     * @param String inputType Which type of input is to be used: "KEYBOARD", "MOUSE", etc.
     * 
     * @param String listen Use a String version of what to listen to (eg. "a"
     * for "KEYBOARD" or "1" for "MOUSE")
     * 
     * @param String method Name of method to map to (do not include brackets)
     * 
     * @param String classname Name of class that wants to use this (eg.
     * "Player" or "Greenfoot")
     * 
     * @param Class<?> paramVals Value of the parameters that the method has
     */
    public void addInput(String inputType, String listen, String method,
            String classname, Object... paramVals) {
        
          try {
            Class myClass = Class.forName(classname);
            Method perform = myClass.getMethod(method, paramTypes);
            if (inputType.equals("KEYBOARD")){
            keymethodMap.put(listen, perform);
            keyparamMap.put(listen, paramVals);
            }
            else if (inputType.equals("MOUSE")){
            parametervalues = paramVals;
            activate = perform;
            }
            paramTypes = null;
        } catch (Throwable e) {
            System.err.println(e);
        }
    

    }

    // Calls this each time through run loop.
    public void act() {
        keyAct();
        mouseAct();
    }
    
    /**
     * Listen for, and perform, actions that have been mapped to keyboard controls
     */
    public void keyAct(){
        String key = Greenfoot.getKey();
        if (key == null) {
            for (String possibleKey : keymethodMap.keySet()) {
                if (Greenfoot.isKeyDown(possibleKey)) {
                    key = possibleKey;
                }
            }
            if (key == null)
                key = "";
        }
        if (keymethodMap.containsKey(key)) {
            try {
                for (int i = 0; i < players.size(); i++) {
                    Method perform = keymethodMap.get(key);
                    Object[] paramVals = keyparamMap.get(key);
                    perform.invoke(players.get(i), paramVals);
                }
            } catch (Throwable e) {
                System.err.println(e);
            }
        }
    }
    
    /**
     * Listen for, and perform, actions that have been mapped to the mouse
     */
    public void mouseAct(){
            try{
                World world = players.get(0).getWorld();
                List list = world.getObjects(null);
                for (int i = 0; i < list.size(); i++){
                    if (Greenfoot.mouseClicked(list.get(i)) || Greenfoot.mouseClicked(world)){
                       for (int j = 0; j < players.size(); j++) {
                             activate.invoke(players.get(j), parametervalues);
                            }
                        }
                }
            }
            catch (Throwable e) {
                System.err.println(e);
            }
        
    }
    
}
