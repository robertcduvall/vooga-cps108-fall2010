package vooga.games.towerdefense.actors.towers;

import java.awt.image.BufferedImage;

import vooga.engine.core.BetterSprite;

/**
 * Abstract tower class that requires you to change the update methods 
 * 
 * @author Derek Zhou, Daniel Koverman, Justin Goldsmith
 *
 */

public abstract class Tower extends BetterSprite{
	
	private static final long serialVersionUID = 1L;
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
