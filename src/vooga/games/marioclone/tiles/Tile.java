package vooga.games.marioclone.tiles;

import java.awt.image.BufferedImage;

import vooga.engine.collision.Collidable;

import com.golden.gamedev.object.Sprite;

@SuppressWarnings("serial")
public abstract class Tile extends Sprite implements Collidable {


	private State state;
	public enum State {active,inactive,removed};
	
	
	public Tile(BufferedImage image, double x, double y) {
		super(image,x,y);
	}

	
	@Override
	public abstract void actOnCollision(Collidable object);
	
	protected void setState(State state) {
		this.state = state;
	}

	public State getState() {
		return state;
	}


	// Unused velocity methods:

	@Override
	public void setVelocity(double vx, double vy) {
		// does not apply		
	}

	@Override
	public double getXVelocity() {
		return 0;
	}



	@Override
	public double getYVelocity() {
		return 0;
	}
	
}
