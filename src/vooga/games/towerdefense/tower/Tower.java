package vooga.games.towerdefense.tower;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

import vooga.engine.event.EventManager;
import vooga.engine.resource.Resources;
import vooga.engine.resource.ResourcesBeta;
import vooga.games.towerdefense.Enemy;
import vooga.games.towerdefense.DropThis;
import vooga.games.towerdefense.TowerShot;

/**
 * Abstract tower class that requires you to change the update methods 
 * 
 * @author Derek Zhou, Daniel Koverman, Justin Goldsmith
 *
 */

public abstract class Tower extends Sprite{
	
	private BufferedImage previewImage;
	private int cost;
	
	public Tower(BufferedImage image, double x, double y, BufferedImage previewImage, int cost){
		super(image, x, y);
		this.previewImage = previewImage;
		this.cost = cost;
	}
	
	public BufferedImage getPreviewImage(){
		return previewImage;
	}

	public int getCost(){
		return cost;
	}
	
	
	public abstract void update(long elapsedTime);
	
	@Override
	public abstract Tower clone();
}
