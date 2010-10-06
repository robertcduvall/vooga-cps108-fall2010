package vooga.engine.player.control;

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

public class MouseControl extends Control implements Controller{
	private Map<Integer, Method> mouseMethodMap;
	private Map<Integer, Object[]> mouseParamMap;
	private Object[] parametervalues;
	
	public MouseControl(){
		super();
		initializeMappings();
	}
	
	public MouseControl(Game game){
		super(game);
		initializeMappings();
	}
	
	public MouseControl(PlayerSprite initialPlayer, Game game){
		super(initialPlayer, game);
		initializeMappings();
	}
	
	public MouseControl(ArrayList<PlayerSprite> initialPlayers, Game game){
		super(initialPlayers, game);
		initializeMappings();
	}
	
	public void initializeMappings(){
		mouseMethodMap = new HashMap<Integer, Method>();
		mouseParamMap = new HashMap<Integer, Object[]>();
	}
	
	/**
     * Create keyset to map input to method. Can be overwritten to create new control scheme  
     * 
     * @param listen Use the Java.awt.event.MouseEvent constant values to determine which button to listen for.
     * Need to import java.awt.event.MouseEvent
     * 
     * @param method Name of method to map to (do not include brackets)
     * 
     * @param classname Name of class that wants to use this (eg.
     * "Player" or "Game") NOTE: You must put the class's fully qualified name (including its package)
     * For example: if a class is called Test and is in the package cps108.games.example then the
     * String here must be "cps108.games.example.Test"
     * 
     * @param paramVals Value of the parameters that the method has
     */
	public void addInput(int listen, String method,
            String classname, Object... paramVals) {
        try{
    		Class myClass = Class.forName(classname);
            Method perform = myClass.getMethod(method,paramTypes);
        	int key = listen;
            mouseMethodMap.put(key, perform);
            mouseParamMap.put(key, paramVals);
            paramTypes = null;
        } catch (Throwable e) {
            System.err.println(e);
        }
    }
	
	public void update(){
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
}
