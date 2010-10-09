package vooga.games.towerdefense;

import java.util.*;
import java.lang.reflect.*;

import vooga.engine.player.control.Control;
import vooga.engine.player.control.Controller;
import vooga.engine.player.control.PlayerSprite;

import com.golden.gamedev.Game;

/**
 * Modification of the example MouseControl class which also
 * moves the controlled PlayerSprite to the location of the 
 * mouse.
 * 
 * @author Daniel Koverman
 */

public class PlayerCursorControl extends Control implements Controller{
	private Map<Integer, Method> mouseMethodMap;
	private Map<Integer, Object[]> mouseParamMap;
	private Object[] parametervalues;
	
	public PlayerCursorControl(){
		super();
		initializeMappings();
	}
	
	public PlayerCursorControl(Game game){
		super(game);
		initializeMappings();
	}
	
	public PlayerCursorControl(PlayerSprite initialPlayer, Game game){
		super(initialPlayer, game);
		initializeMappings();
	}
	
	public PlayerCursorControl(ArrayList<PlayerSprite> initialPlayers, Game game){
		super(initialPlayers, game);
		initializeMappings();
	}
	
	@Override
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
	@Override
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
	
	@Override
	public void update(){
		for (int i = 0; i < players.size(); i++)
        {				
				players.get(i).setLocation(myGame.bsInput.getMouseX(), myGame.bsInput.getMouseY());
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
}
