package vooga.games.marioclone.src.map.tiles;

import java.awt.image.BufferedImage;

import vooga.engine.collision.Collidable;

public class IndestructibleTile extends Tile {
	private BufferedImage image;
	
	public IndestructibleTile(double x, double y, BufferedImage image) {
		super(x,y);
		this.image = image;
	}
	
	@Override
	public void actOnCollision(Collidable object) {
		// do nothing
	}

	@Override
	public BufferedImage getImage() {
		return image;
	}

}
