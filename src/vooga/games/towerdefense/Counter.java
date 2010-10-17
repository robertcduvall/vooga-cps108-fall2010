package vooga.games.towerdefense;

import java.awt.image.BufferedImage;

import vooga.engine.resource.Resources;
import vooga.engine.resource.ResourcesBeta;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.util.ImageUtil;

/**
 * Counter displays the images for a countdown to the start of the game
 * 
 * @author Derek Zhou, Daniel Koverman, Justin Goldsmith
 */

public class Counter extends Sprite{
	
	private Timer timer;
	private BufferedImage image;
	private int i;
	
	public Counter() {
		timer = new Timer(1000);
		image = ResourcesBeta.getImage("10");
		setImage(ImageUtil.resize(image, 150, (int)(((double)image.getHeight()/(double)image.getWidth())*150)));
		i = 9;
	}
	
	
	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		
		if(timer.action(elapsedTime) && i!=0){
			if(i!=-1){
				image = ResourcesBeta.getImage(String.valueOf(i));
				setImage(ImageUtil.resize(image, 150, (int)(((double)image.getHeight()/(double)image.getWidth())*150)));
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
			image = ResourcesBeta.getImage("go");
			setImage(ImageUtil.resize(image, 200, (int)(((double)image.getHeight()/(double)image.getWidth())*200)));
		}
		
		
	}

}
