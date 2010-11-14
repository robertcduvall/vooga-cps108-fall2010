package vooga.widget;

import java.awt.image.BufferedImage;

import vooga.engine.resource.Resources;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.util.ImageUtil;

/**
 * Counter displays the images for a countdown to the start of the game
 * 
 * @author Justin Goldsmith
 */

public class Counter extends Sprite{
	
	private Timer myTimer;
	private Timer myEndTimer;
	private BufferedImage myImage;
	private int myEndTime;
	private int i;
	private GameState mySwitch;
	private GameStateManager myStateManager;
	private boolean go;
	
	
	/**
	 * Creats a counter to countdown to something then switch states.
	 * All images must be initialized in resources before this is called.
	 * 
	 * @param startPoint - the number to start at.
	 * @param timePerImage - time to spend on each image of the counter
	 * @param timeAtEnd - time to spend on the last image of the counter
	 * @param width - width of the images, scales the height to this width
	 * @param stateManager - the GameStateManger of the game
	 * @param switchTo - the GameState to switch to when the counter is done.
	 */
	public Counter(int startPoint, int timePerImage, int timeAtEnd, int width, GameStateManager stateManager, GameState switchTo) {
		myTimer = new Timer(timePerImage);
		myImage = Resources.getImage(String.valueOf(startPoint));
		setImage(ImageUtil.resize(myImage, width, (int)(((double)myImage.getHeight()/(double)myImage.getWidth())*width)));
		i = startPoint;
		myEndTime = timeAtEnd;
		i--;
		myStateManager = stateManager;
		mySwitch = switchTo;
		go = true;
	}
	
	
	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		
		if(myTimer.action(elapsedTime) && i!=0 && go){
			if(i!=-1){
				myImage = Resources.getImage(String.valueOf(i));
				setImage(ImageUtil.resize(myImage, 150, (int)(((double)myImage.getHeight()/(double)myImage.getWidth())*150)));
				if(i==1){
					i = -1;
				}
				else{
					i--;
				}
			}else{
				i = 0;
			}
		}else if(i == 0){
			if(go){
				myImage = Resources.getImage("go");
				setImage(ImageUtil.resize(myImage, 200, (int)(((double)myImage.getHeight()/(double)myImage.getWidth())*200)));
				go = false;
				myEndTimer = new Timer(myEndTime);
			}else if(myEndTimer.action(elapsedTime)){
				myStateManager.toggle(mySwitch);
			}
		}
		
		
	}

}
