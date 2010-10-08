package vooga.games.marioclone.tiles;

import java.awt.image.BufferedImage;

import vooga.engine.collision.Collidable;

@SuppressWarnings("serial")
public class IndestructibleTile extends Tile {

	public IndestructibleTile(double x, double y, BufferedImage image) {
		super(image,x,y);
		System.out.println(x +" "+y);
	}
	

	@Override
	public void actOnCollision(Collidable object) {
		// do nothing
	}


}
