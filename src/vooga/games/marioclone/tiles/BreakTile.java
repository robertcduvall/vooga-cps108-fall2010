package vooga.games.marioclone.tiles;

import java.awt.Image;

import vooga.engine.collision.Collidable;

public class BreakTile extends Tile {	
	private Image image;
	
	public BreakTile(double x, double y, Image image) {
		super(x,y);
		this.image = image;
	}
	
	@Override
	public void actOnCollision(Collidable object) {
		this.setState(State.removed);
	}

	@Override
	public Image getImage() {
		return image;
	}

}
