package vooga.games.towerdefense;

import java.util.*;
import java.lang.reflect.*;
import com.golden.gamedev.Game;
import com.golden.gamedev.engine.BaseInput;
import vooga.engine.player.control.*;

/**
 * Built-in example of how to extend Control class properly. Also a usable mouse
 * Controller creator.
 * @author Choi, Cue, Hawthorne
 * @version 1.0
 */

public class PlayerCursorControl extends Control implements Controller{
	private Map<Integer, Method> mouseMethodMap;
	private Map<Integer, Object[]> mouseParamMap;
	private Object[] parametervalues;
	
	public PlayerCursorControl(PlayerSprite initialPlayer, Game game){
		super(initialPlayer, game);
		initializeMappings();
	}
	
	public void initializeMappings(){
		mouseMethodMap = new HashMap<Integer, Method>();
		mouseParamMap = new HashMap<Integer, Object[]>();
	}
	
	/**
     * Create keyset to map input to method. Can be overwritten to create new control scheme  
     * 
     * @param listen Use a String version of what to listen to (eg. "a"
     * for "KEYBOARD" or "1" for "MOUSE")
     * 
     * @param method Name of method to map to (do not include brackets)
     * 
     * @param classname Name of class that wants to use this (eg.
     * "Player" or "Game")
     * 
     * @param paramVals Value of the parameters that the method has
     */
	public void addInput(String listen, String method,
            String classname, Object... paramVals) {
        try{
    		Class myClass = Class.forName(classname);
            Method perform = myClass.getMethod(method,paramTypes);
        	int key = Integer.parseInt(listen);
            mouseMethodMap.put(key, perform);
            mouseParamMap.put(key, paramVals);
            paramTypes = null;
        } catch (Throwable e) {
            System.err.println(e);
        }
    }
	
	public void update(){
		for (int i = 0; i < players.size(); i++)
        {
             moveToCursor(players.get(i));
        }
		int key = myGame.bsInput.getMousePressed();
        if (mouseMethodMap.containsKey(key))
        {
            try{
                for (int i = 0; i < players.size(); i++)
                {
                     Method perform = mouseMethodMap.get(key);
                     Object[] paramVals = mouseParamMap.get(key);
                     perform.invoke(players.get(i), paramVals);
                }
            }
            catch (Throwable e){
                System.err.println(e);
            }
        }
	}
	
	private void moveToCursor(PlayerSprite player){
		player.forceX(myGame.bsInput.getMouseX());
        player.forceY(myGame.bsInput.getMouseY());
	}
}
