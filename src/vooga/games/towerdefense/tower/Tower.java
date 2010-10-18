package vooga.games.towerdefense.tower;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

import vooga.engine.event.EventManager;
import vooga.engine.resource.Resources;
import vooga.engine.resource.ResourcesBeta;
import vooga.games.towerdefense.Enemy;
import vooga.games.towerdefense.TowerDefense;
import vooga.games.towerdefense.TowerShot;

public abstract class Tower extends Sprite{
	
	private BufferedImage previewImage;
	private int cost;
	private EventManager eventManager;
	
	public Tower(BufferedImage image, double x, double y, BufferedImage previewImage, int cost, EventManager eventManager){
		super(image, x, y);
		this.previewImage = previewImage;
		this.cost = cost;
		this.eventManager = eventManager;
	}
	
	public BufferedImage getPreviewImage(){
		return previewImage;
	}

	public int getCost(){
		return cost;
	}
	
	public EventManager getEventManager(){
		return eventManager;
	}

	
	public abstract void update(long elapsedTime);
}
