package vooga.engine.player.control;

import java.lang.reflect.*;
import java.util.*;
import com.golden.gamedev.Game;
import com.golden.gamedev.engine.BaseInput;

/**
 * Built-in example of how to extend Control class properly. Also a usable keyboard
 * Controller creator.
 * 
 * @author Choi, Cue, Hawthorne
 * @version 1.0
 */

public class KeyboardControl extends Control implements Controller{
	private Map<Integer, Method> keyMethodMap;
	private Map<Integer, Object[]> keyParamMap;
	
	public KeyboardControl(){
		super();
		initializeMappings();
	}
	
	public KeyboardControl(Game game){
		super(game);
		initializeMappings();
	}
	
	public KeyboardControl(GameEntitySprite entity, Game game){
		super(entity, game);
		initializeMappings();
	}
	
	public KeyboardControl(ArrayList<GameEntitySprite> entities, Game game){
		super(entities, game);
		initializeMappings();
	}
	
	@Override
	public void initializeMappings(){
		keyMethodMap = new HashMap<Integer, Method>();
		keyParamMap = new HashMap<Integer, Object[]>();
	}
	
	/**
     * Create keyset to map input to method. Can be overwritten to create new control scheme  
     * 
     * @param listen Use the java.awt.event.KeyEvent constants to determine what to listen for. Need to
     * import java.awt.event.KeyEvent
     * 
     * @param method Name of method to map to (do not include brackets)
     * 
     * @param classname Name of class that wants to use this (eg.
     * "Player" or "Game"). NOTE: You must put the class's fully qualified name (including its package)
     * For example: if a class is called Test and is in the package cps108.games.example then the
     * String here must be "cps108.games.example.Test"
     * 
     * @param paramVals Value of the parameters that the method has
     */
    @Override
	public void addInput(int listen, String method, String classname, Object... paramVals) {

    	try {
            Class myClass = Class.forName(classname);
            Method perform = myClass.getMethod(method, paramTypes);
            keyMethodMap.put(listen, perform);
            keyParamMap.put(listen, paramVals);
            paramTypes = null;
        } catch (Throwable e) {
            System.err.println(e);
        }
    }
    
	@Override
	public void update(){
		int key = myGame.bsInput.getKeyPressed();
        if (key==(BaseInput.NO_KEY)) {
            for (int possibleKey : keyMethodMap.keySet()) {
                if (myGame.bsInput.isKeyDown(possibleKey)) {
                    key = possibleKey;
                }
            }
            if (key==(BaseInput.NO_KEY))
                key = BaseInput.NO_KEY;
        }
        if (keyMethodMap.containsKey(key)) {
            try {
                for (int i = 0; i < entities.size(); i++) {
                    Method perform = keyMethodMap.get(key);
                    Object[] paramVals = keyParamMap.get(key);
                    perform.invoke(entities.get(i), paramVals);
                }
            } catch (Throwable e) {
                System.err.println(e);
            }
        }
	}
}
