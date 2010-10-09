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
 * Controller designed for assisting in tracing paths for making routes 
 * in new levels. It is a modification of the example MouseControl class.
 * Not to be used in actual gameplay.
  * @author Derek Zhou, Daniel Koverman, Justin Goldsmith
 */

public class PathTracer extends Control implements Controller{
        private Map<Integer, Method> mouseMethodMap;
        private Map<Integer, Object[]> mouseParamMap;
        private Object[] parametervalues;
        FileWriter fstream;
        BufferedWriter out;
    // Code only used for level creation
    long myTime;
    long myStartTime;
    int myX;
    int myY;
       
        public PathTracer(PlayerSprite initialPlayer, Game game){
                super(initialPlayer, game);
                initializeMappings();
                 // Code only used for level creation
               /* File file = new File("src/vooga/games/towerdefense/resources/levels/hard.txt");
                try {
                        fstream = new FileWriter(file);
                } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                out = new BufferedWriter(fstream);
                myStartTime = System.currentTimeMillis();*/
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
             try {
                                moveToCursor(players.get(i));
                        } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        }
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
        // Code only used for level creation
       myTime = System.currentTimeMillis() - myStartTime;
       
       
        }
       
        private void moveToCursor(PlayerSprite player) throws IOException{
                player.forceX(myGame.bsInput.getMouseX());
        player.forceY(myGame.bsInput.getMouseY());
       
        // Code only used for level creation
        myTime = 0;
       if(myTime < 45000){
                if(myGame.bsInput.isMouseDown(MouseEvent.BUTTON1)){
                                int x = myGame.bsInput.getMouseX();
                                int y = myGame.bsInput.getMouseY();
                                //if((x != myX) && (y != myY)){
                                        myX = x;
                                        myY = y;
                                       // out.write(myX+ " ");
                                      //  out.write(myY+ " ");
                                        System.out.println(myX + " : " + myY);
                               // }
                        }
        }else{
                System.out.println("stop");
                        out.close();
        }
        
        }
       
       
}


