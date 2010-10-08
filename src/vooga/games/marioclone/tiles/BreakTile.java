package vooga.games.marioclone.tiles;

import java.awt.image.BufferedImage;

import vooga.engine.collision.Collidable;

@SuppressWarnings("serial")
public class BreakTile extends Tile {	
	
	public BreakTile(double x, double y, BufferedImage image) {
		super(image,x,y);		
	}
	
	@Override
	public void actOnCollision(Collidable object) {
		this.setState(State.removed);
	}


}
