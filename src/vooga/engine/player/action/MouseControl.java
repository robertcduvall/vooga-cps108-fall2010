package vooga.engine.player.action;

import java.util.*;
import java.lang.reflect.*;

public class MouseControl extends Control{
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
