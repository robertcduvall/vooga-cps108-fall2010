package engine.player.action;

import java.lang.reflect.*;
import java.util.*;
import java.awt.event.KeyEvent;
import com.golden.gamedev.Game;
import com.golden.gamedev.engine.BaseInput;

public class KeyboardControl extends Control{
	private Map<String, Method> keyMethodMap;
	private Map<String, Object[]> keyParamMap;
	
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
		keyMethodMap = new HashMap<String, Method>();
		keyParamMap = new HashMap<String, Object[]>();
	}
	
	/**
     * Create keyset to map input to method. Can be overwritten to create new control scheme  
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
    public void addInput(String listen, String method, String classname, Object... paramVals) {

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
		String key = String.valueOf(myGame.bsInput.getKeyPressed());
        if (key == game.bsInput.NO_KEY) {
            for (String possibleKey : keymethodMap.keySet()) {
                if (myGame.bsInput.isKeyDown(Integer.parseInt(possibleKey))) {
                    key = possibleKey;
                }
            }
            if (key == game.bsInput.NO_KEY)
                key = game.bsInput.NO_KEY;
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
}
