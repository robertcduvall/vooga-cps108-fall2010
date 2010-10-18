package vooga.games.towerdefense.tower;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

import vooga.engine.resource.Resources;
import vooga.engine.resource.ResourcesBeta;
import vooga.games.towerdefense.Enemy;
import vooga.games.towerdefense.TowerDefense;
import vooga.games.towerdefense.TowerShot;

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

	
	public abstract void update(long elapsedTime);
}
