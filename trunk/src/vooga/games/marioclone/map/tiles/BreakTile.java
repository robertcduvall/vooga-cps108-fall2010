package vooga.games.marioclone.map.tiles;

import java.awt.image.BufferedImage;

import vooga.engine.collision.Collidable;

public class BreakTile extends Tile {	
	private BufferedImage image;
	
	public BreakTile(double x, double y, BufferedImage image) {
		this.image = image;
	}
	
	@Override
	public void actOnCollision(Collidable object) {
		this.setState(State.removed);
	}

	@Override
	public BufferedImage getImage() {
		return image;
	}

}
