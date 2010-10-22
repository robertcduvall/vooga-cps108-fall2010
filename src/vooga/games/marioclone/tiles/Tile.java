package vooga.games.marioclone.tiles;

import java.awt.image.BufferedImage;

import vooga.engine.player.control.ItemSprite;

import com.golden.gamedev.object.Sprite;

@SuppressWarnings("serial")
public abstract class Tile extends Sprite {

	private State state;

	public enum State {
		active, inactive, removed
	};

	public Tile(BufferedImage image, double x, double y) {
		super(image, x, y);
	}

	public abstract void actOnCollision(Sprite sprite);

	protected void setState(State state) {
		this.state = state;
	}

	public State getState() {
		return state;
	}

	// override to spawn items
	public ItemSprite checkItem() {
		return null;
	}

}
