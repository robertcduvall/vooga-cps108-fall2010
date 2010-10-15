package vooga.games.marioclone.tiles;

import java.awt.image.BufferedImage;


import com.golden.gamedev.object.Sprite;

@SuppressWarnings("serial")
public abstract class Tile extends Sprite {


	private State state;
	public enum State {active,inactive,removed};
	
	
	public Tile(BufferedImage image, double x, double y) {
		super(image,x,y);
	}

	
	public abstract void actOnCollision(Sprite sprite);
	
	protected void setState(State state) {
		this.state = state;
	}

	public State getState() {
		return state;
	}


	// Unused velocity methods:

//	@Override
//	public void setVelocity(double vx, double vy) {
//		// does not apply		
//	}
//
//	@Override
//	public double getXVelocity() {
//		return 0;
//	}
//
//
//
//	@Override
//	public double getYVelocity() {
//		return 0;
//	}
	
}
