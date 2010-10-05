package vooga.engine.player.control;

import java.lang.reflect.*;
import java.util.*;
import java.awt.event.KeyEvent;
import com.golden.gamedev.Game;
import com.golden.gamedev.engine.BaseInput;
import vooga.engine.player.control.*;

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
	
	public KeyboardControl(PlayerSprite player, Game game){
		super(player, game);
		initializeMappings();
	}
	
	public KeyboardControl(ArrayList<PlayerSprite> players, Game game){
		super(players, game);
		initializeMappings();
	}
	
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
     * "Player" or "Game")
     * 
     * @param paramVals Value of the parameters that the method has
     */
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
    
	public void update(){
		int key = myGame.bsInput.getKeyPressed();
        if (key==(myGame.bsInput.NO_KEY)) {
            for (int possibleKey : keyMethodMap.keySet()) {
                if (myGame.bsInput.isKeyDown(possibleKey)) {
                    key = possibleKey;
                }
            }
            if (key==(myGame.bsInput.NO_KEY))
                key = myGame.bsInput.NO_KEY;
        }
        if (keyMethodMap.containsKey(key)) {
            try {
                for (int i = 0; i < players.size(); i++) {
                    Method perform = keyMethodMap.get(key);
                    Object[] paramVals = keyParamMap.get(key);
                    perform.invoke(players.get(i), paramVals);
                }
            } catch (Throwable e) {
                System.err.println(e);
            }
        }
	}
}
