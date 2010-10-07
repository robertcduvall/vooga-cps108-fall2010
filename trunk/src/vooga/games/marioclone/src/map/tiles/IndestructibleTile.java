package vooga.games.marioclone.src.map.tiles;

import java.awt.Image;

import vooga.engine.collision.Collidable;

public class IndestructibleTile extends Tile {
	private Image image;
	
	public IndestructibleTile(double x, double y, Image image) {
		super(x,y);
		this.image = image;
	}
	

	@Override
	public void actOnCollision(Collidable object) {
		// do nothing
	}

	@Override
	public Image getImage() {
		return image;
	}

}
