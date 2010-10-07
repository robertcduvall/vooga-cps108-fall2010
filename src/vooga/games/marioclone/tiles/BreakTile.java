package vooga.games.marioclone.tiles;

import java.awt.image.BufferedImage;

import vooga.engine.collision.Collidable;

public class BreakTile extends Tile {	

	private static final long serialVersionUID = -6084850476897563619L;
	private BufferedImage image;
	
	public BreakTile(double x, double y, BufferedImage image) {
		super(x,y);
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
