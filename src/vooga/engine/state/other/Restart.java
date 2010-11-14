/**
 * @author: Yijia Mu
 * @date: 10-17-10
 * @description: This is the restart class. There are two ways of restarting a game. 
 * The first is that the user wants to manually restart the game with a key. The second
 * is that  
 */

package vooga.engine.state.other;

import com.golden.gamedev.Game;

public class Restart {

	private Game currentGame;
	private int manualRestartKey; 
	
	public Restart(Game game, int restartKey) 
	{
		currentGame = game;
		manualRestartKey = restartKey;
	}
	
	public void update(long elapsedTime)
	{
		if(currentGame.keyDown(manualRestartKey) || 
				currentGame.keyPressed(manualRestartKey))
			currentGame.initResources();
		
		//I don't know how to implement the case when the game actually ends, or game over. 
		//Please chip in here.
	}
	
	
	

}
