package vooga.games.marioclone.src.map.tiles;

import java.awt.Image;

import vooga.engine.collision.Collidable;

public abstract class Tile implements Collidable {
	private double x;
	private double y;
	private State state;
	public enum State {active,inactive,removed};
	
	public Tile() {}
	
	public Tile(double x, double y) {
		this(x,y,State.active);
	}
	
	public Tile(double x, double y, State state) {
		this.x = x;
		this.y = y;
		this.state = state;
	}
	
	@Override
	public double getX() {
		return x;
	}
	
	@Override
	public double getY() {
		return y;
	}
	
	public abstract Image getImage();

	public abstract void actOnCollision(Collidable object);
	
	protected void setState(State state) {
		this.state = state;
	}

	public State getState() {
		return state;
	}


	// Unused velocity methods:

	@Override
	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}

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
