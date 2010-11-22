package vooga.games.jumper.events;

import java.awt.Point;
import java.util.Random;

import vooga.engine.event.IEventHandler;
import vooga.engine.resource.Resources;
import vooga.games.jumper.sprites.NormalBlock;
import vooga.games.jumper.states.PlayGameState;

/**
 * Create random blocks
 * 
 * @author Cody
 */
public class InfiniteBlocksEvent implements IEventHandler {
	
	/*
	 bad design... must be refactored
	 */
	private static int blockCounter = 0;
	/*
	 * 
	 */
	
	Random random = new Random();
	int xMax = Resources.getInt("gameWidth");
	int xMin = 0;
	int yMax = Resources.getInt("gameHeight");
	int yMin = 0;
	double xVelocity = 0;
	double yVelocity = -0.2;

	private PlayGameState playState;
	

	public InfiniteBlocksEvent(PlayGameState gamestate) {
		playState = gamestate;
	}

	@Override
	public boolean isTriggered() {
		//poor design...needs to be refactored 	//
		blockCounter++;                        	//
		if(blockCounter==15) {              	//
			blockCounter=0; 					//
			return true;						
		} else {								//
			return false;						//
		}										//
	}

	@Override
	public void actionPerformed() {
		int randomX = random.nextInt(xMax - xMin) + xMin;
		int randomY = random.nextInt(yMax - yMin) + yMax;
		Point initPoint = new Point(randomX, randomY);
		NormalBlock addBlock = new NormalBlock(Resources.getImage("platformWhite"), initPoint, xVelocity, yVelocity);
		playState.getGroup("normalBlocks").add(addBlock);
	}
}
