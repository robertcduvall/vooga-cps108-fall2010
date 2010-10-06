package vooga.games.towerdefense;

import java.util.*;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
	FileWriter fstream;
    BufferedWriter out;
    // Code only used for level creation
   /* long myTime;
    long myStartTime;
    int myX;
    int myY;*/
	
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
            e.printStackTrace();
        }
    }
	
	public void update(){
		int key = myGame.bsInput.getMousePressed();
		for (int i = 0; i < players.size(); i++)
        {
             try {
            	 if(key==1){
            		 PlayerCursor p = (PlayerCursor) players.get(i);
            		 p.buildTower(myGame.bsInput.getMouseX(), myGame.bsInput.getMouseY());
            	 }
				moveToCursor(players.get(i));
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}
	
	private void moveToCursor(PlayerSprite player) throws IOException{
		player.forceX(myGame.bsInput.getMouseX());
        player.forceY(myGame.bsInput.getMouseY());
	}
	
	
}
