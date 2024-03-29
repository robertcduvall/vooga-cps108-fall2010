package vooga.engine.factory;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

@SuppressWarnings("serial")
public abstract class MapTile extends Sprite {

	private State state;

	public enum State {
		active, inactive, removed
	};

	public MapTile(double x, double y, BufferedImage... images) {
		super(images[0], x, y);
	}

	public abstract void actOnCollision(Sprite sprite);

	protected void setState(State state) {
		this.state = state;
	}

	public State getState() {
		return state;
	}

	// override to spawn items
	public Sprite checkItem() {
		return null;
	}

}
